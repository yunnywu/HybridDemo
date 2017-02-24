package wu.cy.com.hybirddemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by wcy8038 on 2017/2/24.
 */

public class YApp extends Application{

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sContext = base;
    }

    public static Context getAppContext(){
        return sContext;
    }
}
