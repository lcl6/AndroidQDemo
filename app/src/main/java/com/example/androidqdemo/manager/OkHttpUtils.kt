package com.example.androidqdemo.manager

import android.content.Context
import com.example.androidqdemo.base.util.PhoneNetUtil
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import java.io.File
import java.io.IOException
import java.net.URLConnection
import java.util.concurrent.TimeUnit


/**
 * HTTP请求工具类
 * 目前所有请求都是POST
 */
class OkHttpUtils private constructor(c: Context) {
    /**
     * 异步上传文件，带进度条。
     * 请求方式：表单
     * 内容：字符串+文件
     * 说明：该方法需验证
     */
    fun asynFormRequestt(url: String?, params: Map<String?, String?>?, files: Map<String?, File?>?, uploadProgressListener: UploadProgressListener?, callback: Callback?) {
        val request = request(url, params, files, uploadProgressListener)
        asynRequest(request, callback)
    }

    /**
     * 异步
     * 请求方式：表单
     * 内容：字符串+文件
     * @return
     */
    fun asynFormRequestt(url: String?, params: Map<String?, String?>?, files: Map<String?, File?>?, callback: Callback?): Call {
        val request = request(url, params, files)
        return asynRequest(request, callback)
    }

    /**
     * 异步
     * 请求方式：表单
     * 内容：字符串
     * @return
     */
    fun asynFormRequestt(url: String?, params: Map<String?, String?>?, callback: Callback?): Call {
        val request = request(url, params)
        return asynRequest(request, callback)
    }

    /**
     * 异步
     * 请求方式：JSON
     * 内容：字符串
     * @return
     */
    fun asynJsonRequest(url: String?, jsonparams: String?, token: String, callback: Callback?): Call {
        val request = request(url, jsonparams, token)
        return asynRequest(request, callback)
    }

    /**
     * 异步 get
     * 请求方式：JSON
     * 内容：字符串
     * @return
     */
    fun asynJsonRequestGet(url: String?, jsonparams: String?, token: String, callback: Callback?): Call {
        val request = requestGet(url, jsonparams, token)
        return asynRequest(request, callback)
    }

    /**
     * 异步请求
     * @return
     */
    fun asynRequest(request: Request?, callback: Callback?): Call {
        val call = mOkHttpClient!!.newCall(request!!)
        call.enqueue(callback!!)
        return call
    }

    /**
     * 实例化reuqest对象
     * 请求格式：表单
     * 内容：参数
     */
    fun request(url: String?, params: Map<String?, String?>?): Request { //表单
        val formBuilder = FormBody.Builder()
        if (params != null) {
            for (key in params.keys) {
                formBuilder.add(key!!, params[key]!!)
            }
        }
        val formBody = formBuilder.build()
        return Request.Builder().url(url!!).post(formBody).build()
    }

    /**
     * 实例化reuqest对象
     * 请求格式：表单
     * 内容：参数+文件
     */
    fun request(url: String?, params: Map<String?, String?>?, files: Map<String?, File?>?): Request { //MultipartBody文件上传
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        //字符
        if (params != null) {
            for (key in params.keys) {
                builder.addFormDataPart(key!!, params[key]!!)
            }
        }
        //文件
        if (files != null) {
            for (key in files.keys) {
                if (files[key] != null) {
                    builder.addFormDataPart(
                            key!!,
                            files[key]!!.name,
                            RequestBody.create(judgeType(files[key]!!.path), files[key]!!)
                    )
                }
            }
        }
        return Request.Builder().url(url!!).post(builder.build()).build()
    }

    /**
     * 实例化reuqest对象
     * 请求格式：表单
     * 内容：参数+文件列表
     */
    fun request(url: String?, params: Map<String?, String?>?, files: List<Map<String?, String?>?>?): Request { //MultipartBody文件上传
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        //字符
        if (params != null) {
            for (key in params.keys) {
                builder.addFormDataPart(key!!, params[key]!!)
            }
        }
        //文件
        if (files != null) {
            for (key in params!!.keys) {
                builder.addFormDataPart("file",
                        key,
                        RequestBody.create(judgeType(params[key]), key!!))
            }
            //循环添加文件
//            for (int i = 0; i < files.size(); i++) {
//
//                builder.addFormDataPart(files.get(i).get,
//                        files.get(key).getName(),
//                        RequestBody.create(judgeType(files.get(key).getPath()), files.get(key)));
//            }
        }
        return Request.Builder().url(url!!).post(builder.build()).build()
    }

