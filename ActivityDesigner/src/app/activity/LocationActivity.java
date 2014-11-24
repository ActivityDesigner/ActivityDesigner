package app.activity;



import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.activitydesigner.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

public class LocationActivity extends Activity{

	private MapView mMapView = null;
	private BaiduMap mBaiduMap = null;
	// 定义一个覆盖物
	private Marker mMarkerA;
	private Marker mMarkerB;
	// 初始化全局bitmap信息
	BitmapDescriptor bda = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_marka);
	// 定义信息现实窗口
	private InfoWindow infoWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// SDKInitializer.initialize(getApplicationContext());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_location);
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.location_bmapView);
		mBaiduMap = mMapView.getMap();
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
		mBaiduMap.setMapStatus(msu);

		// 初始化覆盖物的属性
		initOverlay();

		// 设置覆盖物的点击监听
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				Button button = new Button(getApplicationContext());
				button.setBackgroundResource(R.drawable.slider1);
				//设置高度和宽度没用啊
				//button.setHeight(40);
				//button.setWidth(40);
				OnInfoWindowClickListener listener = null;
				if (marker == mMarkerA) {
					listener = new OnInfoWindowClickListener() {
						@Override
						public void onInfoWindowClick() {
							// TODO Auto-generated method stub
							mBaiduMap.hideInfoWindow();
							Intent intent = new Intent();
							intent.setClass(LocationActivity.this, ActivityAlbumDetailActivity.class);
							startActivity(intent);
						}
					};

					LatLng ll = marker.getPosition();
					infoWindow = new InfoWindow(BitmapDescriptorFactory
							.fromView(button), ll, 0, listener);
					mBaiduMap.showInfoWindow(infoWindow);
				}
				return true;
			}
		});
	}

	// 初始化覆盖物的属性
	private void initOverlay() {
		// TODO Auto-generated method stub
		// 定义Marker坐标点
		LatLng llA = new LatLng(39.963175, 116.400244);
		// 定义覆盖物的属性(位置、图标、层数、拖动性)
		OverlayOptions ooA = new MarkerOptions().position(llA).icon(bda)
				.zIndex(9).draggable(true);
		mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mMapView.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mMapView.onPause();
	}
}
