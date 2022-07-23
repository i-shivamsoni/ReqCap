package com.example.reqcap;

import org.json.JSONObject;

public interface AnswerListAsyncResponse {
    void processFinished(JSONObject response);
}
