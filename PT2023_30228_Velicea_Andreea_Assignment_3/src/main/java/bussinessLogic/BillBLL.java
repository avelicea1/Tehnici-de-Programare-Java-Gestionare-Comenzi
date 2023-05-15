package bussinessLogic;

import dataAcces.BillDAO;
import model.Bill;

import java.util.List;

/**
 * Clasa care se ocupa de logica aplicatiei pentru modelul Bill
 * @author Velicea Andreea - Ioana
 */
public class BillBLL{
    private static BillDAO billDAO = new BillDAO();

    /**
     * Constructorul clasei
     */
    public BillBLL(){

    }

    /**
     * Metoda apeleaza DAO pentru inserarea unei facturi in baza de date
     * @param bill
     */
    public static void insertBill(Bill bill){
        billDAO.insert(bill);
    }

    /**
     * Metoda apeleaza DAO pentru stergerea unei facturi din baza de date
     * @param bill
     */
    public static void deleteBill(Bill bill){
        billDAO.delete(bill.billId(), "billId");
    }

    /**
     * Metoda apeleaza DAO pentru a gasi toate inregistrarile din tabelul bill
     * @return
     */
    public static List<Bill> showAllBils(){
        return billDAO.findAll();
    }

}
