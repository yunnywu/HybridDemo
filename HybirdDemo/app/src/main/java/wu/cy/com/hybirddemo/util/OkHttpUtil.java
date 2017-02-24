package wu.cy.com.hybirddemo.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wcy8038 on 2017/2/24.
 */

public class OkHttpUtil {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient sHttpClient;

    public static OkHttpClient getClient() {
        if (sHttpClient == null) {
            synchronized (OkHttpUtil.class) {
                if(sHttpClient == null){
                    sHttpClient = new OkHttpClient();
                }
            }
        }
        return sHttpClient;
    }

    public static void postRequest(String url, String json, IOkHttpListener listener){
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        Response response = null;
        try {
            response = getClient().newCall(request).execute();
            if(response.isSuccessful()){
                if(listener != null){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if(jsonObject.getInt("status") == 1){
                            listener.onSuccess(jsonObject.getJSONObject("data"));
                        }else {
                            listener.onError("Error " + jsonObject.getString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onError("response json parse error");
                    }
                }
            }else {
                if(listener != null){
                    listener.onError(response.body().string());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError("response json parse error IOException");
        }
    }


    public static void getRequest(){

    }

    public interface IOkHttpListener{
        void onSuccess(JSONObject data);
        void onError(String error);
    }


}
