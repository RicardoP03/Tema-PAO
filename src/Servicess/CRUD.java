package Servicess;
import oracle.jdbc.pool.OracleDataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD<T> {
    private static CRUD<?> instance;
    private Connection dataBase;
    private CRUD() {
        try {
            OracleDataSource obs = new OracleDataSource();
            obs.setURL(DataBaseDetails.getConnLink());
            obs.setUser(DataBaseDetails.getUser());
            obs.setPassword(DataBaseDetails.getPass());
            dataBase = obs.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getTableName(Class<?> cl) {
        String name = cl.getSimpleName().toLowerCase();
        if(name.equals("user")) name += "_";
        return name;
    }
    public static synchronized <T> CRUD<T> getInstance() {
        if (instance == null) {
            instance = new CRUD<>();
        }
        return (CRUD<T>) instance;
    }

    public Integer getNextId(Class<?> cl) {
        String query = "SELECT " + getTableName(cl) + "_sequence.NEXTVAL FROM DUAL";
        try {
            PreparedStatement st = dataBase.prepareStatement(query);
            ResultSet id = st.executeQuery();
            id.next();
            return id.getInt(1);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(T data) {
        try {
            Class<?> cl = data.getClass();
            Field[] fields = cl.getDeclaredFields();

            String sql = "INSERT INTO " + getTableName(cl) + " (";

            int m = fields.length;
            for(int i = 0; i < m; i++) {
                sql += fields[i].getName();
                if(i != m - 1) sql += ", ";
            }

            sql += ") VALUES (";
            for(int i = 0; i < m; i++) {
                sql += "?";
                if(i != m - 1) sql += ", ";
            }

            sql += ")";

            PreparedStatement st = dataBase.prepareStatement(sql);
            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                st.setObject(index++, field.get(data));
            }

            st.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> read(Class<T> cl) {
        List<T> data = new ArrayList<T>();
        try {
            String sql = "SELECT * FROM " + getTableName(cl);
            PreparedStatement st = dataBase.prepareStatement(sql);
            ResultSet res = st.executeQuery();

            Method parseMethod = cl.getDeclaredMethod("parse", ResultSet.class);

            while (res.next()) {
                T instance = cl.cast(parseMethod.invoke(null, res));
                data.add(instance);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public T readByID(Class<T> cl, int id) {
        try {
            String sql = "SELECT * FROM " + getTableName(cl) + " WHERE id = ?";
            PreparedStatement st = dataBase.prepareStatement(sql);
            st.setObject(1, id);
            ResultSet res = st.executeQuery();

            Method parseMethod = cl.getDeclaredMethod("parse", ResultSet.class);

            if(res.next()) {
                T instance = cl.cast(parseMethod.invoke(null, res));
                return instance;
            }
            else return null;

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Class<T> cl, int id) {
        try {
            String sql = "DELETE FROM " + getTableName(cl) + " WHERE id = ?";
            PreparedStatement st = dataBase.prepareStatement(sql);
            st.setObject(1, id);
            ResultSet res = st.executeQuery();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update(T oldData, T newData) {
        try {
            Class<?> cl = oldData.getClass();
            String tableName = getTableName(cl);
            Field[] fields = cl.getDeclaredFields();

            StringBuilder setValues = new StringBuilder();
            for (Field field : fields) {
                field.setAccessible(true);
                setValues.append(field.getName()).append("=?,");
            }

            setValues.deleteCharAt(setValues.length() - 1);

            String condition = "id=?";
            String sql = "UPDATE " + tableName + " SET " + setValues.toString() + " WHERE " + condition;

            PreparedStatement statement = dataBase.prepareStatement(sql);

            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                statement.setObject(index++, field.get(newData));
            }

            Field idField = cl.getDeclaredField("id");
            idField.setAccessible(true);
            statement.setObject(index, idField.get(oldData));

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
