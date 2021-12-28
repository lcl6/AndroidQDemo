package com.example.androidqdemo.view.jsbridge;

import android.text.TextUtils;

import com.lodz.android.corekt.log.PrintLog;


/**
 * @author zhouL
 * @date 2019/4/18
 */
public abstract class BridgeLogHandler implements BridgeHandler {

    private String apiName;

    /**
     * 构造函数
     * @param apiName 接口名
     */
    public BridgeLogHandler(String apiName) {
        this.apiName = apiName;
    }

    @Override
    public void handler(String data, CallBackFunction function) {
        PrintLog.e("BridgeLogHandler", "js call android [" + apiName + "]  <---  " + (TextUtils.isEmpty(data) ? "null" : data));
        onHandler(data, function);
    }

    public abstract void onHandler(String data, CallBackFunction function);

    public String getApiName() {
        return apiName;
    }

}
