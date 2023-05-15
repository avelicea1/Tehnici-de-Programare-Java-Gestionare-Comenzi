package bussinessLogic;

import bussinessLogic.validators.EmailValidator;
import bussinessLogic.validators.Validator;
import dataAcces.ClientDAO;
import model.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care se ocupa de logica aplicatiei pentru modelul Client
 * @author Velicea Andreea - Ioana
 */
public class ClientBLL {
    private static List<Validator<Client>> validators;
    private static ClientDAO clientDAO = new ClientDAO();

    /**
     * Constructorul clasei in care se adauga validatorii
     */

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
    }

    /**
     * Metoda apeleaza DAO pentru gasirea unui client dupa id
     * @param id
     * @return clientul cu id ul cautat
     * @throws Exception
     */
    public static Client findClientById(int id) throws Exception {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new Exception("Clientul cu id =" + id + " nu a fost gasit !");
        }
        return cl;
    }

    /**
     * Metoda apeleaza DAO pentru inserarea unui client in baza de date
     * @param client
     */
    public static void insertClient(Client client){
        try {
            for (Validator<Client> validator : validators) {
                validator.validate(client);
            }
            clientDAO.insert(client);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"email invalid!");
        }

    }

    /**
     * Metoda apeleaza DAO pentru a gasi toate inregistrarile din tabelul Client
     * @return lista de clienti
     */
    public static List<Client> findClientAll(){
        return clientDAO.findAll();
    }

    /**
     * Metoda apeleaza DAO pentru stergerea unui client din baza de date dupa id
     * @param id
     */
    public static void deleteClientById(int id){
        clientDAO.delete(id,"id");
    }

    /**
     * Metoda apeleaza DAO pentru modificarea unui client din baza de date dupa id
     * @param client
     */
    public static void updateClientById(Client client ){
        clientDAO.update(client,"id");
    }
}
