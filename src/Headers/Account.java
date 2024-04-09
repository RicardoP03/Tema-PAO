package Headers;

import java.util.HashMap;
import java.util.Map;
public abstract class Account {
    protected String name;
    private String password;
    protected static Map<String, Account> usedNames = new HashMap<>();

    protected Account(String name, String parola) {
        this.name = name;
        this.password = parola;
        System.out.println("Contul a fost creat");
    }

    public static Account logIn(String name, String password) {
        if(usedNames.containsKey(name)) {
            Account ac = usedNames.get(name);
            if(ac.password.equals(password)) {
                return ac;
            }
            else {
                System.out.println("Parola este gresita");
                return null;
            }
        }
        else {
            System.out.println("Numele de utilizator este gresit");
            return null;
        }
    }


}
