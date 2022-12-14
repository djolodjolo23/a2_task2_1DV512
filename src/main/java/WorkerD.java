import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class WorkerD implements Runnable{

  final Semaphore semaphore;

  ArrayList<Integer> allowedNumbers;

  private AppendedString appendedString;

  public WorkerD(Semaphore semaphore, AppendedString appendedString) {
    this.appendedString = appendedString;
    allowedNumbers = new ArrayList<>();
    this.semaphore = semaphore;
    addToAllowedNumbers();
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      while (Counter.dCounter < 5) {
        try {
          isItMyTurnTest(semaphore);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        try {
          semaphore.acquire();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        appendedString.addToString("D");
        Counter.dCounter++;
        semaphore.release();
        semaphore.notifyAll();
        if (Counter.dCounter == 5) {
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
      if (allowedNumbers.size() % 2 == 0) {
        i = i + 5;
      }
    }
  }

  public ArrayList<Integer> getAllowedNumbers() {
    return allowedNumbers;
  }

  private void isItMyTurnTest(Semaphore semaphore) throws InterruptedException {
    if (!allowedNumbers.contains(appendedString.getCurrentStringSize())) {
      semaphore.wait();
      //.notifyAll();
      isItMyTurnTest(semaphore);
    }
  }

  private boolean isItMyTurn() {
    return allowedNumbers.contains(appendedString.getCurrentStringSize());
  }
}
