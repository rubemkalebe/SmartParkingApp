package br.ufrn.imd.smartparkingapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 13/04/2016.
 */
public class JSONProcessor {

    public synchronized static <T> T toObject(String jsonText, Class<T> clazz) throws JSONException {
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        T t = null;

        try {
            t = gson.fromJson(jsonText, clazz);
        } catch (Exception e) {
            throw new JSONException(e.getMessage());
        }

        return t;
    }

    public synchronized static String toJSON(Object object) {
        Gson gson = new Gson();

        return gson.toJson(object);
    }

    public synchronized static String toJSON(Object object, String formatoData) {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat(formatoData);
        Gson gson = builder.create();
        return gson.toJson(object);
    }

    public synchronized static <T> List<T> toList(String jsonText, Class<T> clazz) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonText);

        List<T> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            T object = toObject(jsonObject.toString(), clazz);

            result.add(object);
        }

        return result;
    }
}
