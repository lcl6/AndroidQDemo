package com.example.androidqdemo.tinker;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;

import io.flutter.FlutterInjector;
import io.flutter.embedding.engine.loader.FlutterApplicationInfo;
import io.flutter.embedding.engine.loader.FlutterLoader;

/**
 * Created by  on 2021/12/31.
 */

public class FlutterPatch {

    private static final String TAG = "Tinker";

    private FlutterPatch() {
    }

    public static String getLibPath(Context context, String abis) {
        String libPath = findLibraryFromTinker(context, "lib" + File.separator + getCpuABI(abis), "libapp.so");
        Log.i("Tinker", "libPath"+libPath);
        return !TextUtils.isEmpty(libPath) && libPath.equals("libapp.so") ? null : libPath;
    }

    public static void reflect(String libPath) {
        try {
            FlutterLoader flutterLoader = FlutterInjector.instance().flutterLoader();
            Field field = FlutterLoader.class.getDeclaredField("flutterApplicationInfo");
            field.setAccessible(true);
            FlutterApplicationInfo flutterApplicationInfo = (FlutterApplicationInfo)field.get(flutterLoader);
            Field aotSharedLibraryNameField = FlutterApplicationInfo.class.getDeclaredField("aotSharedLibraryName");

            aotSharedLibraryNameField.setAccessible(true);
            Log.i("Tinker", "11111---"+libPath);
            aotSharedLibraryNameField.set(flutterApplicationInfo, libPath);
            Log.i("Tinker", "2222---");
            field.set(flutterLoader, flutterApplicationInfo);
            Log.i("Tinker", "flutter patch is loaded successfully");
        } catch (Exception var5) {
            Log.i("Tinker", "flutter reflect is failed");
            var5.printStackTrace();
        }

    }

    public static void hook(Object obj, Object abis) {
        if (obj instanceof Context) {
            Context context = (Context)obj;
            String abisStr = String.valueOf(abis);
            Log.i("Tinker", "find FlutterMain");
            String libPathFromTinker = getLibPath(context, abisStr);
            if (!TextUtils.isEmpty(libPathFromTinker)) {
                reflect(libPathFromTinker);
            }else {
                Log.i("Tinker", "libPathFromTinker is null");
            }
        } else {
            Log.i("Tinker", "Object: " + obj.getClass().getName());
        }

    }

    public static void replaceUrl(Object obj, Object abis) {
        if (obj instanceof Context) {
            Context context = (Context)obj;
            String abisStr = String.valueOf(abis);
            Log.i("Tinker", "find FlutterMain");
            String libPathFromTinker = getLibPath(context, abisStr);
            if (!TextUtils.isEmpty(libPathFromTinker)) {
                reflect(libPathFromTinker);
            }else {
                Log.i("Tinker", "libPathFromTinker is null");
            }
        } else {
            Log.i("Tinker", "Object: " + obj.getClass().getName());
        }

    }



    public static String findLibraryFromTinker(Context context, String relativePath, String libName) throws UnsatisfiedLinkError {
        Tinker tinker = Tinker.with(context);
        libName = libName.startsWith("lib") ? libName : "lib" + libName;
        libName = libName.endsWith(".so") ? libName : libName + ".so";
        String relativeLibPath = relativePath + File.separator + libName;
        Log.i("Tinker", "flutterPatchInit() called   " + tinker.isTinkerLoaded() + " " + tinker.isEnabledForNativeLib()+"---"+relativeLibPath);
        if (tinker.isEnabledForNativeLib() && tinker.isTinkerLoaded()) {
            TinkerLoadResult loadResult = tinker.getTinkerLoadResultIfPresent();
            if (loadResult.libs == null) {
                Log.i("Tinker", "loadResult == null" );
                return libName;
            }

            Iterator var6 = loadResult.libs.keySet().iterator();

            while(var6.hasNext()) {
                String name = (String)var6.next();
                if (name.equals(relativeLibPath)) {
                    String patchLibraryPath = loadResult.libraryDirectory + "/" + name;
                    File library = new File(patchLibraryPath);
                    if (library.exists()) {
                        boolean verifyMd5 = tinker.isTinkerLoadVerify();
                        if (!verifyMd5 || SharePatchFileUtil.verifyFileMd5(library, (String)loadResult.libs.get(name))) {
                            Log.i("Tinker", "findLibraryFromTinker success:" + patchLibraryPath);
                            return patchLibraryPath;
                        }

                        tinker.getLoadReporter().onLoadFileMd5Mismatch(library, 5);
                    }
                }
            }
        }

        return libName;
    }

    public static String getCpuABI(String abis) {
        Log.i("Tinker", "all ndk config >> " + abis);
        String abi = "";
        String[] abiStrs;
        int var4;
        if (Build.VERSION.SDK_INT >= 21) {
            abiStrs = Build.SUPPORTED_ABIS;
            int var3 = abiStrs.length;

            for(var4 = 0; var4 < var3; ++var4) {
                String cpu = abiStrs[var4];
                if (!TextUtils.isEmpty(cpu)) {
                    abi = cpu;
                    break;
                }
            }
        } else {
            abi = Build.CPU_ABI;
        }

        if (TextUtils.isEmpty(abis)) {
            Log.i("Tinker", "cpu abi is:" + abi);
            return abi;
        } else {
            abiStrs = abis.split(",");
            if (abiStrs.length > 0) {
                if (abiStrs.length == 1) {
                    abi = abiStrs[0];
                    Log.i("Tinker", "cpu abi is:" + abi + " from ndk config");
                    return abi;
                } else {
                    String[] var7 = abiStrs;
                    var4 = abiStrs.length;

                    for(int var8 = 0; var8 < var4; ++var8) {
                        String abiStr = var7[var8];
                        if (abiStr.equals(abi)) {
                            Log.i("Tinker", "cpu abi is:" + abi);
                            return abi;
                        }
                    }

                    abi = abiStrs[0];
                    Log.i("Tinker", "cpu abi is:" + abi + " from ndk config");
                    return abi;
                }
            } else {
                return "";
            }
        }
    }
}
