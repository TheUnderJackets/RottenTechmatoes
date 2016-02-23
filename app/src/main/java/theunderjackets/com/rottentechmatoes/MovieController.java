package theunderjackets.com.rottentechmatoes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hudson Lynam on 2/21/16
 */
public class MovieController extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_controller);

        //Get the List control reference (Step 2 above)
        ListView listView = (ListView) findViewById(R.id.listView);

        //Set the click listener here so we can trigger the detail page.
        listView.setOnItemClickListener(this);

        // Add the search stuff, need the context for getInstance()
        // RequestQueueSingleton mgr = RequestQueueSingleton.getInstance();
        // listView.setAdapter(new MovieAdapter(this, R.layout.list_item, R.id.textView, mgr.getMovies()));

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Go to Detail Activity
        // Need help for this
        // Intent i = new Intent(MovieController.this, DetailedMovieActivity.class);
        // Send the position number to Detail Activity too.
        // i.putExtra("position", position);
        // Run the process
        // startActivity(i);
    }
}