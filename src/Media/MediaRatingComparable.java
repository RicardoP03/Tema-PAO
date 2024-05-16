package Media;
import java.util.Comparator;

public class MediaRatingComparable implements Comparator<Media>{
    @Override
    public int compare(Media m1, Media m2) {
        int ratingComparison = Double.compare(m2.getRating(), m1.getRating());
        if (ratingComparison == 0) {
            return Integer.compare(m1.getId(), m2.getId());
        }
        return ratingComparison;
    }
}
