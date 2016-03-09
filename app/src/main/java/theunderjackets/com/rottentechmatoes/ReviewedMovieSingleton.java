package theunderjackets.com.rottentechmatoes;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Hudson Lynam on 3/6/2016.
 */
public class ReviewedMovieSingleton {
    private static ReviewedMovieSingleton movieInstance;
    private List<Movie> reviewedMovies;
    private Movie resultsMovie;
    private static Context cont;

    private ReviewedMovieSingleton(Context context) {
        cont = context;
        reviewedMovies = new ArrayList<Movie>();
    }

    /**
     * Singleton instance accessor
     * @param context current context in application
     * @return single instance of the class
     */
    public static ReviewedMovieSingleton getInstance(Context context) {
        if (movieInstance == null) {
            movieInstance = new ReviewedMovieSingleton(context);
        }
        return movieInstance;
    }

    public List<Movie> getMovies() {
        return reviewedMovies;
    }
    public void addMovie(Movie m) {
        reviewedMovies.add(m);
    }
}
