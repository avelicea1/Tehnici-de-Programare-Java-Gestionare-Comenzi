package dataAcces;

import model.Bill;
import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care extinda AbstractDAO si permite crearea obiectului BillDAO
 * @author Velicea Andreea - Ioana
 */
public class BillDAO extends AbstractDAO<Bill>{
    /**
     * Constructorul clasei
     */
    public BillDAO(){
        super();
    }

    /**
     * Metoda prin care sunt extrase toate facturile din baza de date
     * @return lista de facturi
     */
    public List<Bill> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM bill";
        List<Bill> lista = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt(1);
                int orderId = resultSet.getInt(2);
                int clientId = resultSet.getInt(3);
                int productId = resultSet.getInt(4);
                int quantity = resultSet.getInt(5);
                lista.add(new Bill(billId, orderId, clientId,productId,quantity));
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
}
