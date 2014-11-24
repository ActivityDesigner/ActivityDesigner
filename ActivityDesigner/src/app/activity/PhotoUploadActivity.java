package app.activity;


import java.io.File;


import com.example.activitydesigner.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PhotoUploadActivity extends BaseActivity implements OnTouchListener{

	public static String tag = "PhotoUploadActivity";

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;
	
	/***
	 * ʹ����������ջ�ȡͼƬ
	 */
	public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
	/***
	 * ʹ������е�ͼƬ
	 */
	public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
	
	/***
	 * ��Intent��ȡͼƬ·����KEY
	 */
	public static final String KEY_PHOTO_PATH = "photo_path";
	
	private static final String TAG = "SelectPicActivity";
	
	/**��ȡ����ͼƬ·��*/
	private String picPath;
	
	private Intent lastIntent ;
	
	private Uri photoUri;
	
	LinearLayout btn_layout;
    Button mTakePhoto;
    Button mPickPhoto;
    Button mBack;
    Intent mIntent;
    
	@Override
	public void setView() {
		// TODO Auto-generated method stub

		setContentView(R.layout.activity_photo_upload);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		 btn_layout = (LinearLayout)findViewById(R.id.circle_upload_dialog_layout);
		 mTakePhoto = (Button)findViewById(R.id.photo_upload_take_photo);
		 mPickPhoto = (Button)findViewById(R.id.photo_upload_pick_photo);
		 mBack = (Button)findViewById(R.id.photo_upload_back);
		 lastIntent = getIntent();
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		btn_layout.setOnClickListener(this);
		mTakePhoto.setOnClickListener(this);
		mTakePhoto.setOnTouchListener(this);
		mPickPhoto.setOnClickListener(this);
		mPickPhoto.setOnTouchListener(this);
		mBack.setOnClickListener(this);
		mBack.setOnTouchListener(this); 
	}

	@Override
	public void setProcess() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
 			if (resultCode == RESULT_OK) {
 				Log.e("TAG", "��ȡͼƬ�ɹ���path="+picFileFullName);
 				//toast("��ȡͼƬ�ɹ���path="+picFileFullName);
 				//setImageView(picFileFullName);
 				mIntent = new Intent(this,PhotoFilterActivity.class);
				mIntent.putExtra("PHOTO_PATH", picFileFullName);
				startActivity(mIntent);
 			} else if (resultCode == RESULT_CANCELED) {
 				// �û�ȡ����ͼ�񲶻�
 			} else {
 				// ͼ�񲶻�ʧ�ܣ���ʾ�û�
 				Log.e("TAG", "����ʧ��");
 			}
 		} else if (requestCode == PICK_IMAGE_ACTIVITY_REQUEST_CODE) {
 			if (resultCode == RESULT_OK) {
 				Uri uri = data.getData();
 				if(uri != null){
 					String realPath = getRealPathFromURI(uri);
 					Log.e("TAG", "��ȡͼƬ�ɹ���path="+realPath);
 					mIntent = new Intent(this,PhotoFilterActivity.class);
 					mIntent.putExtra("PHOTO_PATH", realPath);
 					startActivity(mIntent);
 					//toast("��ȡͼƬ�ɹ���path="+realPath);
 					//setImageView(realPath);
 				}else{
 					Log.e("TAG", "������ȡͼƬʧ��");
 				}
 			}
 		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.circle_upload_dialog_layout:
			finish();
			break;
		case R.id.photo_upload_back:
			mBack.setTextColor(Color.BLACK);
			finish();
			break;
		case R.id.photo_upload_take_photo:
			mTakePhoto.setTextColor(Color.BLACK);
			//takePhoto();
			takePicture();
			break;
		case R.id.photo_upload_pick_photo:
			mPickPhoto.setTextColor(Color.BLACK);
			//pickPhoto();
			openAlbum();
			break;
		default:
			finish();
			break;
		}
	}

	@Override
	public boolean onTouch(View view, MotionEvent arg1) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.photo_upload_back:
			mBack.setTextColor(Color.WHITE);
			break;
		case R.id.photo_upload_take_photo:
			mTakePhoto.setTextColor(Color.WHITE);
			break;
		case R.id.photo_upload_pick_photo:
			mPickPhoto.setTextColor(Color.WHITE);
			break;
		default:
			break;
		}
		return false;
	}
	
	/**
	 * ѡ��ͼƬ�󣬻�ȡͼƬ��·��
	 * @param requestCode
	 * @param data
	 */
	private void doPhoto(int requestCode,Intent data)
	{
		if(requestCode == SELECT_PIC_BY_PICK_PHOTO )  //�����ȡͼƬ����Щ�ֻ����쳣�������ע��
		{
			if(data == null)
			{
				Toast.makeText(this, "ѡ��ͼƬ�ļ�Ϊ��", Toast.LENGTH_LONG).show();
				return;
			}
			photoUri = data.getData();
			Log.d(TAG, photoUri.toString());
			if(photoUri == null )
			{
				Toast.makeText(this, "ѡ��ͼƬ�ļ�UriΪ��", Toast.LENGTH_LONG).show();
				return;
			}
		}
		String[] pojo = {MediaStore.Images.Media.DATA};
		Cursor cursor = managedQuery(photoUri, pojo, null, null,null);   
		if(cursor != null )
		{
			Log.i(TAG, "cursor != null");
			int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
			cursor.moveToFirst();
			picPath = cursor.getString(columnIndex);
			cursor.close();
		}
		Log.i(TAG, "imagePath = "+picPath);
		if(picPath != null && ( picPath.endsWith(".png") || picPath.endsWith(".PNG") ||picPath.endsWith(".jpg") ||picPath.endsWith(".JPG")  ))
		{
			lastIntent.putExtra(KEY_PHOTO_PATH, picPath);
			setResult(Activity.RESULT_OK, lastIntent);
			finish();
		}else{
			Toast.makeText(this, "ѡ��ͼƬ�ļ�����ȷ", Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * 
	 */
    //�򿪱������
    public void openAlbum(){
    	Intent intent = new Intent();
    	intent.setType("image/*");   
        intent.setAction(Intent.ACTION_GET_CONTENT);   
    	this.startActivityForResult(intent, PICK_IMAGE_ACTIVITY_REQUEST_CODE);
    }
    
	/**
	 * ���ջ�ȡͼƬ
	 */
	private void takePhoto_2() {
		//ִ������ǰ��Ӧ�����ж�SD���Ƿ����
		String SDState = Environment.getExternalStorageState();
		if(SDState.equals(Environment.MEDIA_MOUNTED))
		{
			
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//"android.media.action.IMAGE_CAPTURE"
			/***
			 * ��Ҫ˵��һ�£����²���ʹ����������գ����պ��ͼƬ����������е�
			 * ����ʹ�õ����ַ�ʽ��һ���ô����ǻ�ȡ��ͼƬ�����պ��ԭͼ
			 * �����ʵ��ContentValues�����Ƭ·���Ļ������պ��ȡ��ͼƬΪ����ͼ������
			 */
			ContentValues values = new ContentValues();  
			photoUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);  
			intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
			/**-----------------*/
			startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
		}else{
			Toast.makeText(this,"�ڴ濨������", Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * ���ջ�ȡͼƬ ͬʱ������ͼƬ�ļ�
	 */
	private static String picFileFullName;
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
        	Log.e("TAG", "��ȷ���Ѿ�����SD��");
        }
    }
	/***
	 * �������ȡͼƬ
	 */
	private void pickPhoto() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
	}
	
	/**
     * This method is used to get real path of file from from uri<br/>
     * http://stackoverflow.com/questions/11591825/how-to-get-image-path-just-captured-from-camera
     * 
     * @param contentUri
     * @return String
     */
	public String getRealPathFromURI(Uri contentUri){
		Log.e(tag, contentUri.toString());
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
}
