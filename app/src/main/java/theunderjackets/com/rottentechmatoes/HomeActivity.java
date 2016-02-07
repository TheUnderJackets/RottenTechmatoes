package theunderjackets.com.rottentechmatoes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences SM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SM = getSharedPreferences("userrecord", 0);
        Boolean isLogin = SM.getBoolean("userlogin", false);
        if (isLogin) {
            Intent intent = new Intent(HomeActivity.this, WelcomeScreenActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String userName = intent.getStringExtra(LoginActivity.EXTRA_LOGIN_USERNAME);
        TextView welcomeUser = (TextView) findViewById(R.id.textViewWelcomeUser);
        welcomeUser.setText("Welcome " + userName + "!");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
