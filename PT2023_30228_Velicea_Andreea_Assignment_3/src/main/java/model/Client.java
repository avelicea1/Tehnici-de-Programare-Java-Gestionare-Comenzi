package model;


/**
 * Clasa care modeleaza entitatea client si are aceleasi campuri ca si tabelul din baza de date.
 *
 * @author Velicea Andreea - Ioana
 */
public class Client {

    private int id;

    private String email;

    private String firstName;

    private String lastName;

    /**
     * Constructorul clasei avand toate variabilele instanta
     * @param id
     * @param email
     * @param firstName
     * @param lastName
     */

    public Client(int id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getter pentru id ul clientului
     * @return id ul clientului
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pentru email ul clientului
     * @return email ul clientului
     */
    public String getEmail() {
        return email;
    }


    /**
     * Getter pentru firstName-ul clientului
     * @return firstName ul clientului
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter pentru lastName ul clientului
     * @return lastName ul clientului
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Setter pentru id-ul clientului
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter pentru email-ul clientului
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter pentru firstName-ul clientului
     * @param firstName
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter pentru lastName-ul clientului
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Metoda prin care este afisat rezultatul intr un mod mai placut
     * @return
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
