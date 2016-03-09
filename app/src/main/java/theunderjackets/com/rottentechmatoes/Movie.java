package theunderjackets.com.rottentechmatoes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {
    private String id;
    private String title;
    private int year;
    private int runtime;
    private String theaterReleaseDate;
    private String apiRating;
    private String synopsis;
    private String thumbnailURL;
    private static List<Double> userRatings = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static double userRating;

    public Movie(String id, String title, int year, int runtime, String theaterReleaseDate, String apiRating, String synopsis, String thumbnailURL, double userRating, ArrayList userRatings) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.theaterReleaseDate = theaterReleaseDate;
        this.apiRating = apiRating;
        this.synopsis = synopsis;
        this.thumbnailURL = thumbnailURL;
        this.userRating = userRating;
        this.userRatings = userRatings;
        this.users = users;
    }

    /**
     * Private constructor for implementation of Parcelable.
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
        this.userRating = in.readDouble();
        this.userRatings = in.readArrayList(Double.class.getClassLoader());


    }

    /**
     * Implementation of Parcelable. Ensures that the read/write are given in FIFO.
     * @param dest destination parcel
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
        dest.writeDouble(userRating);
        dest.writeList(userRatings);
        dest.writeList(users);
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


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public List<User> getUsers() {
        return users;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getTheaterReleaseDate() {
        return theaterReleaseDate;
    }

    public Float getApiRating() {
        return Float.parseFloat(apiRating);
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public double getUserRating() { return userRating; }

    /**
     * Method to add a user rating to a movie
     * @param rating the new rating to be added
     * @param user the user adding the new rating
     */
    public static void addUserRating(double rating, User user) {
        userRatings.add(rating);
        users.add(user);
        updateUserRating();
    }

    /**
     * Method to update the average user rating once a new rating is submitted
     */
    public static void updateUserRating() {
        int total = 0;
        int numElem = 0;
        for (int i = 0; i < userRatings.size(); i++) {
            total += userRatings.get(i);
            numElem++;
        }
        userRating = (total/numElem);
    }

    public List<Double> getUserRatings() { return userRatings; }
}

