package theunderjackets.com.rottentechmatoes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private Toast currentToast;
    private int incorrectLoginCounter = 0;
    public static final String USERSEXTRA = "theunderjackets.com.rottentechmatoes.usersextra";
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
                attemptLogin(username, password);
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

    /**
     * Attempts to login with username and password credentials.
     * @param username the username box
     * @param password the password box
     */
    private void attemptLogin(EditText username, EditText password) {
        final String userName = username.getText().toString();
        System.out.println(userName);
        final String passWord = password.getText().toString();
        password.setText("");
        loginUser(userName, passWord, this, new FireBaseCallBack() {
            @Override
            public void onPostExecute(final Object retValue, int exitCode, final Context cont) {
                if (exitCode == -1) {
                    Firebase usersRef = new Firebase("https://rottentechmatoes.firebaseio.com/users");
                    usersRef.orderByChild("isAdmin").equalTo(false).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ArrayList<User> users = new ArrayList<>();
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                users.add(data.getValue(User.class));
                            }
                            CurrentUser.getInstance().setUser((User) retValue);
                            Intent loginIntent = new Intent(cont, ManageUsersActivity.class);
                            loginIntent.putParcelableArrayListExtra(USERSEXTRA, users);
                            startActivity(loginIntent);
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                            CharSequence msgText = "No connection, sorry.";
                            if (currentToast != null && currentToast.getView().isShown()) {
                                currentToast.cancel();
                            }
                            currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
                            currentToast.show();
                        }
                    });
                } else if (exitCode == 0) {
                    Intent loginIntent = new Intent(cont, HomeActivity.class);
                    CurrentUser current = CurrentUser.getInstance();
                    current.setUser((User) retValue);
                    startActivity(loginIntent);
                } else {
                    if (exitCode == 1) {
                        CharSequence msgText = "This Username does not exist.";
                        if (currentToast != null && currentToast.getView().isShown()) {
                            currentToast.cancel();
                        }
                        currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
                        currentToast.show();
                    } else if (exitCode == 2) {
                        CharSequence msgText = "Sorry, this account has been banned";
                        if (currentToast != null && currentToast.getView().isShown()) {
                            currentToast.cancel();
                        }
                        currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
                        currentToast.show();
                    } else if (exitCode == 3) {
                        CharSequence msgText = "Sorry, this account has been locked, please contact an admin.";
                        if (currentToast != null && currentToast.getView().isShown()) {
                            currentToast.cancel();
                        }
                        currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
                        currentToast.show();
                    } else if (exitCode == 4) {
                        if (++incorrectLoginCounter >= 3) {
                            CharSequence msgText = "Too many login attempts. Your account has been locked.";
                            ((User) retValue).setLocked(true);
                            if (currentToast != null && currentToast.getView().isShown()) {
                                currentToast.cancel();
                            }
                            currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
                            currentToast.show();
                        } else {
                            CharSequence msgText = "Incorrect password, please try again.";
                            if (currentToast != null && currentToast.getView().isShown()) {
                                currentToast.cancel();
                            }
                            currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
                            currentToast.show();
                        }
                    }
                }
            }
        });
    }

    private void loginUser(String username, final String password, final Context cont, final FireBaseCallBack callback) {
        Firebase usersRef = new Firebase("https://rottentechmatoes.firebaseio.com/users");
        Query login = usersRef.orderByChild("userName").equalTo(username);
        login.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int exitCode = -2;
                if (dataSnapshot.getChildrenCount() == 0) {
                    exitCode = 1;
                    callback.onPostExecute(null, 1, cont);
                    return;
                }
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);
                         if (user.getIsAdmin()) {
                            exitCode = -1;
                        } else if (user.getBanned() == true) {
                            exitCode = 2;
                        } else if (user.getLocked()) {
                            exitCode = 3;
                        } else if(user.validatePassword(password)) {
                            exitCode = 0;
                        } else {
                            exitCode = 4;
                        }
                        callback.onPostExecute(user, exitCode, cont);
                        return;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                CharSequence msgText = "Sorry, connection to server could not be made.";
                if (currentToast != null && currentToast.getView().isShown()) {
                    currentToast.cancel();
                }
                currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
                currentToast.show();
            }
        });
    }

    //Cancels the login attempt
    private void cancel(View v) {
        finish();
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
