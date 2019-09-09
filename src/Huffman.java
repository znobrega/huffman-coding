
import java.io.FileInputStream;
import java.io.*;
import java.util.*;

public class Huffman {
  private Heapmin Queue;
  private Node root;
  private ByteInfo[] byteInfos;
  private InputStream file;


  public Huffman(Heapmin Queue) {
    this.Queue = Queue;
    this.root = null;
    this.byteInfos = new ByteInfo[256];
    this.file = null;
  }

  public Node getRoot() {
    return this.root;
  }

  public void buildTree() {
    int counter = 256;
    while(this.Queue.getSize() > 1) {
      Node n1 = this.Queue.extractMin();
      // System.out.println("Extracaão primeiro:");
      // Queue.showElements();
      // System.out.println("Extracaão segundo:");
      // Queue.showElements();

      Node n2 = this.Queue.extractMin();

      Node newNode = new Node(n1.getWeight() + n2.getWeight());
      
 
      
      newNode.setLeft(n1);
      n1.setRightOrLeft("left");

      newNode.setRight(n2);
      n2.setRightOrLeft("right");

      this.Queue.add(newNode);
    }
    // Até aqui não tem erro

    Node root = this.Queue.extractMin();
    System.out.println("Tamanh:" + root.getWeight());
    this.root = root;
  }

  public void printPath(Node node, String huffmanCode) {
        if(node == null){
            return;
        }  


      if(node.hasLeft()) {
        String nextHuffmanCode = huffmanCode + "0";
        printPath(node.getLeft(), nextHuffmanCode);
      }
      
      if(node.hasRight()) {
        String nextHuffmanCode = huffmanCode + "1";
        printPath(node.getRight(), nextHuffmanCode);
      }

      if(node.isLeaf()) {
        System.out.println(huffmanCode + " " + huffmanCode.length());
        // byteInfos[node.getInitCode()] = new ByteInfo(node.getInitCode(), node.getChar(), huffmanCode);
      }

  }

  public void createCodeFile() {
    int num;
    String fileName = "test";
    try {
      file = new FileInputStream(fileName);
      FileOutputStream outputStream = new FileOutputStream("equalHuffman.equal");
      
      // Ler o arquivo novamente
      // Para cada byte, escreve um novo byte em outro arquivo
      // 
      while((num = file.read()) != -1) {
       // outputStream.write(ByteInfo[num].getHuffmanCode());
      }
        outputStream.close();
    } catch(Exception err) {

    }
  }
}