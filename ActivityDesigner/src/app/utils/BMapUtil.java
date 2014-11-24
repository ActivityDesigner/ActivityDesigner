package app.utils;

import java.io.File;
import java.io.IOException;

import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.os.Environment;
import android.view.View;

public class BMapUtil {

	/**
	 * 从view 得到图片
	 * @param view
	 * @return
	 */
	public static Bitmap getBitmapFromView(View view) {
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache(true);
        return bitmap;
	}
	
	/**
	 * 获取图片的本地存储路径。
	 * 
	 * @param imageUrl
	 *            图片的URL地址。
	 * @return 图片的本地存储路径。
	 */
	public static String getImagePath(String imageUrl) {
		int lastSlashIndex = imageUrl.lastIndexOf("/");
		String imageName = imageUrl.substring(lastSlashIndex + 1);
		String imageDir = Environment.getExternalStorageDirectory().getPath()
				+ "/ActivityDesigner/";
		File file = new File(imageDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		String imagePath = imageDir + imageName;
		return imagePath;
	}
	
	/** 
     * 读取照片exif信息中的旋转角度<br/>
     * http://www.eoeandroid.com/thread-196978-1-1.html
     * 
     * @param path 照片路径 
     * @return角度 
     */  
    public static int readPictureDegree(String path) {  
            int degree  = 0;  
            try {  
                    ExifInterface exifInterface = new ExifInterface(path);  
                    int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);  
                    switch (orientation) {  
                    case ExifInterface.ORIENTATION_ROTATE_90:  
                            degree = 90;  
                            break;  
                    case ExifInterface.ORIENTATION_ROTATE_180:  
                            degree = 180;  
                            break;  
                    case ExifInterface.ORIENTATION_ROTATE_270:  
                            degree = 270;  
                            break;  
                    }  
            } catch (IOException e) {  
                    e.printStackTrace();  
            }  
            return degree;  
    }  
}
