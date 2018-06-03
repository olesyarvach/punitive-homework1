package code.collections;

import code.numbers.ComplexNumber;

public class AVLTree {
    private class Node {
        Node left, right;
        ComplexNumber data;
        int height;

        Node() {
            this(new ComplexNumber());
        }

        Node(ComplexNumber n) {
            left = null;
            right = null;
            data = n;
            height = 0;
        }
    }

    private Node root;

    public AVLTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    public void insert(ComplexNumber data) {
        root = insert(data, root);
    }

    private int height(Node t) {
        return t == null ? -1 : t.height;
    }

    private int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    private Node insert(ComplexNumber x, Node t) {
        if (t == null)
            t = new Node(x);
        else if (x.compareTo(t.data) < 0) {
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x.compareTo(t.left.data) < 0) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithLeftChild(t);
                }
            }
        } else if (x.compareTo(t.data) > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x.compareTo(t.right.data) > 0) {
                    t = rotateWithRightChild(t);
                } else {
                    t = doubleWithRightChild(t);
                }
            }
        }

        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private Node rotateWithLeftChild(Node k2) {
        Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private Node rotateWithRightChild(Node k1) {
        Node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }

    private Node doubleWithLeftChild(Node k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private Node doubleWithRightChild(Node k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node r) {
        if (r == null) {
            return 0;
        }
        int l = 1;
        l += countNodes(r.left);
        l += countNodes(r.right);
        return l;
    }

    public boolean search(ComplexNumber val) {
        return search(root, val);
    }

    private boolean search(Node r, ComplexNumber val) {
        boolean found = false;
        while ((r != null) && !found) {
            ComplexNumber rval = r.data;
            if (val.compareTo(rval) < 0) {
                r = r.left;
            } else if (val.compareTo(rval) > 0) {
                r = r.right;
            } else {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(Node r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.data + " ");
            inorder(r.right);
        }
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(Node r) {
        if (r != null) {
            System.out.print(r.data + " ");
            preorder(r.left);
            preorder(r.right);
        }
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(Node r) {
        if (r != null) {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.data + " ");
        }
    }
}
