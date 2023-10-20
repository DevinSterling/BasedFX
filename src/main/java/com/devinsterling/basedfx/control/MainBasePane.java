package com.devinsterling.basedfx.control;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainBasePane extends BorderPane {
    private final VBox containers = new VBox();

    public MainBasePane() {
        super();

        build();
    }

    private void build() {
        ScrollPane scrollPane = new ScrollPane();
        Button addBaseContainer = new Button("Add");

        // Actions
        addBaseContainer.setOnAction(actionEvent -> addBaseContainer());

        // Listeners
        containers.getChildren().addListener((ListChangeListener<? super Node>) observable -> {
            if (containers.getChildren().size() == 1) addBaseContainer();
        });

        // Children
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(containers);
        containers.getChildren().add(addBaseContainer);
        setCenter(scrollPane);
        setBottom(new DeveloperInfo());

        addBaseContainer.getStyleClass().add("add-base-container-button");
    }

    public void addBaseContainer() {
        int index = Math.max(0, containers.getChildren().size() - 1);
        containers.getChildren().add(index, new BaseContainer());
    }
}
