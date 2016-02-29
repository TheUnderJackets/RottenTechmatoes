package theunderjackets.com.rottentechmatoes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class MovieList implements Parcelable {
    private List<Movie> movies = new ArrayList<>();

    /**
     * Default constructor for constructing list.
     */
    public MovieList() {

    }

    /**
     * Private constructor for implementation of Parcelable.
     * @param in parcel used to construct the object
     */
    private MovieList(Parcel in) {
        this.movies = in.readArrayList(Movie.class.getClassLoader());
    }

    /**
     * Implementation of Parcelable. Ensures that the read/write are given in FIFO.
     * @param dest destination parcel
     * @param flags flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(movies);
    }

    /**
     * Basic implementation of describeContents(). For our purposes, we have no need to customize
     * the implementation.
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Constant for implementation of Parcelable.
     */
    public static final Parcelable.Creator<MovieList> CREATOR =
            new Parcelable.Creator<MovieList>() {

                @Override
                public MovieList createFromParcel(Parcel source) {
                    return new MovieList(source);
                }

                @Override
                public MovieList[] newArray(int size) {
                    return new MovieList[size];
                }
            };

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

    /**
     * Getter method for the list of movies.
     * @return list of movies
     */
    public List<Movie> getMovies() {
        return movies;
    }
}
