package theunderjackets.com.rottentechmatoes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;


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


    public ReviewFragment() {
    }

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //listenerForRatingBar(), this is a necessary part of the listener;
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
    public final void listenerForRatingBar(View view) {
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar2);
        textView = (TextView) view.findViewById(R.id.yourRate);
        ratingBar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBartemp, float rating, boolean fromUser) {
                        textView.setText(String.valueOf(rating));
                    }
                }
        );
    }

    public final void onButtonClickListener(View view) {
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
                            //review is not used now, but if adding the review text is
                            //implemented, it will be necessary
                            Review review = new Review(user.getEmail(), CurrentMovie.getInstance().getMovie().getId(), ratingBar.getRating(), reviewBox.getText().toString());
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
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_fragment, container, false);

        listenerForRatingBar(view);
        onButtonClickListener(view);
        return view;

    }

}
