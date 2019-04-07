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

public class MainMovieModel {

    private String page;
    private String totalResults;
    private int totalPages;
    private ArrayList<MovieModel> results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<MovieModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieModel> results) {
        this.results = results;
    }

    public static ArrayList<MainMovieModel> parseArray(JSONArray data) throws JSONException {
        Gson gson = Util.gsonBuilder();
        Type listType = new TypeToken<List<MainMovieModel>>() {
        }.getType();
        return gson.fromJson(String.valueOf(data), listType);
    }

    public static MainMovieModel parse(JSONObject data) throws JSONException {
        Gson gson = Util.gsonBuilder();
        return gson.fromJson(String.valueOf(data), MainMovieModel.class);
    }


}
