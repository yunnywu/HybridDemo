package wu.cy.com.hybirddemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.text.TextUtils;

import org.apache.commons.compress.utils.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import wu.cy.com.hybirddemo.util.FileUtils;
import wu.cy.com.hybirddemo.util.MD5Util;
import wu.cy.com.hybirddemo.util.OkHttpUtil;
import wu.cy.com.hybirddemo.util.PackageUtil;
import wu.cy.com.hybirddemo.util.PatchDiffUtil;
import wu.cy.com.hybirddemo.util.SPUtil;
import wu.cy.com.hybirddemo.util.YLog;

/**
 * update the Resources file
 */
public class ResUpdateIntentService extends IntentService {
    private static final String ACTION_RES_UPDATE= "wu.cy.com.hybirddemo.service.action.RES_UPDATE";

    private static final String ACTION_UNZIP_ASSETS= "wu.cy.com.hybirddemo.service.action.UNZIP_ASSETS";

    private static final String EXTRA_PARAM = "wu.cy.com.hybirddemo.service.extra.PARAM2";

    public ResUpdateIntentService() {
        super("ResUpdateIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     *
     */
    public static void startActionResUpdate(Context context) {
        Intent intent = new Intent(context, ResUpdateIntentService.class);
        intent.setAction(ACTION_RES_UPDATE);
        context.startService(intent);
    }

    /**
     * 解压assets 下的文件到内部存储
     * @param context
     */
    public static void startActionUnzipAssets(Context context) {
        Intent intent = new Intent(context, ResUpdateIntentService.class);
        intent.setAction(ACTION_UNZIP_ASSETS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if(!TextUtils.isEmpty(action)){
                switch (action){
                    case ACTION_RES_UPDATE:
                        handleActionResUpdate();break;
                    case ACTION_UNZIP_ASSETS:
                        handleActionUnzipAssets();break;

                    default:
                        break;

                }
            }
        }
    }

    private void handleActionResUpdate() {
        ResUpdateUtil.updateResFromServer(this);
    }



    private void handleActionUnzipAssets() {
        ResUpdateUtil.assets2Data(this);
    }

}
