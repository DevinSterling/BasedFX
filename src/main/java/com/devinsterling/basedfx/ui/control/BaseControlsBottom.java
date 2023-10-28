package com.devinsterling.basedfx.ui.control;

import com.devinsterling.basedfx.util.ui.textfield.NumberField;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.math.BigInteger;

public class BaseControlsBottom extends HBox {
    private final ObjectProperty<BigInteger> resultProperty = new SimpleObjectProperty<>(BigInteger.ZERO);
    private final Button btnClearBoxes = new Button("Clear");
    private final Button btnClose = new Button("×");

    public BaseControlsBottom() {
        super();

        build();
    }

    private void build() {
        NumberField result = new NumberField(BigInteger.ZERO, BigInteger.ZERO, null);
        HBox leftControls = new HBox(btnClose, btnClearBoxes);

        // Properties
        result.valueProperty().bindBidirectional(resultProperty);

        // Children
        setHgrow(result, Priority.ALWAYS);
        getChildren().addAll(leftControls, result);

        leftControls.getStyleClass().addAll("left-controls");
        result.getStyleClass().add("base-control-result");
        leftControls.getStyleClass().add("base-controls-left");
        btnClose.getStyleClass().addAll("base-control-button", "close-button");
        btnClearBoxes.getStyleClass().addAll("base-control-button", "clear-button");
        getStyleClass().addAll("base-controls", "base-controls-bottom");
    }

    public ObjectProperty<BigInteger> resultProperty() {
        return resultProperty;
    }

    public void setOnClearBoxes(EventHandler<ActionEvent> event) {
        btnClearBoxes.setOnAction(event);
    }

    public void setOnClose(EventHandler<ActionEvent> event) {
        btnClose.setOnAction(event);
    }
}
