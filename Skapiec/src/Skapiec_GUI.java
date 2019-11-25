import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

public class Skapiec_GUI extends javax.swing.JFrame {

    // +++++++++++++These variables used only in the Skapiec GUI-interface+++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private JTextField textField;
    private JButton searchButton;
    private JTextArea productQuantityText;
    private JSpinner productQuantitySpinner;
    private JTextArea minValueText;
    private JSpinner minValueSpinner;
    private JTextArea maxValueText;
    private JSpinner maxValueSpinner;
    private JTextArea sellersRatingText;
    private JComboBox sellersRatingComboBox;
    private JSeparator horizontalSeparator;
    private JSeparator verticalSeparator;
    int smallSpace = 30;
    int bigSpace = 100;
    int productQuantity = 0;
    int minPrice = 0;
    int maxPrice = 0;
    double sellersRating = 0;

    Scrapper scrapper;

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Skapiec_GUI inst = new Skapiec_GUI();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Skapiec_GUI() {
        super();
        initGUI();
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void initGUI() {

        getContentPane().setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Skapiec GUI");

        // ++++++++++++++++++++++++++++++++++ Text Field ++++++++++++++++++++++++++++++++++++++++++++++++++++
        textField = new JTextField();
        getContentPane().add(textField);
        textField.setBounds(250, 20, 1000, 50);
        textField.setFont(new Font("", Font.BOLD, 24));


        // ++++++++++++++++++++++++++++++++++ Search Button++++++++++++++++++++++++++++++++++++++++++++++++++++
        searchButton = new JButton("Wyszukaj");
        getContentPane().add(searchButton);
        searchButton.setBounds(textField.getWidth() + textField.getX() + 50, 20, 150, 50);
        searchButton.setFont(new Font("", Font.BOLD, 20));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    scrapper = new Scrapper(textField.getText());
                    System.out.println(scrapper.getUrls());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                if ((double)sellersRatingComboBox.getSelectedItem() <= scrapper.getRatings()){


                }
                else{
                    JFrame frame = new JFrame("");
                    JOptionPane.showMessageDialog(frame, "Zmień kryteria wyszukiwania!");
                    frame.setFont(new Font("", Font.BOLD, 20));
                    System.out.println(scrapper.getRatings());
                }

                /*JFrame frame = new JFrame("");
                JOptionPane.showMessageDialog(frame, productName);*/
            }
        });

        // ++++++++++++++++++++++++++++++++++ Product Quantity ++++++++++++++++++++++++++++++++++++++++++++++++++++

        productQuantityText = new JTextArea("Ilość produktów:");
        getContentPane().add(productQuantityText);
        productQuantityText.setBounds(30, 120, 200, 40);
        productQuantityText.setEditable(false);
        productQuantityText.setOpaque(false);
        productQuantityText.setFont(new Font("", Font.BOLD, 20));

        productQuantitySpinner = new JSpinner();
        getContentPane().add(productQuantitySpinner);
        productQuantitySpinner.setBounds(30, productQuantityText.getY() + smallSpace, 100, 40);
        productQuantitySpinner.setFont(new Font("", Font.BOLD, 20));
        productQuantitySpinner.setModel(new SpinnerNumberModel(0, 0, 100000, 1));

        productQuantitySpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                productQuantity = (Integer) productQuantitySpinner.getValue();
            }
        });



        // ++++++++++++++++++++++++++++++++++ Price Range ++++++++++++++++++++++++++++++++++++++++++++++++++++

        minValueText = new JTextArea("Cena minimalna:");
        getContentPane().add(minValueText);
        minValueText.setBounds(30, productQuantitySpinner.getY() + bigSpace, 200, 40);
        minValueText.setEditable(false);
        minValueText.setOpaque(false);
        minValueText.setFont(new Font("", Font.BOLD, 20));

        minValueSpinner = new JSpinner();
        getContentPane().add(minValueSpinner);
        minValueSpinner.setBounds(30, minValueText.getY() + smallSpace, 100, 40);
        minValueSpinner.setFont(new Font("", Font.BOLD, 20));
        minValueSpinner.setModel(new SpinnerNumberModel(0, 0, 10000, 1));

        minValueSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                minPrice = (Integer) minValueSpinner.getValue();
            }
        });


        maxValueText = new JTextArea("Cena maksymalna:");
        getContentPane().add(maxValueText);
        maxValueText.setBounds(30, minValueSpinner.getY() + bigSpace, 200, 40);
        maxValueText.setEditable(false);
        maxValueText.setOpaque(false);
        maxValueText.setFont(new Font("", Font.BOLD, 20));

        maxValueSpinner = new JSpinner();
        getContentPane().add(maxValueSpinner);
        maxValueSpinner.setBounds(30, maxValueText.getY() + smallSpace, 100, 40);
        maxValueSpinner.setFont(new Font("", Font.BOLD, 20));
        maxValueSpinner.setModel(new SpinnerNumberModel(0, 0, 10000, 1));

        maxValueSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                maxPrice = (Integer) maxValueSpinner.getValue();
            }
        });


        // ++++++++++++++++++++++++++++++++++ Seller Rating ++++++++++++++++++++++++++++++++++++++++++++++++++++

        sellersRatingText = new JTextArea("Minimalna \nocena sprzedawcy:");
        getContentPane().add(sellersRatingText);
        sellersRatingText.setBounds(30, maxValueSpinner.getY() + bigSpace, 200, 60);
        sellersRatingText.setEditable(false);
        sellersRatingText.setOpaque(false);
        sellersRatingText.setFont(new Font("", Font.BOLD, 20));

        Double[] ratings = {4.0, 4.25, 4.5, 4.75};
        sellersRatingComboBox = new JComboBox(ratings);
        getContentPane().add(sellersRatingComboBox);
        sellersRatingComboBox.setBounds(30, sellersRatingText.getY() + smallSpace + smallSpace, 100, 40);
        sellersRatingComboBox.setFont(new Font("", Font.BOLD, 20));

        sellersRatingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellersRating = (double) sellersRatingComboBox.getSelectedItem();
            }
        });



        // ++++++++++++++++++++++++++++++++++ Set up Separator horizontally +++++++++++++++++++++++++++++++++++++++++++++++++++

        horizontalSeparator = new JSeparator();
        getContentPane().add(horizontalSeparator);
        horizontalSeparator.setBackground(Color.BLACK);
        horizontalSeparator.setBounds(0, textField.getY() + 70, 1920, 1);

        // ++++++++++++++++++++++++++++++++++ Set up Separator vertically +++++++++++++++++++++++++++++++++++++++++++++++++++

        verticalSeparator = new JSeparator();
        getContentPane().add(verticalSeparator);
        verticalSeparator.setOrientation(1);
        verticalSeparator.setBackground(Color.BLACK);
        verticalSeparator.setBounds(textField.getX() - 30, 0, 1, 1080);



        pack();
        this.setSize(1920, 1080);
        this.setResizable(false);
    }
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}