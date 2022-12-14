import java.util.concurrent.Semaphore;

public class WorkerC implements Runnable{

  final Semaphore semaphore;

  public WorkerC(Semaphore semaphore) {
    this.semaphore = semaphore;
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
}
