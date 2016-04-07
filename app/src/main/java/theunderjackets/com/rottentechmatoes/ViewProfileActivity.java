package theunderjackets.com.rottentechmatoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ViewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final User user = CurrentUser.getInstance().getUser();
        final EditText userNameTextField = (EditText) findViewById(R.id.inputUsername);
        userNameTextField.setText(user.getUserName());
        final EditText nameTextField = (EditText) findViewById(R.id.inputName);
        nameTextField.setText(user.getName());
        final EditText emailTextField = (EditText) findViewById(R.id.inputEmail);
        emailTextField.setText(user.getEmail());
        final EditText majorTextField = (EditText) findViewById(R.id.major);
        majorTextField.setText(user.getMajor().toString());
        final EditText descriptionTextField = (EditText) findViewById(R.id.inputDescription);
        if (!"".equals(user.getDescription()) && !"Description".equals(user.getDescription())) {
            descriptionTextField.setText(user.getDescription());
        }
        final Button editProfile = (Button) findViewById(R.id.buttonEditProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intentEdit = new Intent(ViewProfileActivity.this, EditProfileActivity.class);
                startActivity(intentEdit);
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

    @Override
    public void onResume() {
        super.onResume();
        final User user = CurrentUser.getInstance().getUser();
        final EditText userNameTextField = (EditText) findViewById(R.id.inputUsername);
        userNameTextField.setText(user.getUserName());
        final EditText nameTextField = (EditText) findViewById(R.id.inputName);
        nameTextField.setText(user.getName());
        final EditText emailTextField = (EditText) findViewById(R.id.inputEmail);
        emailTextField.setText(user.getEmail());
        final EditText majorTextField = (EditText) findViewById(R.id.major);
        majorTextField.setText(user.getMajor().toString());
        final EditText descriptionTextField = (EditText) findViewById(R.id.inputDescription);
        if (!"".equals(user.getDescription()) && !"Description".equals(user.getDescription())) {
            descriptionTextField.setText(user.getDescription());
        }
    }

}
