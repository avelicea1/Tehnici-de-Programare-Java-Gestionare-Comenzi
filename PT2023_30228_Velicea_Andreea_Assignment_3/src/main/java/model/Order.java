package model;

/**
 * Clasa care modeleaza entitatea client si are aceleasi campuri ca si tabelul din baza de date.
 *
 * @author Velicea Andreea - Ioana
 */
public class Order {
    private int orderId;
    private int clientId;
    private int productId;
    private int quantity;

    /**
     * Constructorul clasei avand toate variabilele instanta
     * @param orderId
     * @param clientId
     * @param productId
     * @param quantity
     */
    public Order(int orderId, int clientId, int productId, int quantity) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Getter pentru id ul comenzii
     * @return id ul comenzii
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Getter pentru id ul clientului
     * @return id ul clientului
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Getter pentru id ul produsului
     * @return id ul produsului
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Getter pentru cantitatea comenzii
     * @return cantitatea comenzii
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter pentru id-ul comenzii
     * @param orderId
     */

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Setter pentru id-ul clientului
     * @param clientId
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Setter pentru id-ul produsului
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Setter pentru cantitatea comenzii
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

