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
    /**
     * Recommend movies based on major
     *
     * @param m the major we're recommending based on
     * @param activity where you are calling to
     * @param goal class that will perform type of search
     */
    public static void recommend(Major m, Context activity, Class goal) {
        Intent intent = new Intent(activity, goal);
        ReviewedMovieSingleton movies = ReviewedMovieSingleton.getInstance(activity);
        List<Movie> temp = movies.getMovies();
        List<Movie> sortedlist = new ArrayList<>();
        //This makes a toast pop if there are no reviewed movies
        if (temp == null) {
            Toast currentToast = new Toast(activity);
            CharSequence msgText = "No Reviewed Movies exist";
            if (currentToast.getView().isShown()) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(activity, msgText, Toast.LENGTH_SHORT);
            currentToast.show();
        }
         else {
            int l = 0;
            int n = 0;
            boolean notadded;
            //This double while loop goes through each movie, checking all the users that have rated
            //it and seeing if any of those users have the Current User's major. If at least one does,
            //adds the movie to the recommendations list.
            while (l < temp.size()) {
                List<User> tempuser = temp.get(l).getUsers();
                notadded = true;
                while (n < tempuser.size() && notadded) {
                    if (tempuser.get(n).getMajor().equals(m)) {
                        sortedlist.add(temp.get(l));
                        notadded = false;
                    }
                    n++;
                }
                n = 0;
                l++;
            }
            int o;
            int p;
            for (Movie m1 : sortedlist) {
                System.out.println(m1.getTitle());
                for (Double d: m1.getUserRatings()) {
                    System.out.println(d);
                }
            }
            //This is a bubble sort for the list of movies given by the previous double while loop.
            //It sorts based on the sum of all the ratings by a valid (major matches the Current User)
            //user.
            for (o = 0; o < (sortedlist.size() - 1); o++) {
                for (p = 0; p < sortedlist.size() - o - 1; p++) {
                    if (comparerating(sortedlist.get(p), sortedlist.get(p + 1), m) < 0) {
                        Movie tempmov = sortedlist.get(p);
                        sortedlist.set(p, sortedlist.get(p + 1));
                        sortedlist.set(p+1, tempmov);
                    }
                }
            }
            for (Movie m1 : sortedlist) {
                System.out.println(m1.getTitle());
                for (Double d: m1.getUserRatings()) {
                    System.out.println(d);
                }
            }
            MovieList list = new MovieList();
            int k = 0;
            //This puts the sorted list into a MovieList, allowing for easy transfer to the activity.
            while (k < sortedlist.size()) {
                list.addMovie(sortedlist.get(k));
                k++;
            }
            intent.putExtra(MOVIES_EXTRAS, list);
            activity.startActivity(intent);
        }
    }
    /**
     * Compares movies for sorting based on rating
     *
     * @param a movie 1
     * @param b movie 2
     * @param major the major we're recommending based on
     * @return an int comparing the two movies
     */
    public static int comparerating(Movie a, Movie b, Major major) {
        double compa = 0;
        double compb = 0;
        List<User> usera = a.getUsers();
        List<User> userb = b.getUsers();
        List<Double> ratea = a.getUserRatings();
        List<Double> rateb = b.getUserRatings();
        int i = 0;
        //Sums up all the ratings by users of a given major for the first movie.
        while(i < usera.size()) {
            if (usera.get(i).getMajor().equals(major)) {
                compa = compa + ratea.get(i);
            }
            i++;
        }
        i = 0;
        //Sums up all the ratings by users of a given major for the second movie.
        while(i < userb.size()) {
            if (userb.get(i).getMajor().equals(major)) {
                compb = compb + rateb.get(i);
            }
            i++;
        }
        double comp = compa - compb;
        if (comp < 0) {
            return -1;
        }
        if (comp > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
