public class ByteInfo {
  private int initCode;
  private char initChar;
  private String huffmanCode;
  private int huffmanCodeInt;


  public ByteInfo(int initCode, char initChar, String huffmanCode){
    this.initCode = initCode;
    this.initChar = initChar;
    this.huffmanCode = huffmanCode;
    this.huffmanCodeInt = castCodeToInt(huffmanCode);
  }

  public ByteInfo(int initCode, char initChar) {
    this.initCode = initCode;
    this.initChar = initChar;
    this.huffmanCode = "";
  }

  public int getInitCode() {
    return this.initCode;
  }

  public String getHuffmanCode() {
    return this.huffmanCode;
  }
  
  public char getInitChar() {
    return this.initChar;
  }

  public void setInitCode(int initCode) {
    this.initCode = initCode;
  }

  public void setInitChar(char initChar) {
    this.initChar = initChar;
  }

  public void setHuffmanCode(String huffmanCode) {
    this.huffmanCode = huffmanCode;
  }  
  
  public void addZeroHuffmanCode() {
      this.huffmanCode = "0" + this.huffmanCode;
  }

  public int castCodeToInt(String code) {
    int intValue = 0;
    
    for (int i = code.length() -1, j = 0; i >= 0; i--, j++) {
      int individual = Integer.parseInt(code.split("")[i]);
      intValue += individual << j;
    }

    return intValue;
  }
}