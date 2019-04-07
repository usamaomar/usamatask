package usama.task.movies.cores.configurations;

import java.util.Map;

import usama.task.movies.cores.helpers.Util;

/**
 * Created by usamaomar on 4/7/19.
 */

public class RequestsUrls extends Config {

    public static final String API_KEY = "4f4618d42987567a3e77624aab9e91c0";
    public static final String AUTHENTICATION = "/authentication";
    public static final String TOKEN = "/token";
    public static final String MOVIE = "/movie";
    public static final String TOP_RATED = "/top_rated";
    public static final String NEW = "/new";
    public static final String VERSION = "/3";
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
    public static final String API_KEY_LINK = VERSION + AUTHENTICATION + TOKEN + NEW;
    public static final String  TOP_RATED_LINK = VERSION + MOVIE + TOP_RATED;



    public static String concat(String url, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null && !value.equals("null")) {
                stringBuilder.append(key);
                stringBuilder.append("=");
                stringBuilder.append(value);
                stringBuilder.append("&");
            }
        }
        return url + "?" + Util.removeLastChar(stringBuilder.toString());
    }
}
