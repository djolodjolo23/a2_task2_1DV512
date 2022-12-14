import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class WorkerA implements Runnable {

  final Semaphore semaphore;

  ArrayList<Integer> allowedNumbers;


  public WorkerA(Semaphore semaphore) {
    allowedNumbers = new ArrayList<>();
    this.semaphore = semaphore;
    addToAllowedNumbers();
  }

  @Override
  public void run() {

    synchronized (semaphore) {
      semaphore.release();
      for (int i = 0; i < 10; i++) {
        try {
          semaphore.acquire();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        AppendedString.addToString("A");
        Counter.aCounter++;
        semaphore.release();
        semaphore.notify();
      }
    }
  }

  public void addToAllowedNumbers() {
    for (int i = 0; i < 100; i++) {
      allowedNumbers.add(i);
      if (allowedNumbers.size() % 3 == 0) {
        i = i + 3;
      }
    }
  }

  public ArrayList<Integer> getAllowedNumbers() {
    return allowedNumbers;
  }
}
