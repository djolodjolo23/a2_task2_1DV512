import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class WorkerC implements Runnable{

  final Semaphore semaphore;

  ArrayList<Integer> allowedNumbers;


  public WorkerC(Semaphore semaphore) {
    allowedNumbers = new ArrayList<>();
    this.semaphore = semaphore;
    addToAllowedNumbers();
  }

  @Override
  public void run() {
    startWhenStringReaches3();

  }

  public void startWhenStringReaches3() {
    while (AppendedString.getCurrentStringSize() < 3) {
      startWhenStringReaches3();
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

  public ArrayList<Integer> getAllowedNumbers() {
    return allowedNumbers;
  }
}
