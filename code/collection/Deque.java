package code.collections;

import code.numbers.LongInt;

public class Deque {
    private class Node {
        Node left, right;
        private final LongInt item;

        Node(LongInt item) {
            this.item = item;
        }

        void connectRight(Node other) {
            this.right = other;
            other.left = this;
        }
    }

    private Node head = new Node(null);
    private Node tail = new Node(null);
    private int size;

    public Deque() {
        head.right = tail;
        tail.left = head;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkInvariants() {
        assert size >= 0;
        assert size > 0 || (head == null && tail == null);
        assert (head == null && tail == null) || (head != null && tail != null);
    }

    public void addFirst(LongInt item) {
        Node toadd = new Node(item);
        Node before = head.right;
        head.right = toadd;
        toadd.left = head;
        before.left = toadd;
        toadd.right = before;
        size++;
        checkInvariants();
    }

    public void addLast(LongInt item) {
        Node newTail = new Node(item);
        Node prevTail = tail;
        if (prevTail != null) {
            prevTail.connectRight(newTail);
        } else {
            head = newTail;
        }
        tail = newTail;
        size++;
        checkInvariants();
    }

    public LongInt removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        size--;
        Node togo = head.right;
        head.right = togo.right;
        head.right.left = head;
        togo.left = null;
        togo.right = null;
        checkInvariants();
        return togo.item;
    }

    public LongInt removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        size--;
        Node prevTail = tail;
        tail = prevTail.left;
        prevTail.left = null;
        if (tail != null) tail.right = null;
        checkInvariants();
        return prevTail.item;
    }
}
