package Meniu;

import Account.*;
import Media.*;
import Servicess.*;

import java.util.Scanner;
import java.util.List;

public class Meniu {
    private static final Scanner scanner = new Scanner(System.in);
    private static Meniu instance;

    private AccountService acService = AccountService.getInstance();
    private operationsService opService = operationsService.getInstance();

    private CSVWriterReader writer = new CSVWriterReader("CSVData/actions.csv");
    private Meniu() {}

    public static Meniu getInstance() {
        if (instance == null) {
            instance = new Meniu();
        }
        return instance;
    }

    public void meniuAutentificare() {
        acService.setAdminInstance();
        while(true) {

            System.out.println("Bine ati venit!\n");
            System.out.println("Pentru a va audentifica introduceti tasta A");
            System.out.println("Pentru a crea un cont nou introduceti tasta C");
            System.out.println("Pentru a inchide aplicatia introduceti tasta X");

            char ch = scanner.next().charAt(0);
            scanner.nextLine();
            if(ch == 'A' || ch == 'a') {
                while(true) {
                    String name = readNameAccount();
                    String password = readPassword();

                    Account ac = acService.logIn(name, password);
                    if (ac != null) {
                        if (ac instanceof User) {
                            writer.exportToCSV("Autentificare cont utilizator");
                            this.meniuUser((User) ac);
                            break;
                        }

                        if (ac instanceof Admin) {
                            writer.exportToCSV("Autentificare cont administrator");
                            this.meniuAdmin((Admin) ac);
                            break;
                        }
                    } else {
                        System.out.println();
                        System.out.println("Introduceti orice tasta X pentru va intoarce la meniul de autentificare");
                        System.out.println("Introduceti orice alta tasta pentru a reintroduce datele");
                        ch = scanner.next().charAt(0);
                        scanner.nextLine();
                        if (ch == 'X' || ch == 'x') break;
                    }
                }
            }
            else if(ch == 'C' || ch == 'c') {
                while(true) {
                    String name = readNameAccount();
                    String password = readPassword();

                    boolean checkedName = acService.nameCheck(name);
                    System.out.println();
                    boolean checkedPassword = acService.passwordCheck(password);
                    if(checkedName && checkedPassword) {
                        User us = new User(name, password);
                        Account ac = new Account(us);
                        CRUD<Account> cr = CRUD.getInstance();
                        cr.create(ac);
                        cr.create(us);
                        writer.exportToCSV("Creare cont utilizator");
                        System.out.println("Introduceti orice tasta pentru a continua");
                        ch = scanner.next().charAt(0);
                        scanner.nextLine();
                        break;
                    }
                    else {
                        System.out.println();
                        System.out.println("Introduceti orice tasta X pentru va intoarce la meniul de autentificare");
                        System.out.println("Introduceti orice alta tasta pentru a reintroduce datele");
                        ch = scanner.next().charAt(0);
                        scanner.nextLine();
                        if(ch == 'X' || ch == 'x') break;
                    }


                }
            }
            else if(ch == 'X' || ch == 'x') {
                break;
            }
            else {
                unkownKey();
            }
        }
    }

    public void meniuUser(User us) {
        while(true) {
            System.out.println("Pentru a cauta o productie media introduceti tasta C");
            System.out.println("Pentru a afisa detaliile unei productii media introduceti tast D");
            System.out.println("Pentru a fisa toate productiile media introduceti tasta T");
            System.out.println("Pentru a va afisa lista de review-uri introduceti tasta L");
            System.out.println("Pentru a vedea watch listul introduceti tasta W");
            System.out.println("Pentru a va deconecta introduceti tasta X");
            char ch = scanner.next().charAt(0);
            scanner.nextLine();

            if(ch == 'X' || ch == 'x') {
                break;
            }
            else if(ch == 'D' || ch == 'd') {
                displayDetailed(us);
            }
            else if(ch == 'C' || ch == 'c') {
                displayMedia(us);
            }

            else if(ch == 'T' || ch == 't') {
                displayMediaAll(us);
            }

            else if(ch == 'L' || ch == 'l') {
                displayReviews(us);
            }

            else if(ch == 'W' || ch == 'w') {
                displayWatchList(us);
            }

            else {
                unkownKey();
            }
        }
    }

