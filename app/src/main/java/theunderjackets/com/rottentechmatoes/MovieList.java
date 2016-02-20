package theunderjackets.com.rottentechmatoes;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class MovieList {
    private Set<Movie> movies = new HashSet<>();

    /**
     * A no args constructor that initiates an empty MovieList
     */
    private MovieList() {

    }

    /**
     * Adds the movie to the list.
     * @param movie Movie to be added
     */
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    /**
     * Checks if the list contains the movie.
     * @param movie Movie to check if in list
     * @return true if the movie is in the list, false otherwise
     */
    public boolean containsMovie(Movie movie) {
        return movies.contains(movie);
    }

    /**
     * Removes the movie from the list.
     * @param movie Movie to be removed
     */
    public void deleteMovie(Movie movie) {
        if (movies.contains(movie)) {
            movies.remove(movie);
        }
    }

    /**
     * Gets a movie by the movie id
     * @param id ID of the movie
     * @return movie if the movie is in the list
     * @throws java.util.NoSuchElementException if movie is not found
     */
    public Movie getMovieById(String id) {
        for (Movie m: movies) {
            if (id.equals(m.getId())) {
                return m;
            }
        }
        throw new NoSuchElementException("Movie is not in list.");
    }

    /**
     * Gets a movie by its title
     * @param title The title of the movie
     * @return movie if the movie is in the list
     * @throws java.util.NoSuchElementException if movie is not found
     */
    public Movie getMovieByTitle(String title) {
        for (Movie m: movies) {
            if (title.equals(m.getTitle())) {
                return m;
            }
        }
        throw new NoSuchElementException("Movie is not in list.");
    }
}
