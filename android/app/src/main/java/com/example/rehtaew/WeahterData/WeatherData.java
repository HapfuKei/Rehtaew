package com.example.rehtaew.WeahterData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

@Getter
public final class WeatherData {
    private int id;
    private String main;
    private String description;
    private String iconId;

    WeatherData(JSONArray jsonArray) throws JSONException {
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        this.id = jsonObject.getInt("id");
        this.main = jsonObject.getString("main");
        this.description = jsonObject.getString("description");
        this.iconId = jsonObject.getString("icon");
    }
}
