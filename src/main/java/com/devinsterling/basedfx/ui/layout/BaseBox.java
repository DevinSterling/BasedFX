package com.devinsterling.basedfx.ui.layout;

import com.devinsterling.basedfx.ui.control.BaseField;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.math.BigInteger;

public class BaseBox extends VBox {
    private final IntegerProperty exponentProperty;
    private final ObjectProperty<BigInteger> calculationProperty = new SimpleObjectProperty<>(BigInteger.ZERO);
    private final BaseField baseField;

    public BaseBox(int base, int exponent) {
        super();

        exponentProperty = new SimpleIntegerProperty(exponent);
        baseField = new BaseField(base);

        addListeners();
        build();
    }

    private void build() {
        Label lblCalculation = new Label();
        Label lblBaseExponent = new Label();
        lblCalculation.textProperty().bind(calculationProperty().map(s -> String.format("%,d", s)));
        lblBaseExponent.textProperty().bind(baseField.baseProperty().asString().concat("^").concat(exponentProperty));

        lblCalculation.getStyleClass().add("field-calculation");
        lblBaseExponent.getStyleClass().add("field-base-exponent");
        getStyleClass().add("base-box");

        getChildren().addAll(lblCalculation, baseField, lblBaseExponent);
    }

    private void addListeners() {
        baseField.baseProperty().addListener(observable -> updateCalculation());
        baseField.valueProperty().addListener(observable -> updateCalculation());
        exponentProperty.addListener(observable -> updateCalculation());
    }

    private void updateCalculation() {
        calculationProperty.set(
            BigInteger
                .valueOf(baseField.baseProperty().get())
                .pow(exponentProperty.get())
                .multiply(BigInteger.valueOf(baseField.valueProperty().get()))
        );
    }

    public IntegerProperty baseProperty() {
        return baseField.baseProperty();
    }

    public IntegerProperty valueProperty() {
        return baseField.valueProperty();
    }

    public ReadOnlyObjectProperty<BigInteger> calculationProperty() {
        return calculationProperty;
    }
}
