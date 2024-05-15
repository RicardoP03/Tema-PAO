package Account;

import Servicess.*;
public class Account {
    int id;
    protected String name;
    private String password;

    protected Account(String name, String parola) {
        this.name = name;
        this.password = parola;
        CRUD<Account> CR = CRUD.getInstance();
        this.id = CR.getNextId(Account.class);
        System.out.println("Contul a fost creat");
    }

    protected Account(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Account(Account ac) {
        this.id = ac.id;
        this.password = ac.password;
        this.name = ac.name;
    }

    public int getId() {
        return id;
    }
}
