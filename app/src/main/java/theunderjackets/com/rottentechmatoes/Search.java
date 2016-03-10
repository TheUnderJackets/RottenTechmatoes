package theunderjackets.com.rottentechmatoes;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.List;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

import android.widget.Toast;

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
    public static void recommend(Major m, Context activity, Class goal) {
        Intent intent = new Intent(activity, goal);
        ReviewedMovieSingleton movies = ReviewedMovieSingleton.getInstance(activity);
        List<Movie> temp = movies.getMovies();
        List<Movie> sortedlist = new ArrayList<Movie>();
        if (temp == null) {
            Toast currentToast = new Toast(activity);
            CharSequence msgText = "No Reviewed Movies exist";
            if (currentToast != null && currentToast.getView().isShown()) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(activity, msgText, Toast.LENGTH_SHORT);
            currentToast.show();
        }
         else {
            for (Movie movie : temp) {
                List<User> tempuser = movie.getUsers();
                for (User u : tempuser) {
                    if (u.getMajor().equals(m) && !sortedlist.contains(movie)) {
                        sortedlist.add(movie);
                    }
                }
            }
            final Major tempmajor = m;
            Collections.sort(sortedlist, new Comparator<Movie>() {
                public int compare(Movie a, Movie b) {
                    int comp = comparerating(a, b, tempmajor);
                    if (comp > 0) {
                        return 1;
                    }
                    if (comp < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            MovieList list = new MovieList();
            for (Movie movie : sortedlist) {
                list.addMovie(movie);
            }
            intent.putExtra(MOVIES_EXTRAS, list);
            activity.startActivity(intent);
        }
    }
    public static int comparerating(Movie a, Movie b, Major major) {
        double compa = 0;
        double compb = 0;
        List<User> usera = a.getUsers();
        List<User> userb = b.getUsers();
        List<Double> ratea = a.getUserRatings();
        List<Double> rateb = b.getUserRatings();
        int i = 0;
        while(i < usera.size()) {
            if (usera.get(i).getMajor().equals(major)) {
                compa = compa + ratea.get(i);
            }
            i++;
        }
        i = 0;
        while(i < userb.size()) {
            if (userb.get(i).getMajor().equals(major)) {
                compb = compb + rateb.get(i);
            }
            i++;
        }
        double comp = compa - compb;
        if (comp > 0) {
            return -1;
        }
        if (comp < 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
