import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    public BinarySearchTree() {
        this.root = null;
    }
    
    public BinarySearchTree(T t) {
        this.root = new Node<T>(t);
    }
    
    void insert(T value) {
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> root, T value) {
        if(root == null) {
            return new Node<T>(value);
        } 
        if (root.value.compareTo(value) >= 0) {
            root.left = insertRec(root.left, value);
        } else {
            root.right = insertRec(root.right, value);
        }
            
        return root;    
    }

    List<T> getAsSortedList() {
        List<T> result = new ArrayList<>();
        inOrderVisit(root, result);
        return result;
    }

    private void inOrderVisit(Node<T> root, List<T> l) {
        if (root != null) {
            inOrderVisit(root.left, l);
            l.add(root.value);
            inOrderVisit(root.right, l);
        }
    }

    List<T> getAsLevelOrderList() {
        LinkedList<Node<T>> stack = new LinkedList<>();
        List<T> result = new ArrayList<T>();
        if (root != null) {
            stack.addLast(root);
        }
        while(!stack.isEmpty()) {
            var node = stack.removeFirst();
            result.add(node.value);
            if(node.left != null) {
                stack.addLast(node.left);
            }
            if(node.right != null) {
                stack.addLast(node.right);
            }
        }
        return result;
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T> {

        private Node<T> left, right;
        private T value;

        public Node(T t) {
            this.value = t;
            this.left = null;
            this.right = null;
        }

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        T getData() {
            return value;
        }

    }
}
