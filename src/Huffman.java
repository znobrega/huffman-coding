import java.io.FileInputStream;
import java.io.*;

public class Huffman {
  private Heapmin Queue;
  private int[] frequencies; 
  private Node root;
  private ByteInfo[] byteInfos;
  private InputStream file;


  public Huffman(Heapmin Queue, int[] frequencies) {
    this.Queue = Queue;
    this.frequencies = frequencies;
    this.root = null;
    this.byteInfos = new ByteInfo[256];
    this.file = null;
  }

  public Node getRoot() {
    return this.root;
  }
  
  public void createQueue() {
      Heapmin Queue = new Heapmin();
      
     for(int i = 0; i< this.frequencies.length; i++) {
      if(this.frequencies[i] > 0) {
        char ch = (char) i;
        Queue.add(new Node(this.frequencies[i], ch, i));
      }
    }
     this.Queue = Queue;
  }

  public void buildTree() {
    while(this.Queue.getSize() > 1) {
      Node n1 = this.Queue.extractMin();
      Node n2 = this.Queue.extractMin();

      Node newNode = new Node(n1.getWeight() + n2.getWeight());
      
      newNode.setLeft(n1);
      n1.setRightOrLeft(false);

      newNode.setRight(n2);
      n2.setRightOrLeft(true);

      this.Queue.add(newNode);
    }

    Node root = this.Queue.extractMin();
    this.root = root;

    printPath(this.root, "");
  }

  public void printPath(Node node, String huffmanCode) {

    if (node.isLeaf(node)) { 
        //System.out.println("Final:  " + huffmanCode); 
        byteInfos[node.getInitCode()] = new ByteInfo(node.getInitCode(), node.getChar(), huffmanCode);
        return; 
    } 
    printPath(node.getLeft(), huffmanCode + "0"); 
    printPath(node.getRight(), huffmanCode + "1"); 

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
    
    return b;
  }
  
  public String ByteToCode(int code) {
      String huffmanCode = "";
      
      for (int i = 7; i >= 0; i--){
          if (Math.pow(2, i) <= code) {
              huffmanCode = "1" + huffmanCode;
          } else {
              huffmanCode = "0" + huffmanCode;
          }
      }
      
      return huffmanCode;
  }
  
  public int verifyTree(String huffmanCode, Node node, int init) {
      for (int i = 0; i< huffmanCode.length(); i++) {
        int bit = Integer.parseInt(huffmanCode.split("")[i]);
        
        if (node.isLeaf(node)) {
            int code = node.getInitCode();
            return code;
        }
        
        if (bit == 1) {
           node = node.getRight();
        } else {
           node = node.getLeft();
        }
      }
      
      return 8;
  }

  public void createCodeFile() {
    this.resizeByte();
    int num;
    String fileName = "C:\\Users\\carl\\Desktop\\Huffman-algorithm-master\\Huffman-algorithm-master\\huffman_algorithm\\generated.fib25";
      System.out.println("ue");
    try {
      file = new FileInputStream(fileName);
      FileOutputStream outputStream = new FileOutputStream("C:\\Users\\carl\\Desktop\\Huffman-algorithm-master\\Huffman-algorithm-master\\huffman_algorithm\\codify.out");
      for (int i = 0; i < this.frequencies.length; i++) {
          outputStream.write(this.frequencies[i]);
      }
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
    try {
    file = new FileInputStream(fileName);
        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\carl\\Desktop\\Huffman-algorithm-master\\Huffman-algorithm-master\\huffman_algorithm\\decompressed.out")) {
            createQueue();
            buildTree();
            
            int num, counter = 0;
            while((num = file.read()) != -1) {
                if (counter > 256) {
                    int i = 0;
                    String huffmanCode = ByteToCode(num);
                    int code = verifyTree(huffmanCode, this.root, 0);
                    while (huffmanCode.length() == 8 && code == 8) {
                       code = verifyTree(huffmanCode, this.root, 8*(i+1));
                       i++;
                    }
                    outputStream.write((byte) code);
                }
                counter++;
            }   
        }
        } catch(Exception err) {

        }
    
    }
}
