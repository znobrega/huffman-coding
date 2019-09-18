
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

public void printByteInfo() {
    
    for (int i = 0; i < this.byteInfos.length; i++) {
        if (this.byteInfos[i] != null) {
            System.out.println(this.byteInfos[i].getHuffmanCode());
        }
    }
    System.out.println(this.byteInfos.length - 1);

}

public void resizeByte() {
  for (int i = 0; i < this.byteInfos.length; i++) {
      if (this.byteInfos[i] != null 
          && this.byteInfos[i].getHuffmanCode().length() % 8 != 0) {
          
          while(this.byteInfos[i].getHuffmanCode().length() % 8 != 0) {
              this.byteInfos[i].addZeroHuffmanCode();
          }
          System.out.println(this.byteInfos[i].getHuffmanCode().substring(0, 8));
      }
  }
}


public byte StringToByte(String code) {
  int intValue = 0;
  
  for (int i = code.length() -1, j = 0; i >= 0; i--, j++) {
      int individual = Integer.parseInt(code.split("")[i]);
      intValue += individual << j;
  }
  
  byte b = (byte) intValue;
  //int byteValue = b & 0xFF;
  
  return b;
}

public void createCodeFile() {
  this.resizeByte();
  int num;
  String fileName = "C:\\Users\\carl\\Desktop\\Huffman-algorithm-master\\Huffman-algorithm-master\\huffman_algorithm\\generated.fib25";
    System.out.println("ue");
  try {
    file = new FileInputStream(fileName);
    FileOutputStream outputStream = new FileOutputStream("C:\\Users\\carl\\Desktop\\Huffman-algorithm-master\\Huffman-algorithm-master\\huffman_algorithm\\codify.out");

    while((num = file.read()) != -1) {
      String code = this.byteInfos[num].getHuffmanCode();
      
      for (int i = 0; i < code.length() / 8; i++ ) {
        byte byteToWrite = StringToByte(code.substring(i*8 , 8*(i+1)));
        outputStream.write(byteToWrite);
      }
    }
      outputStream.close();
  } catch(Exception err) {

  }
}

public void decompression() {
    String fileName = "C:\\Users\\carl\\Desktop\\Huffman-algorithm-master\\Huffman-algorithm-master\\huffman_algorithm\\codify.out";
    
}