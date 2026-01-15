import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Satellite {
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

        var asArray = inorder.stream().toArray(Character[]::new);
        
        return new Tree(buildRecursively(preorder.iterator(), asArray, 0, asArray.length)); 
    }
    
    private static final Node buildRecursively(Iterator<Character> iter, Character[] inorder, int low, int high) {
        char rootVal = iter.next();
        Node root = new Node(rootVal);

        int rootValIndex = -1;
        for (int i = low; low < high; i++) {
            if (inorder[i] == rootVal) {
                rootValIndex = i;
                break;
            }
        }

        if (low < rootValIndex) {
            root.left = buildRecursively(iter, inorder, low, rootValIndex);
        }
        if (high > rootValIndex + 1) {
            root.right = buildRecursively(iter, inorder, rootValIndex + 1, high);
        }
        
        return root;
    }
    
}
