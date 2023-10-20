package com.devinsterling.basedfx;

import com.devinsterling.basedfx.control.MainBasePane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BasedFX extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new MainBasePane(), 420, 240);
        scene.getStylesheets().add(BasedFX.class.getResource("/css/binaryfx.css").toExternalForm());
        stage.setTitle("BasedFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}