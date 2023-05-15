package dataAcces;

import model.Order;

import connection.ConnectionFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Clasa care extinda AbstractDAO si permite crearea obiectului OrderDAO
 * @author Velicea Andreea - Ioana
 */
public class OrderDAO extends AbstractDAO<Order>{

    /**
     * Constructorul clasei
     */
    public OrderDAO(){
        super();
    }

    /**
     * Metoda prin care sunt extrase toate comenzile din baza de date
     * @return lista de comenzi
     */
    public List<Order> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM databasetp.order";
        List<Order> lista = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                int clientId = resultSet.getInt(2);
                int productId = resultSet.getInt(3);
                int quantity = resultSet.getInt(4);
                lista.add(new Order(orderId, clientId, productId,quantity));
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
     * Metoda prin care este cautata o comanda dupa id ul ei
     * @param id
     * @return comanda cu id ul cautat
     */

    public Order findById(int id) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM databasetp.order WHERE orderId = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int clientId = resultSet.getInt(2);
                int productId = resultSet.getInt(3);
                int quantity = resultSet.getInt(4);
                return new Order(id, clientId, productId,quantity);
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

    /**
     * Metoda prin care este creata interogarea de inserare a unui obiect in baza de date
     * @param order
     * @return
     * @throws IllegalAccessException
     */
    private String insertQuery(Order order) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append(" INTO ");
        sb.append("databasetp.order");
        sb.append(" VALUES (");

        for(Field field : order.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(field.get(order) instanceof Integer) {
                sb.append(field.get(order));
                sb.append(",");
            }
            else {
                sb.append("'");
                sb.append(field.get(order));
                sb.append("',");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(");");
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Metoda prin care se adauga o comanda in baza de date
     * @param order
     */
    public void insert(Order order) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            String query=insertQuery(order);
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Metoda prin care este creata interogarea de stergere a unei comenzi din baza de date
     * @param field
     * @return
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(" databasetp.order ");
        sb.append(" WHERE "+ field + " = " + " ?");
        return sb.toString();
    }

    /**
     * Metoda prin care se sterge un obiect din baza de date
     * @param id
     * @param field
     */

    public void delete(int id, String field){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(field);
        try {
            System.out.println(query);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Order"+"Dao:findById" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }

}
