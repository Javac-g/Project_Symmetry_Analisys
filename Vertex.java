package com.max.hexopt.core.topology;

public final class Vertex {

    private final int id;
    private final double x;
    private final double y;

    public Vertex(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int id() { return id; }
    public double x() { return x; }
    public double y() { return y; }
}
