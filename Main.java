package com.denysov.miner;

import com.denysov.miner.game.GenericPolyBoard;

import com.denysov.miner.render.GameHud;
import com.denysov.miner.render.GenericPolyhedronView;
import com.denysov.miner.topology.PolyhedronDefinition;
import com.denysov.miner.topology.PolyhedronLibrary;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private double anchorX;
    private double anchorY;

    private final Rotate rotateX = new Rotate(-20, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(25, Rotate.Y_AXIS);

    @Override
    public void start(Stage stage) {
        Random random = new Random();

        double size = 180;
        PolyhedronDefinition polyhedron = PolyhedronLibrary.random(size, random);

        int subdivision = switch (polyhedron.getName()) {
            case "Tetrahedron" -> 6;
            case "Octahedron" -> 5;
            case "Icosahedron" -> 4;
            default -> 5;
        };

        int estimatedCells = estimateCellCount(polyhedron, subdivision);
        int mines = Math.max(10, estimatedCells / 7);

        GenericPolyBoard board = new GenericPolyBoard(polyhedron, subdivision, mines);
        GenericPolyhedronView view = new GenericPolyhedronView();
        GameHud hud = new GameHud();

        Group poly = view.build(board);
        poly.getTransforms().addAll(rotateX, rotateY);

        AmbientLight ambient = new AmbientLight(Color.color(0.72, 0.72, 0.72));

        PointLight keyLight = new PointLight(Color.color(0.95, 0.95, 0.95));
        keyLight.setTranslateX(-420);
        keyLight.setTranslateY(-220);
        keyLight.setTranslateZ(-650);

        PointLight fillLight = new PointLight(Color.color(0.45, 0.45, 0.45));
        fillLight.setTranslateX(260);
        fillLight.setTranslateY(-80);
        fillLight.setTranslateZ(-320);

        Group world = new Group(poly, ambient, keyLight, fillLight);
        SubScene subScene = new SubScene(world, 1100, 850, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.BLACK);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-900);
        camera.setNearClip(0.1);
        camera.setFarClip(5000);
        subScene.setCamera(camera);

        StackPane centerPane = new StackPane(subScene);
        centerPane.setStyle("-fx-background-color: black;");

        subScene.widthProperty().bind(centerPane.widthProperty());
        subScene.heightProperty().bind(centerPane.heightProperty());

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");
        root.setCenter(centerPane);
        root.setTop(hud);
        BorderPane.setAlignment(hud, Pos.TOP_LEFT);

        Scene scene = new Scene(root, 1100, 850, true);
        scene.setFill(Color.BLACK);

        hud.refresh(board);
        view.refresh(board, rotateX.getAngle(), rotateY.getAngle());
        stage.setTitle(buildWindowTitle(board));

        subScene.setOnMousePressed(e -> {
            anchorX = e.getSceneX();
            anchorY = e.getSceneY();
        });

        subScene.setOnMouseDragged(e -> {
            rotateY.setAngle(rotateY.getAngle() + (e.getSceneX() - anchorX));
            rotateX.setAngle(rotateX.getAngle() - (e.getSceneY() - anchorY));

            anchorX = e.getSceneX();
            anchorY = e.getSceneY();

            view.refresh(board, rotateX.getAngle(), rotateY.getAngle());
            hud.refresh(board);
            stage.setTitle(buildWindowTitle(board));
        });

        subScene.setOnMouseClicked(e -> {
            view.refresh(board, rotateX.getAngle(), rotateY.getAngle());
            hud.refresh(board);
            stage.setTitle(buildWindowTitle(board));
        });

        stage.setScene(scene);
        stage.show();
    }

    private int estimateCellCount(PolyhedronDefinition definition, int subdivision) {
        int trianglesPerFace = subdivision * subdivision;
        int total = 0;

        for (int[] face : definition.getFaces()) {
            int triangulatedPieces = Math.max(1, face.length - 2);
            total += triangulatedPieces * trianglesPerFace;
        }

        return total;
    }

    private String buildWindowTitle(GenericPolyBoard board) {
        return switch (board.getState()) {
            case READY -> "PolyMines - " + board.getDefinition().getName();
            case RUNNING -> "PolyMines - " + board.getDefinition().getName() + " - Running";
            case WON -> "PolyMines - " + board.getDefinition().getName() + " - You Won";
            case LOST -> "PolyMines - " + board.getDefinition().getName() + " - Game Over";
        };
    }

    public static void main(String[] args) {
        launch();
    }
}
