package wu.cy.com.hybirddemo.hybrid;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.cy.wu.jsbridge.BridgeWebView;
import com.cy.wu.jsbridge.BridgeWebViewClient;

import wu.cy.com.hybirddemo.util.YLog;

/**
 * Created by wcy8038 on 2017/3/13.
 */

public class CustomWebViewClient extends BridgeWebViewClient{
    public CustomWebViewClient(BridgeWebView webView) {
        super(webView);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        //This method execute on Not UIThread.
        WebResourceResponse webResourceResponse = LocalIntercept.intercept(view, url);
        if (webResourceResponse != null) {
            YLog.d("替换资源成功");
            return webResourceResponse;
        }
        YLog.d("使用外部资源 ");
        return super.shouldInterceptRequest(view, url);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        //This method execute on Not UIThread.
        WebResourceResponse webResourceResponse = LocalIntercept.intercept(view, request.getUrl().toString());
        if (webResourceResponse != null) {
            YLog.d("替换资源成功");
            return webResourceResponse;
        }
        YLog.d("使用外部资源");
        return super.shouldInterceptRequest(view, request);
    }


}
