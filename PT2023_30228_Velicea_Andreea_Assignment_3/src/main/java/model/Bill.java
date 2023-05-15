package model;

/**
 * @author Velicea Andreea-Ioana
 * Clasa immutable Bill care modeleaza entitatea bill
 * @param billId
 * @param orderId
 * @param clientId
 * @param productId
 * @param quantity
 */

public record Bill(int billId, int orderId, int clientId, int productId, int quantity) {

}
