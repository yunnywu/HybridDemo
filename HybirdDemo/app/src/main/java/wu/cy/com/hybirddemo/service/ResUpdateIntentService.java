package wu.cy.com.hybirddemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.apache.commons.compress.utils.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import wu.cy.com.hybirddemo.util.IoUtils;
import wu.cy.com.hybirddemo.util.MD5Util;
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

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionResUpdate(Context context) {
        Intent intent = new Intent(context, ResUpdateIntentService.class);
        intent.setAction(ACTION_RES_UPDATE);
        context.startService(intent);
    }

    public static void startActionUnzipAssets(Context context) {
        Intent intent = new Intent(context, ResUpdateIntentService.class);
        intent.setAction(ACTION_UNZIP_ASSETS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if(TextUtils.isEmpty(action)){
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
        assets2Data(this);
    }

    private void handleActionUnzipAssets() {
        assets2Data(this);
    }

    /**
     * 把assets中的zip写入缓存中
     */
    public static void assets2Data(final Context context) {
        // 这3个变量每个版本不同需要更新
        final String fileName = "125.zip";
        final String version = "1.25.0";
        final String md5 = "e506b7a123f49ce6dae369914d596a03";
        final String domain = "";
        InputStream inputStream = null;
        String exactFile =  context.getFilesDir().getAbsolutePath().toString() + File.separator + "local";
        try {
            inputStream = context.getResources().getAssets().open(fileName);
            IoUtils.unZipApache(inputStream, exactFile);
            YLog.d("unZipApache Success from asset" + fileName + " to " + exactFile);
        }catch (IOException e) {
            YLog.e("unZipApache Fail from asset" + fileName + " to " + exactFile);
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }


    /**
     * 解压zip包至缓存
     */
//    private static void zip2Data(final Context context, final InputStream inputStream,
//                                 final String path) throws IOException {
//        if (inputStream != null) {
//
//            IoUtils.unZipApache(new ByteArrayInputStream(baos.toByteArray()),
//                            context.getFilesDir().getAbsolutePath().toString() + File.separator + "");
//
//        }
//    }
}
