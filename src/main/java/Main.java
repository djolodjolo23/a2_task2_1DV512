import java.util.concurrent.Semaphore;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    Semaphore semaphore = new Semaphore(0);
    AppendedString appendedString = new AppendedString();

    WorkerA wa = new WorkerA(semaphore, appendedString);
    Thread ta = new Thread(wa);
    WorkerB wb = new WorkerB(semaphore, appendedString);
    Thread tb = new Thread(wb);
    WorkerC wc = new WorkerC(semaphore, appendedString);
    Thread tc = new Thread(wc);
    WorkerD wd = new WorkerD(semaphore, appendedString);
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
