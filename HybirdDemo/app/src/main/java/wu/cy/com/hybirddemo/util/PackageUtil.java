package wu.cy.com.hybirddemo.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import wu.cy.com.hybirddemo.YApp;

/**
 * Created by wcy8038 on 2017/2/24.
 */

public class PackageUtil {

    private static String versionName = "";

    private static int versionCode = 0;

    /**
     * 获取版本名称
     *
     * @return
     */
    public static String getVersionName() {
        if (TextUtils.isEmpty(versionName)) {
            PackageManager pm = YApp.getAppContext().getPackageManager();
            try {
                PackageInfo info = pm.getPackageInfo(YApp.getAppContext().getPackageName(),
                        0);
                versionName = info.versionName;
                versionCode = info.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return versionName;
    }

    public static int getVersionCode() {
        if (versionCode == 0) {
            getVersionName();
        }
        return versionCode;
    }
}
