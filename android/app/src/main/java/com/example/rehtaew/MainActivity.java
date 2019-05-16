package com.example.rehtaew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rehtaew.WeahterData.WeatherHourData;

import java.util.List;

public class MainActivity extends AppCompatActivity {


      // This is our requests queue to process our HTTP requests.
    LinearLayout hourTimeLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button changeButtonText = findViewById(R.id.button);

        changeButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBlock();
            }
        });

    }

    protected void addBlock() {
        WeatherDataManager weatherDataManager = new WeatherDataManager(this,"Daugavpils,lv");
        hourTimeLine = findViewById(R.id.hourTimeLine);

        List<WeatherHourData> weatherHourData = weatherDataManager.getWeatherHourData();

        for (int i=0;i<weatherHourData.size();i++){

        }


        TextView hourTime = new TextView(this);
        TextView hourTemp = new TextView(this);
        hourTime.setText("hallo hallo");
        //hourTimeLine.addView(valueTV);

    }
}
