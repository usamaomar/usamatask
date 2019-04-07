package usama.task.movies.cores.helpers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by usamaomar on 4/7/19.
 */

public class Util {
    public static Gson gsonBuilder() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    public static String removeLastChar(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == '&') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
