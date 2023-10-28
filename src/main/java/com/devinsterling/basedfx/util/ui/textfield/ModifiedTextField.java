package com.devinsterling.basedfx.util.ui.textfield;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;

/**
 * TextField with additional {@code highlighted} property to determine
 * if user has selected one or more characters in the TextField.
 * <br />
 * Similar to {@code focused}, where the property is set to false,
 * when the TextField loses focus.
 */
public class ModifiedTextField extends TextField {
    private final BooleanProperty highlightedProperty = new SimpleBooleanProperty(false);

    public ModifiedTextField() {
        this("");
    }

    public ModifiedTextField(String s) {
        super(s);

        setupListeners();
    }

    private void setupListeners() {
        selectionProperty().addListener(((observable, old, range) -> handleOnSelection(range)));
        focusedProperty().addListener(((observable, old, isFocused) -> handleOnFocus(isFocused)));
        highlightedProperty.addListener(((observableValue, old, isActive) -> handleOnHighlighted(isActive)));
    }

    private void handleOnSelection(IndexRange range) {
        // If a selection occurs, a user is actively editing the field
        if (range.getLength() > 0) highlightedProperty.set(true);
    }

    private void handleOnFocus(boolean isFocused) {
        if (!isFocused) highlightedProperty.set(false);
    }

    private void handleOnHighlighted(boolean isActive) {
        pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), isActive);
    }

    public boolean isHighlighted() {
        return highlightedProperty.get();
    }
}
