import presentation.*;

/**
 * @author : Velicea Andreea - Ioana
 */
public class App {
    /**
     * Metoda main in care imi creez interfetele si controller-ul
     * @param args
     */
    public static void main(String[] args) {

        ClientView client = new ClientView();
        ProductView product = new ProductView();
        OrderView order = new OrderView();
        BillView billView = new BillView();
        View view = new View();
        Controller controller = new Controller(view);
        view.setVisible(true);

    }
}
