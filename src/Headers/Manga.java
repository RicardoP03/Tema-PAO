package Headers;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class Manga extends Media {
    private String author;
    private List<Chapter> chapters = new ArrayList<>();
    public Manga(String name, String author) {
        super(name);
        this.author = author;
        productions.put(id, this);
        System.out.println("Seria manga a fost creata");
    }

    public void addChapter(Chapter ch) {
        chapters.add(ch);
        System.out.println("Capitolul a fost adaugat acestei serii manga.");
    }

    public void removeChapter(int id) {
        Iterator<Chapter> it = chapters.iterator();
        while(it.hasNext()) {
            Chapter mg = it.next();
            if (mg.getId() == id) {
                it.remove();
                System.out.println("Capitolul a fost scos din acesta serie manga.");
                return;
            }
        }
        System.out.println("Capitolul nu apartine acestei serii manga.");
    }

    @Override
    public String shortDescription() {
        return super.toString() + "Categoria: Manga\n";
    }

    public String toString() {
        String s;
        s = super.toString();
        s += "Seria manga este scrisa de: ";
        s += this.author;
        s += "\n";
        s += "Lista capitolelor: \n\n";
        for(Chapter ch: chapters) {
            s += ch.toString() + "\n";
        }
        return s;
    }
}
