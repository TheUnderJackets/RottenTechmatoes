package theunderjackets.com.rottentechmatoes;

import android.content.Context;
import android.content.Intent;

/**
 * Created by will on 2/21/16.
 */
public final class Search {

    /*
     * Private constructor
     */
    private Search() {
    }

    public final static String MOVIES_EXTRAS = "theunderjackets.com.rottentechmatoes.Search.MovieList";

    /**
     * Search through movies based on a string query of a movie title
     *
     * @param limit amount of movies to list
     * @param activity where you are calling to
     * @param goal class that will perform type of search
     * @param query movie title searched for
     */
    public static void byKeyword(int limit, Context activity, Class goal, String query) { //activity - from, goal - to
        query = query.replaceAll(" ", "+");
        RottenTomatoes.getMovies(RottenTomatoesRequest.MOVIE_KEYWORD_SEARCH, limit, activity, goal, new RTCallBack() {
            @Override
            public void fireIntent(MovieList list, Context thisActivityContext, Class goalClass) {
                Intent intent = new Intent(thisActivityContext, goalClass);
                intent.putExtra(MOVIES_EXTRAS, list);
                thisActivityContext.startActivity(intent);
            }
        },query);
    }

    /**
     * Search through movies based on to rentals
     *
     * @param limit amount of movies to list
     * @param activity where you are calling to
     * @param goal class that will perform type of search
     */
    public static void byRentals(int limit, Context activity, Class goal) { //activity - from, goal - to
        RottenTomatoes.getMovies(RottenTomatoesRequest.TOP_RENTALS, limit, activity, goal, new RTCallBack() {
            @Override
            public void fireIntent(MovieList list, Context thisActivityContext, Class goalClass) {
                Intent intent = new Intent(thisActivityContext, goalClass);
                intent.putExtra(MOVIES_EXTRAS, list);
                thisActivityContext.startActivity(intent);
            }
        });
    }

    /**
     * Search through movies based on new DVD releases
     *
     * @param limit amount of movies to list
     * @param activity where you are calling to
     * @param goal class that will perform type of search
     */
    public static void byNewDVD(int limit, Context activity, Class goal) { //activity - from, goal - to
        RottenTomatoes.getMovies(RottenTomatoesRequest.NEW_RELEASES_DVD, limit, activity, goal, new RTCallBack() {
            @Override
            public void fireIntent(MovieList list, Context thisActivityContext, Class goalClass) {
                Intent intent = new Intent(thisActivityContext, goalClass);
                intent.putExtra(MOVIES_EXTRAS, list);
                thisActivityContext.startActivity(intent);
            }
        });
    }

    /**
     * Search through movies based on new movie
     *
     * @param limit amount of movies to list
     * @param activity where you are calling to
     * @param goal class that will perform type of search
     */
    public static void byNewMovies(int limit, Context activity, Class goal) { //activity - from, goal - to
        RottenTomatoes.getMovies(RottenTomatoesRequest.NEW_RELEASES_MOVIES, limit, activity, goal, new RTCallBack() {
            @Override
            public void fireIntent(MovieList list, Context thisActivityContext, Class goalClass) {
                Intent intent = new Intent(thisActivityContext, goalClass);
                intent.putExtra(MOVIES_EXTRAS, list);
                thisActivityContext.startActivity(intent);
            }
        });
    }
}
