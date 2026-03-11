package com.max.hexopt.core.topology;

public class Face {

    private final int id;
    private HalfEdge edge;

    public Face(int id) {
        this.id = id;
    }

    public int id() { return id; }
    public HalfEdge edge() { return edge; }

    void setEdge(HalfEdge edge) { this.edge = edge; }
}
