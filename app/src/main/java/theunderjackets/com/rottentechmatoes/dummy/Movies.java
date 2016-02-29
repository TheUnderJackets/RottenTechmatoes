package theunderjackets.com.rottentechmatoes.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import theunderjackets.com.rottentechmatoes.*;


/**
 *
 * Created by BensComputer on 2/20/16.
 */
public class Movies {
    /**
     * An array of State objects.
     */
    public static final List<theunderjackets.com.rottentechmatoes.Movie> ITEMS = new ArrayList<>();

    /**
     * A map of states  by Name.
     */
    public static final Map<String, theunderjackets.com.rottentechmatoes.Movie> ITEM_MAP = new HashMap<>();

    public static void addItem(theunderjackets.com.rottentechmatoes.Movie item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getTitle(), item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about State: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static void clear() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }
}
