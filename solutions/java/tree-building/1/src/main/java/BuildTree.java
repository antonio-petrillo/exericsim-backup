import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class BuildTree {

    // don't need to override the 'hash()', I *want* to compare the UnionFind by reference
    private static class UnionFind<T> {
        T elem;
        UnionFind<T> parent;
        int size = 1; 

        UnionFind(T elem) {
            this.elem = elem;
            parent = this;
        }

        UnionFind<T> find() {
            if (parent != this) {
                parent = parent.find();
            }
            return parent;
        }

        boolean sameSet(UnionFind<T> other) {
            var parent1 = find();
            var parent2 = other.find();

            return parent1 == parent2;
        }

        boolean union(UnionFind<T> other) {
            var parent1 = find();
            var parent2 = other.find();

            if (parent1 == parent2) return false;
            
            if (parent1.size < parent2.size) {
                var tmp = parent1;
                parent1 = parent2;
                parent2 = tmp;
            }

            parent1.size++;
            parent2.parent = parent1;

            return true;
        }
    }

    public TreeNode buildTree(List<Record> records) throws InvalidRecordsException {
        // Why return 'null' here? 'Optional<TreeNode>' is better
        if (records.isEmpty()) return null; 

        records = records.stream()
            .sorted((r1, r2) -> r1.getRecordId() - r2.getRecordId())
            .toList();
        
        Map<Integer, UnionFind<Record>> unionFinds = new HashMap<>();
        Map<Integer, TreeNode> tree = new HashMap<>();
        
        var root = records.get(0);
        int rootId = root.getRecordId();
        
        if (root.getParentId() != rootId || rootId < 0)
            throw new InvalidRecordsException("Invalid Records");
            
        for (var node : records) {
            var id = node.getRecordId();
            if (id >= records.size()) 
                throw new InvalidRecordsException("Invalid Records");
            
            unionFinds.put(id, new UnionFind<>(node));
            if (tree.containsKey(id)) 
                throw new InvalidRecordsException("Invalid Records");
            tree.put(id, new TreeNode(id));
        }

        for (var record : records) {
            var id = record.getRecordId();
            if (id == rootId) continue;

            var set = unionFinds.get(id);
            var parentSet = unionFinds.get(record.getParentId());
            
            set.union(parentSet);
            
            var node = tree.get(id);
            var parentNode = tree.get(record.getParentId());

            parentNode.getChildren().add(node);
        }

        var iter = unionFinds.values().iterator();
        var first = iter.next();

        while (iter.hasNext()) {
            if (!first.sameSet(iter.next()))
                throw new InvalidRecordsException("Invalid Records");
        }
        
        return tree.get(rootId);
    }

}