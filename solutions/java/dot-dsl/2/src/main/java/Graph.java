import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Graph {

    private final Map<String, String> attributes;
    private final Set<Node> nodes;
    private final Set<Edge> edges;

    public Graph() {
        this.attributes = Collections.emptyMap();
        this.nodes = Collections.emptySet();
        this.edges = Collections.emptySet();
    }

    public Graph(Map<String, String> attributes) {
        this.attributes = Map.copyOf(attributes);
        this.nodes = Collections.emptySet();
        this.edges = Collections.emptySet();
    }

    private Graph(Graph g, Node n) {
        this.attributes = g.attributes; 
        this.nodes = Stream.concat(g.nodes.stream(), Stream.of(n))
            .collect(Collectors.toUnmodifiableSet());
        this.edges = g.edges;
    }

    private Graph(Graph g, Edge e) {
        this.attributes = g.attributes; 
        this.nodes = g.nodes;
        this.edges = Stream.concat(g.edges.stream(), Stream.of(e))
            .collect(Collectors.toUnmodifiableSet());
    }

    public Collection<Node> getNodes() {
        return nodes;
    }

    public Collection<Edge> getEdges() {
        return edges;
    }

    public Graph node(String name) {
        return new Graph(this, new Node(name));
    }

    public Graph node(String name, Map<String, String> attributes) {
        return new Graph(this, new Node(name, attributes));
    }

    public Graph edge(String start, String end) {
        return new Graph(this, new Edge(start, end));
    }

    public Graph edge(String start, String end, Map<String, String> attributes) {
        return new Graph(this, new Edge(start, end, attributes));
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
