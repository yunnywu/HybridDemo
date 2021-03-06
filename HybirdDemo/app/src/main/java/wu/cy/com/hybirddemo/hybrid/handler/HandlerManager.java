package wu.cy.com.hybirddemo.hybrid.handler;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;

import com.cy.wu.jsbridge.BridgeHandler;
import com.cy.wu.jsbridge.BridgeWebView;
import com.cy.wu.jsbridge.CallBackFunction;

import org.json.JSONException;
import org.json.JSONObject;

import wu.cy.com.hybirddemo.util.YLog;

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
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String message = jsonObject.optString("message");
                    String duration = jsonObject.optString("duration");
                    int timeType = TextUtils.equals("long", duration) ?  Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
                    YLog.d("message = " + message + " duration = " + duration + " timeType =  " + timeType);
                    if(!TextUtils.isEmpty(message)) {
                        Toast.makeText(mContext, message, timeType).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        mBridgeWebView.registerHandler("alertDialog", new BridgeHandler() {
            @Override
            public void handler(String data, final CallBackFunction function) {
                try {
                    final JSONObject jsonObject = new JSONObject(data);
                    String title = jsonObject.optString("title");
                    String message = jsonObject.optString("message");
                    if(!TextUtils.isEmpty(message)) {
                        new AlertDialog.Builder(mContext)
                                .setTitle(title)
                                .setMessage(message)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        JSONObject jsonObject1 = new JSONObject();
                                        try {
                                            jsonObject1.put("result","ok");
                                            JsCallBack callBack = new JsCallBack(0, "", jsonObject1);
                                            function.onCallBack(callBack.toJson());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                         JSONObject jsonObject1 = new JSONObject();
                                        try {
                                            jsonObject1.put("result","cancel");
                                            JsCallBack callBack = new JsCallBack(0, "", jsonObject1);
                                            function.onCallBack(callBack.toJson());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                })
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }
}
