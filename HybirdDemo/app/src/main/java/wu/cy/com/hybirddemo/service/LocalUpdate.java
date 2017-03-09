package wu.cy.com.hybirddemo.service;

import android.content.Context;

import java.io.File;

import wu.cy.com.hybirddemo.util.FileUtils;

/**
 * Created by wcy8038 on 2017/3/6.
 */

public class LocalUpdate {

    public static volatile boolean local_updating = false;

    /**
     * 清空local缓存
     */
    public static void clearLocal(Context context) {
        File file = new File(getHybridLocalDir(context));
        FileUtils.deleteDir(file);
        LocalSpHelper.clear();
    }

    /**
     * 清空Patch 文件
     */
    public static void clearPatch(Context context) {
        File patchFile = getHybridPatchDir(context);//delete patch files
        FileUtils.deleteDir(patchFile);
        LocalSpHelper.clear();
    }

    public static final String LOCAL_PATH = "local";

    public static final String PATCH_PATH = "patchs";

    public static String getHybridLocalDir(Context context) {
        String path = "";
        if (context != null) {
            path = context.getFilesDir().getAbsolutePath().toString() + "/" + LOCAL_PATH;
        }
        return path;
    }

    public static String getHybridPatchDirPath(Context context) {
        String path = "";
        if (context != null) {
            path = context.getFilesDir().getAbsolutePath().toString() + "/" + PATCH_PATH;
        }
        return path;
    }

    public static File getHybridPatchDir(Context context) {
        File file = new File(getHybridPatchDirPath(context));
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }
}
