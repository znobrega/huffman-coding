import java.io.FileInputStream;
import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) {
    
    Heapmin Queue = new Heapmin();
    ReadFile file = new ReadFile("C:\\Users\\carl\\Desktop\\Huffman-algorithm-master\\Huffman-algorithm-master\\huffman_algorithm\\generated.fib25");
    int[] freqs = file.getFreqs();

    for(int i = 0; i< freqs.length; i++) {
      if(freqs[i] > 0) {
        char ch = (char) i;
        Queue.add(new Node(freqs[i], ch, i));
      }
    }

    Queue.showElements();
    System.out.println(" ");     
     Huffman huffman = new Huffman(Queue, freqs);
     huffman.buildTree();
     huffman.createCodeFile();
     //huffman.printPath(huffman.getRoot(), "");
  }
}