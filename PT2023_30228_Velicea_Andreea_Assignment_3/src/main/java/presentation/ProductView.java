package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa care se ocupa de realizarea interfetei pentru operatii cu produse
 * @author Velicea Andreea - Ioana
 */
public class ProductView extends JFrame {
    private JPanel contentPane;
    private JLabel id_label;
    private JTextField id_textField;
    private JLabel name_label;
    private JTextField name_textField;
    private JLabel price_label;
    private JTextField price_textField;
    private JTable table;
    private JButton addProduct;
    private JButton deleteProduct;
    private JButton updateProduct;
    private JButton showTableProduct;
    private JButton backButton;
    private JLabel title;
    private JButton clear;
    private JTextField stoc_textField;
    private JLabel stoc_label;
    private JScrollPane jScrollPane;

    /**
     *  Constructoul clasei in care se adauga toate componentele necesare
     */

    public ProductView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 607, 408);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 223, 255));
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        id_label = new JLabel("Id");
        id_label.setForeground(new Color(255, 128, 255));
        id_label.setBackground(new Color(255, 223, 255));
        id_label.setHorizontalAlignment(SwingConstants.CENTER);
        id_label.setBounds(20, 39, 49, 14);
        contentPane.add(id_label);

        id_textField = new JTextField();
        id_textField.setBounds(101, 36, 96, 20);
        contentPane.add(id_textField);
        id_textField.setColumns(10);

        name_label = new JLabel("Name");
        name_label.setForeground(new Color(255, 128, 255));
        name_label.setHorizontalAlignment(SwingConstants.CENTER);
        name_label.setBounds(20, 91, 49, 14);
        contentPane.add(name_label);

        name_textField = new JTextField();
        name_textField.setBounds(101, 88, 96, 20);
        contentPane.add(name_textField);
        name_textField.setColumns(10);

        price_label = new JLabel("Price");
        price_label.setForeground(new Color(255, 128, 255));
        price_label.setHorizontalAlignment(SwingConstants.CENTER);
        price_label.setBounds(20, 147, 49, 14);
        contentPane.add(price_label);

        price_textField = new JTextField();
        price_textField.setBounds(101, 144, 49, 20);
        contentPane.add(price_textField);
        price_textField.setColumns(10);

        stoc_label = new JLabel("Stoc");
        stoc_label.setForeground(new Color(255, 128, 255));
        stoc_label.setBackground(new Color(255, 223, 255));
        stoc_label.setHorizontalAlignment(SwingConstants.CENTER);
        stoc_label.setBounds(20, 186, 49, 14);
        contentPane.add(stoc_label);

        stoc_textField = new JTextField();
        stoc_textField.setBounds(101, 183, 49, 20);
        contentPane.add(stoc_textField);
        stoc_textField.setColumns(10);

        addProduct = new JButton("ADD");
        addProduct.setForeground(new Color(255, 128, 255));
        addProduct.setBackground(new Color(255, 223, 255));
        addProduct.setBounds(20, 227, 96, 23);
        contentPane.add(addProduct);

        deleteProduct = new JButton("DELETE");
        deleteProduct.setForeground(new Color(255, 128, 255));
        deleteProduct.setBackground(new Color(255, 223, 255));
        deleteProduct.setBounds(20, 290, 96, 23);
        contentPane.add(deleteProduct);

        updateProduct = new JButton("UPDATE");
        updateProduct.setForeground(new Color(255, 128, 255));
        updateProduct.setBackground(new Color(255, 223, 255));
        updateProduct.setBounds(127, 255, 89, 23);
        contentPane.add(updateProduct);


        table = new JTable();
        this.jScrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.jScrollPane.setBounds(315, 39, 233, 225);
        this.jScrollPane.setViewportView(this.table);
        this.getContentPane().add(this.jScrollPane);

        showTableProduct = new JButton("SHOW TABLE");
        showTableProduct.setForeground(new Color(255, 128, 255));
        showTableProduct.setBackground(new Color(255, 223, 255));
        showTableProduct.setBounds(354, 290, 152, 28);
        contentPane.add(showTableProduct);

        backButton = new JButton("<-");
        backButton.setBackground(new Color(255, 223, 255));
        backButton.setForeground(new Color(255, 128, 255));
        backButton.setBounds(0, 5, 59, 23);
        contentPane.add(backButton);

        title = new JLabel("PRODUCT");
        title.setFont(new Font("Tahoma", Font.PLAIN, 16));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(255, 223, 255));
        title.setForeground(new Color(255, 128, 255));
        title.setBounds(219, -6, 105, 44);
        contentPane.add(title);

        clear = new JButton("CLEAR");
        clear.setBackground(new Color(255, 223, 255));
        clear.setForeground(new Color(255, 128, 255));
        clear.setBounds(191, 182, 89, 23);
        contentPane.add(clear);
    }

    /**
     * Getter ul pentru id-ul introdus de utilizator
     * @return id-ul introdus de utilizator
     */
    public int getIdProduct() {
        try {
            return Integer.parseInt(id_textField.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"id ul trebuie sa fie int");
        }
        return 0;
    }

    /**
     * Getter ul pentru name introdus de utilizator
     * @return name introdus de utilizator
     */
    public String getNameProduct() {
        return name_textField.getText();
    }

    /**
     * Getter ul pentru price introdus de utilizator
     * @return price introdus de utilizator
     */
    public Float getPriceProduct() {
        return Float.parseFloat(price_textField.getText());
    }

    /**
     * Getter ul pentru stoc introdus de utilizator
     * @return stoc introdus de utilizator
     */
    public int getStocProduct(){ return Integer.parseInt(stoc_textField.getText());}

    /**
     * Getter ul pentru tabel
     * @return tabel
     */

    public JTable getTable() {
        return table;
    }
    /**
     * Metoda care elibereaza casetele pentru reintroducerea altor date
     */

    public void clear() {
        id_textField.setText("");
        name_textField.setText("");
        price_textField.setText("");
        stoc_textField.setText("");
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de ADD
     * @param actionListener
     */
    public void addProductListener(ActionListener actionListener) {
        this.addProduct.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de DELETE
     * @param actionListener
     */
    public void deleteProductListener(ActionListener actionListener) {
        this.deleteProduct.addActionListener(actionListener);
    }
    /**
     * Metoda carea adauga actionListener pentru butonul de UPDATE
     * @param actionListener
     */

    public void updateProductListener(ActionListener actionListener) {
        this.updateProduct.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de SHOW TABLE
     * @param actionListener
     */
    public void showProductTableListener(ActionListener actionListener) {
        this.showTableProduct.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de BACK
     * @param actionListener
     */
    public void backListener(ActionListener actionListener) {
        this.backButton.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de CLEAR
     * @param actionListener
     */
    public void clearListener(ActionListener actionListener) {
        this.clear.addActionListener(actionListener);
    }

}
