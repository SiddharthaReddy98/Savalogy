package com.example.savology;

import static com.example.savology.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.savology.R.layout;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.landing_activity);

        // Initialize views
        TextView textViewWelcome = this.<TextView>findViewById(R.id.textViewWelcome);
        Button buttonTrackHealth = this.<Button>findViewById(R.id.buttonTrackHealth);

        // Set welcome message
        String username = "User"; // You can retrieve username from authentication
        textViewWelcome.setText("Welcome " + username + "!");

        // Set click listener for track health button
        buttonTrackHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to health tracking screen
                navigateToHealthTracking();
            }
        });
    }

    // Method to navigate to health tracking screen
    public void navigateToHealthTracking() {
        Intent intent = new Intent(LandingActivity.this, TrackHealthActivity.class);
        startActivity(intent);
    }
}
