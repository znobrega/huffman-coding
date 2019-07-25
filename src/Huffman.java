public class Huffman {
  Heapmin Queue;

  public Huffman(Heapmin Queue) {
    this.Queue = Queue;
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

    Node lastNode = this.Queue.extractMin();
    System.out.println(lastNode.getWeight());
  }
}