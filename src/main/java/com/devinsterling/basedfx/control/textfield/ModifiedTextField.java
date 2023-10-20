package com.devinsterling.basedfx.control.textfield;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;

/**
 * TextField with additional active property to determine if
 * user is actively modifying the TextField.
 * Actively modifying as in selecting text with mouse and/or
 * typing with keyboard.
 */
public class ModifiedTextField extends TextField {
    private final BooleanProperty activeProperty = new SimpleBooleanProperty(false);

    public ModifiedTextField(String s) {
        super(s);

        setupListeners();
    }

    private void setupListeners() {
        selectionProperty().addListener(((observable, old, range) -> handleOnSelection(range)));
        focusedProperty().addListener(((observable, old, isFocused) -> handleOnFocus(isFocused)));
    }

    private void handleOnSelection(IndexRange range) {
        // If a selection occurs, a user is actively editing the field
        if (range.getLength() > 0) activeProperty.set(true);
    }

    private void handleOnFocus(boolean isFocused) {
        if (!isFocused) activeProperty.set(false);
    }

    public boolean isActive() {
        return activeProperty.get();
    }
}
