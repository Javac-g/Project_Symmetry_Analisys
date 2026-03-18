package com.denysov.miner.render;



import com.denysov.miner.game.*;
import com.denysov.miner.geometry.polyhedra.FaceGeometry;
import javafx.scene.Group;

import java.util.HashMap;
import java.util.Map;

public class Board3DView {

    private final GameBoard board;
    private final Group root = new Group();
    private final Map<Integer, FaceNode> faceNodes = new HashMap<>();

    public Board3DView(GameBoard board) {
        this.board = board;
        build();
    }

    private void build() {
        for (Cell cell : board.getCells()) {
            FaceGeometry geometry = board.getTopology().getModel().getFace(cell.getId());
            FaceNode node = new FaceNode(cell, geometry);

            faceNodes.put(cell.getId(), node);
            root.getChildren().add(node.getMesh());
            root.getChildren().add(node.getLabelGroup());
        }
    }

    public void refresh() {
        faceNodes.values().forEach(FaceNode::refresh);
    }

    public Group getRoot() {
        return root;
    }
}