/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidqdemo.scan.android.camera;

import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.ByteArrayOutputStream;

@SuppressWarnings("deprecation") // camera APIs
final class PreviewCallback implements Camera.PreviewCallback {

  private final CameraConfigurationManager configManager;
  private Handler previewHandler;
  private int previewMessage;

  PreviewCallback(CameraConfigurationManager configManager) {
    this.configManager = configManager;
  }

  void setHandler(Handler previewHandler, int previewMessage) {
    this.previewHandler = previewHandler;
    this.previewMessage = previewMessage;
  }

  @Override
  public void onPreviewFrame(byte[] data, Camera camera) {
    //处理data
    if(camera==null){
      return;
    }
    Camera.Size previewSize = camera.getParameters().getPreviewSize();//获取尺寸,格式转换的时候要用到
    Log.d("PreviewCallback","previewWidth--"+previewSize.width+" previewHeight"+ previewSize.height);
    data= rotateYUV420Degree90(data, previewSize.width, previewSize.height);

    Point cameraResolution = configManager.getCameraResolution();
    Handler thePreviewHandler = previewHandler;
    if (cameraResolution != null && thePreviewHandler != null) {
      Message message = thePreviewHandler.obtainMessage(previewMessage, cameraResolution.x,
          cameraResolution.y, data);
      message.sendToTarget();
      previewHandler = null;
    }
  }

  private byte[] rotateYUV420Degree90(byte[] data, int imageWidth, int imageHeight)
  {
    byte [] yuv = new byte[imageWidth*imageHeight*3/2];
    // Rotate the Y luma
    int i = 0;
    for(int x = 0;x < imageWidth;x++)
    {
      for(int y = imageHeight-1;y >= 0;y--)
      {
        yuv[i] = data[y*imageWidth+x];
        i++;
      }
    }
    // Rotate the U and V color components
    i = imageWidth*imageHeight*3/2-1;
    for(int x = imageWidth-1;x > 0;x=x-2)
    {
      for(int y = 0;y < imageHeight/2;y++)
      {
        yuv[i] = data[(imageWidth*imageHeight)+(y*imageWidth)+x];
        i--;
        yuv[i] = data[(imageWidth*imageHeight)+(y*imageWidth)+(x-1)];
        i--;
      }
    }
    return yuv;
  }

}
