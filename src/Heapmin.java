import java.util.*;  

class Heapmin {
  private ArrayList<Node> allNodes;
  private int size;

  public Heapmin() {
    this.allNodes = new ArrayList<Node>();
    this.size = 0;
  }

  public int getParent(int i) {
    int index = (int) Math.floor((i-1) / 2);
    return i != 0 ? index : 0;

  }

  public int getLeftChild(int i) {
    return i == 0 ? 1 : 2*i+1; 
  }

  public int getRightChild(int i) {
    return i == 0 ? 2 : 2*i+2; 
  }

  public void minHeapify(int i) {
    int left = getLeftChild(i);
    int right = getRightChild(i);
    int smallest = i;

    if (this.size > left && this.allNodes.get(left).getWeight() < this.allNodes.get(i).getWeight()) {
      smallest = left;
    } else {
      smallest = i;
    }

    if(this.size > right && this.allNodes.get(right).getWeight() < this.allNodes.get(smallest).getWeight()) {
      smallest = right;
    }

    if(smallest != i) {
      Collections.swap(allNodes, i, smallest);
      minHeapify(smallest);
    }
  }

  public Node extractMin() {
    if (this.size < 1) {
      throw new IllegalArgumentException("Heap underflow");
    }
    Node min = this.allNodes.get(0);
    Collections.swap(this.allNodes, 0, this.size-1);
    this.allNodes.remove(this.size -1);
    this.size--;
    this.minHeapify(0);

    return min;
  }

  public void add(Node element) {
    this.size++;
    this.allNodes.add(0, element);
    this.minHeapify(0);
  }

  public void showElements() {
    for(int i =0; i<this.size; i++) {
      System.out.print(this.allNodes.get(i).getWeight() + ", ");
    }
  }

  public int getSize() {
    return this.size;
  }
}