package wu.cy.com.hybirddemo.hybrid.handler;

import android.content.Context;
import android.widget.Toast;

import com.cy.wu.jsbridge.BridgeHandler;
import com.cy.wu.jsbridge.BridgeWebView;
import com.cy.wu.jsbridge.CallBackFunction;

/**
 * Created by wcy8038 on 2017/3/8.
 */

public class HandlerManager {
    private BridgeWebView mBridgeWebView;
    private Context mContext;

    public HandlerManager(Context context, BridgeWebView bridgeWebView) {
        mContext = context;
        mBridgeWebView = bridgeWebView;
    }

    public void registerHandlers(){

        mBridgeWebView.registerHandler("toast", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Toast.makeText(mContext, data, Toast.LENGTH_SHORT).show();
            }
        });





    }
}
