package com.example.rehtaew.WeahterData;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

@Getter
final class WindData {
    private double windSpeed;
    private double deg;

    WindData(JSONObject jsonObject) throws JSONException {
        this.deg = jsonObject.getDouble("deg");
        this.windSpeed = jsonObject.getDouble("speed");
    }
}
