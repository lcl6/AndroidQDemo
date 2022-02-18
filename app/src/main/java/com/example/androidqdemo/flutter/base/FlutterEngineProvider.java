package com.example.androidqdemo.flutter.base;


import io.flutter.embedding.engine.FlutterEngine;


public abstract class FlutterEngineProvider {
    public abstract FlutterEngine getFlutterEngine(String engineId);

    public abstract String createAndRunEngine();

    /**
     * flutter 的默认端点名
     */
    public static String entryPointName() {
        //       return "main";
        boolean b =true;
        return b ? "multi_main_debug" : "multi_main";
    }

    public abstract void remove(String engineId);

    public abstract void release();
}


