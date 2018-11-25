package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJson = new JSONObject(json);

            JSONObject sandwichNameJson = sandwichJson.getJSONObject("name");
            String mainName = sandwichNameJson.getString("mainName");

            List<String> alsoKnownAs = getArrayFromJson(sandwichNameJson, "alsoKnownAs");

            String placeOfOrigin = sandwichJson.getString("placeOfOrigin");

            String description = sandwichJson.getString("description");

            String image = sandwichJson.getString("image");

            List<String> ingredients = getArrayFromJson(sandwichJson, "ingredients");

            return new Sandwich(mainName, alsoKnownAs
                    , placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    private static List<String> getArrayFromJson(JSONObject jsonObject, String key) throws JSONException {
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        List<String> list = new ArrayList<>();
        if (jsonArray.length() != 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.getString(i));
            }
        }
        return list;
    }
}
