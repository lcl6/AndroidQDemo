//package com.example.androidqdemo.base.util
//
//import android.content.ContentResolver
//import android.content.Context
//import android.database.Cursor
//import android.icu.lang.UCharacter.GraphemeClusterBreak.L
//import android.net.Uri
//import android.provider.MediaStore
//import android.text.TextUtils
//import java.io.*
//
///**
// *Created by  on 2020/6/17 0017.
// */
//
//object FileUtils {
//
//     fun  getFileFromUri(uri: Uri, context: Context): File? {
//        when (uri.getScheme()) {
//             "content"->   return getFileFromContentUri(uri, context);
//             "file"->return  File(uri.path);
//        }
//        return null
//    }
//
//
//      private fun getFileFromContentUri(contentUri:Uri, context:Context ): File? {
//        if (contentUri == null) {
//            return null;
//        }
//        var file : File? =null
//        var filePath:String
//        var  fileName:String
//        var filePathColumn: Array<String> = arrayOf( MediaStore.MediaColumns.DATA,MediaStore.MediaColumns.DISPLAY_NAME);
//        var contentResolver :ContentResolver= context.contentResolver;
//        var cursor: Cursor? = contentResolver.query(contentUri, filePathColumn, null,null, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0].toString()));
//            fileName = cursor.getString(cursor.getColumnIndex(filePathColumn[1].toString()));
//            cursor.close();
//            if (!TextUtils.isEmpty(filePath)) {
//                file =  File(filePath);
//            }
//            if (file != null) {
//                if (!file.exists() || file.length() <= 0 || TextUtils.isEmpty(filePath)) {
//                    filePath = getPathFromInputStreamUri(context, contentUri, fileName);
//                }
//            }
//            if (!TextUtils.isEmpty(filePath)) {
//                file =  File(filePath);
//            }
//        }
//        return file;
//    }
//
//    /**
//     * 用流拷贝文件一份到自己APP目录下
//     *
//     * @param context
//     * @param uri
//     * @param fileName
//     * @return
//     */
//    fun  getPathFromInputStreamUri( context:Context,  uri:Uri,  fileName:String) : String? {
//        var  inputStream : InputStream? = null;
//        var  filePath :String ?= null;
//
//        if (uri.authority != null) {
//            try {
//                inputStream = context.contentResolver.openInputStream(uri);
//                var file :File = createTemporalFileFrom(context, inputStream, fileName);
//                filePath = file.path;
//
//            } catch ( e :Exception) {
//                e.printStackTrace()
//            } finally {
//                try {
//                    inputStream?.close()
//                } catch (e:Exception ) {
//                    e.printStackTrace()
//                }
//            }
//        }
//        return filePath;
//    }
//
//
//   fun  createTemporalFileFrom(context:Context,  inputStream:InputStream, fileName:String ) : File {
//
//
//         var targetFile: File? = null;
//        if (inputStream != null) {
//           var  read:Int;
//           var buffer =  ByteArray(8 * 1024);
//            //自己定义拷贝文件路径
//            targetFile =  File(context.cacheDir, fileName);
//            if (targetFile.exists()) {
//                targetFile.delete();
//            }
//             var outputStream :OutputStream=  FileOutputStream(targetFile);
//            while ((read = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, read);
//            }
//            outputStream.flush();
//
//            try {
//                outputStream.close();
//            } catch ( e: IOException) {
//                e.printStackTrace();
//            }
//        }
//
//        return targetFile;
//    }
//
//
//
//}