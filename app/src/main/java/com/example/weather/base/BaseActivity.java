package com.example.weather.base;
/**
 * Create By Dengjiao
 * on 2020.11.02
 */

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class BaseActivity extends AppCompatActivity implements Callback.CommonCallback<String>{
    private static final String TAG = "BaseActivity";

    public void loadData(String url){
        RequestParams params = new RequestParams(url);
        x.http().get(params,this);
    }
    @Override
    public void onSuccess(String result) {
        Log.i(TAG, "success");
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.i(TAG, "error");
    }

    @Override
    public void onCancelled(CancelledException cex) {
        Log.i(TAG, "cancelled");
    }

    @Override
    public void onFinished() {
        Log.i(TAG, "finished");
    }
}
