package com.denysov.miner.topology;

import com.denysov.miner.geometry.polyhedra.Vec3;

import java.util.List;
import java.util.Random;

public class PolyhedronLibrary {

    public static PolyhedronDefinition tetrahedron(double s) {
        List<Vec3> vertices = List.of(
                new Vec3( s,  s,  s),
                new Vec3(-s, -s,  s),
                new Vec3(-s,  s, -s),
                new Vec3( s, -s, -s)
        );

        List<int[]> faces = List.of(
                new int[]{0, 1, 2},
                new int[]{0, 3, 1},
                new int[]{0, 2, 3},
                new int[]{1, 3, 2}
        );

        return new PolyhedronDefinition("Tetrahedron", vertices, faces);
    }

    public static PolyhedronDefinition octahedron(double s) {
        List<Vec3> vertices = List.of(
                new Vec3(0, -s, 0),
                new Vec3(-s, 0, 0),
                new Vec3(0, 0, -s),
                new Vec3(s, 0, 0),
                new Vec3(0, 0, s),
                new Vec3(0, s, 0)
        );

        List<int[]> faces = List.of(
                new int[]{0, 1, 2},
                new int[]{0, 2, 3},
                new int[]{0, 3, 4},
                new int[]{0, 4, 1},
                new int[]{5, 2, 1},
                new int[]{5, 3, 2},
                new int[]{5, 4, 3},
                new int[]{5, 1, 4}
        );

        return new PolyhedronDefinition("Octahedron", vertices, faces);
    }

    public static PolyhedronDefinition icosahedron(double s) {
        double phi = (1.0 + Math.sqrt(5.0)) / 2.0;
        double a = s;
        double b = s * phi;

        List<Vec3> vertices = List.of(
                new Vec3(-a,  b, 0), new Vec3( a,  b, 0),
                new Vec3(-a, -b, 0), new Vec3( a, -b, 0),
                new Vec3(0, -a,  b), new Vec3(0,  a,  b),
                new Vec3(0, -a, -b), new Vec3(0,  a, -b),
                new Vec3( b, 0, -a), new Vec3( b, 0,  a),
                new Vec3(-b, 0, -a), new Vec3(-b, 0,  a)
        );

        List<int[]> faces = List.of(
                new int[]{0, 11, 5},
                new int[]{0, 5, 1},
                new int[]{0, 1, 7},
                new int[]{0, 7, 10},
                new int[]{0, 10, 11},
                new int[]{1, 5, 9},
                new int[]{5, 11, 4},
                new int[]{11, 10, 2},
                new int[]{10, 7, 6},
                new int[]{7, 1, 8},
                new int[]{3, 9, 4},
                new int[]{3, 4, 2},
                new int[]{3, 2, 6},
                new int[]{3, 6, 8},
                new int[]{3, 8, 9},
                new int[]{4, 9, 5},
                new int[]{2, 4, 11},
                new int[]{6, 2, 10},
                new int[]{8, 6, 7},
                new int[]{9, 8, 1}
        );

        return new PolyhedronDefinition("Icosahedron", vertices, faces);
    }

    public static PolyhedronDefinition random(double size, Random random) {
        return switch (random.nextInt(3)) {
            case 0 -> tetrahedron(size);
            case 1 -> octahedron(size);
            default -> icosahedron(size * 0.6);
        };
    }
}
