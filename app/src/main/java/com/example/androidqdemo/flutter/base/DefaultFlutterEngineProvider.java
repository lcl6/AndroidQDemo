package com.example.androidqdemo.flutter.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.Nullable;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import io.flutter.FlutterInjector;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.FlutterEngineGroup;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.embedding.engine.loader.FlutterLoader;

/**
 * 默认的 flutter engine
 * 实现 即用即销毁
 */
final class DefaultFlutterEngineProvider extends FlutterEngineProvider {
    private static final String TAG="DefaultFlutterEngineProvider";
    private final FlutterEngineGroup engineGroup;
    private final Set<String> cacheKeys = new HashSet<>();
    private final Application application;
    private static final String ENGINE_ID = "ENOTARY_ID";

    public DefaultFlutterEngineProvider(Application application) {
        this.application = application;
        engineGroup = new FlutterEngineGroup(application);
    }

    @SuppressLint("LongLogTag")
    @Override
    @Nullable
    public synchronized FlutterEngine getFlutterEngine(String engineId) {
        Log.d(TAG,"getFlutterEngine: " + engineId + " keys contain:" + cacheKeys.contains(engineId));
        return FlutterEngineCache.getInstance().get(engineId);
    }

    /**
     * 创建 flutter 引擎
     *
     * @return 返回engineId
     */
    @SuppressLint("LongLogTag")
    @Override
    public synchronized String createAndRunEngine() {
        final FlutterLoader flutterLoader = FlutterInjector.instance().flutterLoader();
        final FlutterEngine flutterEngine = engineGroup.createAndRunEngine(application,
                new DartExecutor.DartEntrypoint(flutterLoader.findAppBundlePath(), entryPointName()));

        final String engineId = ENGINE_ID + "_" + UUID.randomUUID();//engineSize;
        boolean isAdd = cacheKeys.add(engineId);
        if (!isAdd) {
            Log.e(TAG,"flutter cache exist id:" + engineId);
        }
        flutterEngine.addEngineLifecycleListener(new FlutterEngine.EngineLifecycleListener() {
            @Override
            public void onPreEngineRestart() {

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onEngineWillDestroy() {
                Log.d(TAG,"onEngineWillDestroy id:" + engineId);
                cacheKeys.remove(engineId);
            }
        });
        FlutterEngineCache.getInstance().put(engineId, flutterEngine);
        return engineId;
    }

    /**
     * 移除指定的engineId;
     */
    @SuppressLint("LongLogTag")
    @Override
    public synchronized void remove(String engineId) {
        final boolean removed = cacheKeys.remove(engineId);
        final boolean destroy = destroyEngine(engineId);
        Log.d(TAG,"flutter engine destroy:" + destroy + " id:" + engineId + " keys removed:" + removed);
    }

    /**
     * 释放资源
     */
    @Override
    public synchronized void release() {
        final Set<String> clearIds = new HashSet<>(cacheKeys);
        for (String id : clearIds) {
            destroyEngine(id);
        }
        cacheKeys.clear();
        FlutterEngineCache.getInstance().clear();
    }

    private boolean destroyEngine(String engineId) {
        final FlutterEngineCache cache = FlutterEngineCache.getInstance();
        final FlutterEngine currentEngine = cache.get(engineId);
        if (currentEngine == null) {
            return false;
        }
        FlutterEngineCache.getInstance().remove(engineId);
        currentEngine.destroy();
        return true;
    }
}
