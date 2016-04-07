package theunderjackets.com.rottentechmatoes;

import java.util.Arrays;
import java.util.List;

/**
 * This entire class is currently not in use, still exists for possible future functionality. Also,
 * this is an enum, so the constants are self-explanatory.
 * Created by Andrew Suh on 2/18/2016.
 */
public enum Genre {

    ACTION_AND_ADVENTURE("Action & Adventure"),
    ANIMATION("Animation"),
    ART_HOUSE_AND_INTERNATIONAL("Art House & International"),
    CLASSICS("Classics"),
    COMEDY("Comedy"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    HORROR("Horror"),
    KIDS_AND_FAMILY("Kids & Family"),
    MUSICAL_AND_PERFORMING_ARTS("Musical & Performing Arts"),
    MYSTERY_AND_SUSPENSE("Mystery & Suspense"),
    ROMANCE("Romance"),
    SCIENCE_FICTION_AND_FANTASY("Science Fiction & Fantasy"),
    SPECIAL_INTEREST("Special Interest"),
    SPORTS_AND_FITNESS("Sports & Fitness"),
    TELEVISION("Television"),
    WESTERN("Western");

    private final String genreName;

    /**
     * Constructor for Genre with name parameter.
     * @param name name of the genre
     */
    Genre(String name) {
        genreName = name;
    }

    @Override
    public String toString() {
        return genreName;
    }

    /**
     * Getter method for the genre name.
     *
     * @return the string version of the genre
     */
    public String getName() {
        return genreName;
    }

    /**
     * Gets the list of genres in sorted order.
     *
     * @return list of sorted genres
     */
    public static List<Genre> getGenres() {
        return Arrays.asList(Genre.values());
    }
}
