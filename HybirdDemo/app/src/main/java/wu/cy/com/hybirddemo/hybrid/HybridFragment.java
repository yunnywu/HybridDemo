package wu.cy.com.hybirddemo.hybrid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.cy.wu.jsbridge.BridgeWebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.cy.com.hybirddemo.R;
import wu.cy.com.hybirddemo.hybrid.handler.HandlerManager;

/**
 * Created by wcy8038 on 2017/2/21.
 */

public class HybridFragment extends Fragment {

    public static final String URL_BLANK = "about:blank";

    private static final String EXTRA_LOAD_URL = "extra_load_url";

    private static final String TAG = "HybridFragment";

    private String mUrl;

    @Bind(R.id.webView)
    BridgeWebView mWebView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mUrl = getArguments().getString(EXTRA_LOAD_URL);
        }
    }

    public static HybridFragment newInstance(String url) {
        HybridFragment hybridFragment = new HybridFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_LOAD_URL, url);
        hybridFragment.setArguments(bundle);
        return hybridFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hybrid, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWebView();
        loadUrl();
    }

    private void loadUrl() {
        if(TextUtils.isEmpty(mUrl)){
            return;
        }
        mWebView.loadUrl(mUrl);
        //注册handler
        HandlerManager handlerManager = new HandlerManager(getContext(), mWebView);
        handlerManager.registerHandlers();
    }

    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setSupportZoom(true);
//        webSettings.setBuiltInZoomControls(false);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); // 默认缓存机制
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setDefaultFontSize(18);
        webSettings.setDefaultTextEncodingName("utf-8");

        mWebView.setWebChromeClient(new HybridWebChromeClient());
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String s1, String s2, String s3, long l) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        destroyTheWebView();
    }

    private void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            if (!title.contains(URL_BLANK)) {
                mToolbar.setTitle(title);
            }
        }
    }

    private void destroyTheWebView() {
        if (mWebView != null) {
            if (mWebView.getParent() != null) {
                ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            }
            WebView webView = mWebView;
            mWebView = null;
            webView.destroy();
        }
    }

    class HybridWebChromeClient extends WebChromeClient{
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }
}
