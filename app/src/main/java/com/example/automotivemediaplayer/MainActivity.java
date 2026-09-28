package com.example.automotivemediaplayer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MEDIA_PLAYER";

    private TextView statusText;
    private TextView timeText;
    private SeekBar progressBar;

    private boolean isPlaying = false;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);
        timeText = findViewById(R.id.timeText);
        progressBar = findViewById(R.id.progressBar);

        Button playButton = findViewById(R.id.playButton);
        Button pauseButton = findViewById(R.id.pauseButton);
        Button stopButton = findViewById(R.id.stopButton);
        Button previousButton = findViewById(R.id.previousButton);
        Button nextButton = findViewById(R.id.nextButton);

        Log.d(TAG, "Media Service initialized");
        Log.d(TAG, "Application started");

        playButton.setOnClickListener(v -> {
            isPlaying = true;
            statusText.setText(R.string.status_playing);
            Log.d(TAG, "User input: PLAY");
            Log.d(TAG, "Media Service: PLAY request received");
            Log.d(TAG, "Audio Framework: PLAY command sent");
            Log.d(TAG, "Audio HAL: Playback started");
        });

        pauseButton.setOnClickListener(v -> {
            isPlaying = false;
            statusText.setText(R.string.status_paused);
            Log.d(TAG, "User input: PAUSE");
            Log.d(TAG, "Media Service: PAUSE request received");
            Log.d(TAG, "Audio Framework: PAUSE command sent");
            Log.d(TAG, "Audio HAL: Playback paused");
        });

        stopButton.setOnClickListener(v -> {
            isPlaying = false;
            progress = 0;
            progressBar.setProgress(0);
            timeText.setText(R.string.time_start);
            statusText.setText(R.string.status_stopped);
            Log.d(TAG, "User input: STOP");
            Log.d(TAG, "Media Service: STOP request received");
            Log.d(TAG, "Audio Framework: STOP command sent");
            Log.d(TAG, "Audio HAL: Playback stopped");
        });

        previousButton.setOnClickListener(v -> {
            progress = 0;
            progressBar.setProgress(0);
            timeText.setText(R.string.time_start);
            statusText.setText(R.string.status_previous);
            Log.d(TAG, "User input: PREVIOUS");
            Log.d(TAG, "Media Service: Previous track requested");
        });

        nextButton.setOnClickListener(v -> {
            progress = 0;
            progressBar.setProgress(0);
            timeText.setText(R.string.time_start);
            statusText.setText(R.string.status_next);
            Log.d(TAG, "User input: NEXT");
            Log.d(TAG, "Media Service: Next track requested");
        });

        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                if (fromUser) {
                    progress = value;
                    timeText.setText(String.format("%02d:00 / 04:00", value));
                    Log.d(TAG, "User input: SEEK to " + value + " minute(s)");
                    Log.d(TAG, "Media Service: Seek request sent");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "User started seeking");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "User finished seeking");
            }
        });
    }
}
