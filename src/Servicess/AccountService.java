package Servicess;

import Account.*;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class AccountService {
    private static AccountService instance;
    private Connection dataBase;
    private AccountService() {
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

    public static synchronized AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    public ResultSet getByName(String name) {
        try {
            String sql = "SELECT * FROM Account WHERE name = ?";
            PreparedStatement st = dataBase.prepareStatement(sql);
            st.setObject(1, name);
            ResultSet res = st.executeQuery();
            return  res;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public void setAdminInstance() {
        try {
            ResultSet res = getByName("ADMIN");
            if(!res.next()) {
                Admin an = new Admin();
                Account ac = new Account(an);
                CRUD<Account> cr = CRUD.getInstance();
                cr.create(ac);
                cr.create(an);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Account logIn(String name, String password) {
        try {
            ResultSet res = getByName(name);

            if(!res.next()) {
                System.out.println("Numele de utilizator este gresit");
                return null;
            }
            else {
                String pass = res.getString(3);
                if(!pass.equals(password)) {
                    System.out.println("Parola este gresita");
                    return null;
                }

                int id = res.getInt(1);
                if(!name.equals("ADMIN")) {
                    return new User(id, name, password);
                }
                else {
                    return new Admin(id, name, password);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean nameCheck(String name) {
        if(name.isEmpty()) {
            System.out.println("Numele nu poate fi gol!");
            return false;
        }

        if(name.length() > 50) {
            System.out.println("Numele nu poate fi mai lung de 50 de caractere!");
            return false;
        }

        for(char c: name.toCharArray()) {
            if(!Character.isLetterOrDigit(c)) {
                System.out.println("Numele trebuie sa contina doar litere si numere!");
                return false;
            }
        }

        try {
            ResultSet res = getByName(name);

            if(!res.next()) {
                return true;
            }
            else {
                System.out.println("Numele este deja folosit!");
                return false;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean passwordCheck(String password) {

        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        boolean hasLength = password.length() >= 8 && password.length() <= 50;

        for(char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }

            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }

            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        if(!(hasLowerCase && hasUpperCase && hasDigit && hasLength)) {
            System.out.println("Parola trebuia sa aiba:");
            if(!hasLength) {
                System.out.println("- O lungime intre 8 si 50 de caractere");
            }

            if(!hasLowerCase){
                System.out.println("- Cel putin o litera mica");
            }

            if(!hasUpperCase){
                System.out.println("- Cel putin o litera mare");
            }

            if(!hasDigit){
                System.out.println("- Cel putin o cifra");
            }

            return false;
        }

        return true;
    }
}
