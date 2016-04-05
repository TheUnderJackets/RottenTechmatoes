package theunderjackets.com.rottentechmatoes;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by will on 2/21/16.
 */
@SuppressWarnings("DefaultFileTemplate")
public final class Search {

    /*
     * Private constructor
     */
    private Search() {
    }

    public final static String MOVIES_EXTRAS = "theunderjackets.com.rottentechmatoes.Search.MovieList";

    /**
     * Search through movies based on a string query of a movie title
     *
     * @param limit amount of movies to list
     * @param activity where you are calling to
     * @param goal class that will perform type of search
     * @param query movie title searched for
     */
    public static void byKeyword(final int limit, Context activity, Class goal, String query) { //activity - from, goal - to
        query = query.replaceAll(" ", "+");
        RottenTomatoes.getMovies(RottenTomatoesRequest.MOVIE_KEYWORD_SEARCH, limit, activity, goal, new RTCallBack() {
            @Override
            public void fireIntent(MovieList list, Context thisActivityContext, Class goalClass) {
                for (int i = 0; i < list.getMovies().size(); i++) {
                    if (i < limit) {
                        Firebase moviesRef = new Firebase("https://rottentechmatoes.firebaseio.com/movies/" + list.getMovies().get(i).getId());
                        moviesRef.setValue(list.getMovies().get(i));
                    }
                }
                Intent intent = new Intent(thisActivityContext, goalClass);
                intent.putExtra(MOVIES_EXTRAS, list);
                thisActivityContext.startActivity(intent);
            }
        }, query);
    }

    /**
     * Search through movies based on to rentals
     *
     * @param limit amount of movies to list
     * @param activity where you are calling to
     * @param goal class that will perform type of search
     */
    public static void byRentals(final int limit, Context activity, Class goal) { //activity - from, goal - to
        RottenTomatoes.getMovies(RottenTomatoesRequest.TOP_RENTALS, limit, activity, goal, new RTCallBack() {
            @Override
            public void fireIntent(MovieList list, Context thisActivityContext, Class goalClass) {
                for (int i = 0; i < list.getMovies().size(); i++) {
                    if (i < limit) {
                        Firebase moviesRef = new Firebase("https://rottentechmatoes.firebaseio.com/movies/" + list.getMovies().get(i).getId());
                        moviesRef.setValue(list.getMovies().get(i));
                    }
                }
                Intent intent = new Intent(thisActivityContext, goalClass);
                intent.putExtra(MOVIES_EXTRAS, list);
                thisActivityContext.startActivity(intent);
            }
        });
    }

    /**
     * Search through movies based on new DVD releases
     *
     * @param limit amount of movies to list
     * @param activity where you are calling to
     * @param goal class that will perform type of search
     */
    public static void byNewDVD(final int limit, Context activity, Class goal) { //activity - from, goal - to
        RottenTomatoes.getMovies(RottenTomatoesRequest.NEW_RELEASES_DVD, limit, activity, goal, new RTCallBack() {
            @Override
            public void fireIntent(MovieList list, Context thisActivityContext, Class goalClass) {
                for (int i = 0; i < list.getMovies().size(); i++) {
                    if (i < limit) {
                        Firebase moviesRef = new Firebase("https://rottentechmatoes.firebaseio.com/movies/" + list.getMovies().get(i).getId());
                        moviesRef.setValue(list.getMovies().get(i));
                    }
                }
                Intent intent = new Intent(thisActivityContext, goalClass);
                intent.putExtra(MOVIES_EXTRAS, list);
                thisActivityContext.startActivity(intent);
            }
        });
    }

