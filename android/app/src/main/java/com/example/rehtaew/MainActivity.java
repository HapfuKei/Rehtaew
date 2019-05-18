package com.example.rehtaew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

        final Button changeButtonText = findViewById(R.id.button);
        hourTimeLine = findViewById(R.id.hourTimeLine);
        changeButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addBlock(view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    protected void addBlock(View view) throws InterruptedException {
   /*     for (int i=0;i<40;i++){
            //WeatherHourData data = weatherHourData.get(i);
            TextView hourTime = new TextView(this);
            TextView hourTemp = new TextView(this);
            hourTime.setText("time"+i);
            hourTemp.setText("temp"+i);
            hourTimeLine.addView(hourTime);
            hourTimeLine.addView(hourTemp);
        }*/

   WeatherDataManager weatherDataManager = new WeatherDataManager(this,"Daugavpils,lv",view,hourTimeLine);
       // List<WeatherHourData> weatherHourData = weatherDataManager.getWeatherHourData();
       /* for (int i=0;i<40;i++){
            hourTimeLine.addView(createCell("h"+i,"temp"+i));
        }*/
    }
}
