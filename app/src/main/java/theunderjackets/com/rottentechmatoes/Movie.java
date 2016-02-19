package theunderjackets.com.rottentechmatoes;

/**
 * Created by Andrew Suh on 2/18/2016.
 */
public class Movie {
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
    public Movie(String id, String title, String year, Genre[] genres, int runtime, String theaterReleaseDate, String dvdReleaseDate, double apiRating, String synopsis, String thumbnailURL) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.runtime = runtime;
        this.theaterReleaseDate = theaterReleaseDate;
        this.dvdReleaseDate = dvdReleaseDate;
        this.apiRating = apiRating;
        this.synopsis = synopsis;
        this.thumbnailURL = thumbnailURL;
    }
}
