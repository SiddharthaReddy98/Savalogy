package com.example.savology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle login button click
                // For simplicity, assume login is successful and proceed to LandingActivity
                navigateToLandingActivity();
            }
        });
    }

    private void navigateToLandingActivity() {
        Intent intent = new Intent(MainActivity.this, LandingActivity.class);
        startActivity(intent);
        finish(); // Finish MainActivity to prevent it from staying in the back stack
    }
}
