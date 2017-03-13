package wu.cy.com.hybirddemo.hybrid;

import android.content.Context;
import android.util.AttributeSet;

import com.cy.wu.jsbridge.BridgeWebView;
import com.cy.wu.jsbridge.BridgeWebViewClient;

/**
 * Created by wcy8038 on 2017/3/13.
 */

public class CustomWebView extends BridgeWebView{
    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomWebView(Context context) {
        super(context);
    }

    @Override
    protected BridgeWebViewClient generateBridgeWebViewClient() {
        return new CustomWebViewClient(this);
    }
}
