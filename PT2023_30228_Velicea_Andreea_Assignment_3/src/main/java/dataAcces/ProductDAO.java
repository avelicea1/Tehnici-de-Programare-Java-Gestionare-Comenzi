package dataAcces;

import model.Product;
import connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care extinda AbstractDAO si permite crearea obiectului ProductDAO
 * @author Velicea Andreea - Ioana
 */

public class ProductDAO extends AbstractDAO<Product>{

    /**
     * Constructorul clasei
     */
    public ProductDAO(){
        super();
    }

    /**
     * Metoda prin care sunt extrase toate produsele din baza de date
     * @return lista de produse
     */
    public List<Product> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM product";
        List<Product> lista = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                float price = resultSet.getFloat(3);
                int stoc = resultSet.getInt(4);
                lista.add(new Product(id, name, price,stoc));
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
     * Metoda prin care este cautat un produs dupa id ul lui
     * @param id
     * @return produsul cu id ul cautat
     */
    public Product findById(int id) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM product WHERE id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString(2);
                float price = resultSet.getFloat(3);
                int stoc = resultSet.getInt(4);


                return new Product(id, name, price,stoc);
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
