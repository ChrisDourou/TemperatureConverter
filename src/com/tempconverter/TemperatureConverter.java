package com.tempconverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TemperatureConverter {
    public static void main(String[] args) {
        // Δημιουργία του frame
        JFrame frame = new JFrame("Μετατροπή Θερμοκρασίας");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 250);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        // Δημιουργία στοιχείων UI
        JLabel inputLabel = new JLabel("Εισαγωγή θερμοκρασίας:");
        JTextField inputField = new JTextField();
        
        JLabel outputLabel = new JLabel("Αποτέλεσμα:");
        JTextField outputField = new JTextField();
        outputField.setEditable(false); // Μόνο για ανάγνωση
        
        JLabel conversionLabel = new JLabel("Επιλογή μετατροπής:");
        
        // Ταξινομημένη λίστα μετατροπών
        String[] options = {
            "Celsius → Fahrenheit", 
            "Celsius → Kelvin",
            "Fahrenheit → Celsius", 
            "Fahrenheit → Kelvin", 
            "Kelvin → Celsius", 
            "Kelvin → Fahrenheit"
        };
        
        JComboBox<String> conversionBox = new JComboBox<>(options);

        JButton convertButton = new JButton("Μετατροπή");

        // Action Listener για μετατροπή
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedConversion = (String) conversionBox.getSelectedItem();
                    double inputTemp = Double.parseDouble(inputField.getText().replace(",", "."));
                    double result = 0;

                    // Επιλογές μετατροπής
                    switch (selectedConversion) {
                        case "Celsius → Fahrenheit":
                            result = (inputTemp * 9 / 5) + 32;
                            break;
                        case "Celsius → Kelvin":
                            result = inputTemp + 273.15;
                            break;
                        case "Fahrenheit → Celsius":
                            result = (inputTemp - 32) * 5 / 9;
                            break;
                        case "Fahrenheit → Kelvin":
                            result = (inputTemp - 32) * 5 / 9 + 273.15;
                            break;
                        case "Kelvin → Celsius":
                            result = inputTemp - 273.15;
                            break;
                        case "Kelvin → Fahrenheit":
                            result = (inputTemp - 273.15) * 9 / 5 + 32;
                            break;
                    }

                    // Μορφοποίηση αποτελέσματος
                    DecimalFormat df = new DecimalFormat("#.##");
                    outputField.setText(df.format(result));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Παρακαλώ εισάγετε έγκυρο αριθμό!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Προσθήκη στοιχείων στο frame
        frame.add(inputLabel);
        frame.add(inputField);
        frame.add(conversionLabel);
        frame.add(conversionBox);
        frame.add(outputLabel);
        frame.add(outputField);
        frame.add(new JLabel()); // Κενό για διάταξη
        frame.add(convertButton);

        // Εμφάνιση παραθύρου
        frame.setVisible(true);
    }
}
