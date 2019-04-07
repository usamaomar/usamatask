package usama.task.movies.core.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import com.google.gson.Gson;
import usama.task.movies.core.helpers.Util;


/**
 * Created by usamaomar on 4/7/19.
 */
public class ErrorModel {
    private int id;
    private String message = "";
    private String error = "";
    private String code = "";
    private boolean status;
    private transient ArrayList<ErrorModel> errors;

    public ErrorModel() {
    }

    public ErrorModel(String message) {
        this.message = message;
    }

    public ErrorModel(ArrayList<ErrorModel> errors) {
        this.errors = errors;
    }

    public ErrorModel(int id, String message) {
        this.id = id;
        this.message = message;
        if (message == null)
            this.message = error;
    }

    public static ErrorModel parse(JSONObject data) throws JSONException {
        Gson gson = Util.gsonBuilder();
        return gson.fromJson(String.valueOf(data), ErrorModel.class);
    }

    public static ErrorModel parseError(String message) throws JSONException {
        return new ErrorModel(message);
    }

    public static ArrayList<ErrorModel> parseArray(JSONArray data) throws JSONException {
        ArrayList<ErrorModel> models = new ArrayList<>();
        int size = data.length();
        for (int i = 0; i < size; i++) {
            models.add(parse(data.getJSONObject(i)));
        }
        return models;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<ErrorModel> errors) {
        this.errors = errors;
    }

    public String getError() {
        return error + message;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message + error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return message + error;
    }
}
