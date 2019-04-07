package usama.task.movies.core.mangers;

/**
 * Created by usamaomar on 4/7/19.
 */

public class ShortTermManager {

    static ShortTermManager mInstance;
    private String requestToken;


    public static synchronized ShortTermManager getInstance() {
        if (mInstance == null) {
            mInstance = new ShortTermManager();
        }
        return mInstance;
    }


    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }
}