package deque;

import static org.junit.Assert.*;
import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void testAddFirstAndGet() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        assertEquals(2, (int) deque.get(0)); // 第一个元素是 2
        assertEquals(1, (int) deque.get(1)); // 第二个元素是 1
    }

    @Test
    public void testAddLastAndRemove() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addLast("A");
        deque.addLast("B");
        assertEquals("A", deque.removeFirst());
        assertEquals("B", deque.removeLast());
    }

    @Test
    public void testResizeOnAdd() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            deque.addLast(i); // 触发多次扩容
        }
        assertEquals(100, deque.size());
        assertEquals(0, (int) deque.get(0));
        assertEquals(99, (int) deque.get(99));
    }
    @Test
    public void testShrinkOnRemove() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 90; i++) {
            deque.removeLast(); // 触发缩容
        }
        assertTrue(deque.size() == 10);
        assertEquals(0, (int) deque.get(0));
    }
}
