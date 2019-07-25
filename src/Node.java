class Node {
  int weight;
  Node parent;
  Node left;
  Node right;

  // True = Right
  // False = Left
  boolean isRightOrLeft;


  public Node(int weight) {
    this.weight = weight;
    this.parent = null; 
    this.left = null;
    this.right = null;
    this.isRightOrLeft = true;
  }

  public int getWeight() {
    return this.weight;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public void setRight(Node node) {
    this.right = node;
    node.setParent(this);
  } 

  public void setLeft(Node node) {
    this.left = node;
    node.setParent(this);
  }

  public void setRightOrLeft(String value) {
    if(value.trim().contains("right")) {
      this.isRightOrLeft = true;
    } else {
      this.isRightOrLeft = false;
    }
  }
}