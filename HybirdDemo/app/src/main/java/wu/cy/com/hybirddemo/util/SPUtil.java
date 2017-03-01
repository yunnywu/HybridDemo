package wu.cy.com.hybirddemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import wu.cy.com.hybirddemo.YApp;

/**
 * Created by wcy8038 on 2017/2/24.
 */

public class SPUtil {
    private static SharedPreferences sPreference;

    private static SharedPreferences getPreference(){
        if(sPreference == null) {
            synchronized (SPUtil.class) {
                if(sPreference == null){
                    sPreference = YApp.getAppContext().getSharedPreferences(YApp.getAppContext().getPackageName(),
                            Context.MODE_PRIVATE);
                }
            }
        }
        return sPreference;
    }

    public static boolean getBoolean(String key, boolean defaultValue){
        return getPreference().getBoolean(key, defaultValue);
    }

    public static void putBooleanSync(String key, boolean value){
        getPreference().edit().putBoolean(key, value).apply();
    }

    public static void putString(String key, String value){
        getPreference().edit().putString(key, value).apply();
    }

    public static String getString(String key, String defaultValue){
        return getPreference().getString(key, defaultValue);
    }
}
