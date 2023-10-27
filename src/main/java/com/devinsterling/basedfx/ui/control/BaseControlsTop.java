package com.devinsterling.basedfx.ui.control;

import com.devinsterling.basedfx.consts.Default;
import com.devinsterling.basedfx.consts.Limit;
import com.devinsterling.basedfx.util.ui.textfield.NumberField;
import com.devinsterling.basedfx.util.ui.layout.LabeledBox;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class BaseControlsTop extends HBox {
    private final IntegerProperty baseProperty = new SimpleIntegerProperty(Default.BASE);
    /**
     * Used in conjunction with {@link #btnAddBaseBox} and {@link #btnRemoveBaseBox}.
     * <br />
     * Determines how many base boxes to add or remove at once.
     */
    private final IntegerProperty baseBoxStep = new SimpleIntegerProperty(Limit.MIN_ADD_BASE_BOX);
    private final Button btnAddBaseBox = new Button("+");
    private final Button btnRemoveBaseBox = new Button("-");

    public BaseControlsTop() {
        build();
    }

    private void build() {
        NumberField txtCurrentBase = new NumberField(String.valueOf(Default.BASE), Limit.MIN_BASE, Limit.MAX_BASE);
        NumberField txtBaseBoxStep = new NumberField(
            String.valueOf(Limit.MIN_ADD_BASE_BOX),
            Limit.MIN_ADD_BASE_BOX,
            Limit.MAX_ADD_BASE_BOX
        );
        HBox hbxAddOrRemove = new HBox(new LabeledBox(
            "Add/Remove boxes",
            new HBox(btnRemoveBaseBox, txtBaseBoxStep, btnAddBaseBox)
        ));

        // Properties
        baseProperty.bind(txtCurrentBase.valueProperty());
        baseBoxStep.bind(txtBaseBoxStep.valueProperty());

        // Children
        setHgrow(hbxAddOrRemove, Priority.ALWAYS);
        getChildren().addAll(hbxAddOrRemove, new LabeledBox("Base", txtCurrentBase));

        txtCurrentBase.getStyleClass().add("base-control-base");
        txtBaseBoxStep.getStyleClass().add("base-control-step");
        btnAddBaseBox.getStyleClass().addAll("base-control-button", "add-button");
        btnRemoveBaseBox.getStyleClass().addAll("base-control-button", "remove-button");
        getStyleClass().addAll("base-controls", "base-controls-top");
    }

    public ReadOnlyIntegerProperty baseProperty() {
        return baseProperty;
    }

    public ReadOnlyIntegerProperty baseBoxStepProperty() {
        return baseBoxStep;
    }

    public void setOnAddBaseBox(EventHandler<ActionEvent> event) {
        btnAddBaseBox.setOnAction(event);
    }

    public void setOnRemoveBaseBox(EventHandler<ActionEvent> event) {
        btnRemoveBaseBox.setOnAction(event);
    }
}
