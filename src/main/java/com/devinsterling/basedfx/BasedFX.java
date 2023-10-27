package com.devinsterling.basedfx;

import com.devinsterling.basedfx.ui.control.MainBasePane;
import com.devinsterling.basedfx.util.Context;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class BasedFX extends Application {
    @Override
    public void start(Stage stage) {
        Context context = new Context(getHostServices());

        Scene scene = new Scene(new MainBasePane(context), 415, 250);
        scene.getStylesheets().add(Objects.requireNonNull(BasedFX.class.getResource("binaryfx.css")).toString());
        stage.setTitle("BasedFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}