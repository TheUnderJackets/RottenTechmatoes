package theunderjackets.com.rottentechmatoes;

/**
 * Created by will on 2/21/16.
 */
public final class Search {
    private static MovieList list = new MovieList();

    private Search() {
    }
    /**
     * Returns a list from searching by movie name
     * @param movieName name of searched movie
     * @return list containing searched movie
     */
    public static MovieList byName(String movieName) {
        list.addMovie(list.getMovieByTitle(movieName));
        return list;
    }

    /**
     * Returns a list from searching by movie id
     * @param id ID of searched movie
     * @return list containing searched movie
     */
    public static MovieList byID(String id) {
        list.addMovie(list.getMovieById(id));
        return list;
    }

    /**
     * Need more methods in MovieList to implement other search functions.
     */
}
