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
      while (appendedString.getSize() < 30) {
        if (appendedString.getSize() == 26) {
          break;
        }
        while (!isItMyTurn()) {
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
        appendedString.addToString("B");
        semaphore.release();
        semaphore.notifyAll();
        if (appendedString.getSize() == 26) {
          break;
        }
        try {
          semaphore.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        if (appendedString.getSize() == 26) {
          break;
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

  public ArrayList<Integer> getAllowedNumbers() {
    return allowedNumbers;
  }

  private void isItMyTurnTest(Semaphore semaphore) throws InterruptedException {
    if (!allowedNumbers.contains(appendedString.getCurrentStringSize())) {
      semaphore.wait();
      //semaphore.notifyAll();
      isItMyTurnTest(semaphore);
    }
  }

  private boolean isItMyTurn() {
    return allowedNumbers.contains(appendedString.getCurrentStringSize());
  }
}
