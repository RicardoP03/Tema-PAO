package Media;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class Anime extends Media {
    private int duration = 0;
    private List<Episode> episodes = new ArrayList<>();
    public Anime(String name) {
        super(name);
        productions.put(id, this);
        System.out.println("Animeul a fost creat");
    }

    public void addEpisode(Episode ep) {
        episodes.add(ep);
        duration += ep.getDuration();
        System.out.println("Episodul a fost adaugat animeului.");
    }

    public void removeEpisod(int id) {
        Iterator<Episode> it = episodes.iterator();
        while(it.hasNext()) {
            Episode ep = it.next();
            if (ep.getId() == id) {
                it.remove();
                duration -= ep.getDuration();
                System.out.println("Episodul a fost scos din acest anime.");
                return;
            }
        }
        System.out.println("Episodul nu apartine acestui anime.");
    }

    @Override
    public String shortDescription() {
        return super.toString() + "Categoria: Anime\n";
    }

    public String toString() {
        String s;
        s = super.toString();
        s += "Durata totala a animeului este: " + Integer.toString(duration) + " minute\n";
        s += "Animeul are: " + Integer.toString(episodes.size()) +  " episoade\n";
        s += "Lista episodeslor:\n\n";
        for(Episode ep: episodes) {
            s += ep.toString() + "\n";
        }
        return s;
    }
}
