public class Polygon {

    private final List<Point> vertices;

    public Polygon(List<Point> vertices) {
        if (vertices.size() < 3) {
            throw new IllegalArgumentException("Polygon needs 3+ vertices");
        }
        this.vertices = List.copyOf(vertices);
    }

    public List<Point> getVertices() {
        return vertices;
    }
}
