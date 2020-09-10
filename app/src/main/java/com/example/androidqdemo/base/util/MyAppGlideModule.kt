package com.example.androidqdemo.base.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.TimeUnit


/**
 *Created by liancl on 2020/7/7 0007.
 */
@GlideModule
class MyAppGlideModule  : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        //定制OkHttp
        //定制OkHttp
        val httpClientBuilder = OkHttpClient.Builder()

        //请求头设置
        //请求头设置
        httpClientBuilder.interceptors().add(HeadInterceptor())

        val okHttpClient = httpClientBuilder
                .connectTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient))
//                .connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS).build()

    }
    class HeadInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request.Builder = chain.request().newBuilder()
            //这里添加我们需要的请求头
            request.addHeader("Referer", "http://www.baidu.com")
                    .addHeader("Authorization", "2C2FB3CE0245012E735B14FB8437E863")
                    .addHeader("AuthType", "local")


            return chain.proceed(request.build())
        }
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}