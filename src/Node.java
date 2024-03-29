class Node {
  private int weight;
  private Node parent;
  private Node left;
  private Node right;
  private char character;
  private int initCode;

  // True = Right
  // False = Left
  private boolean isRightOrLeft;


  public Node(int weight) {
    this.weight = weight;
    this.parent = null; 
    this.left = null;
    this.right = null;
    this.isRightOrLeft = true;
  }

  public Node(int weight, char character, int initCode) {
    this.weight = weight;
    this.parent = null; 
    this.left = null;
    this.right = null;
    this.isRightOrLeft = true;
    this.character = character;
    this.initCode = initCode;
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

  public void setRightOrLeft(boolean value) {
      this.isRightOrLeft = value;
  }

  public boolean hasLeft() {
    return this.left != null;
  }

  public boolean hasRight() {
    return this.right != null;
  }

  public Node getLeft() {
    return this.left;
  }

  public Node getRight() {
    return this.right;
  }

  public boolean isLeaf(Node node) {
    if (this.right == null && this.left == null) {
      return true;
    }

    return false;
  }

  public char getChar() {
    return this.character;
  }

  public int getInitCode() {
    return this.initCode;
  }
}