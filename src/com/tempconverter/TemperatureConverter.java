package com.tempconverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter {
    public static void main(String[] args) {
        // Ενεργοποίηση του Look and Feel του συστήματος
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Δημιουργία του frame
        JFrame frame = new JFrame("Μετατροπή Θερμοκρασίας");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new BorderLayout(10, 10)); // Αφήνει κενά μεταξύ των στοιχείων

        // Δημιουργία panel για τα στοιχεία με padding
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Εσωτερικό padding

        // Δημιουργία ετικετών, πεδίων, κουμπιού και επιλογών
        JLabel inputLabel = new JLabel("Εισαγωγή Θερμοκρασίας:");
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField inputField = new JTextField();
        JLabel resultLabel = new JLabel("Αποτέλεσμα:");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField resultField = new JTextField();
        resultField.setEditable(false); // Μόνο για ανάγνωση
        JLabel typeLabel = new JLabel("Επιλέξτε μετατροπή:");
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] conversionTypes = {"Fahrenheit σε Celsius", "Celsius σε Fahrenheit"};
        JComboBox<String> conversionBox = new JComboBox<>(conversionTypes);
        JButton convertButton = new JButton("Μετατροπή");

        // Προσθήκη action listener στο κουμπί
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Αντικατάσταση κόμματος με τελεία για σωστή μετατροπή αριθμού
                    String inputText = inputField.getText().replace(",", ".");
                    double inputTemp = Double.parseDouble(inputText);
                    double resultTemp;
                    String conversionType = (String) conversionBox.getSelectedItem();

                    // Έλεγχος τύπου μετατροπής
                    if ("Fahrenheit σε Celsius".equals(conversionType)) {
                        resultTemp = (inputTemp - 32) * 5 / 9;
                    } else { // Celsius σε Fahrenheit
                        resultTemp = (inputTemp * 9 / 5) + 32;
                    }

                    resultField.setText(String.format("%.2f", resultTemp));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Παρακαλώ εισάγετε έναν έγκυρο αριθμό!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Προσθήκη στοιχείων στο panel
        mainPanel.add(typeLabel);
        mainPanel.add(conversionBox);
        mainPanel.add(inputLabel);
        mainPanel.add(inputField);
        mainPanel.add(resultLabel);
        mainPanel.add(resultField);
        mainPanel.add(new JLabel()); // Κενό για καλύτερη διάταξη
        mainPanel.add(convertButton);

        // Προσθήκη panel στο frame
        frame.add(mainPanel, BorderLayout.CENTER);

        // Εμφάνιση παραθύρου
        frame.setVisible(true);
    }
}
