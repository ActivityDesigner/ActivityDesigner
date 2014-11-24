package app.view.widget;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.activitydesigner.R;

import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import app.activity.PhotoDetailActivity;
import app.constant.CommonConst;
import app.constant.UrlConst;
import app.utils.ImageLoader;

public class OnlyScrollView extends ScrollView implements OnTouchListener{

	public static String SD_PATH = "/ActivityDesigner/";
	/**
	 * ÿҳҪ���ص�ͼƬ����
	 */
	public static final int PAGE_SIZE = 15;

	/**
	 * ��¼��ǰ�Ѽ��ص��ڼ�ҳ
	 */
	public int page;

	/**
	 * ÿһ�еĿ��
	 */
	public int columnWidth;

	/**
	 * ��ǰ��һ�еĸ߶�
	 */
	public int firstColumnHeight;

	/**
	 * ��ǰ�ڶ��еĸ߶�
	 */
	public int secondColumnHeight;

	/**
	 * ��ǰ�����еĸ߶�
	 */
	public int thirdColumnHeight;

	/**
	 * �Ƿ��Ѽ��ع�һ��layout������onLayout�еĳ�ʼ��ֻ�����һ��
	 */
	public boolean loadOnce;

	/**
	 * ��ͼƬ���й���Ĺ�����
	 */
	public ImageLoader imageLoader;

	/**
	 * ��һ�еĲ���
	 */
	public LinearLayout firstColumn;

	/**
	 * �ڶ��еĲ���
	 */
	public LinearLayout secondColumn;

	/**
	 * �����еĲ���
	 */
	public LinearLayout thirdColumn;

	/**
	 * ��¼�����������ػ�ȴ����ص�����
	 */
	public static Set<LoadImageTask> taskCollection;

	/**
	 * OnlyScrollView�µ�ֱ���Ӳ��֡�
	 */
	public static View scrollLayout;

	/**
	 * OnlyScrollView���ֵĸ߶ȡ�
	 */
	public static int scrollViewHeight;

	/**
	 * ��¼�ϴ�ֱ����Ĺ������롣
	 */
	public static int lastScrollY = -1;

	/**
	 * ��¼���н����ϵ�ͼƬ�����Կ�����ʱ���ƶ�ͼƬ���ͷš�
	 */
	public List<ImageView> imageViewList = new ArrayList<ImageView>();
	
	public static String imageUrls[];
	
	public static List<String> localImage = new ArrayList<String>();

