package bussinessLogic;

import dataAcces.ProductDAO;
import model.Product;

import java.util.List;

/**
 * Clasa care se ocupa de logica aplicatiei pentru modelul Product
 * @author Velicea Andreea - Ioana
 */
public class ProductBLL {
    private static ProductDAO productDAO = new ProductDAO();

    /**
     * Metoda care apeleaza DAO pentru gasirea unui produs dupa id
     * @param id
     * @return produsul cu id ul cautat
     * @throws Exception
     */
    public static Product findProductById(int id) throws Exception {
        Product pr = productDAO.findById(id);
        if (pr == null) {
            throw new Exception("Produsul cu id =" + id + " nu a fost gasit !");
        }
        return pr;
    }

    /**
     * Metoda apeleaza DAO pentru inserarea unui produs in baza de date
     * @param product
     */

    public static void insertProduct(Product product){
        productDAO.insert(product);
    }

    /**
     * Metoda apeleaza DAO pentru a gasi toate inregistrarile din tabelul Product
     * @return lista de produse
     */
    public static List<Product> findProductAll(){
        return productDAO.findAll();
    }

    /**
     * Metoda apeleaza DAO pentru a sterge un produs dupa id
     * @param id
     */
    public static void deleteProductById(int id){
        productDAO.delete(id,"id");
    }

    /**
     * Metoda apeleaza DAO pentru modificarea unui produs dupa id
     * @param product
     */
    public static void updateProductById(Product product ){
        productDAO.update(product,"id");
    }
}
