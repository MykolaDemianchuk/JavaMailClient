package com.demianchuk.views;

import javax.swing.*;
import java.awt.*;

public class DialogBox {
    private DialogBox(){}

    public static void displayInfoMessage(Component component, String message) {
        JOptionPane.showMessageDialog(component, message);
    }

    public static void displayErrorMessage(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
