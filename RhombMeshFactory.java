package com.max.hexopt.core.aperiodic;

import com.max.hexopt.core.geometry.SpatialVertexRegistry;
import com.max.hexopt.core.topology.MeshBuilder;
import com.max.hexopt.core.topology.Vertex;

import java.util.ArrayList;
import java.util.List;

public class RhombMeshFactory {

    private static final double DEG_TO_RAD = Math.PI / 180.0;

    private final SpatialVertexRegistry registry;
    private final MeshBuilder meshBuilder;

    public RhombMeshFactory(SpatialVertexRegistry registry,
                            MeshBuilder meshBuilder) {
        this.registry = registry;
        this.meshBuilder = meshBuilder;
    }

    public void createRhomb(double centerX,
                            double centerY,
                            double edgeLength,
                            double orientationDeg,
                            RhombType type) {

        double angle = orientationDeg * DEG_TO_RAD;

        double acuteAngleDeg = (type == RhombType.THICK) ? 72.0 : 36.0;
        double acuteAngle = acuteAngleDeg * DEG_TO_RAD;

        // First direction vector
        double dx1 = edgeLength * Math.cos(angle);
        double dy1 = edgeLength * Math.sin(angle);

        // Second direction vector rotated by acute angle
        double dx2 = edgeLength * Math.cos(angle + acuteAngle);
        double dy2 = edgeLength * Math.sin(angle + acuteAngle);

        // Build vertices around center
        double x1 = centerX - dx1 / 2 - dx2 / 2;
        double y1 = centerY - dy1 / 2 - dy2 / 2;

        double x2 = x1 + dx1;
        double y2 = y1 + dy1;

        double x3 = x2 + dx2;
        double y3 = y2 + dy2;

        double x4 = x1 + dx2;
        double y4 = y1 + dy2;

        Vertex v1 = registry.getOrCreate(x1, y1);
        Vertex v2 = registry.getOrCreate(x2, y2);
        Vertex v3 = registry.getOrCreate(x3, y3);
        Vertex v4 = registry.getOrCreate(x4, y4);

        List<Vertex> vertices = new ArrayList<>(4);
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);

        meshBuilder.createFace(vertices);
    }
}
