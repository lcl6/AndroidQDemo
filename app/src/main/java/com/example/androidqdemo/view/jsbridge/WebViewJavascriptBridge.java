package com.example.androidqdemo.view.jsbridge;


public interface WebViewJavascriptBridge {
	
	void send(String data);
	void send(String data, CallBackFunction responseCallback);
	
	

}
