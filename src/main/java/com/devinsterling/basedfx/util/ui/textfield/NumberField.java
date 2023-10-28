package com.devinsterling.basedfx.util.ui.textfield;

import com.devinsterling.basedfx.util.StringUtil;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.math.BigInteger;

public class NumberField extends ModifiedTextField {
    private final ObjectProperty<BigInteger> valueProperty;
    private final ObjectProperty<BigInteger> maxProperty = new SimpleObjectProperty<>(null);
    private final ObjectProperty<BigInteger> minProperty = new SimpleObjectProperty<>(null);

    public NumberField(long value, long min, long max) {
        this(BigInteger.valueOf(value), BigInteger.valueOf(min), BigInteger.valueOf(max));
    }

    public NumberField(BigInteger value, BigInteger min, BigInteger max) {
        super();

        valueProperty = new SimpleObjectProperty<>(min == null ? value : min);

        setMin(min);
        setMax(max);
        setText(formatValue(value));
        addListeners();
    }

    private void addListeners() {
        valueProperty.addListener((observable, old, newValue) -> validateValue(old, newValue));
        textProperty().addListener((observable, old, newInput) -> validateInput(newInput));
        focusedProperty().addListener(((observable, old, isFocused) -> handleOnFocus(isFocused)));
    }

    private void handleOnFocus(boolean isFocused) {
        if (isFocused) return;

        // On loss of focus, revert to last valid value
        setText(formatValue(valueProperty.get()));
    }

    private void validateValue(BigInteger oldValue, BigInteger newValue) {
        if (isWithinBounds(newValue)) setText(formatValue(newValue));
        else valueProperty.set(oldValue);
    }

    private void validateInput(String newInput) {
        String input = newInput.replace(",", "");

        if (StringUtil.isNumeric(input)) {
            BigInteger value = new BigInteger(input);

            if (isWithinBounds(value)) valueProperty.set(value);
        }
    }

    private boolean isWithinBounds(BigInteger value) {
        BigInteger min = minProperty.get();
        BigInteger max = maxProperty.get();

        return min == null && max == null ||
                min == null && value.compareTo(max) <= 0 ||
                max == null && value.compareTo(min) >= 0 ||
                min != null && max != null && value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
    }

    private String formatValue(BigInteger value) {
        return String.format("%,d", value);
    }

    public ObjectProperty<BigInteger> valueProperty() {
        return valueProperty;
    }

    /** @param max value must be greater than {@code min} or 0 */
    public void setMax(BigInteger max) {
        if (maxProperty.get() != null && minProperty.get() != null && max.compareTo(minProperty.get()) < 0) {
            throw new IllegalArgumentException(max + " < " + minProperty.get());
        }

        maxProperty.set(max);
    }

    /** @param min value must be less than {@code max} and greater than 0 */
    public void setMin(BigInteger min) {
        if (minProperty.get() != null && maxProperty.get() != null && min.compareTo(maxProperty.get()) > 0) {
            throw new IllegalArgumentException(min + " > " + minProperty.get());
        }

        minProperty.set(min);
    }
}
