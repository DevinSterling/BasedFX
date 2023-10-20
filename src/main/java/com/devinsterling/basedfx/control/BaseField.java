package com.devinsterling.basedfx.control;

import com.devinsterling.basedfx.control.textfield.ModifiedTextField;
import com.devinsterling.basedfx.util.StringUtil;

import javafx.beans.property.*;
import javafx.scene.input.KeyCode;

public class BaseField extends ModifiedTextField {
    private final IntegerProperty baseProperty;
    private final IntegerProperty valueProperty = new SimpleIntegerProperty(0);

    public BaseField(int base) {
        super("0");

        baseProperty = new SimpleIntegerProperty(base);

        getStyleClass().add("base-field");
        setupListeners();
        setupActions();
    }

    private void setupListeners() {
        // Listener for input conversion
        valueProperty.addListener(((observableValue, old, newValue) -> validateValue(old.intValue(), newValue.intValue())));
        textProperty().addListener(((observable, old, newInput) -> validateInput(true, old, newInput)));
        focusedProperty().addListener((((observable, old, isFocused) -> handleOnFocus(isFocused))));
    }

    private void setupActions() {
        setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown()) setOnMouseClicked(mouseEvent2 -> {
                boolean isDragged = Math.abs(mouseEvent.getX()- mouseEvent2.getX()) > 10;

                if (mouseEvent.getClickCount() == 1 && !isDragged && !isActive()) {
                    getParent().requestFocus();
                    setText(String.valueOf((valueProperty.get() + 1) % baseProperty.get()));
                }
            });
        });
    }

    private void handleOnFocus(boolean isFocused) {
        if (!isFocused) {
            validateInput(false, "0", getText());
            setStyle("");
            return;
        }

        // Retrieve text before edits in case user cancels.
        String inputBeforeChange = getText();

        setStyle("-fx-cursor: text");
        setOnKeyPressed(keyEvent -> {
            KeyCode code = keyEvent.getCode();
            if (code == KeyCode.ENTER || code == KeyCode.ESCAPE) {
                if (code == KeyCode.ESCAPE) setText(inputBeforeChange); // Undo changes
                getParent().requestFocus();
            }
        });
    }

    private void validateValue(int oldValue, int newValue) {
        setText(String.valueOf(newValue >= 0 && newValue < baseProperty.get() ? newValue : oldValue));
    }

    private void validateInput(boolean allowEmpty, String oldInput, String newInput) {
        boolean isNumeric = StringUtil.isNumeric(newInput);
        int value = allowEmpty && newInput.isEmpty() ? 0 : -1;

        // Retrieve value
        if (isNumeric) value = Integer.parseInt(newInput);
        else if (newInput.length() == 1) value = Character.toUpperCase(newInput.charAt(0)) - 55;

        // Determine if input is valid
        boolean isInvalidInput = !(value >= 0 && value < baseProperty.get());

        if (isInvalidInput) setText(oldInput);
        // (Using ASCII) Convert numbers greater than 9 into 'A','E',etc.
        else if (isNumeric && value > 9) setText(String.valueOf((char) (value + 55)));
        // Convert uncapitalized char to capitalized
        else if (!newInput.isEmpty() && newInput.charAt(0) > 96) setText(newInput.toUpperCase());

        if (!isInvalidInput) valueProperty.set(value);
    }

    public IntegerProperty baseProperty() {
        return baseProperty;
    }

    public IntegerProperty valueProperty() {
        return valueProperty;
    }
}
