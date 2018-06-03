package code.examples;

import code.collections.Deque;
import code.numbers.LongInt;

public class DequeExample implements IExample {
    @Override
    public void runExample() {
        System.out.println("Deque Example запущен");

        Deque deque = new Deque();
        deque.addFirst(new LongInt("33"));
        deque.addFirst(new LongInt("35"));

        deque.removeFirst().print();

        deque.addLast(new LongInt("333"));

        deque.removeLast().print();
    }
}
