class byteInfo {
  private int initCode;
  private char initChar
  private int huffmanCode;


  public ByteInfo(int initCode, char initChar, int huffmanCode) {
    this.initCode = initCode;
    this.initChar = initChar;
    this.huffmanCode = huffmanCode;
  }

  public ByteInfo(int initCode, char initChar) {
    this.initCode = initCode;
    this.initChar = initChar;
    this.huffmanCode = -1;
  }

  public int getInitCode() {
    return this.initCode;
  }

  public int huffmanCode() {
    return this.huffmanCode;
  }
  
  public char getInitChar() {
    return this.initChar;
  }

  public void setInitCode(int initCode) {
    this.initCode = initCode;
  }

  public void setInitChar(int initChar) {
    this.initChar = initChar;
  }

  public void setHuffmanCode(int huffmanCode) {
    this.huffmanCode = huffmanCode;
  }  
}