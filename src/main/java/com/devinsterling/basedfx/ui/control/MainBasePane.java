package com.devinsterling.basedfx.ui.control;

import com.devinsterling.basedfx.util.Context;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainBasePane extends BorderPane {
    private final Context context;
    private final VBox containers = new VBox();

    public MainBasePane(Context context) {
        super();

        this.context = context;

        build();
    }

    private void build() {
        ScrollPane scrollPane = new ScrollPane();
        Button addBaseContainer = new Button("╋");

        // Actions
        addBaseContainer.setOnAction(actionEvent -> addBaseContainer());

        // Listeners
        addBaseContainer.prefHeightProperty().bind(scrollPane.heightProperty());
        containers.getChildren().addListener((ListChangeListener<? super Node>) observable -> {
            if (containers.getChildren().size() == 1) addBaseContainer();
        });

        // Children
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(containers);
        containers.getChildren().add(addBaseContainer);
        setCenter(scrollPane);
        setBottom(new DeveloperInfo(context));

        addBaseContainer.getStyleClass().add("add-base-container-button");
    }

    public void addBaseContainer() {
        int index = Math.max(0, containers.getChildren().size() - 1);
        containers.getChildren().add(index, new BaseContainer());
    }
}
