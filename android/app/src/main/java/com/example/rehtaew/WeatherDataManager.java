package com.example.rehtaew;

import android.content.Context;

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
import java.util.List;
import java.util.Objects;

class WeatherDataManager {

    private List<WeatherHourData> weatherHourData;

    List<WeatherHourData> getWeatherHourData() {
        return weatherHourData;
    }



    WeatherDataManager(Context context,String cityName) {
        getJsonDataFromServer(context,cityName );
    }
    private void getJsonDataFromServer(Context context, String cityName) {

        String apiKey = "105aa35c4d473a05d3297b2d972dc497";
        String url = "http://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&lang=en&units=metric&appid=" + apiKey;

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
                for (int i = 0; i < Objects.requireNonNull(weatherHourDataJSONArray).length(); i++) {
                    WeatherHourData weatherHourData = null;
                    try {
                        weatherHourData = new WeatherHourData(weatherHourDataJSONArray.getJSONObject(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    hourDataList.add(weatherHourData);
                }
                weatherHourData = hourDataList;
                System.err.println("!");
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
                System.err.println("!"); //TODO cancel loading
            }
        });
    }
}
