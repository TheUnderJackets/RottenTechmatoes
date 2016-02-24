package theunderjackets.com.rottentechmatoes.dummy;

import java.io.Serializable;

import theunderjackets.com.rottentechmatoes.Genre;

/**
 *
 * Created by BenFrench on 2/20/16.
 */
public class Movie implements Serializable{
    private String id;
    private String title;
    private String year;
    private Genre[] genres;
    private int runtime;
    private String theaterReleaseDate;
    private String dvdReleaseDate;
    private double apiRating;
    private String synopsis;
    private String thumbnailURL;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getTheaterReleaseDate() {
        return theaterReleaseDate;
    }

    public String getDvdReleaseDate() {
        return dvdReleaseDate;
    }

    public double getApiRating() {
        return apiRating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getGenresString() {
        String movieDetails = "";
        for (Genre g: genres) {
            movieDetails = movieDetails + " " + g;

        }
        return movieDetails;
    }
}
