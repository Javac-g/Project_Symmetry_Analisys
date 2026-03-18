package com.denysov.miner.topology;


import com.denysov.miner.geometry.polyhedra.*;

import java.util.List;

public final class OctahedronTopologyFactory {

    private OctahedronTopologyFactory() {
    }

    public static PolyhedronTopology create(double s) {
        Vec3 top = new Vec3(0, -s, 0);
        Vec3 left = new Vec3(-s, 0, 0);
        Vec3 back = new Vec3(0, 0, -s);
        Vec3 right = new Vec3(s, 0, 0);
        Vec3 front = new Vec3(0, 0, s);
        Vec3 bottom = new Vec3(0, s, 0);

        List<FaceGeometry> faces = List.of(
                new FaceGeometry(0, List.of(top, left, back)),
                new FaceGeometry(1, List.of(top, back, right)),
                new FaceGeometry(2, List.of(top, right, front)),
                new FaceGeometry(3, List.of(top, front, left)),
                new FaceGeometry(4, List.of(bottom, back, left)),
                new FaceGeometry(5, List.of(bottom, right, back)),
                new FaceGeometry(6, List.of(bottom, front, right)),
                new FaceGeometry(7, List.of(bottom, left, front))
        );

        List<FaceTopology> topology = List.of(
                new FaceTopology(0, List.of(1, 3, 4)),
                new FaceTopology(1, List.of(0, 2, 5)),
                new FaceTopology(2, List.of(1, 3, 6)),
                new FaceTopology(3, List.of(0, 2, 7)),
                new FaceTopology(4, List.of(0, 5, 7)),
                new FaceTopology(5, List.of(1, 4, 6)),
                new FaceTopology(6, List.of(2, 5, 7)),
                new FaceTopology(7, List.of(3, 4, 6))
        );

        return new PolyhedronTopology(new PolyhedronModel(faces), topology);
    }
}
