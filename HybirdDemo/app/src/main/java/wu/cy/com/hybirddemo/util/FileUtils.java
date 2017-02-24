package wu.cy.com.hybirddemo.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * io操作工具类，未完成
 * Created by WILL on 2016/3/30.
 */
public class FileUtils {

    /**
     * 获取data中文件的input
     *
     * @param context
     * @param fileName 相对路径
     * @return
     */
    public static InputStream readInputFromData(Context context, String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(context.getFilesDir() + "/" + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;

    }

    public static InputStream readInputFromAssets(Context context, String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    public static InputStream readInputFromRaw(Context context, int resId) {
        InputStream inputStream = context.getResources().openRawResource(resId);
        return inputStream;
    }

    public static String readFromData(Context context, String fileName) {
        return readFromInputStream(readInputFromData(context, fileName));

    }

    public static String readFromAssets(Context context, String fileName) {
        return readFromInputStream(readInputFromAssets(context, fileName));
    }

    public static String readFromRaw(Context context, int resId) {
        return readFromInputStream(readInputFromRaw(context, resId));
    }

    public static String readFromInputStream(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();
            for (String str; (str = reader.readLine()) != null; ) {
                sb.append(str);
            }

            reader.close();
            inputStream.close();
            return sb.toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void write2Data(Context context, String fileName, String writeString) {
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(writeString.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean write2File(String filePath,InputStream inputStream) {
        if(filePath == null || inputStream == null){
            return false;
        }
        try {
            File file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            return input2Output(inputStream, fos);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean input2Output(InputStream inputStream, OutputStream outputStream) {
        if(inputStream == null && outputStream == null){
            return false;
        }
        try {
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, count);
            }
            outputStream.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(inputStream);
        }
    }

    public static boolean write2File(Context context, String fileName, InputStream inputStream) {
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, count);
            }
            outputStream.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 解压，使用apache包，解决zip64问题
     */
    public static void unZipApache(InputStream zipFileInputStream, String destDir) throws IOException {
        destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;
        ZipArchiveInputStream is = null;

        try {
            is = new ZipArchiveInputStream(new BufferedInputStream(zipFileInputStream, 2048));
            ZipArchiveEntry entry = null;
            while ((entry = is.getNextZipEntry()) != null) {
                File file = new File(destDir, entry.getName());
                File dir = entry.isDirectory() ? file : file.getParentFile();
                if (!dir.isDirectory() && !dir.mkdirs())
                    throw new FileNotFoundException("Failed to ensure directory: " +
                            dir.getAbsolutePath());

                if (entry.isDirectory())
                    continue;

                OutputStream os = null;
                try {
                    os = new BufferedOutputStream(new FileOutputStream(file), 2048);
                    IOUtils.copy(is, os);
                } finally {
                    IOUtils.closeQuietly(os);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }


    /**
     * 下载文件
     *
     * @param url
     * @param md5
     * return true success, false failure
     */
    public static boolean downloadFile(final String url, final String md5, String destFile) {
        YLog.d("start download url " + url);
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = OkHttpUtil.getClient().newCall(request).execute();
            if (response.isSuccessful()) {
                InputStream inputStream = null;
                inputStream = response.body().byteStream();

                if (inputStream != null) {
                    if(write2File(destFile, inputStream)){
                        if(TextUtils.equals(md5,MD5Util.getFileMD5(destFile))){
                            YLog.d("downloadZip_success to " + destFile);
                            return true;
                        }
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            YLog.d("downloadZip fail");
            e.printStackTrace();
        } catch (IOException e) {
            YLog.d("downloadZip fail");
            e.printStackTrace();
        }
        return false;
    }

    public static String getFileNameFromUrl(String url){
        int lastIndex = url.lastIndexOf("/");
        return url.substring(lastIndex, url.length()-1);
    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if(children !=null){
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }else {
                return dir.delete();
            }
        }else {
            return dir.delete();
        }
        return false;
    }

    public static void createFile(File file, boolean isFile) {// 创建文件
        if (!file.exists()) {// 如果文件不存在
            if (!file.getParentFile().exists()) {// 如果文件父目录不存在
                createFile(file.getParentFile(), false);
            } else {// 存在文件父目录
                if (isFile) {// 创建文件
                    try {
                        file.createNewFile();// 创建新文件
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    file.mkdirs();// 创建目录
                }
            }
        }
    }
}
