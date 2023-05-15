package dataAcces;

import model.Client;
import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care extinda AbstractDAO si permite crearea obiectului ClientDAO
 * @author Velicea Andreea - Ioana
 */
public class ClientDAO extends AbstractDAO<Client> {

    /**
     * Constructorul clasei
     */
    public ClientDAO(){
        super();
    }

    /**
     * Metoda prin care sunt extrasi toti clientii din baza de date
     * @return lista de clienti
     */
    public List<Client> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM client";
        List<Client> lista = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String email = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                lista.add(new Client(id, email, firstName,lastName)); // adaugare client in lista de clienti
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return lista;
    }

    /**
     * Metoda prin care este cautat un client dupa id ul lui
     * @param id
     * @return clientul cu id ul cautat
     */
    public Client findById(int id) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM client WHERE id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String email = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);


                return new Client(id, email, firstName,lastName);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }
}
