package theunderjackets.com.rottentechmatoes.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import theunderjackets.com.rottentechmatoes.Movie;


/**
 *
 * Created by BensComputer on 2/20/16.
 */
//public constructor needed for proper list implementation
public class Movies {
    /**
     * An array of State objects.
     */
    public static final List<Movie> ITEMS = new ArrayList<>();

    /**
     * A map of states  by Name.
     */
    public static final Map<String, Movie> ITEM_MAP = new HashMap<>();

    /**
     * add items to list of movies
     * @param item movie to be added
     */
    public static void addItem(Movie item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getTitle(), item);
    }

    /**
     * clears movie list
     */
    public static void clear() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }
}
