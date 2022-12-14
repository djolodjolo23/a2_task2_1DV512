import java.util.concurrent.Semaphore;

public class WorkerD implements Runnable{

  final Semaphore semaphore;

  public WorkerD(Semaphore semaphore) {
    this.semaphore = semaphore;
  }

  @Override
  public void run() {

  }
}
