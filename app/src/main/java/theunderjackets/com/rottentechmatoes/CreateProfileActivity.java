package theunderjackets.com.rottentechmatoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CreateProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner dropdown = (Spinner)findViewById(R.id.ChooseMajorSpinner);
        String[] items = new String[]{"Aerospace Engineering", "Applied Languages & Interculture Studies",
                "Applied Mathematics", "Applied Physics", "Architecture", "Biochemistry", "Biology",
                "Biomedical Engineering", "Business Administration", "Chemical & Biomolecular Engineering",
                "Chemistry", "Civil Engineering", "Computational Media", "Computer Engineering",
                "Computer Science", "Discrete Mathematics", "Earth & Atmospheric Sciences", "Economics",
                "Economics & International Affairs", "Electrical Engineering", "Environmental Engineering",
                "Global Economics & Modern Languages", "History, Technology, and Society", "Industrial Design",
                "Industrial Engineering", "International Affairs", "International Affairs & Modern Languages",
                "Literature, Media, & Communication", "Materials Science & Engineering",
                "Mechanical Engineering", "Nuclear & Radiological Engineering", "Physics", "Psychology",
                "Public Policy"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Button submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkToHome(v);
            }
        });

        Button noThanks = (Button) findViewById(R.id.noThanksButton);
        noThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkToHome(v);
            }
        });
    }

    private void linkToHome(View v) {
        Intent createProfileIntent = new Intent(this, HomeActivity.class);
        startActivity(createProfileIntent);
    }



}
