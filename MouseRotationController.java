package com.denysov.miner.input;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;

public class MouseRotationController {

    private double anchorX;
    private double anchorY;
    private final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);

    public void attach(Scene scene, Node node) {
        node.getTransforms().addAll(rotateX, rotateY);

        scene.setOnMousePressed(e -> {
            anchorX = e.getSceneX();
            anchorY = e.getSceneY();
        });

        scene.setOnMouseDragged(e -> {
            rotateY.setAngle(rotateY.getAngle() + (e.getSceneX() - anchorX));
            rotateX.setAngle(rotateX.getAngle() - (e.getSceneY() - anchorY));

            anchorX = e.getSceneX();
            anchorY = e.getSceneY();
        });
    }
}
