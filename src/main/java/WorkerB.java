import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class WorkerB extends SuperClass implements Runnable{

  final Semaphore semaphore;

  ArrayList<Integer> allowedNumbers;
  private AppendedString appendedString;

  public WorkerB(Semaphore semaphore, AppendedString appendedString) {
    this.appendedString = appendedString;
    allowedNumbers = new ArrayList<>();
    this.semaphore = semaphore;
    addToAllowedNumbers();
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      while (true) {
        String workerType = "B";
        super.threadWork(allowedNumbers, appendedString, semaphore, workerType);
        if (appendedString.getSize() == 26) {
          break;
        }
        try {
          semaphore.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }

  }

  public void addToAllowedNumbers() {
    for (int i = 1; i < 100; i++) {
      allowedNumbers.add(i);
      i += 5;
    }
  }



}
