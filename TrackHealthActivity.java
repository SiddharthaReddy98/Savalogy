package com.example.savology;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class TrackHealthActivity extends AppCompatActivity {

    // Request codes for permissions
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private static final int SENSOR_PERMISSION_REQUEST_CODE = 101;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 102;

    // Buttons
    private Button buttonViewSensorData;

    // TextView for displaying heart rate
    private TextView textViewHeartRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_health_activity);

        // Initialize buttons
        buttonViewSensorData = findViewById(R.id.buttonViewSensorData);

        // Initialize TextView for heart rate
        textViewHeartRate = findViewById(R.id.textViewHeartRate);

        // Set click listener for the "View Sensor Data" button
        buttonViewSensorData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check and request permissions
                checkAndRequestPermissions();
            }
        });
    }

    // Method to check and request permissions
    private void checkAndRequestPermissions() {
        // Location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        // Sensor permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BODY_SENSORS},
                    SENSOR_PERMISSION_REQUEST_CODE);
            return;
        }

        // Camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
            return;
        }

        // If all permissions are granted, proceed with accessing sensor data
        accessSensorData();
    }

    // Method to access sensor data (placeholder)
    private void accessSensorData() {
        // Placeholder method to access sensor data
        // For heart rate monitoring, you would need to implement sensor logic here
        // Update the textViewHeartRate with the heart rate data
        // For example:
        // float heartRate = getHeartRate(); // Method to get heart rate from sensor
        // textViewHeartRate.setText("Heart Rate: " + heartRate);
    }

    // Handle permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE ||
                requestCode == SENSOR_PERMISSION_REQUEST_CODE ||
                requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with accessing sensor data
                accessSensorData();
            } else {
                // Permission denied, show a message or open app settings
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                openAppSettings();
            }
        }
    }

    // Method to open app settings
    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + getPackageName()));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
