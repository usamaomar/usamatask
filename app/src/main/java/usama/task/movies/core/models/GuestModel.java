package usama.task.movies.core.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import usama.task.movies.core.helpers.Util;

/**
 * Created by usamaomar on 4/7/19.
 */

public class GuestModel {

    private boolean success;
    private String expiresAt;
    private String requestToken;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public static ArrayList<GuestModel> parseArray(JSONArray data) throws JSONException {
        Gson gson = Util.gsonBuilder();
        Type listType = new TypeToken<List<GuestModel>>() {
        }.getType();
        return gson.fromJson(String.valueOf(data), listType);
    }

    public static GuestModel parse(JSONObject data) throws JSONException {
        Gson gson = Util.gsonBuilder();
        return gson.fromJson(String.valueOf(data), GuestModel.class);
    }
}
