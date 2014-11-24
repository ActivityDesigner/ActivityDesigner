package app.utils;

import java.io.File;
import java.io.IOException;

import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.os.Environment;
import android.view.View;

public class BMapUtil {

	/**
	 * ��view �õ�ͼƬ
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
	 * ��ȡͼƬ�ı��ش洢·����
	 * 
	 * @param imageUrl
	 *            ͼƬ��URL��ַ��
	 * @return ͼƬ�ı��ش洢·����
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
     * ��ȡ��Ƭexif��Ϣ�е���ת�Ƕ�<br/>
     * http://www.eoeandroid.com/thread-196978-1-1.html
     * 
     * @param path ��Ƭ·�� 
     * @return�Ƕ� 
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
