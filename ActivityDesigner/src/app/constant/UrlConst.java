package app.constant;

public class UrlConst {

	public static String default_ip = "172.20.10.2";
	
	public static String default_port  = "8080";
	
	private static String default_project = "Struts2Test/";
	
	//public static String ADDRESS = "";
	
	//public static String URL = "http://"+default_ip+default_port+ADDRESS;
	
	/**
	 * 注册 url
	 */
	public static String register_url= "http://"+default_ip+":"+default_port+"/"+default_project+"myregister";
	
	/**
	 * 登陆 url
	 */
	public static String login_url = "http://"+default_ip+":"+default_port+"/"+default_project+"mylogin!login";
	
	/**
	 * 添加活动的 url
	 */
	public static String add_activity_url  = "http://"+default_ip+":"+default_port+"/"+default_project+"createActivity";
	/**
	 * 获得活动的url
	 */
	public static String get_activity_url = "http://"+default_ip+":"+default_port+"/"+default_project+"presentinfo";
   
	/**
	 * 发送通知的url
	 */
	public static String send_msg_url = "http://"+default_ip+":"+default_port+"/"+default_project+"mylogin!login";
	
	/**
	 * 获得通知的url
	 */
	public static String get_msg_url = "http://"+default_ip+":"+default_port+"/"+default_project+"mylogin!login";
	/**
     * 上传活动照片url
     */
	public static String upload_activity_photo_url = "";
	/**
	 * 获得活动照片url
	 */
	public static String get_activity_photo_url = "";
	/**
	 * 上传头像url
	 */
	public static String upload_profile_url = "";
	
	public static String default_username = "liuhaodong";
	
	public static String default_password = "liuhaodong";
}
