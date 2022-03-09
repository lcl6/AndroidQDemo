package com.example.androidqdemo.ac

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.camera.view.video.OnVideoSavedCallback
import androidx.camera.view.video.OutputFileOptions
import androidx.camera.view.video.OutputFileResults
import com.example.androidqdemo.R
import com.example.androidqdemo.base.util.ToastUtils
import com.example.androidqdemo.util.CameraUtil
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * CameraX
 * Created by liancl on 2020/6/17 0017.
 */
class CameraXTestActivity : AppCompatActivity() {


    lateinit var cameraExecutor: ExecutorService
    lateinit var lifecycleCameraController: LifecycleCameraController

    lateinit var preView: PreviewView
    lateinit var focusImage: ImageView

    lateinit var chronometer: Chronometer

    private var focusAnim: ScaleAnimation? = null

    private lateinit var outputDirectory: String

    private var lensFacing = CameraSelector.LENS_FACING_BACK

    private var recordTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_camera)

        preView = findViewById<PreviewView>(R.id.previewView)
        focusImage = findViewById<ImageView>(R.id.focusImage)
        chronometer = findViewById<Chronometer>(R.id.chronometer)

        initFile()
        initController();
        initClickListener()
    }

    private fun initClickListener() {
        findViewById<Button>(R.id.takePicture).setOnClickListener {
            takePicture()
        }
        findViewById<Button>(R.id.switchCamera).setOnClickListener {
            switchCamera()
        }

        findViewById<Button>(R.id.autoFlash).setOnClickListener {
            setFlashMode(ImageCapture.FLASH_MODE_AUTO)
        }

        findViewById<Button>(R.id.openFlash).setOnClickListener {
            setFlashMode(ImageCapture.FLASH_MODE_ON)
        }

        findViewById<Button>(R.id.closeFlash).setOnClickListener {
            setFlashMode(ImageCapture.FLASH_MODE_OFF)
        }

        findViewById<Button>(R.id.startRecord).setOnClickListener {
            startRecord()
        }

        findViewById<Button>(R.id.stopRecord).setOnClickListener {
            stopRecord()
        }
    }

    private fun setFlashMode(mode: Int) {
        if (lifecycleCameraController.imageCaptureFlashMode == mode) {
            return
        }
        lifecycleCameraController.imageCaptureFlashMode = mode
        when (mode) {
            ImageCapture.FLASH_MODE_AUTO -> Toast.makeText(this, "闪光灯自动", Toast.LENGTH_SHORT).show()
            ImageCapture.FLASH_MODE_ON -> Toast.makeText(this, "闪光灯打开", Toast.LENGTH_SHORT).show()
            ImageCapture.FLASH_MODE_OFF -> Toast.makeText(this, "闪光灯关闭", Toast.LENGTH_SHORT).show()
        }
    }

    private fun switchCamera() {
        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) {
            CameraSelector.LENS_FACING_FRONT
        } else {
            CameraSelector.LENS_FACING_BACK
        }
        lifecycleCameraController.cameraSelector =
                CameraSelector.Builder().requireLensFacing(lensFacing).build()
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun isRecording(): Boolean {
        return lifecycleCameraController.isRecording
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun startRecord() {
        if (isRecording()) {
            Toast.makeText(this, "正在录像", Toast.LENGTH_SHORT).show()
            return
        }

        val videoFile = createVideoFile()
        val outputOptions = OutputFileOptions.builder(videoFile).build()
        lifecycleCameraController.setEnabledUseCases(CameraController.VIDEO_CAPTURE)
        lifecycleCameraController.startRecording(
                outputOptions,
                cameraExecutor,
                object : OnVideoSavedCallback {
                    override fun onVideoSaved(output: OutputFileResults) {
                        val savedUri = output.savedUri ?: Uri.fromFile(videoFile)
                        //这里注意 是在视频录制的线程的回调
                        runOnUiThread {
                            ToastUtils.show(this@CameraXTestActivity, "onVideoSaved：${savedUri.path}")
                        }
                    }

                    override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {
                        runOnUiThread {
                            ToastUtils.show(this@CameraXTestActivity, "onError：${message}")
                        }

                    }
                })
        if (isRecording()) {
            Toast.makeText(this, "开始录像", Toast.LENGTH_SHORT).show()
            startChronometer()
        }
    }

    /**
     * 录像计时
     */
    private fun startChronometer() {

        val startTime = System.currentTimeMillis()
        findViewById<Chronometer>(R.id.chronometer).setOnChronometerTickListener {
            recordTime = (System.currentTimeMillis() - startTime) / 1000
            val asText = String.format("%02d", recordTime / 60) + ":" + String.format(
                    "%02d",
                    recordTime % 60
            )
            it.text = asText
            if (CameraUtil.isMaxRecordTime(recordTime)) {
                stopRecord()
            }
        }
        chronometer.start()
        chronometer.visibility = View.VISIBLE
    }

    private fun createVideoFile(): File {
        return File(
                outputDirectory,
                System.currentTimeMillis().toString() + CameraUtil.VIDEO_EXTENSION
        )
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun stopRecord() {
        if (!isRecording()) {
            return
        }
        chronometer.stop()
        chronometer.visibility = View.GONE
        lifecycleCameraController.stopRecording()
    }


    private fun takePicture() {

        val photoFile = createPhotoFile()
        val metadata = ImageCapture.Metadata().apply {
            isReversedHorizontal = lensFacing == CameraSelector.LENS_FACING_FRONT
        }
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
                .setMetadata(metadata)
                .build()
        lifecycleCameraController.setEnabledUseCases(CameraController.IMAGE_CAPTURE or CameraController.IMAGE_ANALYSIS)
        lifecycleCameraController.takePicture(outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                val savedUri = output.savedUri ?: Uri.fromFile(photoFile)
                Log.d("OnImageSavedCallback", "Photo capture succeeded: $savedUri")
                ToastUtils.show(this@CameraXTestActivity, "Photo capture succeeded: $savedUri")
            }

            override fun onError(exc: ImageCaptureException) {
                Log.d("OnImageSavedCallback", "Photo capture failed: ${exc.message}", exc)
                ToastUtils.show(this@CameraXTestActivity, "Photo capture failed: ${exc.message}")
            }

        })


    }

    private fun createPhotoFile(): File {
        return File(
                outputDirectory, System.currentTimeMillis().toString() + CameraUtil.PHOTO_EXTENSION
        )
    }

    private fun initFile() {
        outputDirectory =
                applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath + "/CameraControllerTest/"
        val file = File(outputDirectory)
        if (!file.exists()) {
            file.mkdirs()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initController() {

        cameraExecutor = Executors.newSingleThreadExecutor()
        lifecycleCameraController = LifecycleCameraController(this)
        lifecycleCameraController.bindToLifecycle(this)
        lifecycleCameraController.imageCaptureFlashMode = ImageCapture.FLASH_MODE_AUTO
        preView.controller = lifecycleCameraController
        preView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                startFocusAnim(event.x.toInt(), event.y.toInt())
            }
            false
        }
    }

    /**
     * 设置聚焦动画
     */
    private fun startFocusAnim(x: Int, y: Int) {
        if (focusImage.isShown) {
            return
        }
        if (focusAnim === null) {
            focusAnim = AnimationUtils.loadAnimation(this, R.anim.focus_anim) as ScaleAnimation?
            focusAnim?.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                }

                override fun onAnimationEnd(animation: Animation) {
                    focusImage.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
        }
        focusImage.visibility = View.VISIBLE
        focusImage.post {
            focusImage.layout(
                    x - focusImage.width / 2,
                    y - focusImage.height / 2,
                    x + focusImage.width / 2,
                    y + focusImage.height / 2
            )
            focusImage.startAnimation(focusAnim)
        }
    }


    companion object {
        fun start(context: Context) {
            val starter = Intent(context, CameraXTestActivity::class.java)
//            starter.setFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(starter)
        }
    }

}


