package theunderjackets.com.rottentechmatoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ViewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        final User user = CurrentUser.getInstance().getUser();
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
        final EditText majorTextField = (EditText) findViewById(R.id.major);
        majorTextField.setText(user.getMajor().toString());
        final EditText descriptionTextField = (EditText) findViewById(R.id.inputDescription);
        if (!user.getDescription().equals("") && !user.getDescription().equals("Description")) {
            descriptionTextField.setText(user.getDescription());
        }
        final Button editProfile = (Button) findViewById(R.id.buttonEditProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEdit = new Intent(ViewProfileActivity.this, EditProfileActivity.class);
                startActivity(intentEdit);
                finish();
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
        if (!user.getDescription().equals("") && !user.getDescription().equals("Description")) {
            descriptionTextField.setText(user.getDescription());
        }
    }

}
