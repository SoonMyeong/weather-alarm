package com.soon.world;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataProcess {

    public String apiResponseProcess(String msg){
        JSONObject jsonObject = new JSONObject(msg);
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONObject items = body.getJSONObject("items");
        JSONArray item = items.getJSONArray("item");
        JSONObject getItem = item.getJSONObject(0);
        return getItem.getString("wfSv1");
    }
}
