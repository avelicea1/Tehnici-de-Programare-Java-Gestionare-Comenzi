package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa care se ocupa de conectarea la baza de date
 * @author Velicea Andreea - Ioana
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://localhost:3306/databasetp";
    private static final String USER="root";
    private static final String PASS="root";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Constructorul clasei
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda care creeaza conexiunea
     * @return conexiunea
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Getter ul pentru conexiune
     * @return conexiunea
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * Metoda prin care este inchisa conexiunea
     * @param connection obiectul de tip conexiune
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    /**
     * Metoda care inchide statement ul
     * @param statement obiectul de tip statement
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * Metoda care inchide resulSet
     * @param resultSet obiectul de tip resultSet
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }

}
