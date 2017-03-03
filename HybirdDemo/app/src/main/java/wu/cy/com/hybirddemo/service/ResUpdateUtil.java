package wu.cy.com.hybirddemo.service;

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
 * Created by wcy8038 on 2017/3/1.
 */

public class ResUpdateUtil {

    public static final String RES_UPDATE_VERSION = "ResUpdate_version";
    public static final String RES_UPDATE_MD5 = "ResUpdate_md5";
    public static final String RES_UPDATE_FILENAME = "ResUpdate_file_name";


    public static void updateResFromServer(final Context context){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("version", SPUtil.getString("ResUpdate_version", ""));
            jsonObject.put("md5", SPUtil.getString("ResUpdate_MD5", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtil.postRequest("http://192.168.31.199:9091/check", jsonObject.toString(), new OkHttpUtil.IOkHttpListener() {
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
                        String newFileName = "archive_" + newVersion + "_" + entireMD5 + ".zip";
                        String newFilePath = context.getFilesDir() +
                                File.separator + "cfp/" + newFileName;

                        if(!TextUtils.isEmpty(patchUrl) && !TextUtils.isEmpty(patchMD5)) {
                            String filePath = context.getFilesDir() +
                                    File.separator + "cfp/" + FileUtils.getFileNameFromUrl(patchUrl);
                            if(FileUtils.downloadFile(patchUrl, patchMD5, filePath)) {
                                //start patch process
                                if(patchTheZip(context, filePath, entireMD5, newFilePath)){
                                    try {
                                        File file = new File(newFilePath);
                                        String exactFile = context.getFilesDir().getAbsolutePath().toString() + File.separator + "local";
                                        FileInputStream fileInputStream = new FileInputStream(file);
                                        FileUtils.unZipApache(fileInputStream, exactFile);

                                        SPUtil.putString(RES_UPDATE_VERSION, newVersion);
                                        SPUtil.putString(RES_UPDATE_MD5, entireMD5);
                                        SPUtil.putString(RES_UPDATE_FILENAME, filePath);
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }
                                }else {
                                    //download the whole Patch
                                    downTheWholeFile(patchUrl, patchMD5, newVersion, entireMD5, context);
                                }
                            }
                        }else if(!TextUtils.isEmpty(entireUrl) && !TextUtils.isEmpty(entireMD5)){
                            downTheWholeFile(patchUrl, patchMD5, newVersion, entireMD5, context);
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

    private static void downTheWholeFile(String patchUrl, String patchMD5, String newVersion, String entireMD5, Context context) {
        String filePath = context.getFilesDir() +
                File.separator + "cfp/" + FileUtils.getFileNameFromUrl(patchUrl);
        if(FileUtils.downloadFile(patchUrl, patchMD5, filePath)) {
            //start zip process
            try {
                File file = new File(filePath);
                String exactFile = context.getFilesDir().getAbsolutePath().toString() + File.separator + "local";
                FileInputStream fileInputStream = new FileInputStream(file);
                FileUtils.unZipApache(fileInputStream, exactFile);

                SPUtil.putString(RES_UPDATE_VERSION, newVersion);
                SPUtil.putString(RES_UPDATE_MD5, entireMD5);
                SPUtil.putString(RES_UPDATE_FILENAME, filePath);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static  boolean patchTheZip(Context context,String pathFilePath, String entireMD5,String newFilePath) {
        String fileName = SPUtil.getString(RES_UPDATE_FILENAME, "");
        if(TextUtils.isEmpty(fileName)){
            YLog.e("local file name empty");
            return false;
        }
        String localFilePath = context.getFilesDir() +
                File.separator + "cfp/" + fileName;
        File localFile = new File(localFilePath);
        if(!localFile.exists()){
            YLog.e("local file dismiss");
            return false;
        }


        int result = PatchDiffUtil.patch(localFilePath, newFilePath, pathFilePath);
        YLog.d("PatchDiffUtil patch localFilePath = "  +  localFilePath );
        YLog.d("PatchDiffUtil patch newFilePath = "  +  newFilePath );
        YLog.d("PatchDiffUtil patch pathFilePath = "  +  pathFilePath );
        YLog.d("PatchDiffUtil patch result = "  +  result );
        if(result != 0){
            return false;
        }

        if(MD5Util.getFileMD5(newFilePath).equals(entireMD5)){
            YLog.d("PatchDiffUtil patch success md5 right = "  + entireMD5 );
            return true;
        }
        return false;
    }


    /**
     * 把assets中的zip写入缓存中
     */
    public static void assets2Data(final Context context) {
        // 这3个变量每个版本不同需要更新
        final String fileName = "archive_1.25.0_4bcdaf5caec714285865da7dc8dae3c2.zip";
        final String version = "1.25.0";
        final String md5 = "4bcdaf5caec714285865da7dc8dae3c2";
        final String domain = "";
        InputStream inputStream = null;
        String exactFile =  context.getFilesDir().getAbsolutePath().toString() + File.separator + "local";
        try {
            //先拷贝到内部存储
            inputStream = context.getResources().getAssets().open(fileName);
            File file = new File(context.getFilesDir(), "cfp");
            if(!file.exists()){
                file.mkdirs();
            }
            File destFile = new File(file, fileName);
            FileUtils.write2File(destFile.getAbsolutePath(), inputStream);
            if(MD5Util.getFileMD5(destFile).equals(md5)){
                YLog.d("zip copy successful to " + destFile.getAbsolutePath());
                FileInputStream fileInputStream = new FileInputStream(destFile);
                FileUtils.unZipApache(fileInputStream, exactFile);
                YLog.d("unZipApache Success from asset" + fileName + " to " + exactFile);
                SPUtil.putBooleanSync("ResUpdate" + PackageUtil.getVersionName(), true);
                SPUtil.putString(RES_UPDATE_VERSION, version);
                SPUtil.putString(RES_UPDATE_MD5, md5);
                SPUtil.putString(RES_UPDATE_FILENAME, fileName);
            }
        }catch (IOException e) {
            YLog.e("unZipApache Fail from asset" + fileName + " to " + exactFile);
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