    /**
     * Search through movies based on new movie
     *
     * @param limit amount of movies to list
     * @param activity where you are calling to
     * @param goal class that will perform type of search
     */
    public static void byNewMovies(final int limit, Context activity, Class goal) { //activity - from, goal - to
        RottenTomatoes.getMovies(RottenTomatoesRequest.NEW_RELEASES_MOVIES, limit, activity, goal, new RTCallBack() {
            @Override
            public void fireIntent(MovieList list, Context thisActivityContext, Class goalClass) {
                for (int i = 0; i < list.getMovies().size(); i++) {
                    if (i < limit) {
                        Firebase moviesRef = new Firebase("https://rottentechmatoes.firebaseio.com/movies/" + list.getMovies().get(i).getId());
                        moviesRef.setValue(list.getMovies().get(i));
                    }
                }
                Intent intent = new Intent(thisActivityContext, goalClass);
                intent.putExtra(MOVIES_EXTRAS, list);
                thisActivityContext.startActivity(intent);
            }
        });
    }
    /**
     * Recommend movies based on major
     *
     * @param m the major we're recommending based on
     * @param activity where you are calling to
     * @param goal class that will perform type of search
     */
    public static void recommend(final Major m, final Context activity, Class goal) {
        final Intent intent = new Intent(activity, goal);
        Firebase reviewsRef = new Firebase("https://rottentechmatoes.firebaseio.com/reviews");
        final MovieList movies = new MovieList();
        reviewsRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Review> reviewsTotal = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    reviewsTotal.add(data.getValue(Review.class));
                }
                List<List<Review>> reviews = new ArrayList<>();
                Map<String, Double> ratings = new HashMap<>();
                if (dataSnapshot == null) {
                    intent.putExtra(MOVIES_EXTRAS, movies);
                    activity.startActivity(intent);
                } else {
                    String currentId = "";
                    List<Review> reviewNest = new ArrayList<>();
                    double runningSum = 0;
                    double runningCount = 0;
                    for (Review review : reviewsTotal) {
                        if (review.getMovieId().equals(currentId)) {
                            runningCount++;
                            runningSum += review.getRating();
                            reviewNest.add(review);
                        } else if (currentId.equals("")) {
                            currentId = review.getMovieId();
                            runningSum = review.getRating();
                            runningCount = 1;
                        } else {
                            ratings.put(currentId, runningSum / runningCount);
                            currentId = review.getMovieId();
                            reviews.add(reviewNest);
                            runningSum = review.getRating();
                            runningCount = 1;
                            reviewNest = new ArrayList<>();
                            reviewNest.add(review);
                        }
                    }
                    final List<Map.Entry<String, Double>> list =
                            new LinkedList<>(ratings.entrySet());
                    Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
                        @Override
                        public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                            return (-1) * (o1.getValue()).compareTo(o2.getValue());
                        }
                    });

                    final MovieList movies = new MovieList();
                    System.out.println("List Size: " + list.size());
                    for (int i = 0; i < list.size(); i++) {
                        List<Review> revs = new ArrayList<>();
                        for (List<Review> rev : reviews) {
                            if (rev.get(0).getMovieId().equals(list.get(i).getKey())) {
                                revs = rev;
                            }
                        }
                        final List<Review> movRev = revs;
                        if (i < list.size() - 1) {
                            Firebase movieRef = new Firebase("https://rottentechmatoes.firebaseio.com/movies");
                            movieRef.orderByChild("id").equalTo(list.get(i).getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                                        Movie movie = data.getValue(Movie.class);
                                        movie.addReviews(movRev);
                                        movies.addMovie(movie);
                                    }
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                    Toast toast = Toast.makeText(activity, "Sorry, a connection couldn't be made.", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            });
                        } else {
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                System.out.println("Timer didn't work!");
                            }
                            Firebase movieRef = new Firebase("https://rottentechmatoes.firebaseio.com/movies");

                            movieRef.orderByChild("id").equalTo(list.get(i).getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                                        Movie movie = data.getValue(Movie.class);
                                        movie.addReviews(movRev);
                                        movies.addMovie(movie);
                                    }
                                    intent.putExtra(MOVIES_EXTRAS, movies);
                                    activity.startActivity(intent);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                    Toast toast = Toast.makeText(activity, "Sorry, a connection couldn't be made.", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast toast = Toast.makeText(activity, "Sorry, a connection couldn't be made.", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }

}
