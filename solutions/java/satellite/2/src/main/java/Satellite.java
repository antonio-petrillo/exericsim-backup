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
        
        return new Tree(buildRecursively(preorder.iterator(), inorder)); 
    }
    
    private static final Node buildRecursively(Iterator<Character> iter, List<Character> inorder) {
        char rootVal = iter.next();
        Node root = new Node(rootVal);

        List<Character> left = new ArrayList<>();
        List<Character> right = new ArrayList<>();
        boolean addLeft = true;
        for (var ch : inorder) {
            if (ch == rootVal) {
                addLeft = false;
                continue;
            }
            if (addLeft) 
                left.add(ch);
            else 
                right.add(ch);
        }

        if (!left.isEmpty()) {
            root.left = buildRecursively(iter, left);    
        }
        if (!right.isEmpty()) {
            root.right = buildRecursively(iter, right);
        }
        
        return root;
    }
    
}
