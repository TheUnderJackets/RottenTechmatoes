package theunderjackets.com.rottentechmatoes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Lixin on 3/2/2016
 */
public class ReviewListFragment extends Fragment {
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReviewListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.review_list_fragment, container, false);
        final Button reviewMovie = (Button) v.findViewById(R.id.reviewMov);
        final Button viewTrailer = (Button) v.findViewById(R.id.viewTrailer);
        reviewMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction trans = getFragmentManager().beginTransaction();
                final Fragment goal = new ReviewFragment();
                trans.replace(R.id.reviewListFrame, goal);
                trans.commit();
                reviewMovie.setVisibility(View.GONE);
                viewTrailer.setVisibility(View.GONE);

            }

        });
        return v;

    }
}
