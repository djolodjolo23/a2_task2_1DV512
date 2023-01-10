import java.util.List;
import java.util.concurrent.Semaphore;

public class SuperWorker {

  public boolean isItMyTurn(List<Integer> nums, SizeCounter sizeCounter) {
    return !nums.contains(sizeCounter.getSize());
  }

  public void threadWork(List<Integer> nums, SizeCounter sizeCounter, Semaphore semaphore, String worker) {
    while (isItMyTurn(nums, sizeCounter)) {
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
    sizeCounter.increaseSize();
    switch (worker) {
      case "A" -> System.out.print("A");
      case "B" -> System.out.print("B");
      case "C" -> System.out.print("C");
      case "D" -> System.out.print("D");
    }
    semaphore.release();
    semaphore.notifyAll();
  }
}
