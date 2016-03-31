package theunderjackets.com.rottentechmatoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;


public class WelcomeScreenActivity extends AppCompatActivity {
    private Intent intent = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_welcome_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button logIn = (Button) findViewById(R.id.logInButton);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkToLogin(v);
            }
        });

        Button register = (Button) findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkToRegister(v);
            }
        });


        //Hard Coded Users for M8
        /*
        UserList.addUser(new User("Ben French", "Email1", "Password", "Regular", false));
        User locked = new User("Lixin Wang", "Email2", "Password", "Locked", false);
        UserList.addUser(locked);
        locked.setLocked(true);
        User banned = new User("Hudson Lynam", "Email3", "Password", "Banned", false);
        UserList.addUser(banned);
        banned.setBanned(true);
        AdminList.addAdmin(new Admin("Email4", "Admin", "Password"));
        */
    }

    private void linkToLogin(View v) {
        Intent logInIntent = new Intent(this, LoginActivity.class);
        startActivity(logInIntent);
    }

    private void linkToRegister(View v) {
        Intent registerIntent = new Intent(this, RegistrationActivity.class);
        startActivity(registerIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        finish();
    }
}
