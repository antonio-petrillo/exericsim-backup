import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

public class RelativeDistance {
    private final Map<String, Set<String>> graph = new HashMap<>();

    public RelativeDistance(Map<String, List<String>>  familyTree) {
        familyTree.keySet().stream()
            .forEach(parent -> {
                    List<String> children = familyTree.get(parent);
                    graph.computeIfAbsent(parent, _ -> new HashSet<>())
                        .addAll(children);
                    children.stream()
                        .forEach(c -> {
                                graph.computeIfAbsent(c, _ -> new HashSet<>())
                                    .addAll(children);
                                var set = graph.get(c);
                                set.add(parent);
                                set.remove(c);
                            });
                });
    }

    public int degreeOfSeparation(String personA, String personB) {
        if (personA.equals(personB)) {
            return 0;
        }

        Map<String, Integer> distances = new HashMap<>() { {put(personA, 0);} };
        Deque<String> queue = new ArrayDeque<>();
        queue.addLast(personA);

        while(!queue.isEmpty()) {
           String person = queue.removeFirst();
           int distance = distances.get(person) + 1;
           for (String relative : graph.get(person)) {
               if (!distances.containsKey(relative)) {
                   if (relative.equals(personB)) {
                       return distance;
                   }
                   distances.put(relative, distance);
                   queue.addLast(relative);
               }
           }
        }

        return -1;
    }
}
