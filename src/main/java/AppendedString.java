
public class AppendedString {

  public volatile String finalString = "";

  public volatile int size = 0;

  public void addToString(String letter) {
    finalString += letter;
    size++;
  }

  public String getFinalString() {
    return finalString;
  }

  public int getSize() {
    return size;
  }


  public int getCurrentStringSize() {
    return finalString.length();
  }

}