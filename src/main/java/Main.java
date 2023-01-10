import java.util.concurrent.Semaphore;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    Semaphore semaphore = new Semaphore(0);
    SizeCounter sizeCounter = new SizeCounter();

    WorkerA wa = new WorkerA(semaphore, sizeCounter);
    Thread ta = new Thread(wa);
    WorkerB wb = new WorkerB(semaphore, sizeCounter);
    Thread tb = new Thread(wb);
    WorkerC wc = new WorkerC(semaphore, sizeCounter);
    Thread tc = new Thread(wc);
    WorkerD wd = new WorkerD(semaphore, sizeCounter);
    Thread td = new Thread(wd);


    ta.start();
    tb.start();
    tc.start();
    td.start();

    ta.join();
    tb.join();
    tc.join();
    td.join();

  }

}
