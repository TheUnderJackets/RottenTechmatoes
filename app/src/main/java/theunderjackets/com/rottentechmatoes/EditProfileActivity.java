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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EditProfileActivity extends AppCompatActivity {
    private Toast currentToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        final User user = UserList.getUserByEmail(intent.getStringExtra(HomeActivity.EXTRA_LOGIN_USEREMAIL));
        TextView userNameText = (TextView) findViewById(R.id.textUsername);
        TextView nameText = (TextView) findViewById(R.id.textName);
        TextView emailText = (TextView) findViewById(R.id.textEmail);
        TextView majorText = (TextView) findViewById(R.id.textMajor);
        TextView descriptionText = (TextView) findViewById(R.id.textDescription);
        final EditText userNameTextField = (EditText) findViewById(R.id.inputUsername);
        userNameTextField.setText(user.getUserName());
        final EditText nameTextField = (EditText) findViewById(R.id.inputName);
        nameTextField.setText(user.getName());
        final EditText emailTextField = (EditText) findViewById(R.id.inputEmail);
        emailTextField.setText(user.getEmail());
        final Spinner majorSpinner = (Spinner) findViewById(R.id.majorSpinner);
        List<Major> majors = Major.getMajorList();
        ArrayAdapter<Major> majorAdapter = new ArrayAdapter<Major>(this, android.R.layout.simple_spinner_dropdown_item, majors);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(majorAdapter);
        majorSpinner.setSelection(majorAdapter.getPosition(user.getMajor()));
        final EditText descriptionTextField = (EditText) findViewById(R.id.inputDescription);
        if (!user.getDescription().equals("")) {
            descriptionTextField.setText(user.getDescription());
        }
        final Button applyChanges = (Button) findViewById(R.id.buttonApplyChanges);
        applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameTextField.getText().toString();
                String name = nameTextField.getText().toString();
                String description = descriptionTextField.getText().toString();
                Major major = (Major) majorSpinner.getSelectedItem();
                applyChanges(username, name, description, major, user);
            }
        });
        Button cancelChanges = (Button) findViewById(R.id.buttonCancelChanges);
        cancelChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Method to apply changes to the user profile.
     * @param username username to be changed to
     * @param name name to be changed to
     * @param description description to be changed to
     * @param major major to be changed to
     * @param user current user
     */
    private void applyChanges(String username, String name, String description, Major major, User user) {
        if (user.getUserName().equals(username) || UserList.isUserValid(username)) {
            user.setUserName(username);
            user.setName(name);
            user.setDescription(description);
            user.setMajor(major);
            finish();
        } else {
            CharSequence msgText = "Username is already taken. Please try again.";
            if (currentToast != null && currentToast.getView().isShown()) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(getApplicationContext(), msgText, Toast.LENGTH_SHORT);
            currentToast.show();
        }
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
