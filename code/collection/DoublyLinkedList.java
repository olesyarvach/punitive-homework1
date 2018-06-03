package code.collections;

import code.numbers.LongInt;

import java.util.NoSuchElementException;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        size = 0;
    }

    public void print() {
        Node tmp = head;
        while (tmp != null) {
            tmp.num.print();
            tmp = tmp.next;
        }
    }

    private class Node {
        LongInt num;
        Node next;
        Node prev;

        public Node(LongInt num, Node next, Node prev) {
            this.num = num;
            this.next = next;
            this.prev = prev;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(LongInt element) {
        Node tmp = new Node(element, head, null);
        if (head != null) {
            head.prev = tmp;
        }
        head = tmp;
        if (tail == null) {
            tail = tmp;
        }
        size++;
    }

    public void addLast(LongInt element) {
        Node tmp = new Node(element, null, tail);
        if (tail != null) {
            tail.next = tmp;
        }
        tail = tmp;
        if (head == null) {
            head = tmp;
        }
        size++;
    }

    @SuppressWarnings("Duplicates")
    public LongInt removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = head;
        head = head.next;
        head.prev = null;
        size--;
        return tmp.num;
    }

    @SuppressWarnings("Duplicates")
    public LongInt removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        return tmp.num;
    }
}
