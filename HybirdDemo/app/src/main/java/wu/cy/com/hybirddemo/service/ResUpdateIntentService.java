package wu.cy.com.hybirddemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.text.TextUtils;

import org.apache.commons.compress.utils.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import wu.cy.com.hybirddemo.util.FileUtils;
import wu.cy.com.hybirddemo.util.OkHttpUtil;
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
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("version", "1.2.5");
            jsonObject.put("md5", "12131213");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        OkHttpUtil.postRequest("http://10.4.55.29:9091/check", jsonObject.toString(), new OkHttpUtil.IOkHttpListener() {
            @Override
            public void onSuccess(JSONObject dataObj) {
                try {
                    boolean needUpdate = dataObj.getBoolean("needUpdate");
                    if (needUpdate){
                        String patchUrl = dataObj.getString("patchUrl");
                        String patchMD5 = dataObj.getString("pathMD5");
                        String newVersion = dataObj.getString("newVersion");
                        String entireUrl = dataObj.getString("entireUrl");
                        String entireMD5 = dataObj.getString("entireMD5");

                        if(!TextUtils.isEmpty(patchUrl) && !TextUtils.isEmpty(patchMD5)) {
                            String filePath = ResUpdateIntentService.this.getFilesDir() +
                                    File.separator + "cfp/" + FileUtils.getFileNameFromUrl(patchUrl);
                            if(FileUtils.downloadFile(patchUrl, patchMD5, filePath)) {
                                //start patch process
                            }
                        }else if(!TextUtils.isEmpty(entireUrl) && !TextUtils.isEmpty(entireMD5)){
                            String filePath = ResUpdateIntentService.this.getFilesDir() +
                                    File.separator + "cfp/" + FileUtils.getFileNameFromUrl(patchUrl);
                            if(FileUtils.downloadFile(patchUrl, patchMD5, filePath)) {
                                //start zip process
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (dataObj == null) {
                    YLog.d("error parse data");
                    return;
                }
            }

            @Override
            public void onError(String error) {
                YLog.d("result error " + error);
            }
        });
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
            FileUtils.unZipApache(inputStream, exactFile);
            YLog.d("unZipApache Success from asset" + fileName + " to " + exactFile);
        }catch (IOException e) {
            YLog.e("unZipApache Fail from asset" + fileName + " to " + exactFile);
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
