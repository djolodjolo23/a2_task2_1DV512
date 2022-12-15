import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SuperClass {

  public void testingtesting(Semaphore semaphore, ArrayList<Integer> nums, AppendedString appendedString) throws InterruptedException {
    if (!nums.contains(appendedString.getSize())) {
      semaphore.wait();
      testingtesting(semaphore, nums, appendedString);
    }
  }

}
