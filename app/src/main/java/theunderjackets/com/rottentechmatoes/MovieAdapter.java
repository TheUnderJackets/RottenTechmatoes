package theunderjackets.com.rottentechmatoes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hudson Lynam on 2/21/16.
 */
public class MovieAdapter extends ArrayAdapter<Movie>{

    //Add the UI stuff
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        //Here we provide the mapping from our object data to the list layout elements
        Movie item = (Movie)getItem(position);
        assert item != null;
        // Add the UI stuff here--textviews, ratings, etc.

        return view;

    }
    // Constructor of ArrayAdapter
    public MovieAdapter(Context context, int resource, int textViewResourceId, List<Movie> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }
}

