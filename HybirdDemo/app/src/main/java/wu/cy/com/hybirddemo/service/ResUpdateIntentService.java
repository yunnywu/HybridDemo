package wu.cy.com.hybirddemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import wu.cy.com.hybirddemo.util.IoUtils;

/**
 * update the Resources file
 */
public class ResUpdateIntentService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_RES_UPDATE= "wu.cy.com.hybirddemo.service.action.RES_UPDATE";


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
    public static void startActionResUpdate(Context context,  String param) {
        Intent intent = new Intent(context, ResUpdateIntentService.class);
        intent.setAction(ACTION_RES_UPDATE);
        intent.putExtra(EXTRA_PARAM, param);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_RES_UPDATE.equals(action)) {
                final String param = intent.getStringExtra(EXTRA_PARAM);
                handleActionResUpdate(param);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionResUpdate(String param) {
        assets2Data(this);
    }

    /**
     * 把assets中的zip写入缓存中
     */
    public static void assets2Data(final Context context) {
        // 这3个变量每个版本不同需要更新
        final String fileName = "archive_1.26.0_e506b7a123f49ce6dae369914d596a03.zip";
        final String version = "1.26.0";
        final String md5 = "e506b7a123f49ce6dae369914d596a03";
        final String domain = "";
        InputStream inputStream = IoUtils.readInputFromAssets(context, fileName);
        zip2Data(context, inputStream, version, md5, domain);
    }


    /**
     * 解压zip包至缓存
     */
    private static void zip2Data(final Context context, final InputStream inputStream, final String version, final String md5, final String domain) {
                if (inputStream != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 因为inputStream不能重用，用于复制inputStream
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) > -1) {
                        baos.write(buffer, 0, len);
                    }
                    baos.flush();

                    String zipMD5 = MD5.getFileMD5(new ByteArrayInputStream(baos.toByteArray()));
                    LogUtils.d(LogUtils.TAG_LOCAL, "zipMD5:%s,md5:%s", zipMD5, md5);
                    if (md5.equals(zipMD5)) {
                        try {
                            IoUtils.unZipApache(new ByteArrayInputStream(baos.toByteArray()), context.getFilesDir().getAbsolutePath().toString() + "/" + LocalIntercept.LOCAL_PATH);
                            LocalSpHelper.saveVersionInfo(version, md5);
                            LocalSpHelper.saveDomain(domain);
                            LogUtils.d(LogUtils.TAG_LOCAL, "zip解压至data成功");
                            LocalSpHelper.setUpdating(false);//标记更新完毕
                            LocalIntercept.clearInterceptDomain();
                        } catch (Exception e) {
                            LogUtils.e(LogUtils.TAG_LOCAL, Log.getStackTraceString(e));
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

    }
}
