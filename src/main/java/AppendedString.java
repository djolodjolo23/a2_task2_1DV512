import java.util.concurrent.atomic.AtomicInteger;

public class AppendedString {

  public String finalString = "";

  public AtomicInteger size = new AtomicInteger(0);


  public void addToString(String letter) {
    finalString += letter;
    size.incrementAndGet();
  }

  public String getFinalString() {
    return finalString;
  }

  public int getSize() {
    return size.get();
  }
}
