package com.example.androidqdemo.flutter.base;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import io.flutter.FlutterInjector;
import io.flutter.Log;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.FlutterEngineGroup;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.embedding.engine.loader.FlutterLoader;

/**
 * flutter engine v2
 * 缓存root engine 。复用引擎即用即毁，目前没有经过全面测试，建议不要在生产环境上使用
 * debug 模式下，第一次使用，会黑屏，是正常的
 * 缓存了rootEngine ，第二次在使用会看到非常短暂的上个页面，一会就会消失
 */
public class FlutterEngineProviderV2 extends FlutterEngineProvider {
    public static final String TAG="FlutterEngineProviderV2";

    private final FlutterEngineGroup engineGroup;
    private final List<String> cacheKeys = new ArrayList<>();
    private final Application application;
    private static final String ENGINE_ID = "ENOTARY_ID_V2";
    private int useCount = 0;
    private String rootEngineId = "";

    public FlutterEngineProviderV2(Application application) {
        this.application = application;
        engineGroup = new FlutterEngineGroup(application);
        getRootEngine();
    }

    @Override
    @Nullable
    public synchronized FlutterEngine getFlutterEngine(String engineId) {
        Log.d(TAG,"FlutterEngineProvider=>getFlutterEngine: " + engineId + " keys contain:" + cacheKeys.contains(engineId));
        return FlutterEngineCache.getInstance().get(engineId);
    }

    @Override
    public synchronized String createAndRunEngine() {
        if (useCount == 0) {
            //第一次使用
            return getRootEngine();
        }
        useCount++;
        //使用复制的镜像
        return createSpawnEngine();
    }

    /**
     * 创建 flutter 引擎
     *
     * @return 返回engineId
     */
    private synchronized String createSpawnEngine() {
        final String engineId = generationEngineId();//engineSize;
        if (cacheKeys.contains(engineId)) {
            Log.e(TAG,"FlutterEngineProvider=>createEngine. flutter cache exist id:" + engineId);
        } else {
            cacheKeys.add(engineId);
        }
        createEngine(engineId);
        return engineId;
    }

    /**
     * 移除指定的engineId;
     */
    @Override
    public synchronized void remove(String engineId) {
        final boolean removed = cacheKeys.remove(engineId);
        if (removed) {
            releaseUse();
        }
        if (!TextUtils.equals(engineId, rootEngineId)) {
            final boolean destroy = destroyEngine(engineId);
            Log.d(TAG,"FlutterEngineProvider=> remove engine :" + destroy + " id:" + engineId + " keys removed:" + removed);
        } else {
            Log.d(TAG,"FlutterEngineProvider=> remove engine  rootEngine maintain  keys removed:" + removed);
        }
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
        destroyEngine(rootEngineId);
        rootEngineId = "";
        useCount = 0;
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

    private synchronized String getRootEngine() {
        String engineId = this.rootEngineId;
        FlutterEngine flutterEngine = FlutterEngineCache.getInstance().get(engineId);
        if (flutterEngine == null) {
            cacheKeys.remove(engineId);
            engineId = generationEngineId();
            createEngine(engineId);
        }
        if (!cacheKeys.contains(engineId)) {
            //放在顶部
            cacheKeys.add(0, engineId);
        }
        this.rootEngineId = engineId;
        return rootEngineId;
    }

    @SuppressWarnings("UnusedReturnValue")
    private FlutterEngine createEngine(String engineId) {
        final long start = System.currentTimeMillis();
        final FlutterLoader flutterLoader = FlutterInjector.instance().flutterLoader();
        final FlutterEngine flutterEngine = engineGroup.createAndRunEngine(application,
                new DartExecutor.DartEntrypoint(flutterLoader.findAppBundlePath(), entryPointName()));
        flutterEngine.addEngineLifecycleListener(new FlutterEngine.EngineLifecycleListener() {
            @Override
            public void onPreEngineRestart() {

            }

            @Override
            public void onEngineWillDestroy() {
                final boolean removed = cacheKeys.remove(engineId);
                if (removed) {
                    useCount--;
                    boolean isRoot = TextUtils.equals(engineId, rootEngineId);
                    Log.d(TAG,"FlutterEngineProvider=> onEngineWillDestroy remove id:" + engineId + " isRoot:" + isRoot);
                    if (isRoot) {
                        rootEngineId = "";
                    }
                }
            }
        });
        final long end = System.currentTimeMillis();
        Log.d(TAG,"FlutterEngineProvider=> create engine time:" + (end - start) + "ms");
        //追加到缓存里面，给activity fragment能获取的到
        FlutterEngineCache.getInstance().put(engineId, flutterEngine);
        return flutterEngine;
    }

    private String generationEngineId() {
        return ENGINE_ID + "_" + UUID.randomUUID();
    }

    private void releaseUse() {
        if (useCount > 0) {
            useCount--;
        }
    }
}
