package com.denysov.miner.render;

import com.denysov.miner.geometry.polyhedra.Vec3;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class TextLabelFactory {

    public Group createNumber(int number, Vec3 position) {
        return createNumberLabel(number, position);
    }

    public Group createNumberLabel(int number, Vec3 position) {
        Text text = new Text(String.valueOf(number));
        text.setFill(numberColor(number));
        text.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(0.35);

        Group group = new Group(text);
        group.setTranslateX(position.x());
        group.setTranslateY(position.y());
        group.setTranslateZ(position.z());

        text.setTranslateX(-5);
        text.setTranslateY(5);

        return group;
    }

    public Group createFlagLabel(Vec3 position) {
        Text text = new Text("F");
        text.setFill(Color.web("#f08c00"));
        text.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(0.35);

        Group group = new Group(text);
        group.setTranslateX(position.x());
        group.setTranslateY(position.y());
        group.setTranslateZ(position.z());

        text.setTranslateX(-5);
        text.setTranslateY(5);

        return group;
    }

    public void applyBillboard(Group label, double rotateXAngle, double rotateYAngle) {
        label.getTransforms().setAll(
                new Rotate(-rotateYAngle, Rotate.Y_AXIS),
                new Rotate(-rotateXAngle, Rotate.X_AXIS)
        );
    }

    private Color numberColor(int n) {
        return switch (n) {
            case 1 -> Color.web("#1d4ed8");
            case 2 -> Color.web("#15803d");
            case 3 -> Color.web("#dc2626");
            case 4 -> Color.web("#7c3aed");
            case 5 -> Color.web("#b45309");
            case 6 -> Color.web("#0f766e");
            case 7 -> Color.web("#111827");
            case 8 -> Color.web("#6b7280");
            default -> Color.web("#111827");
        };
    }
}
