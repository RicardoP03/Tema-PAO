package Media;

public class Chapter {
    private String name;
    private int id;
    private static int idMax;

    public Chapter(String name) {
        this.name = name;
        idMax++;
        id = idMax;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        String s;
        s = "name: " + this.name + "\n";
        s += "ID: " + String.valueOf(this.id) + "\n";
        return s;
    }
}
