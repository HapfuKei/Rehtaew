package com.example.rehtaew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    MainActivity mainActivity;
      // This is our requests queue to process our HTTP requests.
    LinearLayout hourTimeLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity= this;

        hourTimeLine = findViewById(R.id.hourTimeLine);
        WeatherDataManager weatherDataManager = new WeatherDataManager(this,"Daugavpils,lv",hourTimeLine);;
    }
}
