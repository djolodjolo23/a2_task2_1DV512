import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class WorkerA extends SuperWorker implements Runnable {

  final Semaphore semaphore;

  ArrayList<Integer> allowedNumbers;

  private SizeCounter sizeCounter;


  public WorkerA(Semaphore semaphore, SizeCounter sizeCounter) {
    this.sizeCounter = sizeCounter;
    allowedNumbers = new ArrayList<>();
    this.semaphore = semaphore;
    addToAllowedNumbers();
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      semaphore.release();
      while (true){
        String workerType = "A";
        super.threadWork(allowedNumbers, sizeCounter, semaphore, workerType);
        if (sizeCounter.getSize() == 27) {
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
    for (int i = 0; i < 100; i++) {
      allowedNumbers.add(i);
      if (allowedNumbers.size() % 3 == 0) {
        i = i + 3;
      }
    }
  }

}
