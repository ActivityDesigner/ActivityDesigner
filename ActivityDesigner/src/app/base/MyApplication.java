package app.base;


import java.util.ArrayList;

import com.baidu.mapapi.SDKInitializer;


import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import app.constant.NewActivityConst;
import app.model.ActivityModel;

public class MyApplication extends Application{

	
    
	@Override
    public void onCreate() {
	    super.onCreate();
	    SDKInitializer.initialize(this);
		
	}
	
}
