package theunderjackets.com.rottentechmatoes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_LOGIN_USERNAME = "theunderjackets.com.rottentechmatoes.USERNAME";
    private Toast currentToast;
    private int incorrectLoginCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        Button signInButton = (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin(v, username, password);
                username.requestFocus();
            }
        });
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(v);
            }
        });
    }
    //Attempt login based on text in @var username and @var password
    private void attemptLogin(View v, EditText username, EditText password) {
        String userName = username.getText().toString();
        System.out.println(userName);
        String passWord = password.getText().toString();
        password.setText("");
        if (userName.equals("user") && passWord.equals("pass")) {
            Intent loginIntent = new Intent(this, HomeActivity.class);
            loginIntent.putExtra(EXTRA_LOGIN_USERNAME, userName);
            startActivity(loginIntent);
        } else {
            if (++incorrectLoginCounter >= 3) {
                CharSequence msgText = "Too many incorrect login attempts.";
                if (currentToast != null && currentToast.getView().isShown()) {
                    currentToast.cancel();
                }
                currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
                currentToast.show();
            } else {
                CharSequence msgText = "Incorrect Login. Please try again.";
                if (currentToast != null && currentToast.getView().isShown()) {
                    currentToast.cancel();
                }
                currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
                currentToast.show();
            }
        }
    }

    //Cancels the login attempt
    private void cancel(View v) {
        finish();
    }

    // This is here to make it so that clicking outside of text field will remove focus from the
    // textbox.
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

}
