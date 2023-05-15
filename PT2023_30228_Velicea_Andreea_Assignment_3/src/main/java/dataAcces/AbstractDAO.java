package dataAcces;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * Clasa generica DAO pentru interactiunea directa cu baza de date
 * @author Velicea Andreea - Ioana
 * @param <T>
 */

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("uncheked")
    /**
     * Constructorul clasei, unde este instantiat tipul clasei care va efectua operatiile
     */
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Metoda care creeaza interogarea pentru inserarea unui obiect
     * @param t
     * @return string ul specific interogarii de inserare
     * @throws IllegalAccessException
     */
    private String insertQuery(T t) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append(" INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES (");

        for(Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(field.get(t) instanceof Integer) {
                sb.append(field.get(t));
                sb.append(",");
            }
            else {
                sb.append("'");
                sb.append(field.get(t));
                sb.append("',");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(");");
        System.out.println(sb.toString());
        return sb.toString();
    }


    /**
     * Metoda care inserareaza un obiect in baza de date folosind interogarea creata in metoda insertQuery
     * @param t
     */

    public void insert(T t) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            String query=insertQuery(t);
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        }catch(SQLException | IllegalAccessException e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Metoda care creeaza interogarea pentru modificarea unui obiect
     * @param t
     * @param fields
     * @param id
     * @return
     * @throws IllegalAccessException
     */

    private String updateQuery(T t,String[] fields, String id) throws IllegalAccessException{
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        StringBuilder s= new StringBuilder();
        s.append("");
        int i=0 ;
        for(Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if(field.get(t) instanceof Integer) {

                sb.append(" "+fields[i]+" = "+field.get(t));
                if(i==0)
                    s.append(field.get(t));
                sb.append(",");
                i++;
            }
            else {
                sb.append(fields[i]+" = '"+field.get(t));
                sb.append("',");
                i++;
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(" WHERE "+id+" = " + s);
        System.out.println(sb.toString());
        return sb.toString();

    }


    /**
     * Metoda care modifica un obiect din baza de date
     * @param t
     * @param field
     * @return
     */
    public T update(T t,String field) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Field[] fields=type.getDeclaredFields();
            String[] fieldNames=new String[fields.length];
            for(int i=0;i<fields.length;i++){
                fieldNames[i]=fields[i].getName();
            }
            String query=updateQuery(t,fieldNames,field);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.executeUpdate();
        } catch (SQLException|IllegalAccessException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Metoda care creeaza interogarea pentru stergerea unui obiect
     * @param field
     * @return
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE "+ field + " = " + " ?");
        return sb.toString();
    }

    /**
     * Metoda care sterge un obiect din baza de date
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
            LOGGER.log(Level.WARNING, type.getName()+"Dao:findById" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }

    /**
     * Metoda prin care se face fetch obiectelor
     * @param resultSet
     * @return
     */


    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
}

