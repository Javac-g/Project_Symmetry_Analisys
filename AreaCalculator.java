public class AreaCalculator {

    public double calculate(Polygon polygon) {
        List<Point> v = polygon.getVertices();
        int n = v.size();
        double sum = 0;

        for (int i = 0; i < n; i++) {
            Point p1 = v.get(i);
            Point p2 = v.get((i + 1) % n);

            sum += (p1.x() * p2.y()) - (p2.x() * p1.y());
        }

        return Math.abs(sum) / 2.0;
    }
}
