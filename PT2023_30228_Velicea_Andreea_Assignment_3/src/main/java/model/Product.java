package model;

/**
 * Clasa care modeleaza entitatea product si are aceleasi campuri ca si tabelul din baza de date.
 *
 * @author Velicea Andreea - Ioana
 */
public class Product {

    private int id;
    private String name;
    private float price;
    private int stoc;

    /**
     * Constructorul clasei avand toate variabilele instanta
     * @param id este id-ul produsului
     * @param name este numele produsului
     * @param price este pretul produsului
     * @param stoc este stocul produsului
     */
    public Product(int id, String name, float price, int stoc) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stoc = stoc;
    }

    /**
     * Getter pentru id ul produsului
     * @return id ul produsului
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pentru numele produsului
     * @return numele produsului
     */
    public String getName() {
        return name;
    }

    /**
     * Getter pentru pretul produsului
     * @return pretul produsului
     */
    public float getPrice() {
        return price;
    }

    /**
     * Setter pentru id-ul produsului
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter pentru numele produsului
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter pentru pretul produsului
     * @param price
     */

    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Getter pentru stocul produsului
     * @return  stocul produsului
     */
    public int getStoc() {
        return stoc;
    }

    /**
     * Setter pentru stocul produsului
     * @param stoc
     */
    public void setStoc(int stoc) {
        this.stoc = stoc;
    }
}
