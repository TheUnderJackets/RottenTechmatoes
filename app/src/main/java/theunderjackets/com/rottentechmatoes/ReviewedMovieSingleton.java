package theunderjackets.com.rottentechmatoes;

import android.content.Context;


import java.util.List;
import java.util.ArrayList;

/**
 * Created by Hudson Lynam on 3/6/2016.
 */
public final class ReviewedMovieSingleton {
    private static ReviewedMovieSingleton movieInstance;
    private static List<Movie> reviewedMovies;

    private ReviewedMovieSingleton() {
        reviewedMovies = new ArrayList<Movie>();
    }

    /**
     * Singleton instance accessor
     * @param context current context in application
     * @return single instance of the class
     */
    public static ReviewedMovieSingleton getInstance(Context context) {
        if (movieInstance == null) {
            movieInstance = new ReviewedMovieSingleton();
        }
        return movieInstance;
    }

    public List<Movie> getMovies() {
        return reviewedMovies;
    }
    public void addMovie(Movie m) {
        reviewedMovies.add(m);
    }
    public void addMovie(Movie m, int i) {
        reviewedMovies.add(i, m);
    }
    public void removeMovie(Movie m) {
        reviewedMovies.remove(m);
    }
    public void removeMovie(int m) {
        reviewedMovies.remove(m);
    }
}
