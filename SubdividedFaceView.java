package com.denysov.miner.render;

import com.denysov.miner.geometry.polyhedra.TriangleCellData;
import com.denysov.miner.geometry.polyhedra.TriangleSubdivision;
import com.denysov.miner.geometry.polyhedra.Vec3;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;

import java.util.List;


public class SubdividedFaceView {

    public List<TriangleCellData> cells;

    public Group build() {
        Vec3 a = new Vec3(0, -180, 0);
        Vec3 b = new Vec3(180, 180, 0);
        Vec3 c = new Vec3(-180, 180, 0);

        cells = TriangleSubdivision.subdivide(a, b, c, 5);

        Group g = new Group();

        PhongMaterial upMat = new PhongMaterial(Color.web("#4dabf7"));
        PhongMaterial downMat = new PhongMaterial(Color.web("#1971c2"));
        PhongMaterial openMat = new PhongMaterial(Color.web("#ced4da"));

        for (TriangleCellData cell : cells) {
            cell.getMesh().setMaterial(cell.isUp() ? upMat : downMat);

            cell.getMesh().setOnMouseClicked(e -> {
                cell.getMesh().setMaterial(openMat);
                e.consume();
            });

            g.getChildren().add(cell.getMesh());
        }

        return g;
    }
}
