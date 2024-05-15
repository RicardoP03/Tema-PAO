package Account;

public class User extends Account {
    int id;

    public User(String name, String password) {
        super(name, password);
        this.id = super.id;
    }

    public User(int id, String name, String password) {
        super(id, name, password);
        this.id = id;
    }

}
