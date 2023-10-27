package com.devinsterling.basedfx.ui.control;

import com.devinsterling.basedfx.consts.Default;
import com.devinsterling.basedfx.converter.BaseConverter;
import com.devinsterling.basedfx.ui.layout.BaseBoxCollection;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class BaseContainer extends BorderPane {
    private final BaseConverter baseConverter = new BaseConverter();

    public BaseContainer() {
        build();
    }

    public void build() {
        // Create button to easily add more base boxes to collection
        Button btnAddBaseBox = new Button("+");
        BaseBoxCollection collection = new BaseBoxCollection(Default.BASE);
        // Container for button and collection
        HBox baseBoxes = new HBox(btnAddBaseBox, collection);
        // In case of overflow by base boxes, show a scrollbar
        ScrollPane scrollPane = new ScrollPane(baseBoxes);

        // Primary Controls
        BaseControlsTop controlsTop = new BaseControlsTop();
        BaseControlsBottom controlsBottom = new BaseControlsBottom();

        // Actions
        btnAddBaseBox.setOnAction(actionEvent -> collection.addBaseBox(1));
        controlsBottom.setOnClearBoxes(actionEvent -> collection.clear());
        controlsBottom.setOnClose(actionEvent -> ((Pane) getParent()).getChildren().remove(this));
        controlsTop.setOnAddBaseBox(actionEvent -> collection.addBaseBox(controlsTop.baseBoxStepProperty().get()));
        controlsTop.setOnRemoveBaseBox(actionEvent -> collection.removeBaseBox(controlsTop.baseBoxStepProperty().get()));

        // Listeners
        baseBoxes.minWidthProperty().bind(scrollPane.widthProperty().subtract(2));
        controlsBottom.resultProperty().bind(collection.sumProperty().map(s -> String.format("%,d", s)));
        controlsTop.baseProperty().addListener((observable, old, newBase) -> {
            int[] values = baseConverter.convert(collection.sumProperty().get(), newBase.intValue());
            collection.baseProperty().set(newBase.intValue());
            collection.setValues(values);
        });

        // Children
        setTop(controlsTop);
        setCenter(scrollPane);
        setBottom(controlsBottom);

        btnAddBaseBox.getStyleClass().add("base-box-add-button");
        baseBoxes.getStyleClass().add("boxes");
        getStyleClass().add("base-container");
    }
}