	/**
	 * ��Handler�н���ͼƬ�ɼ��Լ����жϣ��Լ����ظ���ͼƬ�Ĳ�����
	 */
	public static Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			OnlyScrollView onlyScrollView = (OnlyScrollView) msg.obj;
			int scrollY = onlyScrollView.getScrollY();
			// �����ǰ�Ĺ���λ�ú��ϴ���ͬ����ʾ��ֹͣ����
			if (scrollY == lastScrollY) {
				// ����������ײ������ҵ�ǰû���������ص�����ʱ����ʼ������һҳ��ͼƬ
				if (scrollViewHeight + scrollY >= scrollLayout.getHeight()
						&& taskCollection.isEmpty()) {
					onlyScrollView.loadMoreImages();
				}
				onlyScrollView.checkVisibility();
			} else {
				lastScrollY = scrollY;
				Message message = new Message();
				message.obj = onlyScrollView;
				// 5������ٴζԹ���λ�ý����ж�
				handler.sendMessageDelayed(message, 5);
			}
		};

	};

	/**
	 * OnlyScrollView�Ĺ��캯����
	 * 
	 * @param context
	 * @param attrs
	 */
	public OnlyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		imageLoader = ImageLoader.getInstance();
		imageUrls = new String[1];
		taskCollection = new HashSet<LoadImageTask>();
		setOnTouchListener(this);
	}
	
	public void SetImageUrls(String []urls){
		this.imageUrls = urls;
	}
	
	public void setLocalImage(List<String> path){
		localImage = path;
	}

	/*
	 * 
	 * ����һЩ�ؼ��Եĳ�ʼ����������ȡOnlyScrollView�ĸ߶ȣ��Լ��õ���һ�еĿ��ֵ���������￪ʼ���ص�һҳ��ͼƬ��
	 * 
	 * @param changed
	 * 
	 * @param l
	 * 
	 * @param t
	 * 
	 * @param r
	 * 
	 * @param b
	 * 
	 * @see android.widget.ScrollView#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if (changed && !loadOnce) {
			scrollViewHeight = getHeight();
			scrollLayout = getChildAt(0);
			firstColumn = (LinearLayout) findViewById(R.id.first_column);
			secondColumn = (LinearLayout) findViewById(R.id.second_column);
			thirdColumn = (LinearLayout) findViewById(R.id.third_column);
			columnWidth = firstColumn.getWidth();
			loadOnce = true;
			loadMoreImages();
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			Message message = new Message();
			message.obj = this;
			handler.sendMessageDelayed(message, 5);
		}
		return false;
	}

	/**
	 * ��ʼ������һҳ��ͼƬ��ÿ��ͼƬ���Ὺ��һ���첽�߳�ȥ���ء�
	 */
	public void loadMoreImages() {
		if (hasSDCard()) {
			int startIndex = page * PAGE_SIZE;
			int endIndex = page * PAGE_SIZE + PAGE_SIZE;
			for (int i = 0; i < localImage.size(); i++) {
				String localPath = localImage.get(i);
				LocalImageTask task = new LocalImageTask();
				//taskCollection.add(task);
				task.execute(localPath);
			}
			if (startIndex < imageUrls.length) {
				Toast.makeText(getContext(), "���ڼ���...", Toast.LENGTH_SHORT)
						.show();
				if (endIndex > imageUrls.length) {
					endIndex = imageUrls.length;
				}
				for (int i = startIndex; i < endIndex; i++) {
					LoadImageTask task = new LoadImageTask();
					taskCollection.add(task);
					task.execute(i);
				}
				page++;
			}
			else {
				Toast.makeText(getContext(), "��û�и���ͼƬ", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(getContext(), "δ����SD��", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * ����imageViewList�е�ÿ��ͼƬ����ͼƬ�Ŀɼ��Խ��м�飬���ͼƬ�Ѿ��뿪��Ļ�ɼ���Χ����ͼƬ�滻��һ�ſ�ͼ��
	 */
	public void checkVisibility() {
		for (int i = 0; i < imageViewList.size(); i++) {
			ImageView imageView = imageViewList.get(i);
			int borderTop = (Integer) imageView.getTag(R.string.border_top);
			int borderBottom = (Integer) imageView
					.getTag(R.string.border_bottom);
			if (borderBottom > getScrollY()
					&& borderTop < getScrollY() + scrollViewHeight) {
				String imageUrl = (String) imageView.getTag(R.string.image_url);
				Bitmap bitmap = imageLoader.getBitmapFromMemoryCache(imageUrl);
				if (bitmap != null) {
					imageView.setImageBitmap(bitmap);
				} else {
					LoadImageTask task = new LoadImageTask(imageView);
					task.execute(i);
				}
			} else {
				imageView.setImageResource(R.drawable.photo_display_stub);
			}
		}
	}

	/**
	 * �ж��ֻ��Ƿ���SD����
	 * 
	 * @return ��SD������true��û�з���false��
	 */
	private boolean hasSDCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState());
	}
	/**
	 * 
	 * TODO<�첽����ͼƬ��>
	 * 
	 * @author ZhuZiQiang
	 * @data: 2014-4-4 ����3:25:00
	 * @version: V1.0
	 */
	class LoadImageTask extends AsyncTask<Integer, Void, Bitmap> {

		/**
		 * ��¼ÿ��ͼƬ��Ӧ��λ��
		 */
		private int mItemPosition;

		/**
		 * ͼƬ��URL��ַ
		 */
		private String mImageUrl;

		/**
		 * ���ظ�ʹ�õ�ImageView
		 */
		private ImageView mImageView;

		public LoadImageTask() {
		}

		/**
		 * �����ظ�ʹ�õ�ImageView����
		 * 
		 * @param imageView
		 */
		public LoadImageTask(ImageView imageView) {
			mImageView = imageView;
		}

		@Override
		protected Bitmap doInBackground(Integer... params) {
			mItemPosition = params[0];
			mImageUrl = imageUrls[mItemPosition];//���url
			Bitmap imageBitmap = imageLoader.getBitmapFromMemoryCache(mImageUrl);
			if (imageBitmap == null) {
				imageBitmap = loadImage(mImageUrl);
			}
			return imageBitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (bitmap != null) {
				double ratio = bitmap.getWidth()
						/ (columnWidth * 1.0);
				int scaledHeight = (int) (bitmap.getHeight() / ratio);
				addImage(bitmap, columnWidth, scaledHeight);
			}
			taskCollection.remove(this);
		}

		/**
		 * ���ݴ����URL����ͼƬ���м��ء��������ͼƬ�Ѿ�������SD���У���ֱ�Ӵ�SD�����ȡ������ʹ����������ء�
		 * 
		 * @param imageUrl
		 *            ͼƬ��URL��ַ
		 * @return ���ص��ڴ��ͼƬ��
		 */
		private Bitmap loadImage(String imageUrl) {
			File imageFile = new File(getImagePath(imageUrl));
			if (!imageFile.exists()) {
				downloadImage(imageUrl);
			}
			if (imageUrl != null) {
				Bitmap bitmap = ImageLoader.decodeSampledBitmapFromResource(
						imageFile.getPath(),columnWidth);
				if (bitmap != null) {
					imageLoader.addBitmapToMemoryCache(imageUrl,
							bitmap);
					return bitmap;
				}
			}
			return null;
		}

		/**
		 * ��ImageView�����һ��ͼƬ
		 * 
		 * @param bitmap
		 *            ����ӵ�ͼƬ
		 * @param imageWidth
		 *            ͼƬ�Ŀ��
		 * @param imageHeight
		 *            ͼƬ�ĸ߶�
		 */
		private void addImage(Bitmap bitmap, int imageWidth, int imageHeight) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					imageWidth, imageHeight);
			if (mImageView != null) {
				mImageView.setImageBitmap(bitmap);
			} else {
				ImageView imageView = new ImageView(getContext());
				imageView.setLayoutParams(params);
				imageView.setImageBitmap(bitmap);
				imageView.setScaleType(ScaleType.FIT_XY);
				imageView.setPadding(5, 5, 5, 5);
				imageView.setTag(R.string.image_url, mImageUrl);
				imageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getContext(),
								PhotoDetailActivity.class);
						intent.putExtra("IMAGE_POSITION", mItemPosition);
						intent.putExtra("IMAGE_URLS",imageUrls);
						getContext().startActivity(intent);
					}
				});
				findColumnToAdd(imageView, imageHeight).addView(imageView);
				imageViewList.add(imageView);
			}
		}

		/**
		 * �ҵ���ʱӦ�����ͼƬ��һ�С�ԭ����Ƕ����еĸ߶Ƚ����жϣ���ǰ�߶���С��һ�о���Ӧ����ӵ�һ�С�
		 * 
		 * @param imageView
		 * @param imageHeight
		 * @return Ӧ�����ͼƬ��һ��
		 */
		private LinearLayout findColumnToAdd(ImageView imageView, int imageHeight) {
			if (firstColumnHeight <= secondColumnHeight) {
				if (firstColumnHeight <= thirdColumnHeight) {
					imageView.setTag(R.string.border_top,
							firstColumnHeight);
					firstColumnHeight += imageHeight;
					imageView.setTag(R.string.border_bottom,
							firstColumnHeight);
					return firstColumn;
				}
				imageView.setTag(R.string.border_top,
						thirdColumnHeight);
				thirdColumnHeight += imageHeight;
				imageView.setTag(R.string.border_bottom,
						thirdColumnHeight);
				return thirdColumn;
			} else {
				if (secondColumnHeight <= thirdColumnHeight) {
					imageView.setTag(R.string.border_top,
							secondColumnHeight);
					secondColumnHeight += imageHeight;
					imageView.setTag(R.string.border_bottom,
							secondColumnHeight);
					return secondColumn;
				}
				imageView.setTag(R.string.border_top,
						thirdColumnHeight);
				thirdColumnHeight += imageHeight;
				imageView.setTag(R.string.border_bottom,
						thirdColumnHeight);
				return thirdColumn;
			}
		}

		/**
		 * ��ͼƬ���ص�SD������������
		 * 
		 * @param imageUrl
		 *            ͼƬ��URL��ַ��
		 */
		private void downloadImage(String imageUrl) {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				Log.d("TAG", "monted sdcard");
			} else {
				Log.d("TAG", "has no sdcard");
			}
			HttpURLConnection con = null;
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			BufferedInputStream bis = null;
			File imageFile = null;
			try {
				URL url = new URL(imageUrl);
				con = (HttpURLConnection) url.openConnection();
				con.setConnectTimeout(5 * 1000);
				con.setReadTimeout(15 * 1000);
				con.setDoInput(true);
//				con.setDoOutput(true);
				bis = new BufferedInputStream(con.getInputStream());
				imageFile = new File(getImagePath(imageUrl));
				fos = new FileOutputStream(imageFile);
				bos = new BufferedOutputStream(fos);
				byte[] b = new byte[1024];
				int length;
				while ((length = bis.read(b)) != -1) {
					bos.write(b, 0, length);
					bos.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (bis != null) {
						bis.close();
					}
					if (bos != null) {
						bos.close();
					}
					if (con != null) {
						con.disconnect();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (imageFile != null) {
				Bitmap bitmap = ImageLoader.decodeSampledBitmapFromResource(
						imageFile.getPath(), columnWidth);
				if (bitmap != null) {
					imageLoader.addBitmapToMemoryCache(imageUrl,
							bitmap);
				}
			}
		}

		/**
		 * ��ȡͼƬ�ı��ش洢·����
		 * 
		 * @param imageUrl
		 *            ͼƬ��URL��ַ��
		 * @return ͼƬ�ı��ش洢·����
		 */
		private String getImagePath(String imageUrl) {
			int lastSlashIndex = imageUrl.lastIndexOf("/");
			String imageName = imageUrl.substring(lastSlashIndex + 1,imageUrl.length());
			String imageDir = Environment.getExternalStorageDirectory().getPath()
					+ SD_PATH;
			File file = new File(imageDir);
			if (!file.exists()) {
				file.mkdirs();
			}
			String imagePath = imageDir + imageName;
			return imagePath;
		}

	}
	
	
	
	
	
	
	class LocalImageTask extends AsyncTask<String, Void, Bitmap>{
		
		/**
		 * ��¼ÿ��ͼƬ��Ӧ��λ��
		 */
		private int mItemPosition;

		/**
		 * ͼƬ��URL��ַ
		 */
		private String mImageUrl;

		/**
		 * ���ظ�ʹ�õ�ImageView
		 */
		private ImageView mImageView;

		public LocalImageTask() {
		}

		/**
		 * �����ظ�ʹ�õ�ImageView����
		 * 
		 * @param imageView
		 */
		public LocalImageTask(ImageView imageView) {
			mImageView = imageView;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			String path  = params[0];
			Bitmap imageBitmap = BitmapFactory.decodeFile(path);
			return imageBitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (bitmap != null) {
				double ratio = bitmap.getWidth()
						/ (columnWidth * 1.0);
				int scaledHeight = (int) (bitmap.getHeight() / ratio);
				addImage(bitmap, columnWidth, scaledHeight);
			}
			taskCollection.remove(this);
		}

		/**
		 * ���ݴ����URL����ͼƬ���м��ء��������ͼƬ�Ѿ�������SD���У���ֱ�Ӵ�SD�����ȡ������ʹ����������ء�
		 * 
		 * @param imageUrl
		 *            ͼƬ��URL��ַ
		 * @return ���ص��ڴ��ͼƬ��
		 */
		private Bitmap loadImage(String imageUrl) {
			File imageFile = new File(getImagePath(imageUrl));
			if (!imageFile.exists()) {
				downloadImage(imageUrl);
			}
			if (imageUrl != null) {
				Bitmap bitmap = ImageLoader.decodeSampledBitmapFromResource(
						imageFile.getPath(),columnWidth);
				if (bitmap != null) {
					imageLoader.addBitmapToMemoryCache(imageUrl,
							bitmap);
					return bitmap;
				}
			}
			return null;
		}

		/**
		 * ��ImageView�����һ��ͼƬ
		 * 
		 * @param bitmap
		 *            ����ӵ�ͼƬ
		 * @param imageWidth
		 *            ͼƬ�Ŀ��
		 * @param imageHeight
		 *            ͼƬ�ĸ߶�
		 */
		private void addImage(Bitmap bitmap, int imageWidth, int imageHeight) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					imageWidth, imageHeight);
			if (mImageView != null) {
				mImageView.setImageBitmap(bitmap);
			} else {
				ImageView imageView = new ImageView(getContext());
				imageView.setLayoutParams(params);
				imageView.setImageBitmap(bitmap);
				imageView.setScaleType(ScaleType.FIT_XY);
				imageView.setPadding(5, 5, 5, 5);
				imageView.setTag(R.string.image_url, mImageUrl);
				imageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getContext(),
								PhotoDetailActivity.class);
						intent.putExtra("IMAGE_POSITION", mItemPosition);
						intent.putExtra("IMAGE_URLS",imageUrls);
						getContext().startActivity(intent);
					}
				});
				findColumnToAdd(imageView, imageHeight).addView(imageView);
				imageViewList.add(imageView);
			}
		}

		/**
		 * �ҵ���ʱӦ�����ͼƬ��һ�С�ԭ����Ƕ����еĸ߶Ƚ����жϣ���ǰ�߶���С��һ�о���Ӧ����ӵ�һ�С�
		 * 
		 * @param imageView
		 * @param imageHeight
		 * @return Ӧ�����ͼƬ��һ��
		 */
		private LinearLayout findColumnToAdd(ImageView imageView, int imageHeight) {
			if (firstColumnHeight <= secondColumnHeight) {
				if (firstColumnHeight <= thirdColumnHeight) {
					imageView.setTag(R.string.border_top,
							firstColumnHeight);
					firstColumnHeight += imageHeight;
					imageView.setTag(R.string.border_bottom,
							firstColumnHeight);
					return firstColumn;
				}
				imageView.setTag(R.string.border_top,
						thirdColumnHeight);
				thirdColumnHeight += imageHeight;
				imageView.setTag(R.string.border_bottom,
						thirdColumnHeight);
				return thirdColumn;
			} else {
				if (secondColumnHeight <= thirdColumnHeight) {
					imageView.setTag(R.string.border_top,
							secondColumnHeight);
					secondColumnHeight += imageHeight;
					imageView.setTag(R.string.border_bottom,
							secondColumnHeight);
					return secondColumn;
				}
				imageView.setTag(R.string.border_top,
						thirdColumnHeight);
				thirdColumnHeight += imageHeight;
				imageView.setTag(R.string.border_bottom,
						thirdColumnHeight);
				return thirdColumn;
			}
		}

		/**
		 * ��ͼƬ���ص�SD������������
		 * 
		 * @param imageUrl
		 *            ͼƬ��URL��ַ��
		 */
		private void downloadImage(String imageUrl) {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				Log.d("TAG", "monted sdcard");
			} else {
				Log.d("TAG", "has no sdcard");
			}
			HttpURLConnection con = null;
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			BufferedInputStream bis = null;
			File imageFile = null;
			try {
				URL url = new URL(imageUrl);
				con = (HttpURLConnection) url.openConnection();
				con.setConnectTimeout(5 * 1000);
				con.setReadTimeout(15 * 1000);
				con.setDoInput(true);
//				con.setDoOutput(true);
				bis = new BufferedInputStream(con.getInputStream());
				imageFile = new File(getImagePath(imageUrl));
				fos = new FileOutputStream(imageFile);
				bos = new BufferedOutputStream(fos);
				byte[] b = new byte[1024];
				int length;
				while ((length = bis.read(b)) != -1) {
					bos.write(b, 0, length);
					bos.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (bis != null) {
						bis.close();
					}
					if (bos != null) {
						bos.close();
					}
					if (con != null) {
						con.disconnect();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (imageFile != null) {
				Bitmap bitmap = ImageLoader.decodeSampledBitmapFromResource(
						imageFile.getPath(), columnWidth);
				if (bitmap != null) {
					imageLoader.addBitmapToMemoryCache(imageUrl,
							bitmap);
				}
			}
		}

		/**
		 * ��ȡͼƬ�ı��ش洢·����
		 * 
		 * @param imageUrl
		 *            ͼƬ��URL��ַ��
		 * @return ͼƬ�ı��ش洢·����
		 */
		private String getImagePath(String imageUrl) {
			int lastSlashIndex = imageUrl.lastIndexOf("/");
			String imageName = imageUrl.substring(lastSlashIndex + 1,imageUrl.length());
			String imageDir = Environment.getExternalStorageDirectory().getPath()
					+ SD_PATH;
			File file = new File(imageDir);
			if (!file.exists()) {
				file.mkdirs();
			}
			String imagePath = imageDir + imageName;
			return imagePath;
		}
	}
}
