package com.lurencun.android.system;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * author : 桥下一粒砂 (chenyoca@gmail.com)
 * date   : 2013-07-16
 * 裁剪图片辅助类
 */
public class CropPhotoHelper {

    private final Activity context;

    public CropPhotoHelper(Activity context) {
        this.context = context;
    }

    /**
     * 获取从裁剪图片结果中返回图片路径。
     * @param data 裁剪图片结果返回的数据
     * @param imageOutputPath 如果裁剪结果不直接返回地址，需要指定一个临时保存路径
     * @return
     */
    public String extraPhotoPath(Intent data,String imageOutputPath){
        String imagePath = null;
        Uri uri = data.getData();
        if(uri != null){
            String [] columns={MediaStore.Images.Media.DATA};
            Cursor cursor = context.managedQuery(uri, columns, null, null, null);
            if(cursor != null){
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                imagePath = cursor.getString(column_index);
            }else{
                String extraPath = uri.toString();
                if(extraPath != null) imagePath = extraPath.substring(7);
            }
        }else{
            Bundle extras = data.getExtras();
            Bitmap img = (Bitmap)extras.getParcelable("data");
            imagePath = saveCropImageToTempFile(img,imageOutputPath) ? imageOutputPath : null;
        }
        return imagePath;
    }

    private boolean saveCropImageToTempFile(Bitmap img,String imageOutputPath){
        boolean result = true;
        File cropFile = new File(imageOutputPath);
        try{
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(cropFile));
            img.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        }catch(Exception exp){
            exp.printStackTrace();
            result = false;
        }
        return result;
    }
}
