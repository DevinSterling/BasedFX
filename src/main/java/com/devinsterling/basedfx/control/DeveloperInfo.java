package com.devinsterling.basedfx.control;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class DeveloperInfo extends HBox {
    public DeveloperInfo() {
        super();

        build();
    }

    private void build() {
        HBox author = new HBox(new Label("Made by Devin Sterling"));
        Hyperlink site = new Hyperlink("devinsterling.com");

        site.setOnAction(actionEvent -> {
            //HostServices services = Application.getInstance();
        });

        setHgrow(author, Priority.ALWAYS);
        getChildren().addAll(author, site);

        getStyleClass().add("developer-info");
    }
}
