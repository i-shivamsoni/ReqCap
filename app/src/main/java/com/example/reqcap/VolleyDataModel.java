package com.example.reqcap;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyDataModel {
    String status;
    Context context;
    public String getStatus() {
        return status;
    }
    VolleyDataModel(Context context){ this.context = context;}
    VolleyDataModel(){}
    public void setStatus(String status) {
        this.status = status;
    }
    public VolleyDataModel GetVollyData(final AnswerListAsyncResponse callBack){
    //    String url = "http://anantshri.info/check.php";
        String url ="https://raw.githubusercontent.com/i-shivamsoni/Sample-Json-Data/main/sampledata.json";
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", "onResponse: "+response);
                if (null != callBack) callBack.processFinished(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                JSONObject json=null;
              //  Log.d("response", "onResponsee: "+error);
                String string = "{\"error\": \"" + error.toString()+"\"}";
             //   Log.d("response", "onResponsee: "+string);
                try {
                     json = new JSONObject(string);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (null != callBack) callBack.processFinished(json);
            }
        }
        );
        queue.add(jsonObjectRequest);
        return null;
    }
}
