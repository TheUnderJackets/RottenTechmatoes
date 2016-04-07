package theunderjackets.com.rottentechmatoes;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements Parcelable {
    private String id;
    private String title;
    private int year;
    private int runtime;
    private String theaterReleaseDate;
    private String apiRating;
    private String synopsis;
    private String thumbnailURL;
    private final List<Review> reviews = new ArrayList<>();

    /**
     * empty Movie constructor
     */
    public Movie() {

    }

    /**
     * Movie constructor
     *
     * @param id                 of movie
     * @param title              of movie
     * @param year               movie was released
     * @param runtime            length of movie
     * @param theaterReleaseDate release date in theaters
     * @param apiRating          Rotten Tomatoes rating
     * @param synopsis           plot of movie
     * @param thumbnailURL       link to movie
     */
    public Movie(String id, String title, int year, int runtime, String theaterReleaseDate, String apiRating, String synopsis, String thumbnailURL) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.theaterReleaseDate = theaterReleaseDate;
        this.apiRating = apiRating;
        this.synopsis = synopsis;
        this.thumbnailURL = thumbnailURL;
    }

    /**
     * Private constructor for implementation of Parcelable.
     *
     * @param in parcel used to construct the object
     */
    private Movie(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.year = in.readInt();
        this.runtime = in.readInt();
        this.theaterReleaseDate = in.readString();
        this.apiRating = in.readString();
        this.synopsis = in.readString();
        this.thumbnailURL = in.readString();
        in.readTypedList(this.reviews, Review.CREATOR);
    }

    /**
     * Implementation of Parcelable. Ensures that the read/write are given in FIFO.
     *
     * @param dest  destination parcel
     * @param flags flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeInt(year);
        dest.writeInt(runtime);
        dest.writeString(theaterReleaseDate);
        dest.writeString(apiRating);
        dest.writeString(synopsis);
        dest.writeString(thumbnailURL);
        dest.writeTypedList(reviews);
    }

    /**
     * Basic implementation of describeContents(). For our purposes, we have no need to customize
     * the implementation.
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Constant for implementation of Parcelable.
     */
    public static final Parcelable.Creator<Movie> CREATOR =
            new Parcelable.Creator<Movie>() {

                @Override
                public Movie createFromParcel(Parcel source) {
                    return new Movie(source);
                }

                @Override
                public Movie[] newArray(int size) {
                    return new Movie[size];
                }
            };

    /**
     * gets the movie ID
     *
     * @return id of movie
     */
    public String getId() {
        return id;
    }

    /**
     * gets the movie title
     *
     * @return title of movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * gets the movie year
     *
     * @return year of movie
     */
    public int getYear() {
        return year;
    }

    /**
     * gets the movie runtime
     *
     * @return runtime of movie
     */
    public int getRuntime() {
        return runtime;
    }

    /**
     * gets the movie theater release date
     *
     * @return theater release of movie
     */
    public String getTheaterReleaseDate() {
        return theaterReleaseDate;
    }

    /**
     * gets the movie rating
     *
     * @return RottenTomatoes rating of movie
     */
    public String getApiRating() {
        return apiRating;
    }

    /**
     * gets the movie synopsis
     *
     * @return synopsis of movie
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * gets the movie thumbnailURL
     *
     * @return thumbnailURL of movie
     */
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    /**
     * gets the movie user reviews
     *
     * @return user reviews of movie
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Adds user review
     *
     * @param review of movie to add
     */
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * Adds user reviews
     *
     * @param reviews of movie to add
     */
    public void addReviews(Collection<Review> reviews) {
        for (final Review review : reviews) {
            this.reviews.add(review);
        }
    }
}

