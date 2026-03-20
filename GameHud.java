package com.denysov.miner.render;

import com.denysov.miner.game.GameState;
import com.denysov.miner.game.GenericPolyBoard;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameHud extends HBox {

    private final Label polyLabel = new Label();
    private final Label minesLabel = new Label();
    private final Label flagsLabel = new Label();
    private final Label stateLabel = new Label();

    public GameHud() {
        setSpacing(24);
        setPadding(new Insets(12));
        setMouseTransparent(true);

        style(polyLabel);
        style(minesLabel);
        style(flagsLabel);
        style(stateLabel);

        getChildren().addAll(polyLabel, minesLabel, flagsLabel, stateLabel);
    }

    private void style(Label label) {
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
    }

    public void refresh(GenericPolyBoard board) {
        polyLabel.setText("Polyhedron: " + board.getDefinition().getName());
        minesLabel.setText("Mines: " + board.getMineCount());
        flagsLabel.setText("Flags: " + board.getFlagCount());
        stateLabel.setText("State: " + pretty(board.getState()));
    }

    private String pretty(GameState state) {
        return switch (state) {
            case READY -> "Ready";
            case RUNNING -> "Running";
            case WON -> "Won";
            case LOST -> "Game Over";
        };
    }
}
