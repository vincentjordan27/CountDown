package com.example.android.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILIS = 60000;

    TextView countDownText;
    Button countDownBtn, countDownPauseBtn, countDownResetBtn;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 60000; // 1 minutes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer mediaBegin = MediaPlayer.create(this, R.raw.beginning) ;
        final MediaPlayer middle = MediaPlayer.create(this, R.raw.middle) ;
        final MediaPlayer ending = MediaPlayer.create(this, R.raw.sound) ;


        countDownText = findViewById(R.id.countdown_text);
        countDownBtn = findViewById(R.id.startcount_btn);
        countDownPauseBtn = findViewById(R.id.pausecount_btn);
        countDownResetBtn = findViewById(R.id.resetcount_btn);


        countDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownBtn.setEnabled(false);
                countDownPauseBtn.setEnabled(true);
                countDownResetBtn.setEnabled(false);
                countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {

                    @Override
                    public void onTick(long l) {
                        timeLeftInMilliSeconds = l;
                        if (timeLeftInMilliSeconds / 1000 == 59){
                            mediaBegin.start();
                        }
                        if (timeLeftInMilliSeconds / 1000 == 35){
                            middle.start();
                        }
                        if (timeLeftInMilliSeconds / 1000 == 25){
                            middle.start();
                        }
                        updateTimer();
                    }

                    @Override
                    public void onFinish() {
                        ending.start();
                        countDownResetBtn.setEnabled(true);
                        countDownPauseBtn.setEnabled(false);
                    }
                }.start();

            }
        });

        countDownPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                countDownBtn.setEnabled(true);
                countDownPauseBtn.setEnabled(false);
                countDownResetBtn.setEnabled(true);
            }
        });

        countDownResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeLeftInMilliSeconds = START_TIME_IN_MILIS;
                updateTimer();
                countDownBtn.setEnabled(true);
            }
        });

    }


    public void updateTimer(){
        int minutes = (int) timeLeftInMilliSeconds / 60000;
        int seconds = (int) timeLeftInMilliSeconds % 60000 / 1000;

        String timelefttext;
        timelefttext = "" + minutes;
        timelefttext += ":";
        if (seconds < 10) timelefttext += "0";
        timelefttext += seconds;

        countDownText.setText(timelefttext);
    }
}
