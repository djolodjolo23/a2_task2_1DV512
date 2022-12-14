
public class AppendedString {

  static String finalString = "";

  static void addToString(String letter) {
    finalString =  finalString + letter;
  }

  static String getFinalString() {
    return finalString;
  }

  static int getCurrentStringSize() {
    return finalString.length();
  }

}