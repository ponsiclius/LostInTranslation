package translation;

import javax.swing.*;
import java.awt.event.*;

public class GUI {
    
    private static String getTranslation(JComboBox<String> languageComboBox, JComboBox<String> countryComboBox, Translator translator) {
        String language = languageComboBox.getSelectedItem().toString();
        String country = countryComboBox.getSelectedItem().toString();
        return translator.translate(country, language);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JPanel countryPanel = new JPanel();

            Translator translator = new JSONTranslator("sample.json");

            countryPanel.add(new JLabel("Country:"));
            final JComboBox<String> countryComboBox = new JComboBox<>();

            for(String countryCode : translator.getCountryCodes()) {
                countryComboBox.addItem(countryCode);
            }
            countryPanel.add(countryComboBox);

            JPanel languagePanel = new JPanel();
            languagePanel.add(new JLabel("Language:"));
            final JComboBox<String> languageComboBox = new JComboBox<>();

            for(String languageCode : translator.getLanguageCodes()) {
                languageComboBox.addItem(languageCode);
            }

            languagePanel.add(languageComboBox);
            
            String output = getTranslation(languageComboBox, countryComboBox, translator);

            JPanel buttonPanel = new JPanel();
            JLabel resultLabelText = new JLabel("Translation:");
            buttonPanel.add(resultLabelText);
            JLabel resultLabel = new JLabel(output);
            buttonPanel.add(resultLabel);

            countryComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String output = getTranslation(languageComboBox, countryComboBox, translator);
                        resultLabel.setText((output));
                    }
                }
            });

            languageComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String output = getTranslation(languageComboBox, countryComboBox, translator);
                        resultLabel.setText((output));
                    }
                }
            });

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(countryPanel);
            mainPanel.add(languagePanel);
            mainPanel.add(buttonPanel);

            JFrame frame = new JFrame("Country Name Translator");
            frame.setContentPane(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);


        });
    }
}
