package code.examples;

import code.collections.DoublyLinkedList;
import code.numbers.LongInt;

public class DoublyLinkedListExample implements IExample {
    @Override
    public void runExample() {
        System.out.println("DoublyLinkedList Example запущен");

        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addFirst(new LongInt("222"));
        doublyLinkedList.addFirst(new LongInt("456"));
        doublyLinkedList.addLast(new LongInt("556"));
        doublyLinkedList.addLast(new LongInt("567"));

        doublyLinkedList.print();
        System.out.println();

        doublyLinkedList.removeFirst();
        doublyLinkedList.removeLast();

        doublyLinkedList.print();
        System.out.println();
    }
}
