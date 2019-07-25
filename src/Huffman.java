public class Huffman {
  private Heapmin Queue;
  private Node root;

  public Huffman(Heapmin Queue) {
    this.Queue = Queue;
    this.root = null;
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

  public void printPath(Node node, String code) {
    if(node.hasLeft()) {
      code+="0";
      printPath(node.getLeft(), code);
    }
    
    if(node.hasRight()) {
      code+="1";
      printPath(node.getRight(), code);
    }

    if(node.isLeaf()) {
      System.out.println("Caracter: " + node.getChar() +" " + "Code: " +code);
    }
  }

  public Node getRoot() {
    return this.root;
  }
}