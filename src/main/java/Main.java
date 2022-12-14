import java.util.concurrent.Semaphore;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    Semaphore semaphore = new Semaphore(0);

    WorkerA wa = new WorkerA(semaphore);
    Thread ta = new Thread(wa);
    WorkerB wb = new WorkerB(semaphore);
    Thread tb = new Thread(wb);
    WorkerC wc = new WorkerC(semaphore);
    Thread tc = new Thread(wc);
    WorkerD wd = new WorkerD(semaphore);
    Thread td = new Thread(wd);

    for (Integer i : wa.getAllowedNumbers()) {
      System.out.println(i);
    }

    for (Integer i : wc.getAllowedNumbers()) {
      System.out.println(i);
    }

  }

}
