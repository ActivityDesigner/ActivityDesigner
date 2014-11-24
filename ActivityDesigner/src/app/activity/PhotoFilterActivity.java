package app.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.activitydesigner.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import app.adapter.PhotoFilterAdapter;
import app.camera.base.FilterInfo;
import app.camera.base.Image;
import app.camera.base.MyImageFilter;
import app.camera.filter.AutoAdjustFilter;
import app.camera.filter.ColorQuantizeFilter;
import app.camera.filter.FeatherFilter;
import app.camera.filter.OldPhotoFilter;
import app.camera.filter.RainBowFilter;
import app.camera.filter.SaturationModifyFilter;
import app.camera.filter.SepiaFilter;
import app.camera.filter.VignetteFilter;
import app.camera.filter.VintageFilter;
import app.camera.filter.WaterWaveFilter;
import app.camera.filter.XRadiationFilter;
import app.constant.CommonConst;
import app.utils.BMapUtil;


/**
 * �༭��Ƭ
 * �����˾�Ч��
 * @author liuhaodong
 *
 */
public class PhotoFilterActivity extends Activity implements OnClickListener{

	private Bitmap mData;
	private ImageView mImageView;
	private ImageView mFinish;
	private ImageView mBack;
	private ProgressBar mProgressBar;
	private List<FilterInfo> filterArray = new ArrayList<FilterInfo>();
	Bitmap mBitmap;
	String photoPath;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_photo_filter);
		initial();
		photoPath = getIntent().getStringExtra("PHOTO_PATH");
		Log.d("ssssssssssssss",photoPath);
		mBitmap = BitmapFactory.decodeFile(photoPath);
		int degree = BMapUtil.readPictureDegree(photoPath);
		//ע����androidϵͳ�ϣ��ֻ�ͼƬ�ߴ羡��������480*480��Χ��,�����ڸ�˹����ʱ��������ڴ����������
		//Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.demo_12);
		if(degree <= 0){
			mImageView.setImageBitmap(mBitmap);
 		}else{
 			Log.e("tag", "rotate:"+degree);
 			//��������ͼƬ���õ�matrix����
 	 		Matrix matrix=new Matrix();
 	 		//��תͼƬ����
 	 		matrix.postRotate(degree);
 	 		//������ͼƬ
 	 		Bitmap resizedBitmap=Bitmap.createBitmap(mBitmap,0,0,mBitmap.getWidth(),mBitmap.getHeight(),matrix,true);
 	 		mData = resizedBitmap;
 	 		mImageView.setImageBitmap(resizedBitmap);
 		}
		loadData();
		LoadImageFilter();
	}
	
	private void initial()
	{
		mImageView= (ImageView) findViewById(R.id.photo_filter_show);
		mProgressBar = (ProgressBar) findViewById(R.id.photo_filter_progressbar);
		mBack = (ImageView)findViewById(R.id.photo_filter_back);
		mBack.setOnClickListener(this);
		mFinish = (ImageView)findViewById(R.id.photo_filter_finish);
		mFinish.setOnClickListener(this);
	}
	
	private void LoadImageFilter() {
		GridView gallery = (GridView) findViewById(R.id.gallery);
		final PhotoFilterAdapter mAdapter = new PhotoFilterAdapter(this,filterArray);
		gallery.setAdapter(mAdapter);
		gallery.setNumColumns(11);
		gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				mAdapter.changeStatus(position);
				mAdapter.notifyDataSetChanged();
				MyImageFilter filter = (MyImageFilter) mAdapter.getItem(position);
				//��ʼ��Ⱦ
				new ProcessImageTask(PhotoFilterActivity.this, filter).execute();
			}
		});
	}
	
	/**
	 * ����ͼƬfilter
	 */
	private void loadData(){    
		filterArray.add(new FilterInfo(R.drawable.filter_saturationmodity,new SaturationModifyFilter()/* �˴�������ԭͼЧ�� */,true));
		filterArray.add(new FilterInfo(R.drawable.filter_vignette,	new VignetteFilter(),false));
		filterArray.add(new FilterInfo(R.drawable.filter_autoadjust,new AutoAdjustFilter(),false));
		filterArray.add(new FilterInfo(R.drawable.filter_colorquantize,	new ColorQuantizeFilter(),false));
		filterArray.add(new FilterInfo(R.drawable.filter_waterwave,	new WaterWaveFilter(),false));
		filterArray.add(new FilterInfo(R.drawable.filter_vintage,new VintageFilter(),false));
		filterArray.add(new FilterInfo(R.drawable.filter_oldphoto,new OldPhotoFilter(),false));
		filterArray.add(new FilterInfo(R.drawable.filter_sepia,	new SepiaFilter(),false));
		filterArray.add(new FilterInfo(R.drawable.filter_rainbow,new RainBowFilter(),false));
		filterArray.add(new FilterInfo(R.drawable.filter_feather,new FeatherFilter(),false));
		filterArray.add(new FilterInfo(R.drawable.filter_xradiation,new XRadiationFilter(),false));		
	}
	
	//��Ⱦ������ͼƬ�첽����
	public class ProcessImageTask extends AsyncTask<Void, Void, Bitmap> {
			private MyImageFilter filter;
	        private Activity activity = null;
			public ProcessImageTask(Activity activity, MyImageFilter imageFilter) {
				this.filter = imageFilter;
				this.activity = activity;
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				mProgressBar.setVisibility(View.VISIBLE);
			}

			public Bitmap doInBackground(Void... params) {
				Image img = null;
				try
		    	{
					img = new Image(mBitmap);
					if (filter != null) {
						img = filter.process(img);
						img.copyPixelsFromBuffer();
					}
					return img.getImage();
		    	}
				catch(Exception e){
					if (img != null && img.destImage.isRecycled()) {
						img.destImage.recycle();
						img.destImage = null;
						System.gc(); // ����ϵͳ��ʱ����
					}
				}
				finally{
					if (img != null && img.image.isRecycled()) {
						img.image.recycle();
						img.image = null;
						System.gc(); // ����ϵͳ��ʱ����
					}
				}
				return null;
			}
			
			
			@Override
			protected void onPostExecute(Bitmap result) {
				if(result != null){
					super.onPostExecute(result);
					mData = result;
					mImageView.setImageBitmap(result);	
				}
				mProgressBar.setVisibility(View.GONE);
			}
		}

	private static String picFileFullName;
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.photo_filter_back:
			this.finish();
			//�ϴ�ԭ��Ƭ
			break;
		case R.id.photo_filter_finish:
			//�������Ƭ�����
			File outDir = new File(Environment.getExternalStorageDirectory().getPath()
					+"/ActivityDesigner/");  
            if (!outDir.exists()) {  
            	outDir.mkdirs();  
            }  
            File outFile =  new File(outDir, System.currentTimeMillis() + ".jpg");  
            picFileFullName = outFile.getAbsolutePath();
            CommonConst.localImage.add(picFileFullName);
		   FileOutputStream out;
			try {
				out = new FileOutputStream(outFile);
				mData.compress(Bitmap.CompressFormat.PNG,90, out);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//mData.
			Intent mIntent = new Intent(getBaseContext(),ActivityAlbumActivity.class);
			startActivity(mIntent);
			break;
		default:
			break;
		}
	}
}
