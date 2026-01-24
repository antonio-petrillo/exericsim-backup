// wtf is this API?
public class Zipper {
    public Zipper up;
    public Zipper left;
    public Zipper right;
    public int val;

    public Zipper(int val) {
        this.val = val;
    }

    public BinaryTree toTree() {
        var root = this;
        while (root.up != null)
            root = root.up;
        return new BinaryTree(root);
    }

    public int getValue() {
        return val;
    }

    public Zipper setLeft(Zipper leftChild) {
        if (leftChild != null)
            leftChild.up = this;
        left = leftChild;
        return left;
    }

    public Zipper setRight(Zipper rightChild) {
        if (rightChild != null)
            rightChild.up = this;
        right = rightChild;
        return right;

        // var zipper = new Zipper(val);
        // zipper.up = up;
        // zipper.left = left;
        // zipper.right = rightChild;
        // rightChild.up = zipper;
        // return zipper;
    }

    public void setValue(int val) {
        this.val = val;
    }
}

class BinaryTree {
    public Zipper root;

    public BinaryTree(int value) {
        root = new Zipper(value);
    }

    public BinaryTree(Zipper root) {
        this.root = root;
    }

    public Zipper getRoot() {
        return root;
    }

    public String printTree() {
        return String.format("value: %d, left: %s, right: %s",
                             root.getValue(),
                             build(root.left),
                             build(root.right));
    }

    private static String build(Zipper root) {
        if (root == null) {
            return "null";
        }

        return String.format("{ value: %d, left: %s, right: %s }",
                             root.getValue(),
                             build(root.left),
                             build(root.right));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;

        if (o instanceof BinaryTree b) {
            return root.equals(b.root);
        }
        return false;
    }
}
