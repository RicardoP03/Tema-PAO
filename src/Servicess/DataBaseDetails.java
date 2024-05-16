package Servicess;

public class DataBaseDetails {
    static public String getConnLink() {
        return "jdbc:oracle:thin:@localhost:1521:XE";
    }

    static public String getUser() {
        return "utilizator";
    }

    static public String getPass() {
        return "parola123";
    }
}
