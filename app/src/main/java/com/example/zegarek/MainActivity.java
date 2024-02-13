package com.example.zegarek;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button setAlarmButton;
    private TimePicker alarmTimePicker;
    private TextView currentTimeTextView;
    private Handler handler;
    private boolean isTimePickerVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAlarmButton = findViewById(R.id.setAlarmButton);
        alarmTimePicker = findViewById(R.id.alarmTimePicker);
        currentTimeTextView = findViewById(R.id.currentTimeTextView);

        handler = new Handler(Looper.getMainLooper());

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimePickerVisible) {
                    setAlarm();
                } else {
                    showTimePicker();
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void showTimePicker() {
        alarmTimePicker.setVisibility(View.VISIBLE);
        setAlarmButton.setText("Potwierdź");
        isTimePickerVisible = true;
    }

    private void setAlarm() {
        int hour = alarmTimePicker.getCurrentHour();
        int minute = alarmTimePicker.getCurrentMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        Toast.makeText(this, "Alarm ustawiony na: " + hour + ":" + minute, Toast.LENGTH_SHORT).show();

        // Tutaj możesz uruchomić metodę do ustawienia alarmu za pomocą AlarmManagera
        // Na razie brak tej funkcjonalności w kodzie

        alarmTimePicker.setVisibility(View.GONE);
        setAlarmButton.setText("Ustaw Alarm");
        isTimePickerVisible = false;
    }

    private void updateTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String timeString = String.format("%02d:%02d:%02d", hour, minute, second);
        currentTimeTextView.setText(timeString);
    }
}
