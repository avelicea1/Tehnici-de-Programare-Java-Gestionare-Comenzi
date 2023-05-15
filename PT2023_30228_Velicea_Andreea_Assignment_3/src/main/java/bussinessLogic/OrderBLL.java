package bussinessLogic;

import bussinessLogic.validators.QuantityValidator;
import bussinessLogic.validators.Validator;
import dataAcces.OrderDAO;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care se ocupa de logica aplicatiei pentru modelul Order
 * @author Velicea Andreea - Ioana
 */
public class OrderBLL {
    private static OrderDAO orderDAO = new OrderDAO();
    private static List<Validator<Order>> validators;
    /**
     * Constructorul clasei in care se adauga validatorii
     */

    public OrderBLL(){
        validators = new ArrayList<Validator<Order>>();
        validators.add(new QuantityValidator());
    }

    /**
     * Metoda apeleaza DAO pentru a gasi toate inregistrarile din tabelul Order
     * @return lista de comenzi
     */
    public static List<Order> findOrdersAll(){
        return orderDAO.findAll();
    }

    /**
     * Metoda apeleaza DAO pentru a gasi o comanda dupa id
     * @param id
     * @return comanda cu id ul cautat
     * @return comanda cu id ul cautat
     * @throws Exception
     */

    public static Order findOrderById(int id) throws Exception {
        Order or = orderDAO.findById(id);
        if (or == null) {
            throw new Exception("Comanda cu id =" + id + " nu a fost gasita !");
        }
        return or;
    }

    /**
     * Metoda care apeleaza DAO pentru inserarea unei comenzi in baza de date
     * @param or
     * @throws Exception
     */
    public static void insertOrders(Order or) throws Exception {
        Product product = ProductBLL.findProductById(or.getProductId());
        if(product == null)
            return;
        Client client = ClientBLL.findClientById(or.getClientId());
        if(client==null)
            return;
        if(product.getStoc()<or.getQuantity()) {
            JOptionPane.showMessageDialog(null, "Nu exista suficiente produse in stoc");
            return;
        }
        else
            product.setStoc(product.getStoc()-or.getQuantity());
        createBill(or);
        ProductBLL.updateProductById(product);

        try{
            for(Validator<Order> validator : validators) {
                validator.validate(or);
            }
            orderDAO.insert(or);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }

    /**
     * Metoda care apeleaza DAO pentru stergerea unei comenzi din baza de date
     * @param or
     * @throws Exception
     */
    public static void deleteOrder(Order or) throws Exception {
        Product product = ProductBLL.findProductById(or.getProductId());
        if(product == null)
            return;
        product.setStoc(product.getStoc()+or.getQuantity());
        ProductBLL.updateProductById(product);
        Bill bill = new Bill(or.getOrderId(),or.getOrderId(), or.getClientId(), or.getProductId(), or.getQuantity());
        BillBLL.deleteBill(bill);
        orderDAO.delete(or.getOrderId(),"orderId");
    }

    /**
     * Metoda care apeleaza DAO pentru crearea unei facturi si adaugarea acesteia in baza de date
     * @param orders
     */
    public static void createBill( Order orders)  {
        Bill bill = new Bill(orders.getOrderId(),orders.getOrderId(), orders.getClientId(), orders.getProductId(), orders.getQuantity());
        BillBLL.insertBill(bill);
    }
}
