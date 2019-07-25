import java.io.FileInputStream;
import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    Heapmin Queue = new Heapmin();
    ReadFile file = new ReadFile("./files/generated.fib25");
    int[] freqs = file.getFreqs();
    
    // int j = 0;
    // while(j < freqs.length) {
    //   System.out.print(freqs[j] + ", ");
    //   j++;
    // }

    for(int i =0; i< freqs.length; i++) {
      if(freqs[i] > 0) {
        Queue.add(new Node(freqs[i]));
      }
    }
    Huffman huffman = new Huffman(Queue);
    Queue.showElements();
  }
}