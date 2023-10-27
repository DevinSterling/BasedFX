package com.devinsterling.basedfx.ui.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class BaseControlsBottom extends HBox {
    private final StringProperty resultProperty = new SimpleStringProperty();
    private final Button btnClearBoxes = new Button("Clear");
    private final Button btnClose = new Button("×");

    public BaseControlsBottom() {
        super();

        build();
    }

    private void build() {
        Label lblResult = new Label();
        HBox leftControls = new HBox(btnClose, btnClearBoxes);

        // Properties
        lblResult.textProperty().bind(resultProperty);

        // Children
        setHgrow(leftControls, Priority.ALWAYS);
        getChildren().addAll(leftControls, lblResult);

        leftControls.getStyleClass().addAll("left-controls");
        lblResult.getStyleClass().add("base-control-result");
        leftControls.getStyleClass().add("base-controls-left");
        btnClose.getStyleClass().addAll("base-control-button", "close-button");
        btnClearBoxes.getStyleClass().addAll("base-control-button", "clear-button");
        getStyleClass().addAll("base-controls", "base-controls-bottom");
    }

    public StringProperty resultProperty() {
        return resultProperty;
    }

    public void setOnClearBoxes(EventHandler<ActionEvent> event) {
        btnClearBoxes.setOnAction(event);
    }

    public void setOnClose(EventHandler<ActionEvent> event) {
        btnClose.setOnAction(event);
    }
}
