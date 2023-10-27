package com.devinsterling.basedfx.util.ui.layout;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LabeledBox extends VBox {
    public LabeledBox(String label, Node node) {
        super(node);

        getChildren().addFirst(new Label(label));

        getStyleClass().add("labeled-box");
    }
}