    public void meniuAdmin(Admin ad) {
        while(true) {
            System.out.println("Pentru a adauga un nou anime introduceti tasta A");
            System.out.println("Pentru a adauga un nou roman introduceti tasta R");
            System.out.println("Pentru a adauga o noua serie manga introduceti tasta M");
            System.out.println("Pentru a afisa toate productiile media introduceti tasta T");
            System.out.println("Pentru a cauta o productie media introduceti tasta C");
            System.out.println("Pentru a afisa detaliile unei productii media introduceti tasta D");
            System.out.println("Pentru a sterge o productie media introduceti tasta E");
            System.out.println("Pentru a va deconecta introduceti tasta X");
            char ch = scanner.next().charAt(0);
            scanner.nextLine();

            if(ch == 'A' || ch == 'a') {
                String name = readNameMedia();
                Anime an = new Anime(name);
                Media md = new Media(an);
                CRUD<Media> cr = CRUD.getInstance();
                cr.create(md);
                cr.create(an);
                writer.exportToCSV("Creare anime");
                returnKey();
            }

            else if(ch == 'R' || ch == 'r') {
                String name = readNameMedia();
                String author = readNameAuthor();
                Novel nv = new Novel(name, author);
                Media md = new Media(nv);
                CRUD<Media> cr = CRUD.getInstance();
                cr.create(md);
                cr.create(nv);
                writer.exportToCSV("Creare roman");
                returnKey();
            }

            else if(ch == 'M' || ch == 'm') {
                String name = readNameMedia();
                String author = readNameAuthor();
                Manga mg = new Manga(name, author);
                Media md = new Media(mg);
                CRUD<Media> cr = CRUD.getInstance();
                cr.create(md);
                cr.create(mg);
                writer.exportToCSV("Creare manga");
                returnKey();
            }

            else if(ch == 'T' || ch == 't') {
                displayMediaAll(ad);
            }

            else if(ch == 'C' || ch == 'c') {
                displayMedia(ad);
            }

            else if(ch == 'D' || ch == 'd') {
                displayDetailed(ad);
            }

            else if(ch == 'E' || ch == 'e') {
                eraseMedia();
            }

            else if(ch == 'X' || ch == 'x') {
                break;
            }

            else {
                unkownKey();
            }
        }
    }

    public void unkownKey() {
        System.out.println("Comanda nerecunoscuta introduceti orice tasta pentru a reincarca meniul");
        scanner.next().charAt(0);
        scanner.nextLine();
    }

    public char returnKey() {
        System.out.println("Pentru a va intoarce introduceti orice alta tasta");
        char ch = scanner.next().charAt(0);
        scanner.nextLine();
        return ch;
    }

    public char idNotFound() {
        System.out.println("ID-ul nu corespunde nici unei productii\n" +
                "Pentru a va intoarce introduceti tasta X\n" +
                "Pentru a reintroduce id-ul, intoduceti orice alta tasta");
        char ch = scanner.next().charAt(0);
        scanner.nextLine();
        return ch;
    }

    public String readNameAccount() {
        System.out.println("Introduceti numele: ");
        String name = scanner.next();
        scanner.nextLine();
        return name;
    }

    public String readPassword() {
        System.out.println("Introduceti parola: ");
        String password = scanner.next();
        scanner.nextLine();
        return password;
    }

    public String readNameMedia() {
        System.out.println("Introduceti numele: ");
        String name = scanner.nextLine().trim();
        System.out.println();
        return name;
    }

    public String readNameAuthor() {
        System.out.println("Introduceti numele autorului: ");
        String name = scanner.nextLine().trim();
        System.out.println();
        return name;
    }

