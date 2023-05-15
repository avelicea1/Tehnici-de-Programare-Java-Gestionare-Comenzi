package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Fereastra principala de unde pot sa ajung in fiecare tabel: Client, Product, Order
 *
 * @author Velicea Andreea - Ioana
 */
public class View extends JFrame {
    private JPanel contentPane;
    private JButton tableClient;
    private JButton tableProduct;
    private JButton tableOrder;
    private JLabel title;
    private JLabel chooseTable;
    private JButton showBill;

    /**
     * Constructorul clasei in care se adauga toate componentele necesare
     */

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 223, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        tableClient = new JButton("CLIENT");
        tableClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableClient.setForeground(new Color(255, 128, 255));
        tableClient.setBackground(new Color(255, 223, 255));
        tableClient.setBounds(130, 86, 132, 29);
        contentPane.add(tableClient);

        title = new JLabel("Order Management");
        title.setFont(new Font("Tahoma", Font.PLAIN, 13));
        title.setForeground(new Color(255, 128, 255));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(130, 21, 148, 29);
        contentPane.add(title);

        chooseTable = new JLabel("Choose a table:");
        chooseTable.setForeground(new Color(255, 128, 255));
        chooseTable.setBounds(141, 61, 97, 14);
        contentPane.add(chooseTable);

        tableProduct = new JButton("PRODUCT");
        tableProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableProduct.setBackground(new Color(255, 223, 255));
        tableProduct.setForeground(new Color(255, 128, 255));
        tableProduct.setBounds(130, 136, 132, 29);
        contentPane.add(tableProduct);

        tableOrder = new JButton("ORDER");
        tableOrder.setForeground(new Color(255, 128, 255));
        tableOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableOrder.setBackground(new Color(255, 223, 255));
        tableOrder.setBounds(130, 181, 132, 29);
        contentPane.add(tableOrder);

        showBill = new JButton("BILL");
        showBill.setForeground(new Color(255, 128, 255));
        showBill.setBackground(new Color(255, 223, 255));
        showBill.setBounds(153, 221, 89, 23);
        contentPane.add(showBill);
    }

    /**
     * Metoda care adauga actionListener pentru butonul de afisare a inteferetei Client
     * @param actionListener
     */
    public void clientShow(ActionListener actionListener) {
        this.tableClient.addActionListener(actionListener);
    }

    /**
     * Metoda care adauga actionListener pentru butonul de afisare a intefetei Product
     * @param actionListener
     */
    public void productShow(ActionListener actionListener) {
        this.tableProduct.addActionListener(actionListener);
    }

    /**
     * Metoda care adauga actionListener pentru butonul de afisare a interfetei Order
     * @param actionListener
     */

    public void orderShow(ActionListener actionListener) {
        this.tableOrder.addActionListener(actionListener);
    }

    /**
     * Metoda care adauga actionListener pentru butonul de afisare a interfetei Bill
     * @param actionListener
     */
    public void billShow(ActionListener actionListener){
        this.showBill.addActionListener(actionListener);
    }
}
