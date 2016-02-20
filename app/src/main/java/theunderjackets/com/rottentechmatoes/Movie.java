package theunderjackets.com.rottentechmatoes;

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
}
