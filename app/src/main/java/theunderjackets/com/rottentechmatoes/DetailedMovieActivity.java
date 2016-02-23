package theunderjackets.com.rottentechmatoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailedMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_movie_data);

        // Need search details, also need a context for getInstance()

        // RequestQueueSingleton movies = RequestQueueSingleton.getInstance();
        int position = getIntent().getIntExtra("position", 1);

        // Movie m = movies.getUserAt(position);

        // This is the UI stuff from the example, should replace with our UI stuff for the movie

        // TextView nameText = (TextView)findViewById(R.id.name);
        // nameText.setText(m.getId());

        // TextView emailText = (TextView)findViewById(R.id.email);
        // emailText.setText(m.getTitle());

        // TextView passText = (TextView) findViewById(R.id.password);
        // passText.setText(m.getYear());
    }
}
