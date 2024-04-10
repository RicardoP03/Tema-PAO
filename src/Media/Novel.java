package Media;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class Novel extends Media {
    private String author;
    private Integer nrPages = 0;
    private List<Volume> volumes = new ArrayList<>();
    public Novel(String name, String author) {
        super(name);
        this.author = author;
        productions.put(id, this);
        System.out.println("Romanul a fost creat");
    }

    public void addVolume(Volume vl) {
        volumes.add(vl);
        nrPages += vl.getPages();
        System.out.println("Volumul a fost adaugat la acest roman.");
    }

    public void removeVolume(int id) {
        Iterator<Volume> it = volumes.iterator();
        while(it.hasNext()) {
            Volume vl = it.next();
            if (vl.getId() == id) {
                it.remove();
                System.out.println("Volumul a fost scos din acest roman.");
                return;
            }
        }
        System.out.println("Volumul nu apartine acestui roman.");
    }

    @Override
    public String shortDescription() {
        return super.toString() + "Categoria: Roman\n";
    }

    public String toString() {
        String s;
        s = super.toString();
        s += "Romanul este scris de: ";
        s += this.author;
        s += "\n";
        s += "Lista volumelor\n\n";
        for(Volume vl: volumes) {
            s += vl.toString() + "\n";
        }
        return s;
    }

}
