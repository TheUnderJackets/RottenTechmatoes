package theunderjackets.com.rottentechmatoes;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import theunderjackets.com.rottentechmatoes.dummy.*;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "Title";

    /**
     * The dummy content this fragment is presenting.
     */
    protected Movie mItem;
    private static CurrentMovie current;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            if (getArguments().containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                mItem = Movies.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

                final Activity activity = this.getActivity();

                current = CurrentMovie.getInstance();
                current.setMovie(mItem);

                Toolbar appBarLayout = (Toolbar) activity.findViewById(R.id.detail_toolbar);
                if (appBarLayout != null) {
                    appBarLayout.setTitle(mItem.getTitle());
                    appBarLayout.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            activity.finish();
                            return false;
                        }
                    });
                }
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);

        // Show the content details
        if (mItem != null) {
            //Bitmap bmp = getBitmapFromURL(mItem.getThumbnailURL());
            //if (bmp != null) {
            //    ((ImageView) rootView.findViewById(R.id.thumbnail)).setImageBitmap(bmp);
            //}

            ((TextView) rootView.findViewById(R.id.textView_title)).setText(mItem.getTitle());
            ((TextView) rootView.findViewById(R.id.textView_release)).append(mItem.getTheaterReleaseDate());
            ((RatingBar) rootView.findViewById(R.id.movieRating)).setRating(mItem.getApiRating() / 20);
            ((TextView) rootView.findViewById(R.id.textView_synopsis)).setText(mItem.getSynopsis());
        }

        return rootView;
    }
    public static CurrentMovie getCurrent() {
        return current;
    }
}
