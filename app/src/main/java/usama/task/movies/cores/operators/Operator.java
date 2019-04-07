package usama.task.movies.cores.operators;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import usama.task.movies.cores.models.ErrorModel;


/**
 * Created by usamaomar on 4/7/19.
 */

public class Operator {
    private static final String noInternetString = "No Internet";
    private static final String somethingWrong = "Something went wrong";


    /**
     * Convert vollyError to our ErrorModel
     *
     * @param volleyError
     * @return ErrorModel
     */
    public static ErrorModel parseVolleyError(VolleyError volleyError) {
        try {
            if (volleyError == null)
                return new ErrorModel(-14, noInternetString);
            String s = new String(volleyError.networkResponse.data, HttpHeaderParser.parseCharset(volleyError.networkResponse.headers, "utf-8"));
            JSONObject jsonObject = new JSONObject(s);
            return ErrorModel.parse(jsonObject);
        } catch (UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
            return new ErrorModel(-1, somethingWrong);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorModel(4, somethingWrong);
        }
    }

    /**
     * @param networkResponse
     * @return Json
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    public static JSONObject parseNetworkResponse(NetworkResponse networkResponse) throws JSONException, UnsupportedEncodingException {
        String s = new String(networkResponse.data,
                HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"));
        return new JSONObject(s);
    }

    public interface OnServerResponseListener<T> {
        void onSuccess(T response);

        void onError(ErrorModel e);
    }

    class ErrorStatusException extends Exception {
        String id;

        public ErrorStatusException() {
        }

        //Constructor that accepts a message
        public ErrorStatusException(String message, String id) {
            super(message);
            this.id = id;
        }
    }
}


