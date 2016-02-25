package theunderjackets.com.rottentechmatoes;

import android.content.Context;
import android.content.Intent;

/**
 * Created by will on 2/21/16.
 */
public final class Search {
//Different Search for each type
    //Keyword, new rel, top rentals

    private Search() {
    }

    /**
     * Returns a list from searching by movie name
     * @param movieName name of searched movie
     * @return list containing searched movie
     */
    public final static String MOVIES_EXTRAS = "theunderjackets.com.rottentechmatoes.Search.MovieList";
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