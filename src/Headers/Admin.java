package Headers;

public class Admin extends Account{
    private static Admin instance;
    private Admin() {
        super("ADMIN", "AdminSecretPa55");
        usedNames.put(name, this);
    }

    public static Admin getInstance() {
        if (instance == null) {
            synchronized (Admin.class) {
                if (instance == null) {
                    instance = new Admin();
                }
            }
        }
        return instance;
    }

    public Anime CreateAnime(String nume) {
        return new Anime(nume);
    }

    public Manga CreateManga(String nume, String author) {
        return new Manga(nume, author);
    }

    public Novel CreateNovel(String nume, String author) {
        return new Novel(nume, author);
    }

    public void addEpisode(String name, int duration, int idAnime) {
        Episode ep = new Episode(name, duration);

    }
}
