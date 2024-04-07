package Headers;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Meniu {
    private static final Scanner scanner = new Scanner(System.in);
    protected static Map<String, List<Media>> media = new HashMap<>();
    private static Meniu instance;
    private Meniu() {}

    public static Meniu getInstance() {
        if (instance == null) {
            synchronized (Meniu.class) {
                if (instance == null) {
                    instance = new Meniu();
                }
            }
        }
        return instance;
    }

    public void meniuAutentificare() {
        Admin.getInstance();
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

                    Account ac = Account.logIn(name, password);
                    if (ac != null) {
                        if (ac instanceof User) {
                            this.meniuUser((User) ac);
                        }

                        if (ac instanceof Admin) {
                            this.meniuAdmin((Admin) ac);
                            break;
                        }
                    } else {
                        System.out.println();
                        System.out.println("introduceti orice tasta X pentru va intoarce la meniul de autentificare");
                        System.out.println("introduceti orice alta tasta pentru a reintroduce datele");
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

                    boolean checkedName =  User.nameCheck(name);
                    System.out.println();
                    boolean checkedPassword = User.passwordCheck(password);
                    if(checkedName &&checkedPassword) {
                        User.createAccount(name, password);
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
            System.out.println("Pentru a adauga o cauta o productie media introduceti tasta C");
            System.out.println("Pentru a afisa detaliile unei productii media introduceti tast D");
            System.out.println("Pentru a adauga un review sau al actualiza pe cel vechi introduceti tasta R");
            System.out.println("Pentru a sterge un review introduceti tasta S");
            System.out.println("Pentru a va afisa lista de review-uri introduceti tasta L");
            System.out.println("Pentru a vedea watch listul introduceti tasta W");
            System.out.println("Pentru a va deconecta introduceti tasta X");
            char ch = scanner.next().charAt(0);
            scanner.nextLine();

            if(ch == 'X' || ch == 'x') {
                break;
            }
            else if(ch == 'R' || ch == 'r') {
                while(true) {
                    int ID = readID();
                    Media md = Media.getById(ID);
                    if (md == null) {
                        ch = idNotFound();
                        if(ch == 'X' || ch == 'x') {
                            break;
                        }
                    }
                    else {
                        reviewAdd(us, md);
                        System.out.println("Pentru a va intoarce introduceti orice tasta");
                        scanner.next().charAt(0);
                        scanner.nextLine();
                        break;
                    }
                }
            }
            else if(ch == 'D' || ch == 'd') {
                displayDetailed(us);
            }
            else if(ch == 'C' || ch == 'c') {
                displayMedia(us);
            }
            else if(ch == 'S' || ch == 's') {
                reviewErase(us);
            }
            else if(ch == 'L' || ch == 'l') {
                System.out.println(us.displayReview());
                System.out.println();
                System.out.println("Pentru a va intoarce introduceti orice tasta");
                scanner.next().charAt(0);
                scanner.nextLine();
            }

            else if(ch == 'W' || ch == 'w') {
                while(true) {
                    System.out.println(us.displayWatch());
                    System.out.println("Pentru a adauga o productie in watch list A");
                    System.out.println("Pentru a sterge o productie din watch list S");
                    ch = returnKey();
                    if(ch == 'A' || ch == 'a') {
                        addToWatch(us);
                    }

                    else if(ch == 'S' || ch == 's') {
                        removeFromWatch(us);
                    }

                    else break;

                }
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
            System.out.println("Pentru a adauga o cauta o productie media introduceti tasta C");
            System.out.println("Pentru a afisa detaliile unei productii media introduceti tasta D");
            System.out.println("Pentru a va deconecta introduceti tasta X");
            char ch = scanner.next().charAt(0);
            scanner.nextLine();
            
            if(ch == 'A' || ch == 'a') {
                String name = readNameMedia();
                Anime an;
                an = ad.CreateAnime(name);
                addMedia(an);
            }

            else if(ch == 'R' || ch == 'r') {
                String name = readNameMedia();
                String author = readNameAuthor();
                Novel nv = ad.CreateNovel(name, author);
                addMedia(nv);
            }

            else if(ch == 'M' || ch == 'm') {
                String name = readNameMedia();
                String author = readNameAuthor();
                Manga mg = ad.CreateManga(name, author);
                addMedia(mg);
            }

            else if(ch == 'C' || ch == 'c') {
                displayMedia(ad);
            }

            else if(ch == 'D' || ch == 'd') {
                displayDetailed(ad);
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

    public void addMedia(Media md) {
        String name = md.getName().toLowerCase();
        if(media.containsKey(name)) {
            media.get(name).add(md);
        }
        else {
            media.put(name, new ArrayList<>());
            media.get(name).add(md);
        }
        System.out.println("Introduceti orice tasta pentru va intoarce la meniul de administrator");
        scanner.next().charAt(0);
        scanner.nextLine();
    }

    public void displayMedia(Account ac) {
        String name = readNameMedia().toLowerCase();
        for(Media md: media.get(name)) {
            System.out.println(md.shortDescription());
        }
        System.out.println("Pentru a afisa detaliile unei productii media introduceti tasta D");
        char ch = returnKey();

        if(ch == 'D' || ch == 'd') {
            displayDetailed(ac);
            returnKey();
        }

    }
    
    public Episode readEpisode() {
        String name = readNameMedia();
        System.out.println("Intoduceti durata episodului: ");
        int ln = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return new Episode(name, ln);
    }


    public Chapter readChapter() {
        String name = readNameMedia();
//        System.out.println("Intoduceti durata episodului: ");
//        int ln = scanner.nextInt();
        System.out.println();
        return new Chapter(name);
    }


    public Volume readVolum() {
        String name = readNameMedia();
        System.out.println("Intoduceti numarul de pagini: ");
        int pg = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return new Volume(name, pg);
    }


    public void displayDetailed(Account ac) {
        Media md = null;
        while(md == null) {
            int id = readID();
            md = Media.getById(id);
            if (md == null) {
                char ch = idNotFound();
                if(ch == 'X' || ch == 'x') return;
            }
        }

        if(ac instanceof  Admin) {
            if (md instanceof Anime) {
                System.out.println(md);
                System.out.println("Pentru a adauga un episod introduceti tasta A");
                System.out.println("Pentru a sterge un episod introduceti tasta S");
                char ch = returnKey();
                if (ch == 'A' || ch == 'a') {
                    ((Anime) md).addEpisode(readEpisode());
                    returnKey();
                }
                if (ch == 'S' || ch == 's') {
                    int idEpisod = readID();
                    ((Anime) md).removeEpisod(idEpisod);
                    returnKey();
                }
            }

            else if (md instanceof Novel) {
                System.out.println(md);
                System.out.println("Pentru a adauga un volum introduceti tasta A");
                System.out.println("Pentru a sterge un volum introduceti tasta S");
                char ch = returnKey();
                if (ch == 'A' || ch == 'a') {
                    ((Novel) md).addVolume(readVolum());
                    returnKey();
                }
                if (ch == 'S' || ch == 's') {
                    int idVolume = readID();
                    ((Novel) md).removeVolume(idVolume);
                    returnKey();
                }
            }

            else if (md instanceof Manga) {
                System.out.println(md);
                System.out.println("Pentru a adauga un capitol introduceti tasta A");
                System.out.println("Pentru a sterge un captiol introduceti tasta S");
                char ch = returnKey();
                if (ch == 'A' || ch == 'a') {
                    ((Manga) md).addChapter(readChapter());
                    returnKey();
                }
                if (ch == 'S' || ch == 's') {
                    int idCapitol = readID();
                    ((Manga) md).removeChapter(idCapitol);
                    returnKey();
                }
            }
        }

        else if(ac instanceof User) {
            System.out.println(md);
            System.out.println("Pentru a adauga un review sau al actualiza pe cel vechi introduceti tasta R");
            System.out.println("Pentru a va sterge review-ul introduceti tasta S");
            char ch = returnKey();
            if(ch == 'R') {
                reviewAdd((User) ac, md);
            }
            if(ch == 'S' || ch == 's') {
                ((User) ac).eraseReview(md);
                returnKey();
            }
        }

    }

    public void reviewAdd(User us, Media md) {
        while(true) {
            System.out.println("Nota trebuie sa fie un numar natural intre 1 si 10");
            System.out.println("Intoduceti nota: ");
            int nota = scanner.nextInt();
            scanner.nextLine();
            if(nota >= 1 && nota <= 10) {
                us.addReview(md, nota);
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

    void reviewErase(User us) {
        while(true) {
            int ID = readID();
            Media md = Media.getById(ID);
            if (md == null) {
                char ch = idNotFound();
                if (ch == 'X' || ch == 'x') {
                    break;
                }
            }
            else {
                us.eraseReview(md);
                returnKey();
                break;
            }
        }
    }
    void addToWatch(User us) {
        while(true) {
            int ID = readID();
            Media md = Media.getById(ID);
            if (md == null) {
                char ch = idNotFound();
                if(ch == 'X' || ch == 'x') {
                    break;
                }
            }
            else {
                us.addToWatch(md);
                break;
            }
        }
    }

    void removeFromWatch(User us) {
        while(true) {
            int ID = readID();
            Media md = Media.getById(ID);
            if (md == null) {
                char ch = idNotFound();
                if(ch == 'X' || ch == 'x') {
                    break;
                }
            }
            else {
                us.removeFromWatch(md);
                break;
            }
        }
    }
}

