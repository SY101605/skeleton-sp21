package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> atest = new AListNoResizing<>();
        BuggyAList<Integer> btest = new BuggyAList<>();
        for (int i = 4; i <= 6; i++){
            atest.addLast(i);
            btest.addLast(i);
            assertEquals(atest.size(), btest.size());
            assertEquals(atest.getLast(), btest.getLast());
        }
        for (int i = 6; i >= 4; i--){
            assertEquals(atest.size(), btest.size());
            assertEquals(atest.removeLast(), btest.removeLast());
        }
    }
    @Test
    public void randomTest(){
        AListNoResizing<Integer> atest = new AListNoResizing<>();
        BuggyAList<Integer> btest = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                atest.addLast(randVal);
                btest.addLast(randVal);
                assertEquals(atest.size(), btest.size());
                assertEquals(atest.getLast(), btest.getLast());
            }else if (operationNumber == 1){
                //getLast
                if (atest.size() > 0){
                    assertEquals(atest.getLast(), btest.getLast());
                }
            }else if (operationNumber == 2){
                //removeLast
                if (atest.size() > 0){
                    assertEquals(atest.removeLast(), btest.removeLast());
                }
            }else if (operationNumber == 3){
                //size
                assertEquals(atest.size(), btest.size());
            }

        }

    }
}
