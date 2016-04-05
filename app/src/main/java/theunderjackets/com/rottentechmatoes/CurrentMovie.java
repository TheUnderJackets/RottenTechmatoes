package theunderjackets.com.rottentechmatoes;

/**
 * Created by Hudson Lynam on 3/7/2016.
 */
public class CurrentMovie {
    private static CurrentMovie currentInstance = new CurrentMovie();
    private static Movie currentMovie;

    /**
     * empty CurrentMovie constructor
     */
    private CurrentMovie() {
    }

    /**
     * Getter method for the Singleton Pattern.
     * @return the instance of CurrentMovie
     */
    public static CurrentMovie getInstance() {
        return currentInstance;
    }

    /**
     * Getter methpd for the current movie.
     * @return the current movie
     * @throws IllegalStateException if there is no current movie
     */
    public Movie getMovie() {
        if (currentMovie == null) {
            throw new IllegalStateException("There is no current movie.");
        }
        return currentMovie;
    }

    /**
     * Setter method to set the current movie
     * @param movie movie to set the currentMovie to
     */
    public void setMovie(Movie movie) {
        currentMovie = movie;
    }
}
