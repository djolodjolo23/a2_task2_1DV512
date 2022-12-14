import java.util.concurrent.Semaphore;

public class WorkerB implements Runnable{

  final Semaphore semaphore;

  public WorkerB(Semaphore semaphore) {
    this.semaphore = semaphore;
  }

  @Override
  public void run() {

  }
}
