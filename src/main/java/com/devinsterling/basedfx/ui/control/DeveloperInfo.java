package com.devinsterling.basedfx.ui.control;

import com.devinsterling.basedfx.util.Context;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class DeveloperInfo extends HBox {
    private static final String WEBSITE = "devinsterling.com";
    private final Context context;

    public DeveloperInfo(Context context) {
        super();

        this.context = context;

        build();
    }

    private void build() {
        HBox author = new HBox(new Label("Made by Devin Sterling"));
        Hyperlink site = new Hyperlink(WEBSITE);

        // Actions
        site.setOnAction(actionEvent -> context.getHostServices().showDocument("https://" + WEBSITE));

        // Children
        setHgrow(author, Priority.ALWAYS);
        getChildren().addAll(author, site);

        // Style
        getStyleClass().add("developer-info");
    }
}
