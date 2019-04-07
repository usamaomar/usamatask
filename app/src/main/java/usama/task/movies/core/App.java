package usama.task.movies.core;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by usamaomar on 4/7/19.
 */

public class App extends MultiDexApplication {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}