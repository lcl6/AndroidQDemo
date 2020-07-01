package com.example.androidqdemo.manager

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.Buffer
import okio.BufferedSink
import okio.ForwardingSink
import okio.buffer
import java.io.IOException

class ExMultipartBody : RequestBody {
    private var mMultipartBody: MultipartBody
    private var mProgressListener: UploadProgressListener? = null
    private var mCurrentLength: Long = 0

    constructor(multipartBody: MultipartBody) {
        mMultipartBody = multipartBody
    }

    constructor(multipartBody: MultipartBody, progressListener: UploadProgressListener?) {
        mMultipartBody = multipartBody
        mProgressListener = progressListener
    }

    override fun contentType(): MediaType? {
        return mMultipartBody.contentType()
    }

    @Throws(IOException::class)
    override fun contentLength(): Long {
        return mMultipartBody.contentLength()
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) { //这里需要另一个代理类来获取写入的长度
        val forwardingSink: ForwardingSink = object : ForwardingSink(sink) {
            val totalLength = contentLength()
            @Throws(IOException::class)
            override fun write(source: Buffer, byteCount: Long) { //这里可以获取到写入的长度
                mCurrentLength += byteCount
                //回调进度
                if (mProgressListener != null) {
                    mProgressListener!!.onProgress(totalLength, mCurrentLength)
                }
                super.write(source, byteCount)
            }
        }
        //转一下
        val bufferedSink = forwardingSink.buffer()
        //写数据
        mMultipartBody.writeTo(bufferedSink)
        //刷新一下数据
        bufferedSink.flush()
    }
}