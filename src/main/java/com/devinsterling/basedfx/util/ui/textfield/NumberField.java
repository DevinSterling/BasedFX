package com.devinsterling.basedfx.util.ui.textfield;

import com.devinsterling.basedfx.util.StringUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class NumberField extends ModifiedTextField {
    private final IntegerProperty maxProperty = new SimpleIntegerProperty(Integer.MAX_VALUE);
    private final IntegerProperty minProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty valueProperty;

    public NumberField(String s, int min, int max) {
        super(s);

        setMin(min);
        setMax(max);
        valueProperty = new SimpleIntegerProperty(min);

        validateInput(s);
        addListeners();
    }

    private void addListeners() {
        textProperty().addListener((observableValue, old, newInput) -> validateInput(newInput));
        focusedProperty().addListener(((observableValue, old, isFocused) -> handleOnFocus(isFocused)));
    }

    private void handleOnFocus(boolean isFocused) {
        if (isFocused) return;
        setText(String.valueOf(getText().isEmpty() ? minProperty.get() : valueProperty.get()));
    }

    private void validateInput(String newInput) {
        boolean isValidInput = false;
        int value = minProperty.get();

        if (StringUtil.isNumeric(newInput)) {
            value = Integer.parseInt(newInput);
            isValidInput = value >= minProperty.get() && value <= maxProperty.get();
        }

        if (isValidInput) valueProperty.set(value);
    }

    public ReadOnlyIntegerProperty valueProperty() {
        return valueProperty;
    }

    /** @param max value must be greater than {@code min} or 0 */
    public void setMax(int max) {
        if (max < minProperty.get()) throw new IllegalArgumentException(max + " < " + minProperty.get());
        maxProperty.set(max);
    }

    /** @param min value must be less than {@code max} and greater than 0 */
    public void setMin(int min) {
        min = Math.max(0, min);

        if (min > maxProperty.get()) throw new IllegalArgumentException(min + " > " + maxProperty.get());
        minProperty.set(min);
    }
}
