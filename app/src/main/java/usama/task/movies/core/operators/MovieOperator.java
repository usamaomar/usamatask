package usama.task.movies.core.operators;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;

import usama.task.movies.core.communicator.Communicator;
import usama.task.movies.core.configuration.RequestsUrls;
import usama.task.movies.core.models.MainMovieModel;

/**
 * Created by usamaomar on 4/7/19.
 */

public class MovieOperator extends Operator {

    public static void getTopRated(int page, final OnServerResponseListener<MainMovieModel> listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("api_key", RequestsUrls.API_KEY);
        params.put("language", "en-US");
        params.put("page", String.valueOf(page));

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    try {
                        MainMovieModel categoryMainSup = MainMovieModel.parse(response);
                        listener.onSuccess(categoryMainSup);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        listener.onError(parseVolleyError(new VolleyError()));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    listener.onError(parseVolleyError(new VolleyError()));
                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(parseVolleyError(error));
            }
        };
        Communicator.getInstance().get(RequestsUrls.concat(RequestsUrls.TOP_RATED_LINK, params), new HashMap<String, String>(), responseListener, errorListener);
    }
}
