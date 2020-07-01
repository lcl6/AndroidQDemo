package com.example.androidqdemo.manager

/**
 * @author ChenHH .
 * description：
 */
interface UploadProgressListener {
    fun onProgress(totalLength: Long, mCurrentLength: Long)
}