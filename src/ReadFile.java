import java.io.*;

public class ReadFile {
  private String fileName;
  private int[] freqs;
  private InputStream file;
  
  public ReadFile(String fileName) {
    this.fileName = fileName;
    this.freqs = new int[256];
    this.file = null;
    initArray();
    countBytes();
    //testWithString();
  }

  public void initArray() {
    for(int i = 0; i < freqs.length; i++) {
      freqs[i] = 0;
    }
  }

  public void testWithString() {
    String test = "abcdefghijklmnopqrsuvwxyz";

    for (int i = 0; i < test.length(); i++) {
      int unsignedByte = test.charAt(i) & 0xFF;
      freqs[unsignedByte]++;
    }
  }

  public void countBytes() {
    int num;
    try {
      file = new FileInputStream(this.fileName);
      while((num = file.read()) != -1 ) {
        freqs[num]++;
      }
    } catch(Exception err) {

    } 
  }

  public int[] getFreqs() {
    return this.freqs;
  }
}
