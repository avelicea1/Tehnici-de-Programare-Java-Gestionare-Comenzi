package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa care se ocupa de realizarea interfetei pentru operatii cu clienti
 *
 * @author Velicea Andreea - Ioana
 */
public class ClientView extends JFrame {
    private JPanel contentPane;
    private JLabel id_label;
    private JTextField id_textField;
    private JTextField email_textField;
    private JLabel email_label;
    private JTextField firstName_textField;
    private JLabel firstName_label;
    private JTextField lastName_textField;
    private JLabel lastName_label;
    private JTable table;
    private JLabel title;
    private JButton deleteClient;
    private JButton addClient;
    private JButton updateClient;
    private JButton showTableClient;
    private JButton backButton;
    private JButton clear;
    private JScrollPane jScrollPane = new JScrollPane();

    /**
     *  Constructoul clasei in care se adauga toate componentele necesare
     */
    public ClientView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 607, 408);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 223, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        id_label = new JLabel("Id");
        id_label.setForeground(new Color(255, 128, 255));
        id_label.setHorizontalAlignment(SwingConstants.CENTER);
        id_label.setBounds(10, 46, 49, 14);
        contentPane.add(id_label);

        id_textField = new JTextField();
        id_textField.setBounds(69, 43, 35, 20);
        contentPane.add(id_textField);
        id_textField.setColumns(10);

        email_label = new JLabel("email");
        email_label.setForeground(new Color(255, 128, 255));
        email_label.setHorizontalAlignment(SwingConstants.CENTER);
        email_label.setBounds(10, 103, 49, 14);
        contentPane.add(email_label);

        email_textField = new JTextField();
        email_textField.setBounds(69, 100, 119, 20);
        contentPane.add(email_textField);
        email_textField.setColumns(10);

        firstName_label = new JLabel("First Name");
        firstName_label.setForeground(new Color(255, 128, 255));
        firstName_label.setHorizontalAlignment(SwingConstants.CENTER);
        firstName_label.setBounds(10, 163, 94, 14);
        contentPane.add(firstName_label);

        contentPane.add(jScrollPane);
        firstName_textField = new JTextField();
        firstName_textField.setBounds(109, 160, 96, 20);
        contentPane.add(firstName_textField);
        firstName_textField.setColumns(10);

        lastName_label = new JLabel("Last Name");
        lastName_label.setForeground(new Color(255, 128, 255));
        lastName_label.setHorizontalAlignment(SwingConstants.CENTER);
        lastName_label.setBounds(10, 218, 94, 14);
        contentPane.add(lastName_label);

        lastName_textField = new JTextField();
        lastName_textField.setBounds(109, 215, 96, 20);
        contentPane.add(lastName_textField);
        lastName_textField.setColumns(10);

        deleteClient = new JButton("DELETE");
        deleteClient.setForeground(new Color(255, 128, 255));
        deleteClient.setBackground(new Color(255, 223, 255));
        deleteClient.setBounds(145, 295, 89, 23);
        contentPane.add(deleteClient);

        addClient = new JButton("ADD");
        addClient.setForeground(new Color(255, 128, 255));
        addClient.setBackground(new Color(255, 223, 255));
        addClient.setBounds(15, 273, 89, 23);
        contentPane.add(addClient);

        updateClient = new JButton("UPDATE");
        updateClient.setForeground(new Color(255, 128, 255));
        updateClient.setBackground(new Color(255, 223, 255));
        updateClient.setBounds(15, 326, 89, 23);
        contentPane.add(updateClient);

        showTableClient = new JButton("SHOW TABLE");
        showTableClient.setForeground(new Color(255, 128, 255));
        showTableClient.setBackground(new Color(255, 223, 255));
        showTableClient.setBounds(354, 326, 161, 23);
        contentPane.add(showTableClient);

        table = new JTable();
        this.jScrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.jScrollPane.setBounds(304, 46, 239, 237);
        this.jScrollPane.setViewportView(this.table);
        this.getContentPane().add(this.jScrollPane);


        backButton = new JButton("<-");
        backButton.setForeground(new Color(255, 128, 255));
        backButton.setBackground(new Color(255, 233, 255));
        backButton.setBounds(10, 12, 49, 23);
        contentPane.add(backButton);

        title = new JLabel("CLIENT");
        title.setFont(new Font("Tahoma", Font.PLAIN, 14));
        title.setForeground(new Color(255, 128, 255));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(181, 9, 161, 24);
        contentPane.add(title);

        clear = new JButton("CLEAR");
        clear.setBackground(new Color(255, 223, 255));
        clear.setForeground(new Color(255, 128, 255));
        clear.setBounds(181, 46, 89, 23);
        contentPane.add(clear);
    }

    /**
     * Getter ul pentru id-ul introdus de utilizator
     * @return id-ul introdus de utilizator
     */
    public int getIdClient() {
        try {
            int id = Integer.parseInt(this.id_textField.getText());
            return id;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"id ul trebuie sa fie un int");
        }
        return 0;
    }

    /**
     * Getter ul pentru email-ul introdus de utilizator
     * @return email-ul introdus de utilizator
     */
    public String getEmailClient() {
        return this.email_textField.getText();
    }

    /**
     * Getter ul pentru firstName introdus de utilizator
     * @return firstName introdus de utilizator
     */
    public String getFirstName() {
        return this.firstName_textField.getText();
    }

    /**
     * Getter ul pentru lastName introdus de utilizator
     * @return lastName introdus de utilizator
     */
    public String getLastName() {
        return this.lastName_textField.getText();
    }

    /**
     * Getter ul pentru tabel
     * @return tabelul
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Metoda care elibereaza casetele pentru reintroducerea altor date
     */

    public void clear() {
        id_textField.setText("");
        email_textField.setText("");
        firstName_textField.setText("");
        lastName_textField.setText("");
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de ADD
     * @param actionListener
     */

    public void addClientListener(ActionListener actionListener) {
        this.addClient.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de UPDATE
     * @param actionListener
     */
    public void updateClientListener(ActionListener actionListener) {
        this.updateClient.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de DELETE
     * @param actionListener
     */
    public void deleteClientListener(ActionListener actionListener) {
        this.deleteClient.addActionListener(actionListener);
    }

    /**
     * Metoda carea adauga actionListener pentru butonul de SHOW TABLE
     * @param actionListener
     */
    public void showTableClientListener(ActionListener actionListener) {
        this.showTableClient.addActionListener(actionListener);
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
