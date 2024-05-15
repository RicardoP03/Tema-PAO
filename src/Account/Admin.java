package Account;

public class Admin extends Account {
    int id;
    public Admin() {
        super("ADMIN", "AdminSecretPa55");
        this.id = super.id;
    }

    public Admin(int id, String name, String password) {
        super(id, name, password);
        this.id = id;
    }

}
