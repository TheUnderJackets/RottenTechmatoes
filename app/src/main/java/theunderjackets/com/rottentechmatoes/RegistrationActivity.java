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

public class RegistrationActivity extends AppCompatActivity {
    private Toast currentToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText emailaddress = (EditText) findViewById(R.id.emailaddress);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText passwordrepeat = (EditText) findViewById(R.id.repeatpassword);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegistration(v, emailaddress, name, username, password, passwordrepeat);
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancelRegistrationButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * Attempts to register a new user based on info entered.
     * @param v the current view
     * @param emailaddress the emailaddress box
     * @param username the username box
     * @param password the password box
     * @param passwordrepeat the retype password box
     */
    private void attemptRegistration(View v, EditText emailaddress, EditText name, EditText username, EditText password, EditText passwordrepeat) {
        String email = emailaddress.getText().toString();
        String aName = name.getText().toString();
        String userName = username.getText().toString();
        String pass = password.getText().toString();
        String passRepeat = passwordrepeat.getText().toString();
        if (email.equals("") || aName.equals("") || userName.equals("") || pass.equals("")) {
            CharSequence msgText = "This is a required field. Make sure to enter something.";
            if (currentToast != null && currentToast.getView().isShown()) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
            currentToast.show();
        } else if (!UserList.isUserNameValid(userName)) {
            CharSequence msgText = "Username has been taken. Please enter a different one.";
            if (currentToast != null && currentToast.getView().isShown()) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
            currentToast.show();
            username.setText("");
            username.requestFocus();
        } else if (!UserList.isEmailValid(email)) {
            CharSequence msgText = "Email is already taken. Enter a different one.";
            if (currentToast != null && currentToast.getView().isShown()) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
            currentToast.show();
            emailaddress.setText("");
            emailaddress.requestFocus();
        } else if (!pass.equals(passRepeat)){
            CharSequence msgText = "Repeat password does not match first password. Please try again.";
            if (currentToast != null && currentToast.getView().isShown()) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
            currentToast.show();
            password.setText("");
            passwordrepeat.setText("");
            password.requestFocus();
        } else {
            User newUser = new User(aName, email, pass, userName);
            UserList.addUser(newUser);
            finish();
        }
    }

    /**
     * Checks to see if the email used during registration is available or not.
     * @param email the email to check
     * @return true if email is unused, false otherwise
     */
    public static boolean isEmailValid(String email) {
        return true;
    }

    /**
     * Makes it so that edittext will not be focused on anymore once clicked out of.
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

}
