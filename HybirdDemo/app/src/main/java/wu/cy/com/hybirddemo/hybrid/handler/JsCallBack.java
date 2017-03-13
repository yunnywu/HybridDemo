package wu.cy.com.hybirddemo.hybrid.handler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wcy8038 on 2017/3/13.
 */

public class JsCallBack {

    int retCode;

    String retMessage;

    JSONObject retData;


    public JsCallBack(int retCode, String retMessage, JSONObject retData) {
        this.retCode = retCode;
        this.retMessage = retMessage;
        this.retData = retData;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public JSONObject getRetData() {
        return retData;
    }

    public void setRetData(JSONObject retData) {
        this.retData = retData;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("retCode", retCode);

            if(retMessage != null){
                jsonObject.put("retMessage", retMessage);
            }
            if(retData != null){
                jsonObject.put("retData", retData.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
