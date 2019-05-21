package com.example.rehtaew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    MainActivity mainActivity;
      // This is our requests queue to process our HTTP requests.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity= this;

        LinearLayout hourTimeLine = findViewById(R.id.hourTimeLine);
        LinearLayout dailyTimeLine = findViewById(R.id.dailyTimeLine);
        WeatherDataManager weatherDataManager = new WeatherDataManager(this,"Daugavpils,lv",hourTimeLine,dailyTimeLine);
    }
}
