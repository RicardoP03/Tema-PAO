package Headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Account{
    protected static Map<Media, Integer> reviews = new HashMap<>();
    protected static List<Media> watchlist = new ArrayList<>();
    private User(String name, String password) {
        super(name, password);
        usedNames.put(name, this);
    }

    public static boolean passwordCheck(String password) {

        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        boolean hasLength = password.length() >= 12;

        for(char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }

            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }

            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        if(!(hasLowerCase && hasUpperCase && hasDigit && hasLength)) {
            System.out.println("Parola trebuia sa aiba:");
            if(!hasLength) {
                System.out.println("- O lungime minima de 12 caractere");
            }

            if(!hasLowerCase){
                System.out.println("- Cel putin o litera mica");
            }

            if(!hasUpperCase){
                System.out.println("- Cel putin o litera mare");
            }

            if(!hasDigit){
                System.out.println("- Cel putin o cifra");
            }

            return false;
        }

        return true;
    }

    public static boolean nameCheck(String name) {
        if(name.isEmpty()) {
            System.out.println("Numele nu poate fi gol!");
            return false;
        }

        if(usedNames.containsKey(name)) {
            System.out.println("Numele este deja folosit!");
            return false;
        }

        for(char c: name.toCharArray()) {
            if(!Character.isLetterOrDigit(c)) {
                System.out.println("Numele trebuie sa contina doar litere si numere!");
                return false;
            }
        }

        return true;
    }
    public static User createAccount(String name, String password) {
        return new User(name, password);
    }

    public void addReview(Media md, int val) {
        if(!reviews.containsKey(md)) {
            md.addReview(val);
        }
        else {
            md.updateReview(val - reviews.get(md));
        }

        reviews.put(md, val);
        System.out.println("Review-ul a fost adaugat/actualizat");
    }

    public void eraseReview(Media md) {
        if(!reviews.containsKey(md)) {
            System.out.println("Nu ati overit nici un review acestei productii");
        }
        else {
            md.eraseReview(reviews.get(md));
            reviews.remove(md);
            System.out.println("Review-ul a fost sters");
        }
    }

    public String displayReview() {
        if(reviews.isEmpty()) {
            return "Nu ati adaugat nici un review";
        }
        StringBuilder sb = new StringBuilder();
        reviews.forEach((key, value) -> {
            sb.append(key.shortDescription())
                    .append("Nota oferita: ")
                    .append(Integer.toString(value)).append("\n\n");
        });
        return sb.toString();
    }

    public void addToWatch(Media md) {
        if(watchlist.contains(md)) {
            System.out.println("Productia se afla deja in watch list");
        }
        else {
            watchlist.add(md);
            System.out.println("Productia a fost adaugata la watch list");
        }
    }

    public void removeFromWatch(Media md) {
        watchlist.remove(md);
        System.out.println("Productia a fost scoasa din watch list\n");
    }

    public String displayWatch() {
        if(watchlist.isEmpty()) {
            return "Nu ati adaugat nici o productie in watch list";
        }
        StringBuilder sb = new StringBuilder();
        for(Media md: watchlist) {
            sb.append(md.shortDescription()).append("\n");
        }
        return sb.toString();
    }

}
