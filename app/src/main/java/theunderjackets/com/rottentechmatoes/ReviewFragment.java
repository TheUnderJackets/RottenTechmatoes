package theunderjackets.com.rottentechmatoes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

import java.util.List;

/**
 * Created by Lixin on 3/2/2016
 */
public class ReviewFragment extends Fragment {
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    private static Button submitButton;
    private static Button cancelButton;
    private static TextView textView;
    private static RatingBar ratingBar;
    private static EditText reviewBox;
    private static Activity activity;
    private String pos;


    public ReviewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //listenerForRatingBar();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                if(v == getView().findViewById(R.id.submitReview)) {
                    fragment = new ReviewListFragment();
                }
                if(v == getView().findViewById(R.id.cancelReview)) {
                    getActivity().getFragmentManager().popBackStack();
                }
                android.support.v4.app.FragmentManager manager = getFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

            }
        };

    }
    public void listenerForRatingBar(View view) {
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar2);
        textView = (TextView) view.findViewById(R.id.yourRate);
        ratingBar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        textView.setText(String.valueOf(rating));
                    }
                }
        );
    }

    public void onButtonClickListener(View view) {
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar2);
        submitButton = (Button) view.findViewById(R.id.submitReview);
        cancelButton = (Button) view.findViewById(R.id.cancelReview);
        reviewBox = (EditText) view.findViewById(R.id.reviewBox);
        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ratingBar.getRating() == 0) {
                            makeText(getContext(), "Rating cannot be empty.", Toast.LENGTH_SHORT).show();
                        } else {
                            makeText(getContext(), String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
                            User user = CurrentUser.getInstance().getUser();
                            Movie.addUserRating(ratingBar.getRating(), user);
                            ReviewedMovieSingleton review = ReviewedMovieSingleton.getInstance(getActivity());
                            Movie movie = MovieDetailFragment.getCurrent().getMovie();
                            List<Movie> movies = review.getMovies();
                            boolean added = true;
                            int j = 0;
                            while (j < movies.size() && added) {
                                if (movies.get(j).getTitle().equals(movie.getTitle())) {
                                    added = false;
                                }
                                j++;
                            }
                            if (added) {
                                review.addMovie(movie);
                            }
                            getActivity().getFragmentManager().popBackStack();
                            cancelButton.setVisibility(View.GONE);
                            submitButton.setVisibility(View.GONE);
                            //Add the stuff to add the review text here, right below here

                            reviewBox.setText("");
                            reviewBox.setVisibility(View.GONE);
                            android.support.v4.app.FragmentTransaction trans = getFragmentManager().beginTransaction();
                            Fragment goal = new ReviewListFragment();
                            trans.replace(R.id.reviewFragment, goal);
                            trans.commit();
                        }
                    }

                }
        );
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction trans = getFragmentManager().beginTransaction();
                Fragment goal = new ReviewListFragment();
                trans.replace(R.id.reviewFragment, goal);
                trans.commit();
                cancelButton.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);
                reviewBox.setText("");
                reviewBox.setVisibility(View.GONE);


            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_fragment, container, false);

        listenerForRatingBar(view);
        onButtonClickListener(view);
        activity = this.getActivity();
        return view;

    }

}

