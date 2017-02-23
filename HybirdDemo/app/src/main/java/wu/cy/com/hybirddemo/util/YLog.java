package wu.cy.com.hybirddemo.util;

/**
 * Created by wcy8038 on 2017/2/23.
 */

public class YLog {

    private static final String TAG = "wcy";
    private static final boolean LOG_ENABLE = true;

    public static void d(String message){
        d(TAG, message);
    }

    public static void d(String tag, String message){
        if(LOG_ENABLE) {
            android.util.Log.d(tag, message);
        }
    }

    public static void e(String message){
        e(TAG, message);
    }

    public static void e(String tag, String message){
        if(LOG_ENABLE) {
            android.util.Log.e(tag, message);
        }
    }
}
