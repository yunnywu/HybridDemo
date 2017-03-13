package wu.cy.com.hybirddemo.hybrid;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import wu.cy.com.hybirddemo.service.LocalSpHelper;
import wu.cy.com.hybirddemo.service.LocalUpdate;
import wu.cy.com.hybirddemo.util.YLog;

/**
 * Created by wcy8038 on 2017/3/13.
 */

public class LocalIntercept {

    private static final String INTERCEPT_DOMAIN = "http://10.21.18.47:9091/example/img/";

    private static final HashMap<String, String> postfixMap = new HashMap<String, String>();

    static {
        postfixMap.put(".js", "application/x-javascript");
        postfixMap.put(".css", "text/css");
        postfixMap.put(".jpg", "image/jpeg");
        postfixMap.put(".jpeg", "image/jpeg");
        postfixMap.put(".png", "image/png");
        postfixMap.put(".gif", "image/gif");
        postfixMap.put(".svg", "text/xml");
        postfixMap.put(".ttf", "application/octet-stream");
        postfixMap.put(".woff", "application/octet-stream");
        postfixMap.put(".woff2", "application/octet-stream");
        postfixMap.put(".eot", "application/octet-stream");
        postfixMap.put(".html", "text/html");
    }

    public static WebResourceResponse intercept(WebView webview, String url) {
        if (url != null) {
            YLog.d("尝试拦截资源:" + url);
            return getInterceptFile(webview.getContext(), url);
        }
        return null;
    }

    private static WebResourceResponse getInterceptFile(Context context, String url) {
        if(shouldInterceptUrl(url)){
            InputStream localFile = getLocalFileInputStream(context, url);
            if (localFile != null) {
                WebResourceResponse resourceResponse = new WebResourceResponse(
                        postfixMap.get(getPostfix(url)), "UTF-8", localFile);
                return resourceResponse;
            }
        }
        return null;
    }

    private static boolean shouldInterceptUrl(String url) {
        if(TextUtils.isEmpty(url) || url.endsWith(".html")){
            return false;
        }
        if(LocalUpdate.local_updating){//正在更新
            return false;
        }

        if(!url.contains(INTERCEPT_DOMAIN)){
           return false;
        }
        return true;
    }

    /**
     * 获取文件后缀名
     *
     * @param url
     * @return
     */
    public static String getPostfix(String url) {
        if (url != null) {
            if (url.contains(".")) {
                return url.substring(url.lastIndexOf(".") + 1);
            }
        }
        return null;
    }

    /**
     * 获取去掉域名后带路径的文件名
     *
     * @param url
     * @return
     */
    public static String getFileNameWithPath(String url) {
        if (url != null) {
           YLog.d("原始url: " + url);

            url = url.substring(url.indexOf(INTERCEPT_DOMAIN) + INTERCEPT_DOMAIN.length());

            if (url.contains("?")) {
                url = url.substring(0, url.indexOf("?"));
            }
            YLog.d("拦截url: " + url);
            return url;

        }
        return null;
    }

    /**
     * 从本地缓存中获取文件
     *
     * @return
     */
    private static InputStream getLocalFileInputStream(Context context, String url) {
        String fileName = getFileNameWithPath(url);
        if (!TextUtils.isEmpty(fileName)) {
            fileName = LocalUpdate.LOCAL_PATH + "/" + LocalSpHelper.getVersion() + "/" + fileName;
            InputStream inputStream = null;
           YLog.d("尝试从data中获取文件：" + fileName);
            try {
                inputStream = new FileInputStream(context.getFilesDir() + "/" + fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (inputStream != null) {
                YLog.e("获取文件成功");
                return inputStream;
            } else {
                YLog.e("获取文件失败");
            }
            return inputStream;
        }
        return null;
    }
}
