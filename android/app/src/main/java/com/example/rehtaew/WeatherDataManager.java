package com.example.rehtaew;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
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

    LinearLayout hourTimeLine;
    private List<WeatherHourData> weatherHourData;
    private View view;

    WeatherDataManager(Context context, String cityName, View view, LinearLayout hourTimeLine) {
        this.hourTimeLine = hourTimeLine;
        this.view = view;
        getJsonDataFromServer(context, cityName);
    }

    List<WeatherHourData> getWeatherHourData() {
        return weatherHourData;
    }

    void getJsonDataFromServer(final Context context, String cityName) {

        final ProgressDialog progress = new ProgressDialog(context);
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
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<JSONObject>() {
            @Override
            public void onRequestFinished(Request<JSONObject> request) {
                drawElements(view);
                progress.dismiss();
            }
        });
    }

    private void drawElements(View view) {
        for (int i = 0; i < weatherHourData.size(); i++) {
            WeatherHourData data = weatherHourData.get(i);
            Calendar cal = Calendar.getInstance();
            cal.setTime(data.getTime());
            String time = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
            String temperature = String.valueOf(data.getMain().getTemp());
            hourTimeLine.addView(createCell(time,temperature));
        }
    }

    @SuppressLint("SetTextI18n")
    private LinearLayout createCell(String hourTimeText, String hourTempText) {
        LinearLayout linearLayout = new LinearLayout(hourTimeLine.getContext());
        TextView hourTime = new TextView(linearLayout.getContext());
        TextView hourTemp = new TextView(linearLayout.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(150,200));
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);


        hourTime.setText(hourTimeText+":00");
        hourTemp.setText(hourTempText+"\u2109");

        hourTemp.setTextColor(Color.WHITE);
        hourTime.setTextColor(Color.WHITE);

        linearLayout.addView(hourTemp);
        linearLayout.addView(hourTime);
        return linearLayout;
    }
}
