import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class WorkerB implements Runnable{

  final Semaphore semaphore;

  ArrayList<Integer> allowedNumbers;

  public WorkerB(Semaphore semaphore) {
    allowedNumbers = new ArrayList<>();
    this.semaphore = semaphore;
    addToAllowedNumbers();
  }

  @Override
  public void run() {

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
