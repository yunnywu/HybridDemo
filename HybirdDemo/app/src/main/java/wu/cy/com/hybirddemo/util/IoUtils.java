package wu.cy.com.hybirddemo.util;

import android.content.Context;
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

/**
 * io操作工具类，未完成
 * Created by WILL on 2016/3/30.
 */
public class IoUtils {

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
            Log.d("wcy", "write2Data success");
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

    public static void write2Data(Context context, String fileName, InputStream inputStream) {
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            byte[] buffer = new byte[512];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, count);
            }
            outputStream.flush();
            Log.d("wcy", "write2Data success");
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
    public static void unZipApache(InputStream zipFileInputStream, String destDir) throws Exception {
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

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }


    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }


    public static void renameDir(String fromDir, String toDir) {
        File from = new File(fromDir);
        if (!from.exists() || !from.isDirectory()) {
            Log.d("wcy", "Directory does not exist: " + fromDir);
            return;
        }

        File to = new File(toDir);
        if (from.renameTo(to)) {
            Log.d("wcy", "rename Success");
        } else {
            Log.d("wcy", "rename fail");
        }
    }
}
