package app.activity;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import com.example.activitydesigner.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import app.view.widget.headview.ImageCacheManager;
import app.view.widget.headview.RingView;

public class ProfilePhotoUploadActivity extends Activity{

	
	private static final String tag = "ProfilePhotoUploadActivity";
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;
	public static int WIN_WIDTH;
	public static int WIN_HEIGHT;

	//private ImageView imageView;

	Button mCamera;
	Button mAlbum;
	Button mBack;
	Button mSave;
	RingView mRingView;
	Intent mIntent;
	
	private void ringViewInitial(){
		
		mRingView = (RingView) findViewById(R.id.profile_photo_upload_ringview);
		mRingView.circle_radius = dp2Px(this, 140); // 半径设置,
															// 这里设置给的值只是显示用,
															// 项目中应自己考虑适配
		mRingView.offset_y = dp2Px(this, 250); // y轴偏移设置 根据1280*
														// 720设置的
														// 建议在多个dimens中设置
		mRingView.setMoveAble(true); // 设置头像可以移动, 我的项目中查看头像状态是不能编辑移动差价头像的.
		mRingView.setHeadView(ImageCacheManager.getInstance().getBitmap(
				"http://testurl"));
		mRingView.setMoveAble(false);
		mRingView.setHeadView(ImageCacheManager.getInstance().getBitmap("http://testurl"));
		
	}
	
