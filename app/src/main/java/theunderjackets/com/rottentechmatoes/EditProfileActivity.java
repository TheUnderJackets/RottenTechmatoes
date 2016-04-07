package theunderjackets.com.rottentechmatoes;

import android.content.Context;
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

import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final User user = CurrentUser.getInstance().getUser();
        final EditText userNameTextField = (EditText) findViewById(R.id.inputUsername);
        userNameTextField.setText(user.getUserName());
        final EditText nameTextField = (EditText) findViewById(R.id.inputName);
        nameTextField.setText(user.getName());
        final EditText emailTextField = (EditText) findViewById(R.id.inputEmail);
        emailTextField.setText(user.getEmail());
        final Spinner majorSpinner = (Spinner) findViewById(R.id.majorSpinner);
        final List<Major> majors = Major.getMajorList();
        final ArrayAdapter<Major> majorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, majors);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(majorAdapter);
        majorSpinner.setSelection(majorAdapter.getPosition(user.getMajor()));
        final EditText descriptionTextField = (EditText) findViewById(R.id.inputDescription);
        if (!"".equals(user.getDescription())) {
            descriptionTextField.setText(user.getDescription());
        }
        descriptionTextField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && "Description".equals(descriptionTextField.getText().toString())) {
                    descriptionTextField.setText("");
                } else if (!hasFocus && "".equals(descriptionTextField.getText().toString())) {
                    descriptionTextField.setText(getResources().getString(R.string.input_text_description));
                }
            }
        });
        final Button applyChanges = (Button) findViewById(R.id.buttonApplyChanges);
        applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = userNameTextField.getText().toString();
                final String name = nameTextField.getText().toString();
                final String description = descriptionTextField.getText().toString();
                final Major major = (Major) majorSpinner.getSelectedItem();
                applyChanges(username, name, description, major, user);
                finish();
            }
        });
        final Button cancelChanges = (Button) findViewById(R.id.buttonCancelChanges);
        cancelChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Method to apply changes to the user profile.
     *
     * @param username    username to be changed to
     * @param name        name to be changed to
     * @param description description to be changed to
     * @param major       major to be changed to
     * @param user        current user
     */
    private static void applyChanges(String username, String name, String description, Major major, User user) {
        user.setUserName(username);
        user.setName(name);
        if (!"Description".equals(description)) {
            user.setDescription(description);
        }
        user.setMajor(major);
        UserList.updateUser(user);
    }

    /**
     * Makes it so that EditText will not be focused on anymore once clicked out of.
     *
     * @param event click outside of box
     * @return super.dispatchTouchEvent(event)
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            final View view = getCurrentFocus();
            if (view instanceof EditText) {
                final Rect out = new Rect();
                view.getGlobalVisibleRect(out);
                if (!out.contains((int) event.getRawX(), (int) event.getRawY())) {
                    view.clearFocus();
                    final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

}
