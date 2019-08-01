public class Huffman {
  private Heapmin Queue;
  private Node root;
  private ByteInfo[] byteInfos;


  public Huffman(Heapmin Queue) {
    this.Queue = Queue;
    this.root = null;
    this.byteInfos = new ByteInfo[256];
  }

  public Node getRoot() {
    return this.root;
  }

  public void buildTree() {
    int counter = 256;
    while(this.Queue.getSize() > 1) {
      Node n1 = this.Queue.extractMin();
      Node n2 = this.Queue.extractMin();

      Node newNode = new Node(n1.getWeight() + n2.getWeight());
      newNode.setLeft(n1);
      n1.setRightOrLeft("left");
      newNode.setRight(n2);
      n2.setRightOrLeft("right");

      this.Queue.add(newNode);
    }

    Node root = this.Queue.extractMin();
    this.root = root;
  }

  public void printPath(Node node, String huffmanCode) {
    if(node.hasLeft()) {
      huffmanCode+="0";
      printPath(node.getLeft(), huffmanCode);
    }
    
    if(node.hasRight()) {
      huffmanCode+="1";
      printPath(node.getRight(), huffmanCode);
    }

    if(node.isLeaf()) {
      System.out.println("Caracter: " + node.getChar() +" " + "Code: " +huffmanCode);
      byteInfos[node.getInitCode()] = new ByteInfo(node.getInitCode(), node.getChar(), huffmanCode);
    }
  }
}