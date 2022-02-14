# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-ignorewarnings
-ignorewarnings

-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
-keepclasseswithmembernames class ** {
    native <methods>;
}



-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
-keepclasseswithmembernames class ** {
    native <methods>;
}

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


 -keep class me.jessyan.autosize.** { *; }
 -keep interface me.jessyan.autosize.** { *; }
 -keep class io.flutter.facade.**{*;}
 -keep class io.flutter.plug.**{*;}
 -keep class io.flutter.app.** { *; }
 -keep class io.flutter.plugin.**  { *; }
 -keep class io.flutter.util.**  { *; }
 -keep class io.flutter.view.**  { *; }
 -keep class io.flutter.**  { *; }
 -keep class io.flutter.plugins.**  { *; }


 # Bugly混淆规则
 -dontwarn com.tencent.bugly.**
 -keep public class com.tencent.bugly.**{*;}
 # tinker混淆规则
 -dontwarn com.tencent.tinker.**
 -keep class com.tencent.tinker.** { *; }


 -keep class com.umeng.** {*;}
 -keepclassmembers class * {
    public <init> (org.json.JSONObject);
 }
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }


#-------------- okhttp3 start-------------
# OkHttp3
# https://github.com/square/okhttp
# okhttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.* { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

# okhttp 3
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Okio
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }
#----------okhttp end--------------


-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
-keepattributes Signature
-keepattributes Annotation
-keepattributes InnerClasses
