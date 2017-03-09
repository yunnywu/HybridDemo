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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import wu.cy.com.hybirddemo.Global;
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
        updateResFromServer(this);
    }


    public  void updateResFromServer(final Context context){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("version", LocalSpHelper.getVersion());
            jsonObject.put("md5", LocalSpHelper.getMd5());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtil.postRequest(Global.HOST+"/check", jsonObject.toString(), new OkHttpUtil.IOkHttpListener() {
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

                        handleActionDownload(entireUrl, entireMD5, newVersion, patchUrl, patchMD5);

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

    private void handleActionDownload(final String downloadUrl, final String md5,
                                      final String version,final String patchUrl, final String patchMd5) {
        if(TextUtils.equals(md5, LocalSpHelper.getMd5()) &&
                TextUtils.equals(version, LocalSpHelper.getVersion())){
            return;//如果要下载的和本地一样。不下载。
        }

        LocalUpdate.local_updating = true; //标记需要更新
        YLog.d("local更新接口返回===md5:" + md5 + " version =" + version + " downloadUrl = "
                + downloadUrl + " patchUrl = " + patchUrl + " patchMd5 = " + patchMd5);

        if(!TextUtils.isEmpty(patchUrl)){//download patch, merge the patch
            String patchFileName = FileUtils.getFileNameFromUrl(patchUrl);//patch file name
            String newFileName = FileUtils.getFileNameFromUrl(downloadUrl); //合成之后的文件
            String baseFileName = LocalSpHelper.getFileName();//本地当前版本的文件
            boolean patchResult = downloadPatchAndMerge(patchUrl, md5, version, patchFileName,
                    newFileName, baseFileName, patchMd5);
            if(!patchResult){//patch 下载合成失败 ， 用完整的url 下载解压
                downloadTheWholeZip(downloadUrl, md5, version);
            }
        }else {
            downloadTheWholeZip(downloadUrl, md5, version);
        }
    }

    /**
     * 下载patch 文件并且合成
     * @param downloadUrl
     * @param md5
     * @param version
     * @param patchFileName
     * @param newFileName
     * @param baseFileName
     */
    private boolean downloadPatchAndMerge(String downloadUrl, String md5, String version, String patchFileName,
                                          String newFileName, String baseFileName, String patchMD5) {
        if(downloadZip(ResUpdateIntentService.this, downloadUrl, patchFileName)){//下载成功
            File patchFold = LocalUpdate.getHybridPatchDir(ResUpdateIntentService.this);
            File patchFile = new File(patchFold, patchFileName); //pacth 文件
            YLog.d("patchFile MD5 = %s" + MD5Util.calculateMD5(patchFile));
            if(!TextUtils.equals(MD5Util.calculateMD5(patchFile), patchMD5)){
                return false;//下载文件失败
            }

            File baseFile = new File(patchFold, baseFileName); //当前版本的本地文件
            File newFile = new File(patchFold, newFileName); //需要生成的版本文件
            if(patchFile.exists() && baseFile.exists()){
                YLog.d("PatchUtil.patch from "
                         + baseFile.getAbsolutePath() + " + " + patchFile.getAbsolutePath()+ " = " + newFile.getAbsolutePath());
                int result = PatchDiffUtil.patch(baseFile.getAbsolutePath(), newFile.getAbsolutePath(),
                        patchFile.getAbsolutePath());
                if(result == 0 && TextUtils.equals(md5, MD5Util.calculateMD5(newFile))){
                    YLog.d("PatchUtil.patch successful  MD5 Right!!");
                    return zipFileToLocal(ResUpdateIntentService.this, version, md5, newFile, newFileName);
                }else {
                    YLog.d("PatchUtil.patch Fail result = "  + result);
                }
            }
        }

        return false;
    }

    private void downloadTheWholeZip(String downloadUrl, String md5, String version) {
        String fileName = FileUtils.getFileNameFromUrl(downloadUrl);
        if(downloadZip(ResUpdateIntentService.this,downloadUrl, fileName)){//下载成功
            File patchFold = LocalUpdate.getHybridPatchDir(ResUpdateIntentService.this);
            File wholeFile = new File(patchFold, fileName);
            YLog.d("wholeFile MD5 = " + MD5Util.calculateMD5(wholeFile));
            if(TextUtils.equals(md5, MD5Util.calculateMD5(wholeFile))) {
                zipFileToLocal(ResUpdateIntentService.this, version, md5, wholeFile, fileName);
            }
        }
    }


    private void handleActionUnzipAssets() {
        LocalUpdate.local_updating = true;
        LocalUpdate.clearLocal(ResUpdateIntentService.this);// 清空缓存
        LocalUpdate.clearPatch(ResUpdateIntentService.this);//清空patch 文件夹
        assets2Data(ResUpdateIntentService.this);// 加载assets内的缓存到data中
    }

    /**
     * 把assets中的zip写入缓存中
     */
    private void assets2Data(final Context context) {
        final String fileName = "res_1.0.0_ca35bee9f659e9c11abe2cd300e1295d.zip";
        final String version = "1.0.0";
        final String md5 = "ca35bee9f659e9c11abe2cd300e1295d";

        //重新建立patch fold
        File patchFold = LocalUpdate.getHybridPatchDir(context);
        File patchFile = new File(patchFold, fileName);
        if(copyAssetsToData(context, fileName, patchFile)){//copy to patch/
            zipFileToLocal(context, version, md5, patchFile, fileName);
        }
    }

    private static boolean copyAssetsToData(Context context,String fileName, File patchFile){
        YLog.d("copyAssetsToData " + fileName + " to " + patchFile.getAbsolutePath());
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            is = context.getResources().getAssets().open(fileName);
            if (!patchFile.exists()) {// 判断目标目录中文件是否存在
                patchFile.createNewFile();
            }
            fos = new FileOutputStream(patchFile);// 文件输出流
            byte[] buffer = new byte[1024];// 字节数组
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            return true;
        } catch (IOException e) {// 捕获异常
            e.printStackTrace();
            return false;
        }finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(fos);
        }
    }

    /**
     * 下载zip文件包
     *
     * @param url
     * @param fileName 下载保存的的文件名
     */
    private boolean downloadZip(final Context context, final String url, final String fileName) {
        YLog.d("downloadZip:" + url);
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                YLog.d("downloadZip_success");
                inputStream = response.body().byteStream();
                if (inputStream != null) {
                    File destFile = new File(LocalUpdate.getHybridPatchDirPath(context), fileName);
                    YLog.d("downloadZip: to " + destFile.getAbsolutePath());
                    fos = new FileOutputStream(destFile);
                    byte[] buf = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                }
                return true;
            }
        } catch (IllegalArgumentException e) {
            YLog.d("downloadZip_onFailure");
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            YLog.d("downloadZip_onFailure");
            e.printStackTrace();
            return false;
        }finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(fos);
        }
        return false;
    }

    private boolean zipFileToLocal(Context context, String version, String md5,
                                   File patchFile, String fileName) {
        YLog.d("zipFileToLocal " +  patchFile.getAbsolutePath());
        LocalUpdate.clearLocal(this);//删除原来的资源包
        try {
            //zip from patch to local
            FileUtils.unZipApache(new FileInputStream(patchFile),
                    context.getFilesDir().getAbsolutePath().toString() + "/" + LocalUpdate.LOCAL_PATH);
            LocalSpHelper.saveVersionInfo(version, md5);
            LocalSpHelper.saveFileName(fileName);
            clearTheOldFiles(fileName);//删除其他文件，只保留一个当前的版本的完整文件
            YLog.d("zip解压至data成功");
            LocalUpdate.local_updating = false;//标记更新完毕
            SPUtil.putBooleanSync("ResUpdate" + PackageUtil.getVersionName() , true);
            //LocalIntercept.clearInterceptDomain();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除其他文件，只保留一个当前的版本的完整文件
     * @param fileName 要保留的文件名
     */
    private void clearTheOldFiles(String fileName) {
        File file = LocalUpdate.getHybridPatchDir(ResUpdateIntentService.this);
        if(file != null && file.exists()){
            File[] files = file.listFiles();
            if(files != null){
                for(File f: files){
                    if(!f.getName().equals(fileName)){
                        f.delete();
                    }
                }
            }
        }
    }

}
