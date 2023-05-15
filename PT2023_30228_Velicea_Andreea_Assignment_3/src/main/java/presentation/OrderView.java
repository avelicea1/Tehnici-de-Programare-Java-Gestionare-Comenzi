package presentation;

import connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clasa care se ocupa de realizarea interfetei pentru operatii cu comenzi
 * @author Velicea Andreea - Ioana
 */
public class OrderView extends JFrame {
    private JPanel contentPane;
    private JLabel id_label;
    private JTextField id_textField;
    private JLabel clientId_label;
    private JComboBox clientId_combo;
    private JLabel productId_label;
    private JComboBox productId_combo;
    private JLabel quantity_label;
    private JTextField quantity_textField;
    private JTable table;
    private JLabel title;
    private JButton addOrder;
    private JButton back;
    private JButton deleteOrder;
    private JButton updateOrder;
    private JButton showOrderTable;

    private JButton clear;
    private JScrollPane jScrollPane;

    /**
     *  Constructoul clasei in care se adauga toate componentele necesare
     */
    public OrderView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 607, 408);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 223, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        id_label = new JLabel("Order id");
        id_label.setForeground(new Color(255, 128, 255));
        id_label.setHorizontalAlignment(SwingConstants.CENTER);
        id_label.setBounds(22, 35, 69, 14);
        contentPane.add(id_label);

        id_textField = new JTextField();
        id_textField.setBounds(101, 32, 41, 20);
        contentPane.add(id_textField);
        id_textField.setColumns(10);

        clientId_label = new JLabel("Client id");
        clientId_label.setHorizontalAlignment(SwingConstants.CENTER);
        clientId_label.setForeground(new Color(255, 128, 255));
        clientId_label.setBounds(22, 67, 69, 14);
        contentPane.add(clientId_label);


        clientId_combo = new JComboBox();
        clientId_combo.setBackground(new Color(255, 223, 255));
        clientId_combo.setBounds(101, 63, 60, 18);
        contentPane.add(clientId_combo);

        productId_label = new JLabel("Product id");
        productId_label.setForeground(new Color(255, 128, 255));
        productId_label.setHorizontalAlignment(SwingConstants.CENTER);
        productId_label.setBounds(22, 104, 69, 14);
        contentPane.add(productId_label);

        productId_combo = new JComboBox();
        productId_combo.setBackground(new Color(255, 223, 255));
        productId_combo.setBounds(101, 96, 60, 22);
        contentPane.add(productId_combo);

        quantity_label = new JLabel("Quantity");
        quantity_label.setForeground(new Color(255, 128, 255));
        quantity_label.setHorizontalAlignment(SwingConstants.CENTER);
        quantity_label.setBounds(22, 146, 69, 14);
        contentPane.add(quantity_label);

        quantity_textField = new JTextField();
        quantity_textField.setBounds(101, 143, 41, 20);
        contentPane.add(quantity_textField);
        quantity_textField.setColumns(10);

        title = new JLabel("ORDER");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.PLAIN, 16));
        title.setForeground(new Color(255, 128, 255));
        title.setBounds(198, 11, 128, 28);
        contentPane.add(title);


        table = new JTable();
        this.jScrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.jScrollPane.setBounds(264, 48, 280, 223);
        this.jScrollPane.setViewportView(this.table);
        this.getContentPane().add(this.jScrollPane);

        showOrderTable = new JButton("Show table");
        showOrderTable.setFont(new Font("Tahoma", Font.PLAIN, 13));
        showOrderTable.setBackground(new Color(255, 223, 255));
        showOrderTable.setForeground(new Color(255, 128, 255));
        showOrderTable.setBounds(322, 296, 176, 28);
        contentPane.add(showOrderTable);

        addOrder = new JButton("ADD");
        addOrder.setBackground(new Color(255, 223, 255));
        addOrder.setForeground(new Color(255, 128, 255));
        addOrder.setBounds(22, 223, 89, 23);
        contentPane.add(addOrder);

        deleteOrder = new JButton("DELETE");
        deleteOrder.setForeground(new Color(255, 128, 255));
        deleteOrder.setBackground(new Color(255, 223, 255));
        deleteOrder.setBounds(22, 318, 89, 23);
        contentPane.add(deleteOrder);

        back = new JButton("<-");
        back.setBackground(new Color(255, 223, 255));
        back.setForeground(new Color(255, 128, 255));
        back.setBounds(0, 1, 60, 20);
        contentPane.add(back);

        clear = new JButton("CLEAR");
        clear.setBackground(new Color(255, 223, 255));
        clear.setForeground(new Color(255, 128, 255));
        clear.setBounds(148, 190, 89, 23);
        contentPane.add(clear);
    }

    /**
     * Getter ul pentru id-ul comenzii introdus de utilizator
     * @return id-ul comenzii introdus de utilizator
     */

    public int getIdOrder() {
        try {
            return Integer.parseInt(id_textField.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"id ul trebuie sa fie un int");
        }
        return 0;
    }

    /**
     * Getter ul pentru id-ul produsului introdus de utilizator
     * @return id-ul produsului introdus de utilizator
     */
    public int getProductId() {
        return (int) productId_combo.getSelectedItem();
    }

    /**
     * Getter ul pentru id-ul clientului introdus de utilizator
     * @return id-ul clientului introdus de utilizator
     */

    public int getClientId() {
        return (int) clientId_combo.getSelectedItem();
    }
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
        quantity_textField.setText("");
    }

    /**
     * Metoda prin care sunt adaugate in comboBox valorile id-ului din tabelul client
     */

    public void comboIdClient() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id FROM client");
            while (rs.next()) {
                clientId_combo.addItem(rs.getInt(1));
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Couldn't connect to db");
        }
    }

    /**
     * Metoda prin care sunt adaugate in comboBox valorile id-ului din tabelul product
     */
    public void comboIdProduct() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id FROM product");
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                productId_combo.addItem(rs.getInt(1));
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Couldn't connect to db");
        }
    }

    /**
     * Getter ul pentru quantity introdusa de utilizator
     * @return quantity introdusa de utilizator
     */
    public int getQuantity() {
        return Integer.parseInt(quantity_textField.getText());
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de ADD
     * @param actionListener
     */
    public void addOrderListener(ActionListener actionListener) {
        this.addOrder.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de DELETE
     * @param actionListener
     */

    public void deleteOrderListener(ActionListener actionListener) {
        this.deleteOrder.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de SHOW TABLE
     * @param actionListener
     */
    public void showTableOrderListener(ActionListener actionListener) {
        this.showOrderTable.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de BACK
     * @param actionListener
     */
    public void backListener(ActionListener actionListener) {
        this.back.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de CLEAR
     * @param actionListener
     */
    public void clearListener(ActionListener actionListener) {
        this.clear.addActionListener(actionListener);
    }
}
