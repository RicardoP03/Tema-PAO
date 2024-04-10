package Media;

public class Episode {
    private String name;
    private int duration;
    private int id;
    private static int idMax = 0;


    public Episode(String name, int duration) {
        this.name = name;
        this.duration = duration;
        idMax++;
        id = idMax;
        System.out.println("Episodul a fost creat");
    }

    public int getDuration() {
        return this.duration;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        String s;
        s = "Nume: " + this.name + "\n";
        s += "ID: " + String.valueOf(this.id) + "\n";
        s += "Durata: " +  String.valueOf(this.duration) + " minute\n";
        return s;
    }

}
