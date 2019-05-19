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

    LinearLayout hourTimeLine;
    private List<WeatherHourData> weatherHourData;

    WeatherDataManager(Context context, String cityName, LinearLayout hourTimeLine) {
        this.hourTimeLine = hourTimeLine;
        getJsonDataFromServer(context, cityName);
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
                drawElements();
                progress.dismiss();
            }
        });
    }

    private void drawElements() {
        for (int i = 0; i < weatherHourData.size(); i++) {
            WeatherHourData data = weatherHourData.get(i);
            Calendar cal = Calendar.getInstance();
            cal.setTime(data.getTime());
            String time = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
            String temperature = String.valueOf(data.getMain().getTemp());
            String imgId = data.getWeather().getIconId();
            hourTimeLine.addView(createCell(time, temperature, imgId));
        }
    }

    @SuppressLint("SetTextI18n")
    private LinearLayout createCell(String hourTimeText, String hourTempText, String imgId) {
        LinearLayout linearLayout = new LinearLayout(hourTimeLine.getContext());
        TextView hourTime = new TextView(linearLayout.getContext());
        TextView hourTemp = new TextView(linearLayout.getContext());
        ImageView weatherStatusImg = new ImageView(hourTimeLine.getContext());
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
        linearLayout.addView(weatherStatusImg);
        linearLayout.addView(hourTemp);
        return linearLayout;
    }
}
