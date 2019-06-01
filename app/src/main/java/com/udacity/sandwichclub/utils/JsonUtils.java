package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            //Json object decleration
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");

            //main name
            String main_name = name.getString("mainName");

            //Also known as
            JSONArray also_known_as = name.getJSONArray("alsoKnownAs");
            List<String> aka = new ArrayList<>();
            for(int i=0 ; i <also_known_as.length() ; i++)
            {
                aka.add(also_known_as.getString(i));
            }

            //Image
            String image = jsonObject.getString("image");

            //Description
            String discription = jsonObject.getString("description");

            //Ingeridents
            JSONArray ineridents = jsonObject.getJSONArray("ingredients");
            List<String> ingredients_string= new ArrayList<>();
            for(int i=0 ; i < ineridents.length() ; i++)
            {
                ingredients_string.add(ineridents.getString(i));
            }

            //Place of origin
            String place_of_origin = jsonObject.getString("placeOfOrigin");

            sandwich = new Sandwich(main_name,aka,place_of_origin,discription,image,ingredients_string);
            Log.d("data" , main_name);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