	private void initWindowHW() {
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metric);
		WIN_WIDTH = metric.widthPixels; // 屏幕宽度（像素）
		WIN_HEIGHT = metric.heightPixels; // 屏幕高度（像素）
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_profile_photo_upload);
		initWindowHW();
		ringViewInitial();
	
		mCamera = (Button) findViewById(R.id.profile_photo_upload_take_photo);
		mCamera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				takePicture();
			}
		});

		mAlbum = (Button) this.findViewById(R.id.profile_photo_upload_pick_photo);
		mAlbum.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openAlbum();
			}
		});
		
		mBack = (Button)findViewById(R.id.profile_photo_upload_back);
		mBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		mSave = (Button)findViewById(R.id.profile_photo_upload_save);
		mSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Bitmap bm = mRingView.getBitmap(ProfilePhotoUploadActivity.this);
				// TODO 根据新的url保存新的头像， 并更新用户信息里的头像url
				mRingView.setSelected(false);// 重置头像背景为透明
				ImageCacheManager.getInstance().putBitmap("http://testurl", bm);
				//mHvHead.setHeadBitmap(bm);		
				//mRlHead.setVisibility(View.GONE);
				mIntent = new Intent();
				mIntent.putExtra("HEAD_BITMAP","success");
				ProfilePhotoUploadActivity.this.setResult(1000,mIntent);
				ProfilePhotoUploadActivity.this.finish();
				Log.e(tag, "d");
			}
		});
	}

	private static String picFileFullName;
	//拍照
    public void takePicture(){
    	String state = Environment.getExternalStorageState();  
        if (state.equals(Environment.MEDIA_MOUNTED)) {  
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
            File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);  
            if (!outDir.exists()) {  
            	outDir.mkdirs();  
            }  
            File outFile =  new File(outDir, System.currentTimeMillis() + ".jpg");  
            picFileFullName = outFile.getAbsolutePath();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));  
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);  
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);  
        } else{
        	Log.e(tag, "请确认已经插入SD卡");
        }
    }
    
    //打开本地相册
    public void openAlbum(){
    	Intent intent = new Intent();
    	intent.setType("image/*");   
        intent.setAction(Intent.ACTION_GET_CONTENT);   
    	this.startActivityForResult(intent, PICK_IMAGE_ACTIVITY_REQUEST_CODE);
    }
    
 	@Override
 	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 		super.onActivityResult(requestCode, resultCode, data);
 		mCamera.setVisibility(View.GONE);
 		mAlbum.setVisibility(View.GONE);
 		mSave.setVisibility(View.VISIBLE);
 		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
 			if (resultCode == RESULT_OK) {
 				Log.e(tag, "获取图片成功，path="+picFileFullName);
 				toast("获取图片成功，path="+picFileFullName);
 				setImageView(picFileFullName);
 			} else if (resultCode == RESULT_CANCELED) {
 				// 用户取消了图像捕获
 			} else {
 				// 图像捕获失败，提示用户
 				Log.e(tag, "拍照失败");
 			}
 		} else if (requestCode == PICK_IMAGE_ACTIVITY_REQUEST_CODE) {
 			if (data != null) {
 				Bitmap bm = getBitmapFromLocal(data.getData());
				if (bm != null) {
				mRingView.setMoveAble(true);
				mRingView.setImageBitmap(bm);
			} else {
				// mRvHead.setVisibility(View.GONE);
				Toast.makeText(this, "替换头像失败", Toast.LENGTH_SHORT);
			   }
			}
 			else{
 				Toast.makeText(this, "替换头像失败", Toast.LENGTH_SHORT);
 			}
 	       	/**if (resultCode == RESULT_OK) {
 				Uri uri = data.getData();
 				if(uri != null){
 					String realPath = getRealPathFromURI(uri);
 					Log.e(tag, "获取图片成功，path="+realPath);
 					toast("获取图片成功，path="+realPath);
 					setImageView(realPath);
 				}else{
 					Log.e(tag, "从相册获取图片失败");
 				}**/
 			}
 		}
 	
 	
 	private void setImageView(String realPath){
 		
 		mIntent = new Intent();
 		mIntent.putExtra("PROFILE_PHOTO_PATH", realPath);
 		ProfilePhotoUploadActivity.this.setResult(100,mIntent);
 		ProfilePhotoUploadActivity.this.finish();
 	}
 	
 	/**
     * This method is used to get real path of file from from uri<br/>
     * http://stackoverflow.com/questions/11591825/how-to-get-image-path-just-captured-from-camera
     * 
     * @param contentUri
     * @return String
     */
	public String getRealPathFromURI(Uri contentUri){
        try{
            String[] proj = {MediaStore.Images.Media.DATA};
            // Do not call Cursor.close() on a cursor obtained using this method, 
            // because the activity will do that for you at the appropriate time
            Cursor cursor = this.managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }catch (Exception e){
            return contentUri.getPath();
        }
	}
	
	public void toast(String msg){
		Toast.makeText(this,"头像更改成功", Toast.LENGTH_SHORT).show();
	} 
	
	public static int dp2Px(Context context, float dp) {
		return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
	}
	
	/**
	 * 根据uri获取到图片资源
	 * 
	 * @param uri
	 * @return
	 */
	private Bitmap getBitmapFromLocal(Uri uri) {
		Bitmap bitmap = null;
		Log.d(tag+" getBitmapFromLocal", uri.toString());
		Cursor cursor = getContentResolver().query(uri, null,
				null, null, null);
		//如果是从相册中取
		if (cursor != null) {
			
		if (cursor.moveToFirst()) {
			String filePath = cursor.getString(1);
			bitmap = GetBitmapForFile(new File(filePath),
					mRingView.getHeight());
			cursor.close();
		 }
		}
		//如果是从文件管理中取
		else {
			String path = getRealPathFromURI(uri);
			bitmap = GetBitmapForFile(new File(path),mRingView.getHeight());
			
		}
		return bitmap;
	}
	
	/**
	 * 通过文件获取图片
	 * 
	 * @param file
	 *            图片文件
	 * @param newWidth
	 *            要生成图片的宽度
	 * @param isRotate
	 *            是否要旋转
	 * @return
	 */
	public static Bitmap GetBitmapForFile(File file, int newHeight) {
		Bitmap bitmap;
		Options op = new BitmapFactory.Options();
		op.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeFile(file.getPath());

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		int scale = width / WIN_WIDTH > height
				/ WIN_HEIGHT ? height
				/ WIN_HEIGHT : width
				/ WIN_WIDTH;
		op.inSampleSize = scale;
		op.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(file.getPath(), op);

		width = bitmap.getWidth();
		height = bitmap.getHeight();
		// 根据高度来缩放， 高度满屏, 是满要显示控件的屏
		float temp = ((float) newHeight) / ((float) height);

		Matrix matrix = new Matrix();
		matrix.postScale(temp, temp);

		int degrees = readPictureDegree(file.getAbsolutePath());
		matrix.postRotate(degrees);

		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		bitmap.recycle();
		bitmap = null;

		return resizedBitmap;
	}
	
	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
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
