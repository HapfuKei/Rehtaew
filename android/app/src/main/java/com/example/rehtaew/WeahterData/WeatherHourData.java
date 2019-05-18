package com.example.rehtaew.WeahterData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import lombok.Getter;

@Getter
public class WeatherHourData {
    private MainData main;
    private Date time;
    private WeatherData weather;
    private double clouds;
    private WindData wind;
    private double rain;
    private double snow;

    public WeatherHourData(JSONObject jsonObject) throws JSONException {
        this.main = new MainData(jsonObject.getJSONObject("main"));
        this.time = new Date(Long.parseLong(jsonObject.getString("dt")) * 1000L);
        this.weather = new WeatherData(jsonObject.getJSONArray("weather"));
        this.clouds = jsonObject.getJSONObject("clouds").getDouble("all");
        this.wind = new WindData(jsonObject.getJSONObject("wind"));
        if (jsonObject.has("rain")&&!jsonObject.getJSONObject("rain").isNull("3h")) {
            this.rain = jsonObject.getJSONObject("rain").getDouble("3h");
        } else {
            rain = -1;
        }
        if (jsonObject.has("snow")&&!jsonObject.getJSONObject("snow").isNull("3h")) {
            this.rain = jsonObject.getJSONObject("snow").getDouble("3h");
        } else {
            snow = -1;
        }
    }
}
