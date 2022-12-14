
public class AppendedString {

  public String finalString = "";

  public void addToString(String letter) {
    finalString =  finalString + letter;
  }

  public String getFinalString() {
    return finalString;
  }

  public int getCurrentStringSize() {
    return finalString.length();
  }

}