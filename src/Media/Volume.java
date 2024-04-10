package Media;

public class Volume {
    private String name;
    private int pages;
    private int id;
    private static int idMax;

    public Volume(String name, int pages) {
        this.name = name;
        this.pages = pages;
        idMax++;
        id = idMax;
    }

    public int getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    public String toString() {
        String s;
        s = "Nume: " + this.name + "\n";
        s += "ID: " + String.valueOf(this.id) + "\n";
        s += "Numar pagini: " + String.valueOf(this.pages) + "\n";
        return s;
    }

}
