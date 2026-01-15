import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class Satellite {
    private static final int USE_INDEX_THRESHOLD = 1_000;
    
    public Tree treeFromTraversals(List<Character> preorder, List<Character> inorder) {
        {
            if (preorder.size() != inorder.size()) 
                throw new IllegalArgumentException("traversals must have the same length");

            Set<Character> charsInOrder = inorder.stream()
                .distinct()
                .collect(Collectors.toSet());

            Set<Character> charsPreOrder = preorder.stream()
                .distinct()
                .collect(Collectors.toSet());
        
            if (charsInOrder.size() != preorder.size() || charsPreOrder.size() != preorder.size())
                throw new IllegalArgumentException("traversals must contain unique items");

            charsInOrder.addAll(charsPreOrder);
            if (charsInOrder.size() != preorder.size())
                throw new IllegalArgumentException("traversals must have the same elements");

            if (preorder.isEmpty())
                return new Tree(null);
        }

        if (preorder.size() > USE_INDEX_THRESHOLD) {
            var invertedIndex = new HashMap<Character, Integer>();
            int index = 0;
            for (var ch : inorder) {
                invertedIndex.put(ch, index++);
            }
            return new Tree(buildRecursively(preorder.iterator(), invertedIndex, 0, preorder.size())); 
        } 
        var asArray = inorder.stream().toArray(Character[]::new);
        return new Tree(buildRecursively(preorder.iterator(), asArray, 0, asArray.length)); 
    }

    // for small trees
    private static final Node buildRecursively(Iterator<Character> iter, Character[] inorder, int low, int high) {
        char rootVal = iter.next();
        Node root = new Node(rootVal);

        int rootValIndex = -1;
        for (int i = low; i < high; i++) {
            if (inorder[i] == rootVal) {
                rootValIndex = i;
                break;
            }
        }

        if (low < rootValIndex) {
            root.left = buildRecursively(iter, inorder, low, rootValIndex);
        }
        if (rootValIndex + 1 < high) {
            root.right = buildRecursively(iter, inorder, rootValIndex + 1, high);
        }
        
        return root;
    }

    // for large trees
    private static final Node buildRecursively(Iterator<Character> iter, Map<Character, Integer> inorderIndex, int low, int high) {
        char rootVal = iter.next();
        Node root = new Node(rootVal);

        int rootValIndex = inorderIndex.get(rootVal);

        if (low < rootValIndex) {
            root.left = buildRecursively(iter, inorderIndex, low, rootValIndex);
        }
        if (rootValIndex + 1 < high) {
            root.right = buildRecursively(iter, inorderIndex, rootValIndex + 1, high);
        }
        
        return root;
    }
    
}
