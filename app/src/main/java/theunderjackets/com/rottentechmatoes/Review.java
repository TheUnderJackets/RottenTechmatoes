package theunderjackets.com.rottentechmatoes;

import android.os.Parcel;
import android.os.Parcelable;

import com.firebase.client.Firebase;

/**
 * Represents a review that users make on movies.
 * Created by Andrew Suh on 3/30/2016.
 */
public class Review implements Parcelable {
    private String userEmail;
    private String movieId;
    private double rating;
    private String review = "";
    private static final String URL = "https://rottentechmatoes.firebaseio.com/";

    /**
     * Default constructor for FireBase to use. Very much necessary.
     */
    public Review() {
    }

    /**
     * Constructor that should be used to construct a review.
     * @param email email of the reviewer
     * @param id id of the movie reviewed
     * @param rating rating given by user
     * @param review text of the review
     */
    public Review(String email, String id, double rating, String review) {
        this.movieId = id;
        this.userEmail = email;
        this.rating = rating;
        if (review != null) {
            this.review = review;
        } else {
            this.review = "";
        }
        final Firebase reviewRef = new Firebase(URL + "reviews").push();
        reviewRef.setValue(this);
    }


    /**
     * Getter method for user email. Necessary for FireBase.
     * @return email of reviewer
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Getter method for movie id. Necessary for FireBase.
     * @return movie id
     */
    public String getMovieId() {
        return movieId;
    }

    /**
     * Getter method for rating the review. Necessary for FireBase.
     * @return rating of the review
     */
    public double getRating() {
        return rating;
    }

    /**
     * Getter method for review text. Necessary for FireBase.
     * @return review text of review
     */
    public String getReview() {
        return review;
    }

    /**
     * Private constructor for implementation of Parcelable.
     *
     * @param in parcel used to construct the object
     */
    private Review(Parcel in) {
        this.userEmail = in.readString();
        this.movieId = in.readString();
        this.rating = in.readDouble();
        this.review = in.readString();
    }

    /**
     * Implementation of Parcelable. Ensures that the read/write are given in FIFO.
     *
     * @param dest  destination parcel
     * @param flags flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userEmail);
        dest.writeString(movieId);
        dest.writeDouble(rating);
        dest.writeString(review);
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
    public static final Parcelable.Creator<Review> CREATOR =
            new Parcelable.Creator<Review>() {

                @Override
                public Review createFromParcel(Parcel source) {
                    return new Review(source);
                }

                @Override
                public Review[] newArray(int size) {
                    return new Review[size];
                }
            };
}
