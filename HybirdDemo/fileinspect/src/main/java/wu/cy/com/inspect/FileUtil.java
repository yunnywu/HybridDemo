package wu.cy.com.inspect;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by wcy on 17/2/23.
 */

public class FileUtil {

    public static boolean copyFile(File toFile, File fromFile) {
        if(!fromFile.exists()){
            return false;
        }
        if (toFile.exists()) {// 判断目标目录中文件是否存在
            toFile.delete();
            createFile(toFile, true);// 创建文件
        }
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = new FileInputStream(fromFile);// 创建文件输入流
            fos = new FileOutputStream(toFile);// 文件输出流
            byte[] buffer = new byte[1024];// 字节数组
            while (is.read(buffer) != -1) {// 将文件内容写到文件中
                fos.write(buffer);
            }
            return true;
        } catch (FileNotFoundException e) {// 捕获文件不存在异常
            e.printStackTrace();
            return false;
        } catch (IOException e) {// 捕获异常
            e.printStackTrace();
            return false;
        }finally {
            closeSilence(is);
            closeSilence(fos);
        }
    }

    private static void closeSilence(Closeable is) {
        if(is!=null) {
            try {
                is.close();// 输入流关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                    file.mkdir();// 创建目录
                }
            }
        }
    }

    public static String getFileMD5(String fileName) {
        File file = new File(fileName);
        if (!file.isFile()|| !file.exists()) {
            return null;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return getFileMD5(in);
    }

    public static String getFileMD5(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        MessageDigest digest = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
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
}