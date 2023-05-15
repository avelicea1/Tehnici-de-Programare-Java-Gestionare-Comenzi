package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa care se ocupa de realizarea interfetei pentru facturi
 *
 * @author Velicea Andreea - Ioana
 */
public class BillView extends JFrame{
    private JPanel contentPane;
    private JTable table;
    private JButton showBillTable;
    private JScrollPane jScrollPane;
    private JButton backButton;

    /**
     * Constructorul clasei in care se adauga toate componentele necesare
     */
    public BillView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 223, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        table = new JTable();
        this.jScrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.jScrollPane.setBounds(10, 11, 402, 177);
        this.jScrollPane.setViewportView(this.table);
        this.getContentPane().add(this.jScrollPane);

        showBillTable = new JButton("SHOW TABLE");
        showBillTable.setBackground(new Color(255, 223, 255));
        showBillTable.setForeground(new Color(255, 128, 255));
        showBillTable.setBounds(108, 208, 135, 23);
        contentPane.add(showBillTable);

        backButton = new JButton("<-");
        backButton.setBackground(new Color(255, 223, 255));
        backButton.setForeground(new Color(255, 128, 255));
        backButton.setBounds(347, 209, 61, 23);
        contentPane.add(backButton);
    }

    /**
     * Metoda care adauga actionListener pentru butonul SHOW TABLE
     * @param actionListener
     */
    public void showTable(ActionListener actionListener){
        this.showBillTable.addActionListener(actionListener);
    }

    /**
     * Metoda care adauga actionListener pentru butonul BACK
     * @param actionListener
     */
    public void backListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

    /**
     * Getter ul pentru tabel
     * @return tabel
     */
    public JTable getTable() {
        return table;
    }

}
