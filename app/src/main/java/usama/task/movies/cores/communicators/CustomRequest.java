package usama.task.movies.cores.communicators;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by usamaomar on 4/7/19.
 */

public class CustomRequest extends Request<JSONObject> {
    private static final int TIMEOUT_MS = 10000;
    private static final int MAX_RETRIES = 2;
    private Response.Listener<JSONObject> listener;
    private Response.Listener<String> listenerString;
    private Map<String, String> params;
    private Map<String, String> headers;


    public CustomRequest(String url, Map<String, String> params, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener, int method) {
        super(method, url, errorListener);
        this.listener = responseListener;
        this.params = params;
        //
        headers = new HashMap<>();
        // headers.put("Headers", ""); Headers
        this.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MS, MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    public String getBodyContentType() {
        return super.getBodyContentType();
        //        return "application/json; charset=utf-8";
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (headers != null) return headers;
        return super.getHeaders();
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        try {
            String s = new String(volleyError.networkResponse.data, HttpHeaderParser.parseCharset(volleyError.networkResponse.headers, "utf-8"));
            //here you can handle errors by model from backend
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.parseNetworkError(volleyError);
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }
}
