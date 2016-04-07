package theunderjackets.com.rottentechmatoes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    public static final String MOVIE_LIST_EXTRA = "theunderjackets.com.rottentechmatoes.MOVIELIST";
    private Toast currentToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final User user = CurrentUser.getInstance().getUser();
        final TextView welcomeUser = (TextView) findViewById(R.id.textViewWelcomeUser);

        final EditText nameSearch = (EditText) findViewById(R.id.searchEditText);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String keyword = nameSearch.getText().toString();
                if (keyword.equals("") || keyword == null) {
                    CharSequence msgText = "Search field cannot be empty.";
                    if (currentToast != null && currentToast.getView().isShown()) {
                        currentToast.cancel();
                    }
                    currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
                    currentToast.show();
                } else {
                    Search.byKeyword(10, HomeActivity.this, MovieListActivity.class, keyword);
                }
            }
        });

        Button topRentalsButton = (Button) findViewById(R.id.topRentButton);
        topRentalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search.byRentals(10, HomeActivity.this, MovieListActivity.class);
            }
        });

        Button newReleaseButton = (Button) findViewById(R.id.newReleaseButton);
        newReleaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search.byNewMovies(10, HomeActivity.this, MovieListActivity.class);
            }
        });

        Button newDVDButton = (Button) findViewById(R.id.newDVDButton);
        newDVDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search.byNewDVD(10, HomeActivity.this, MovieListActivity.class);
            }
        });

        Button recommendButton = (Button) findViewById(R.id.recommendButton);
        recommendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search.recommend(user.getMajor(), HomeActivity.this, MovieListActivity.class);
            }
        });
    }

    /**
     * Makes it so that edittext will not be focused on anymore once clicked out of.
     *
     * @param event click outside of box
     * @return super.dispatchTouchEvent(event)
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view instanceof EditText) {
                Rect out = new Rect();
                view.getGlobalVisibleRect(out);
                if (!out.contains((int) event.getRawX(), (int) event.getRawY())) {
                    view.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onResume() {
        super.onResume();
        final User user = CurrentUser.getInstance().getUser();
        final TextView welcomeUser = (TextView) findViewById(R.id.textViewWelcomeUser);
        welcomeUser.setText("Welcome " + user.getUserName() + "!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logged_in_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.view_profile) {
            Intent intent = new Intent(this, ViewProfileActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.edit_profile) {
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.logout) {
            CurrentUser.getInstance().setUser(null);
            Intent intent = new Intent(this, WelcomeScreenActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

}
