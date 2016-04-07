package theunderjackets.com.rottentechmatoes;

import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by Hudson on 4/4/2016.
 */
public class MovieListTest {
    private MovieList movieListsample;
    private Movie m1;
    private Movie m2;
    private Movie m3;
    @Test
    public void checkValidString() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        assertSame(movieListsample.getMovieByTitle("LOTR"), m1);
        assertSame(movieListsample.getMovieByTitle("Hunger Games"), m2);
        assertSame(movieListsample.getMovieByTitle("Star Wars"), m3);
    }
    @Test(expected=IllegalArgumentException.class)
    public void testNull() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        movieListsample.getMovieByTitle(null);
    }
    @Test(expected=NoSuchElementException.class)
    public void testEmptyString() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        movieListsample.getMovieByTitle("");
    }
    @Test(expected=NoSuchElementException.class)
    public void testTabandNewline() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        movieListsample.getMovieByTitle("Hunger" + "\t" + "Games" + "\n");
    }
    @Test(expected=NoSuchElementException.class)
    public void testInvalidString() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        movieListsample.getMovieByTitle("Deadpool");
    }
    @Test(expected=NoSuchElementException.class)
    public void testCloseToValidString() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        movieListsample.getMovieByTitle("LOTR ");
    }
    //getMovieByID Tests
    @Test
    public void checkValidString2() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        assertSame(movieListsample.getMovieById("112233"), m1);
        assertSame(movieListsample.getMovieById("112244"), m2);
        assertSame(movieListsample.getMovieById("112255"), m3);
    }
    @Test(expected=IllegalArgumentException.class)
    public void testNull2() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        movieListsample.getMovieById(null);
    }
    @Test(expected=NoSuchElementException.class)
    public void testEmptyString2() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        movieListsample.getMovieById("");
    }
    @Test(expected=NoSuchElementException.class)
    public void testTabandNewline2() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        movieListsample.getMovieById("112" + "\t" + "255" + "\n");
    }
    @Test(expected=NoSuchElementException.class)
    public void testInvalidString2() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        movieListsample.getMovieById("111111");
    }
    @Test(expected=NoSuchElementException.class)
    public void testCloseToValidString2() {
        MovieList movieListsample = new MovieList();
        Movie m1 = new Movie("112233", "LOTR", 2001, 123, "July 18th, 2001", "4.8",
                "synopsis", "LOTR.com");
        Movie m2 = new Movie("112244", "Hunger Games", 2013, 115, "March 21th, 2013", "3.3", "synopsis",
                "HungerGames.com");
        Movie m3 = new Movie("112255", "Star Wars", 2015, 132, "December 13th, 2015", "4.4", "synopsis",
                "StarWars.com");
        movieListsample.addMovie(m1);
        movieListsample.addMovie(m2);
        movieListsample.addMovie(m3);
        movieListsample.getMovieById("112233 ");
    }

}