package com.denysov.miner.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private final List<Cell> cells = new ArrayList<>();

    public Board() {
        for (int i = 0; i < 8; i++) {
            cells.add(new Cell(i));
        }

        connectNeighbors();
        placeMines(2);
        calculateNumbers();
    }

    private void connectNeighbors() {
        int[][] adjacency = {
                {1, 2, 3},
                {0, 2, 4},
                {0, 1, 5},
                {0, 4, 5},
                {1, 3, 6},
                {2, 3, 7},
                {4, 7, 0},
                {5, 6, 1}
        };

        for (int i = 0; i < adjacency.length; i++) {
            for (int n : adjacency[i]) {
                cells.get(i).addNeighbor(cells.get(n));
            }
        }
    }

    private void placeMines(int count) {
        List<Cell> shuffled = new ArrayList<>(cells);
        Collections.shuffle(shuffled);

        for (int i = 0; i < count; i++) {
            shuffled.get(i).setMine(true);
        }
    }

    private void calculateNumbers() {
        for (Cell cell : cells) {
            int mines = 0;

            for (Cell neighbor : cell.getNeighbors()) {
                if (neighbor.isMine()) {
                    mines++;
                }
            }

            cell.setNeighborMines(mines);
        }
    }

    public List<Cell> getCells() {
        return cells;
    }
}