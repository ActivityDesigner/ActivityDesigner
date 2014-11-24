package app.constant;

import java.util.ArrayList;
import java.util.List;


public class CommonConst {

	public static  String current_id = "-1";
	
	public static final String current_username = "current_username";
	/**
	 * 
	 */
	public static final String LOGIN_SUCCESS = "登录成功";
	/**
	 * 
	 */
	public static final String LOGIN_FAIL = "登录失败";
	/**
	 * 初始化状态常量
	 */
	public static final int STATUS_INIT = 1;

	/**
	 * 图片放大状态常量
	 */
	public static final int STATUS_OUT = 2;

	/**
	 * 图片缩小状态常量
	 */
	public static final int STATUS_IN = 3;

	/**
	 * 图片拖动状态常量
	 */
	public static final int STATUS_MOVE = 4;
	
	protected final static String TAG = CommonConst.class.getSimpleName();

	public final static String NetworkExceptionMessage = "亲,您的网络不给力啊";

	public final static String SystemExceptionMessage = "系统错误";

	protected final static String ConnectExceptionMessage = "亲,您的网络不给力啊";

	protected final static String ServerExceptionMessage = "服务器处理错误";

	protected final static String UnsupportedEncodingExceptionMessage = "无法识别的编码";

	protected final static String IOExceptionMessage = "亲,您的网络不给力啊";

	protected final static String JSONExceptionMessage = "数据解析失败,请重试";

	public final static int SystemExceptionCode = -2;

	public final static int NetworkExceptionCode = -1;

	public final static int ConnectExceptionCode = 1;

	public final static int ServerExceptionCode = 2;

	public final static int UnsupportedEncodingExceptionCode = 3;

	public final static int IOExceptionCode = 4;

	public final static int JSONExceptionCode = 5;

	// 访问成功值标记
	public static final int SUCCESS_CODE = 0;

	public static final int FAILED_CODE = -100;

    protected final static String RESULT = "result";

	protected final static String STATUS = "status";
	protected final static String SUCCESS = "success";

	protected final static String ACTION = "action";

	protected final static String COUNT = "count";

	protected final static String LIST = "list";
	
	protected final static String DATE_TIME = "datetime";

	protected final static String MENU = "menu";
	
	protected final static String PROGRAM = "program";
	
	protected final static String PROGRAM_DETAIL = "programdetail";

	protected final static String MESSAGE = "message";
	
	public static List<String> localImage = new ArrayList<String>();
}
