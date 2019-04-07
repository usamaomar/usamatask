package usama.task.movies.cores.communicators;


import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import usama.task.movies.cores.App;
import usama.task.movies.cores.configurations.Config;

/**
 * Created by usamaomar on 4/7/19.
 */

public class Communicator {

    private static final String TAG = Communicator.class.getSimpleName();
    private static Communicator mInstance;
    private RequestQueue mRequestQueue;

    public static synchronized Communicator getInstance() {
        if (mInstance == null) {
            mInstance = new Communicator();
        }
        return mInstance;
    }

    public static Map<String, String> removeNulls(Map<String, String> params) {
        Map<String, String> newParams = new HashMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null && !value.equalsIgnoreCase("null")) newParams.put(key, value);
        }
        return params;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            try {
                mRequestQueue = Volley.newRequestQueue(App.getContext());
            } catch (Exception ignored) {
            }

        }

        return mRequestQueue;
    }


    public void get(String url, Map<String, String> parameters, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        url = Config.SERVER_URL_LIVE + url;
        parameters = removeNulls(parameters);
        CustomRequest jsonObjReq = new CustomRequest(url, parameters, responseListener, errorListener, Request.Method.GET) {
        };
        String tag = url;
        addToRequestQueue(jsonObjReq, tag);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        try {
            req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
            getRequestQueue().add(req);
        } catch (Exception ignored) {
        }

    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}

