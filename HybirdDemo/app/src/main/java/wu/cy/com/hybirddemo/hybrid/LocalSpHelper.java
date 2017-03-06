package wu.cy.com.hybirddemo.hybrid;

import wu.cy.com.hybirddemo.util.SPUtil;

/**
 * Created by wcy8038 on 2017/3/6.
 */

public class LocalSpHelper {

    private static String SP_LOCAL_ZIP_VERSION = "sp_local_zip_version";
    private static String SP_LOCAL_ZIP_MD5 = "sp_local_zip_md5";
    private static String SP_LOCAL_ZIP_FILE_NAME = "sp_local_zip_file_name";

    public static void saveVersionInfo(String version, String md5) {
        SPUtil.putString(SP_LOCAL_ZIP_VERSION, version);
        SPUtil.putString(SP_LOCAL_ZIP_MD5, md5);
    }

    public static void saveFileName(String fileName){
        SPUtil.putString(SP_LOCAL_ZIP_FILE_NAME, fileName);
    }

    public static String getMd5() {
        return SPUtil.getString(SP_LOCAL_ZIP_MD5, "");
    }

    public static void saveMD5(String md5) {
        SPUtil.putString(SP_LOCAL_ZIP_MD5, md5);
    }

    public static String getVersion() {
        return SPUtil.getString(SP_LOCAL_ZIP_VERSION, "");
    }


    public static String getFileName() {
        return SPUtil.getString(SP_LOCAL_ZIP_FILE_NAME, "");
    }


    public static void clear() {
        saveVersionInfo("", "");
        saveMD5("");
    }


    public static void clearFileName() {
        saveFileName("");
    }
}
