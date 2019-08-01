import java.io.FileInputStream;
import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    Heapmin Queue = new Heapmin();
    ReadFile file = new ReadFile("./files/generated.equal");
    int[] freqs = file.getFreqs();

    for(int i =0; i< freqs.length; i++) {
      if(freqs[i] > 0) {
        char ch = (char) i;
        Queue.add(new Node(freqs[i], ch, i));
      }
    }
    Huffman huffman = new Huffman(Queue);
    huffman.buildTree();
    huffman.printPath(huffman.getRoot(), "");

    //Queue.showElements();
  }
}