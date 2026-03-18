package com.denysov.miner.topology;

import com.denysov.miner.geometry.polyhedra.*;

import java.util.ArrayList;
import java.util.List;

public final class GenericTopologyBuilder {

    private GenericTopologyBuilder() {
    }

    public static PolyhedronTopology build(List<Vec3> vertices, List<int[]> faceIndices) {
        List<FaceGeometry> faces = new ArrayList<>();
        List<FaceTopology> topology = new ArrayList<>();

        for (int i = 0; i < faceIndices.size(); i++) {
            int[] face = faceIndices.get(i);
            List<Vec3> faceVertices = new ArrayList<>();

            for (int vertexIndex : face) {
                faceVertices.add(vertices.get(vertexIndex));
            }

            faces.add(new FaceGeometry(i, faceVertices));
        }

        for (int i = 0; i < faceIndices.size(); i++) {
            List<Integer> neighbors = new ArrayList<>();

            for (int j = 0; j < faceIndices.size(); j++) {
                if (i == j) {
                    continue;
                }

                if (areAdjacent(faceIndices.get(i), faceIndices.get(j))) {
                    neighbors.add(j);
                }
            }

            topology.add(new FaceTopology(i, neighbors));
        }

        return new PolyhedronTopology(new PolyhedronModel(faces), topology);
    }

    private static boolean areAdjacent(int[] faceA, int[] faceB) {
        int shared = 0;

        for (int a : faceA) {
            for (int b : faceB) {
                if (a == b) {
                    shared++;
                }
            }
        }

        return shared >= 2;
    }
}