    public Integer readID() {
        System.out.println("Intoduceti id-ul: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return id;
    }

    public void displayMediaAll(Account ac) {
        writer.exportToCSV("Afisare toate productiile media");
        CRUD<Media> cr = CRUD.getInstance();
        List<Media> data = cr.read(Media.class);
        System.out.println("Lista productiilor media");
        for(Media md: data) {
            System.out.println(md.toString());
        }

        System.out.println("Pentru a afisa detaliile unei productii media introduceti tasta D");
        char ch = returnKey();

        if(ch == 'D' || ch == 'd') {
            displayDetailed(ac);
            returnKey();
        }
    }

    public void displayMedia(Account ac) {
        writer.exportToCSV("Cautare productii media");
        String name = readNameMedia().toLowerCase();
        List<Media> data = opService.getMediaByName(name);
        System.out.println("Lista productiilor media");
        for(Media md: data) {
            System.out.println(md.toString());
        }

        System.out.println("Pentru a afisa detaliile unei productii media introduceti tasta D");
        char ch = returnKey();

        if(ch == 'D' || ch == 'd') {
            displayDetailed(ac);
            returnKey();
        }

    }

    public Episode readEpisode(int id_anime) {
        String name = readNameMedia();
        System.out.println("Intoduceti durata episodului: ");
        int ln = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return new Episode(name, ln, id_anime);
    }


    public Chapter readChapter(int id_manga) {
        String name = readNameMedia();
        System.out.println();
        return new Chapter(name, id_manga);
    }


    public Volume readVolum(int id_novel) {
        String name = readNameMedia();
        System.out.println("Intoduceti numarul de pagini: ");
        int pg = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return new Volume(name, pg, id_novel);
    }

    public Media getValidMedia() {
        Media md = null;
        while(md == null) {
            int id = readID();
            md =  opService.getDetailedInfo(id);
            if (md == null) {
                char ch = idNotFound();
                if(ch == 'X' || ch == 'x') return null;
            }
        }
        return md;
    }


    public void displayDetailed(Account ac) {
        Media md = getValidMedia();
        if(md == null) return;

        writer.exportToCSV("Afisare detaliata productie media");
        if(ac instanceof Admin) {
            if(md instanceof Anime) {
                while(true) {
                    System.out.println(md);
                    System.out.println("Pentru a adauga un episod introduceti tasta A");
                    System.out.println("Pentru a sterge un episod introduceti tasta S");
                    char ch = returnKey();
                    CRUD<Episode> cr = CRUD.getInstance();
                    if (ch == 'A' || ch == 'a') {
                        Episode ep = readEpisode(md.getId());
                        cr.create(ep);
                        writer.exportToCSV("Creare episod");
                        returnKey();

                    }
                    else if (ch == 'S' || ch == 's') {
                        int idEpisod = readID();
                        cr.delete(Episode.class, idEpisod);
                        System.out.println("Episodul a fost sters");
                        writer.exportToCSV("Stergere episod");
                        returnKey();
                    }
                    else break;
                }
            }

            else if (md instanceof Novel) {
                while(true) {
                    System.out.println(md);
                    System.out.println("Pentru a adauga un volum introduceti tasta A");
                    System.out.println("Pentru a sterge un volum introduceti tasta S");
                    char ch = returnKey();
                    CRUD<Volume> cr = CRUD.getInstance();
                    if (ch == 'A' || ch == 'a') {
                        Volume vl = readVolum(md.getId());
                        cr.create(vl);
                        writer.exportToCSV("Creare volum");
                        returnKey();
                    }
                    else if (ch == 'S' || ch == 's') {
                        int idVolume = readID();
                        cr.delete(Volume.class, idVolume);
                        System.out.println("Volumul a fost sters");
                        writer.exportToCSV("Stergere volum");
                        returnKey();
                    }
                    else break;
                }
            }

            else if (md instanceof Manga) {
                while (true) {
                    System.out.println(md);
                    System.out.println("Pentru a adauga un capitol introduceti tasta A");
                    System.out.println("Pentru a sterge un captiol introduceti tasta S");
                    char ch = returnKey();
                    CRUD<Chapter> cr = CRUD.getInstance();
                    if (ch == 'A' || ch == 'a') {
                        Chapter chap = readChapter(md.getId());
                        cr.create(chap);
                        writer.exportToCSV("Creare capitol");
                        returnKey();
                    }
                    else if (ch == 'S' || ch == 's') {
                        int idCapitol = readID();
                        cr.delete(Chapter.class, idCapitol);
                        System.out.println("Capitolul a fost sters");
                        writer.exportToCSV("Stergere capitol");
                        returnKey();
                    }
                    else break;
                }
            }
        }

        else if(ac instanceof User) {
            while(true) {
                md =  opService.getDetailedInfo(md.getId());
                System.out.println(md);
                CRUD<Review> cr = CRUD.getInstance();
                Review r = opService.getReview(ac.getId(), md.getId());
                if(r != null) {
                    System.out.println("Nota oferita: " + String.valueOf(r.getRating()));

                }
                WatchList w = opService.getWatchListItem(ac.getId(), md.getId());
                if(w == null) {
                    System.out.println("Productia nu se afla in watch list \n");
                    System.out.println("Pentru a adauga productia din watch list introduceti tasta W");
                }
                else {
                    System.out.println("Productia se afla in watch list \n");
                    System.out.println("Pentru a sterge productia din watch list introduceti tasta W");
                }

                System.out.println("Pentru a adauga un review sau al actualiza pe cel vechi introduceti tasta R");
                if(r != null) System.out.println("Pentru a va sterge review-ul introduceti tasta S");
                char ch = returnKey();
                if(ch == 'R') {
                    reviewAdd((User) ac, md);
                    returnKey();
                }
                else if(ch == 'W' || ch == 'w') {
                    if(w == null) addToWatch((User) ac, md);
                    else removeFromWatch((User) ac, md);
                    returnKey();
                }
                else if((ch == 'S' || ch == 's') && r != null) {
                    reviewErase((User) ac, md, true);
                    returnKey();
                }
                else break;
            }
        }

    }

    void eraseMedia() {
        int id = readID();
        Media md = opService.getDetailedInfo(id);

        if(md.getCategory() != null) {
            if (md.getCategory().equals("anime")) {
                List<Episode> episodes = operationsService.getInstance().getEpisodes(md.getId());
                CRUD<Episode> cr = CRUD.getInstance();
                for (Episode ep : episodes) {
                    cr.delete(Episode.class, ep.getId());
                }
                CRUD<Anime> cran = CRUD.getInstance();
                cran.delete(Anime.class, md.getId());
                writer.exportToCSV("Stergere anime");
            }

            if (md.getCategory().equals("manga")) {
                List<Chapter> chapters = operationsService.getInstance().getChapter(md.getId());
                CRUD<Chapter> cr = CRUD.getInstance();
                for (Chapter ch : chapters) {
                    cr.delete(Chapter.class, ch.getId());
                }
                CRUD<Manga> crmg = CRUD.getInstance();
                crmg.delete(Manga.class, md.getId());
                writer.exportToCSV("Stergere manga");
            }

            if (md.getCategory().equals("novel")) {
                List<Volume> volumes = operationsService.getInstance().getVolumes(md.getId());
                CRUD<Volume> cr = CRUD.getInstance();
                for (Volume vl : volumes) {
                    cr.delete(Volume.class, vl.getId());
                }
                CRUD<Novel> crnv = CRUD.getInstance();
                crnv.delete(Novel.class, md.getId());
                writer.exportToCSV("Stergere novel");
            }
        }

        CRUD<Media> cr = CRUD.getInstance();
        cr.delete(Media.class, md.getId());

        System.out.println("Productia media a fost stearsa");
        returnKey();
    }

    public void reviewAdd(User us, Media md) {
        while(true) {
            System.out.println("Nota trebuie sa fie un numar natural intre 1 si 10");
            System.out.println("Intoduceti nota: ");
            int nota = scanner.nextInt();
            scanner.nextLine();
            if(nota >= 1 && nota <= 10) {
                reviewErase(us, md, false);
                CRUD<Review> cr = CRUD.getInstance();
                Review r = new Review(nota, md.getId(), us.getId());
                cr.create(r);
                System.out.println("Review-ul a fost adaugat/actualizat");
                writer.exportToCSV("Adaugare/Actualizare review");
                opService.ratingUpdate(md);
                writer.exportToCSV("Actualizare rating productie Media");
                break;
            }
            else {
                System.out.println("Nota nu respecta cerintele, introduceti orice tasta inafara de X pentru a reintroduce nota");
                System.out.println("Introduceti tasta X pentru a va intoarce");
                char ch = scanner.next().charAt(0);
                scanner.nextLine();
                if(ch == 'X') {
                    break;
                }
            }
        }
    }

    void reviewErase(User us, Media md, boolean flag) {
        CRUD<Review> cr = CRUD.getInstance();
        Review r = opService.getReview(us.getId(), md.getId());
        if(r != null) cr.delete(Review.class, r.getId());
        if(flag) {
            System.out.println("Review-ul a fost sters");
            writer.exportToCSV("Stergere review");
            opService.ratingUpdate(md);
            writer.exportToCSV("Actualizare rating productie Media");
        }
    }

    void displayReviews(User us) {
        writer.exportToCSV("Afisare review-uri");
        while(true) {
            System.out.println("Lista review-urilor:\n");
            List<Review> data = opService.getReviewList(us.getId());
            for(Review r: data) {
                Media md = opService.getDetailedInfo(r.getId_media());
                System.out.print(md.shortDescription());
                System.out.println("Nota oferita: " + String.valueOf(r.getRating()));
                System.out.println();
            }

            System.out.println("Pentru a adauga o recenzie introduceti tasta A");
            System.out.println("Pentru a sterge o recenzie introduceti tasta S");
            char ch = returnKey();
            if(ch == 'A' || ch == 'a' || ch == 'S' || ch == 's') {
                Media md = getValidMedia();

                if(md != null) {

                    if (ch == 'A' || ch == 'a') {
                        reviewAdd(us, md);
                        returnKey();
                    }
                    else {
                        reviewErase(us, md, true);
                        returnKey();
                    }
                }
            }
            else break;
        }
    }

    void displayWatchList(User us) {
        writer.exportToCSV("Afisare watch list");
        while(true) {
            System.out.println("WatchList:\n");
            List<WatchList> data = opService.getWatchList(us.getId());
            for(WatchList w: data) {
                Media md = opService.getDetailedInfo(w.getId_media());
                System.out.println(md.shortDescription());
            }

            System.out.println("Pentru a adauga o productie in watch list introduceti tasta A");
            System.out.println("Pentru a sterge o productie din watch list intoduceti tasta S");
            char ch = returnKey();
            if(ch == 'A' || ch == 'a' || ch == 'S' || ch == 's') {
                Media md = getValidMedia();

                if(md != null) {

                    if (ch == 'A' || ch == 'a') {
                        addToWatch(us, md);
                        returnKey();
                    }
                    else {
                        removeFromWatch(us, md);
                        returnKey();
                    }
                }
            }
            else break;
        }
    }

    void addToWatch(User us, Media md) {
        WatchList w = new WatchList(md.getId(), us.getId());
        CRUD<WatchList> cr = CRUD.getInstance();
        cr.create(w);
        writer.exportToCSV("Adaugare productie watch list");
        System.out.println("Productia media a fost adaugata in watch list");
    }

    void removeFromWatch(User us, Media md) {
        CRUD<WatchList> cr = CRUD.getInstance();
        WatchList w = opService.getWatchListItem(us.getId(), md.getId());
        if(w != null) cr.delete(WatchList.class, w.getId());
        writer.exportToCSV("Stergere productie watch list");
        System.out.println("Productia media a fost stearsa din watch list");
    }
}

