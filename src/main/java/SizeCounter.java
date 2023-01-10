import java.util.concurrent.atomic.AtomicInteger;

public class SizeCounter {


  public AtomicInteger size = new AtomicInteger(0);


  public void increaseSize() {
    size.incrementAndGet();
  }


  public int getSize() {
    return size.get();
  }
}
