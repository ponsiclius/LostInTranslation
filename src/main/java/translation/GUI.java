package translation;

import javax.swing.*;
import java.awt.event.*;


// TODO Task D: Update the GUI for the program to align with UI shown in the README example.
//            Currently, the program only uses the CanadaTranslator and the user has
//            to manually enter the language code they want to use for the translation.
//            See the examples package for some code snippets that may be useful when updating
//            the GUI.
public class GUI {

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

            JPanel buttonPanel = new JPanel();

            JLabel resultLabelText = new JLabel("Translation:");
            buttonPanel.add(resultLabelText);
            JLabel resultLabel = new JLabel("");
            buttonPanel.add(resultLabel);

            // TODO: automatically change the output based on what dropdown is selected
            // fetch the current language / country codes
            // input this into translation function in JSONTranslator
            // change the output of the resultLabel

            countryComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String language = languageComboBox.getSelectedItem().toString();
                        String country = countryComboBox.getSelectedItem().toString();
                        String output = translator.translate(language, country);
                        resultLabel.setText((output));
                    }
                }
            });

            languageComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String language = languageComboBox.getSelectedItem().toString();
                        String country = countryComboBox.getSelectedItem().toString();
                        String output = translator.translate(language, country);
                        resultLabel.setText((output));
//                      JOptionPane.showMessageDialog(null, "user selected " + country + "!");
                    }
                }
            });



            // adding listener for when the user clicks the submit button
//            submit.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String language = languageField.getText();
//                    String country = countryField.getText();
//
//                    // for now, just using our simple translator, but
//                    // we'll need to use the real JSON version later.
//
//                    String result = translator.translate(country, language);
//                    if (result == null) {
//                        result = "no translation found!";
//                    }
//                    resultLabel.setText(result);
//
//                }
//
//            });

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
