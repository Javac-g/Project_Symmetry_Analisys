package com.max.hexopt.core.geometry;

import com.max.hexopt.core.topology.Vertex;

import java.util.HashMap;
import java.util.Map;

public class SpatialVertexRegistry {

    private final double epsilon;
    private final Map<Long, Vertex> registry = new HashMap<>();
    private int vertexCounter = 0;

    public SpatialVertexRegistry(double epsilon) {
        this.epsilon = epsilon;
    }

    public Vertex getOrCreate(double x, double y) {

        long key = computeKey(x, y);

        Vertex existing = registry.get(key);
        if (existing != null) {
            return existing;
        }

        Vertex created = new Vertex(vertexCounter++, x, y);
        registry.put(key, created);
        return created;
    }

    private long computeKey(double x, double y) {

        long qx = Math.round(x / epsilon);
        long qy = Math.round(y / epsilon);

        return pack(qx, qy);
    }

    private long pack(long x, long y) {
        return (x << 32) ^ (y & 0xffffffffL);
    }

    public int size() {
        return registry.size();
    }
}
