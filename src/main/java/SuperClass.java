import java.util.List;
import java.util.concurrent.Semaphore;

public class SuperClass {

  public boolean isItMyTurn(List<Integer> nums, AppendedString appendedString) {
    return !nums.contains(appendedString.getSize());
  }

  public void threadWork(List<Integer> nums, AppendedString appendedString, Semaphore semaphore, String worker) {
    while (isItMyTurn(nums, appendedString)) {
      try {
        semaphore.wait();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    try {
      semaphore.acquire();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    switch (worker) {
      case "A" -> appendedString.addToString("A");
      case "B" -> appendedString.addToString("B");
      case "C" -> appendedString.addToString("C");
      case "D" -> appendedString.addToString("D");
    }
    semaphore.release();
    semaphore.notifyAll();
  }
}
