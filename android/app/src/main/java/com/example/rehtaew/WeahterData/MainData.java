package com.example.rehtaew.WeahterData;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

@Getter
public final class MainData {
    private double temp;
    private double tempMax;
    private double tempMin;
    private double pressure;
    private double seaLevel;
    private double grndLevel;
    private double humidity;

    MainData(JSONObject jsonObject) throws JSONException {
        this.temp = jsonObject.getDouble("temp");
        this.tempMax = jsonObject.getDouble("temp_max");
        this.tempMin = jsonObject.getDouble("temp_min");
        this.pressure = jsonObject.getDouble("pressure");
        this.seaLevel = jsonObject.getDouble("sea_level");
        this.grndLevel = jsonObject.getDouble("grnd_level");
        this.humidity = jsonObject.getDouble("humidity");
    }
}
