import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class WorkerC extends SuperWorker implements Runnable{

  final Semaphore semaphore;

  ArrayList<Integer> allowedNumbers;

  private SizeCounter sizeCounter;


  public WorkerC(Semaphore semaphore, SizeCounter sizeCounter) {
    this.sizeCounter = sizeCounter;
    allowedNumbers = new ArrayList<>();
    this.semaphore = semaphore;
    addToAllowedNumbers();
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      while (true) {
        String workerType = "C";
        super.threadWork(allowedNumbers, sizeCounter, semaphore, workerType);
        if (sizeCounter.getSize() == 30) {
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
    for (int i = 3; i < 100; i++) {
      allowedNumbers.add(i);
      if (allowedNumbers.size() % 3 == 0) {
        i = i + 3;
      }
    }
  }


}
