package com.example.rehtaew;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rehtaew.WeahterData.WeatherHourData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class WeatherDataManager {

    private final ProgressDialog progress;
    private LinearLayout hourTimeLine;
    private LinearLayout dailyTimeLine;
    private List<WeatherHourData> weatherHourData;

    WeatherDataManager(Context context, String cityName, LinearLayout hourTimeLine,LinearLayout dailyTimeLine) {
        this.hourTimeLine = hourTimeLine;
        this.dailyTimeLine=dailyTimeLine;
        progress = new ProgressDialog(context);
        getJsonDataFromServer(context, cityName).addRequestFinishedListener(new RequestQueue.RequestFinishedListener<JSONObject>() {
            @Override
            public void onRequestFinished(Request<JSONObject> request) {
                drawElementsForHourTimeLine();
                drawElementsForDailyLine();
            }
        });
        progress.dismiss();
    }

    private RequestQueue getJsonDataFromServer(final Context context, String cityName) {

        progress.setTitle("Loading");
        progress.setMessage("Please, wait getting data from server");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        String apiKey = "105aa35c4d473a05d3297b2d972dc497";
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&lang=en&units=metric&appid=" + apiKey;

        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray weatherHourDataJSONArray = null;
                try {
                    weatherHourDataJSONArray = response.getJSONArray("list");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                List<WeatherHourData> hourDataList = new ArrayList<>();
                for (int i = 0; i < weatherHourDataJSONArray.length(); i++) {
                    WeatherHourData weatherHourData = null;
                    try {
                        weatherHourData = new WeatherHourData(weatherHourDataJSONArray.getJSONObject(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    hourDataList.add(weatherHourData);
                }
                weatherHourData = hourDataList;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println("!"); //TODO Show error
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
        return requestQueue;
    }

    private void drawElementsForHourTimeLine() {
        for (int i = 0; i < weatherHourData.size(); i++) {
            WeatherHourData data = weatherHourData.get(i);
            String time = String.valueOf(data.getTime().get(Calendar.HOUR_OF_DAY));
            String temperature = String.valueOf(data.getMain().getTemp());
            String imgId = data.getWeather().getIconId();
            hourTimeLine.addView(createCellForHourTimeLine(time, temperature, imgId));
        }
    }

    private ImageView getIconFromIconId(Context context, String imgId){
        ImageView weatherStatusImg = new ImageView(context);
        switch (imgId) {
            case "01d":
                weatherStatusImg.setImageResource(R.drawable.img01d);
                break;
            case "01n":
                weatherStatusImg.setImageResource(R.drawable.img01n);
                break;
            case "02d":
                weatherStatusImg.setImageResource(R.drawable.img02d);
                break;
            case "02n":
                weatherStatusImg.setImageResource(R.drawable.img02n);
                break;
            case "03d":
                weatherStatusImg.setImageResource(R.drawable.img03d);
                break;
            case "03n":
                weatherStatusImg.setImageResource(R.drawable.img03n);
                break;
            case "04d":
                weatherStatusImg.setImageResource(R.drawable.img04d);
                break;
            case "04n":
                weatherStatusImg.setImageResource(R.drawable.img04n);
                break;
            case "09d":
                weatherStatusImg.setImageResource(R.drawable.img09d);
                break;
            case "09n":
                weatherStatusImg.setImageResource(R.drawable.img09n);
                break;
            case "10d":
                weatherStatusImg.setImageResource(R.drawable.img10d);
                break;
            case "10n":
                weatherStatusImg.setImageResource(R.drawable.img10n);
                break;
            case "11d":
                weatherStatusImg.setImageResource(R.drawable.img11d);
                break;
            case "11n":
                weatherStatusImg.setImageResource(R.drawable.img11n);
                break;
            case "13d":
                weatherStatusImg.setImageResource(R.drawable.img13d);
                break;
            case "13n":
                weatherStatusImg.setImageResource(R.drawable.img13n);
                break;
            case "50d":
                weatherStatusImg.setImageResource(R.drawable.img50d);
                break;
            case "50n":
                weatherStatusImg.setImageResource(R.drawable.img50n);
                break;
        }
        return weatherStatusImg;
    }

    @SuppressLint("SetTextI18n")
    private LinearLayout createCellForHourTimeLine(String hourTimeText, String hourTempText, String imgId) {
        LinearLayout linearLayout = new LinearLayout(hourTimeLine.getContext());
        TextView hourTime = new TextView(linearLayout.getContext());
        TextView hourTemp = new TextView(linearLayout.getContext());

        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.setPadding(25, 30, 25, 30);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        hourTime.setText(hourTimeText + ":00");
        hourTemp.setText(hourTempText + "\u2103");

        hourTemp.setTextColor(Color.WHITE);
        hourTime.setTextColor(Color.WHITE);
        hourTemp.setGravity(Gravity.CENTER);
        hourTime.setGravity(Gravity.CENTER);

        linearLayout.addView(hourTime);
        linearLayout.addView(getIconFromIconId(hourTimeLine.getContext(),imgId));
        linearLayout.addView(hourTemp);
        return linearLayout;
    }

    private void drawElementsForDailyLine(){
        int  prevDay=weatherHourData.get(0).getTime().get(Calendar.DAY_OF_WEEK);
        double minTemperature = 9999;
        double maxTemperature = -9999;
        for (int i=0;i<weatherHourData.size();i++){
            WeatherHourData data=weatherHourData.get(i);
            int currentDay = data.getTime().get(Calendar.DAY_OF_WEEK);
            if (currentDay==prevDay){
                if (maxTemperature<data.getMain().getTempMax()){
                    maxTemperature=data.getMain().getTempMax();
                }
                if (minTemperature>data.getMain().getTempMin()){
                    minTemperature=data.getMain().getTempMin();
                }
            }
            String iconId = data.getWeather().getIconId();
            if (currentDay!=prevDay){
                dailyTimeLine.addView(createDailyCell(dailyTimeLine.getContext(),dayName.values()[prevDay],iconId,maxTemperature,minTemperature));
            }
           prevDay=currentDay;
        }
    }

    private enum dayName{Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday};



    private LinearLayout createDailyCell(Context context,dayName dayName,String iconId,double maxTemperature,double minTemperature){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView day=new TextView(context);
        TextView minTempView=new TextView(context);
        TextView maxTempView=new TextView(context);

        day.setText(dayName.toString());
        minTempView.setText(String.valueOf(minTemperature));
        maxTempView.setText(String.valueOf(maxTemperature));

        linearLayout.addView(day);
        linearLayout.addView(maxTempView);
        linearLayout.addView(minTempView);
        linearLayout.addView(getIconFromIconId(context,iconId));

        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        return linearLayout;
    }
}
