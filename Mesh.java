public class Mesh {

    private final Map<Integer, Vertex> vertices = new HashMap<>();
    private final List<HalfEdge> edges = new ArrayList<>();
    private final List<Face> faces = new ArrayList<>();

    public Collection<Vertex> getVertices() { return vertices.values(); }
    public List<Face> getFaces() { return faces; }
}
