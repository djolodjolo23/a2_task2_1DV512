import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class WorkerA extends SuperClass implements Runnable {

  final Semaphore semaphore;

  ArrayList<Integer> allowedNumbers;

  private AppendedString appendedString;


  public WorkerA(Semaphore semaphore, AppendedString appendedString) {
    this.appendedString = appendedString;
    allowedNumbers = new ArrayList<>();
    this.semaphore = semaphore;
    addToAllowedNumbers();
  }

  @Override
  public void run() {
    synchronized (semaphore) {
      semaphore.release();
      while (appendedString.getSize() < 30){
        if (appendedString.getSize() == 27) {
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
        appendedString.addToString("A");
        semaphore.release();
        semaphore.notifyAll();
        if (appendedString.getSize() == 27) {
          break;
        }
        try {
          semaphore.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        if (appendedString.getSize() == 27) {
          break;
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
    if (allowedNumbers.contains(appendedString.getSize())) {
      return true;
    }
    return false;
  }
}