    /**
     * 实例化reuqest对象  get请求
     * 请求格式：JSONString
     * 内容：参数
     */
    fun requestGet(url: String?, params: String?, token: String): Request { //参数处理
        val stringBuffer = StringBuffer()
        return Request.Builder()
                .url(url!!)
                .get()
                .addHeader("Authorization", "Bearer $token")
                .addHeader("Terminal-Type", "app").build()
    }

    /**
     * 实例化reuqest对象
     * 请求格式：JSONString
     * 内容：参数
     */
    fun request(url: String?, params: String?, token: String): Request {
        val JSON: MediaType? = "application/json;charset=utf-8".toMediaTypeOrNull()
        val body = RequestBody.create(JSON, params!!)
        return Request.Builder()
                .url(url!!)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Terminal-Type", "app").build()
    }

    /**
     * 上传文件，带进度条
     *
     * @param url
     * @param params
     */
    fun request(url: String?, params: Map<String?, String?>?, files: Map<String?, File?>?, uploadProgressListener: UploadProgressListener?): Request {
        val request: Request
        request = if (uploadProgressListener != null) {
            requestWithProgress(url, params, files, uploadProgressListener)
        } else {
            request(url, params, files)
        }
        return request
    }

    private fun judgeType(path: String?): MediaType? {
        val fileNameMap = URLConnection.getFileNameMap()
        var contentTypeFor = fileNameMap.getContentTypeFor(path)
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream"
        }
        return contentTypeFor.toMediaTypeOrNull()
    }

    fun requestWithProgress(url: String?, params: Map<String?, String?>?, files: Map<String?, File?>?, listener: UploadProgressListener?): Request { //MultipartBody文件上传
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        //字符
        if (params != null) {
            for (key in params.keys) {
                builder.addFormDataPart(key!!, params[key]!!)
            }
        }
        //文件
        if (files != null) {
            for (key in files.keys) {
                if (files[key] != null) {
                    builder.addFormDataPart(
                            key!!,
                            files[key]!!.name,
                            RequestBody.create(judgeType(files[key]!!.path), files[key]!!)
                    )
                }
            }
        }
        val exMultipartBody = ExMultipartBody(builder.build(), listener)
        return Request.Builder().url(url!!).post(exMultipartBody).build()
    }

    companion object {
        private var intance: OkHttpUtils? = null
        private var mOkHttpClient: OkHttpClient? = null
        fun getInstance(c: Context): OkHttpUtils? {
            if (intance == null) {
                intance = OkHttpUtils(c)
            }
            return intance
        }
    }

    init {
        val okhttpCache = File(c.externalCacheDir, "okhttpCache")
        /**
         * 断网时候的缓存
         */
        val REWRITE_CACHE_CONTROL_INTERCEPTOR: Interceptor = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                if (PhoneNetUtil.isConnect(c)) {
                    return chain.proceed(chain.request())
                }
                val offlineCacheTime = 300 //离线的时候的缓存的过期时间
                val originalResponse = chain.proceed(chain.request())
                return originalResponse.newBuilder()
                        .removeHeader("Pragma") //去掉一个header
                        .header("Cache-Control", "public, only-if-cached, max-stale=$offlineCacheTime")
                        .build()
            }
        }
//        /**
//         * 有网时候的缓存
//         */
//        val NetCacheInterceptor: Interceptor = object : Interceptor {
//            @Throws(IOException::class)
//            override fun intercept(chain: Interceptor.Chain): Response {
//                val request = chain.request()
//                val response = chain.proceed(request)
//                val onlineCacheTime =5 //在线的时候的缓存过期时间，如果想要不缓存，直接时间设置为0
//                return response.newBuilder()
//                        .header("Cache-Control", "public, max-age=$onlineCacheTime")
//                        .removeHeader("Pragma")
//                        .build()
//            }
//        }

        /**
         * 有网时候的缓存
         */
        val NetCacheInterceptor: Interceptor = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                val response = chain.proceed(request)
                return if (PhoneNetUtil.isConnect(c)) {
                    val onlineCacheTime =0 //在线的时候的缓存过期时间，如果想要不缓存，直接时间设置为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=$onlineCacheTime")
                            .removeHeader("Pragma")
                            .build()

                }else{
                    val onlineCacheTime =300 //在线的时候的缓存过期时间，如果想要不缓存，直接时间设置为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=$onlineCacheTime")
                            .removeHeader("Pragma")
                            .build()
                }
            }
        }
        mOkHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(NetCacheInterceptor)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache( okhttp3.Cache(okhttpCache, 8 * 1024 * 1024))
                .connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build()
    }


    fun get(url: String,call: Callback){

        val request: Request = Request.Builder()
                .get()
                .url(url)
                .build()
        mOkHttpClient?.newCall(request)?.enqueue(call)
    }


}