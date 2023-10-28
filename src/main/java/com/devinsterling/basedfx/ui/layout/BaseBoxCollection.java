package com.devinsterling.basedfx.ui.layout;

import com.devinsterling.basedfx.consts.Default;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.math.BigInteger;

public class BaseBoxCollection extends HBox {
    /**  Ensures that there is always at least {@code N} BaseBoxes upon their removal. */
    private static final int MINIMUM_BOXES = 1;
    private final IntegerProperty baseProperty;
    private final ObjectProperty<BigInteger> sumProperty = new SimpleObjectProperty<>(BigInteger.ZERO);

    public BaseBoxCollection(int base) {
        super();

        baseProperty = new SimpleIntegerProperty(base);

        addListeners();
        build();
    }

    private void build() {
        addBaseBox(Default.BASE_FIELD_COUNT);
        getStyleClass().add("base-box-collection");
    }

    private void addListeners() {
        getChildren().addListener((ListChangeListener<? super Node>) observable -> calculateSum());
    }

    private void calculateSum() {
        BigInteger sum = BigInteger.ZERO;

        // All children are guaranteed to be a BaseBox
        for (Node node : getChildren()) {
            sum = sum.add(((BaseBox) node).calculationProperty().get());
        }

        sumProperty.set(sum);
    }

    public IntegerProperty baseProperty() {
        return baseProperty;
    }

    public ReadOnlyObjectProperty<BigInteger> sumProperty() {
        return sumProperty;
    }

    public void clear() {
        for (int i = 0; i < getChildren().size(); i++) {
            BaseBox baseBox = (BaseBox) getChildren().get(i);
            baseBox.valueProperty().set(0);
        }
    }

    public void setValues(int[] values) {
        if (values.length == 0) return;

        int size = getChildren().size();
        int difference = Math.abs(size - values.length);

        // If values has a length greater than the available base boxes, add the difference
        if (values.length > size) addBaseBox(difference);
        else if (values.length < size) removeBaseBox(difference);

        for (int i = 0; i < getChildren().size(); i++) {
            BaseBox baseBox = (BaseBox) getChildren().get(i);
            baseBox.valueProperty().set(values[i]);
        }
    }

    public void addBaseBox(int amount) {
        int size = getChildren().size();

        for (int i = 0; i < amount; i++) {
            BaseBox field = new BaseBox(baseProperty.get(), size + i);
            field.baseProperty().bind(baseProperty);
            field.calculationProperty().addListener(observable -> calculateSum());
            getChildren().addFirst(field);
        }
    }

    public void removeBaseBox(int amount) {
        amount = Math.min(amount, getChildren().size() - MINIMUM_BOXES);

        for (int i = 0; i < amount; i++) {
            getChildren().removeFirst();
        }
    }
}
