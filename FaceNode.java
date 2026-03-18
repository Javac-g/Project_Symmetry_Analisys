package com.denysov.miner.render;


import com.denysov.miner.game.Cell;
import com.denysov.miner.geometry.polyhedra.FaceGeometry;
import com.denysov.miner.geometry.polyhedra.Vec3;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;


public class FaceNode {

    private final Cell cell;
    private final FaceGeometry geometry;
    private final MeshView mesh;
    private final Group labelGroup = new Group();

    public FaceNode(Cell cell, FaceGeometry geometry) {
        this.cell = cell;
        this.geometry = geometry;
        this.mesh = createMesh(geometry);
        refresh();
    }

    private MeshView createMesh(FaceGeometry face) {
        TriangleMesh triangleMesh = new TriangleMesh();

        for (Vec3 v : face.getVertices()) {
            triangleMesh.getPoints().addAll((float) v.x(), (float) v.y(), (float) v.z());
        }

        triangleMesh.getTexCoords().addAll(0, 0);

        if (face.getVertices().size() == 3) {
            triangleMesh.getFaces().addAll(0, 0, 1, 0, 2, 0);
        } else if (face.getVertices().size() == 4) {
            triangleMesh.getFaces().addAll(
                    0, 0, 1, 0, 2, 0,
                    0, 0, 2, 0, 3, 0
            );
        } else if (face.getVertices().size() == 5) {
            triangleMesh.getFaces().addAll(
                    0, 0, 1, 0, 2, 0,
                    0, 0, 2, 0, 3, 0,
                    0, 0, 3, 0, 4, 0
            );
        } else {
            throw new IllegalArgumentException("Unsupported face vertex count: " + face.getVertices().size());
        }

        MeshView view = new MeshView(triangleMesh);
        view.setUserData(cell.getId());
        return view;
    }

    public void refresh() {
        labelGroup.getChildren().clear();

        if (!cell.isRevealed()) {
            mesh.setMaterial(cell.isFlagged() ? Materials.FLAG : Materials.HIDDEN);

            if (cell.isFlagged()) {
                labelGroup.getChildren().add(TextLabelFactory.createFlagLabel(offsetCenter(1.05)));
            }
            return;
        }

        if (cell.isMine()) {
            mesh.setMaterial(Materials.MINE);
            return;
        }

        mesh.setMaterial(Materials.REVEALED);

        if (cell.getNeighborMines() > 0) {
            labelGroup.getChildren().add(
                    TextLabelFactory.createNumberLabel(cell.getNeighborMines(), offsetCenter(1.05))
            );
        }
    }

    private Vec3 offsetCenter(double factor) {
        return geometry.center().normalize().multiply(geometry.center().length() * factor);
    }

    public MeshView getMesh() {
        return mesh;
    }

    public Group getLabelGroup() {
        return labelGroup;
    }
}