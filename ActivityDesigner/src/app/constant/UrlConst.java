package app.constant;

public class UrlConst {

	public static String default_ip = "172.20.10.2";
	
	public static String default_port  = "8080";
	
	private static String default_project = "Struts2Test/";
	
	//public static String ADDRESS = "";
	
	//public static String URL = "http://"+default_ip+default_port+ADDRESS;
	
	/**
	 * ע�� url
	 */
	public static String register_url= "http://"+default_ip+":"+default_port+"/"+default_project+"myregister";
	
	/**
	 * ��½ url
	 */
	public static String login_url = "http://"+default_ip+":"+default_port+"/"+default_project+"mylogin!login";
	
	/**
	 * ��ӻ�� url
	 */
	public static String add_activity_url  = "http://"+default_ip+":"+default_port+"/"+default_project+"createActivity";
	/**
	 * ��û��url
	 */
	public static String get_activity_url = "http://"+default_ip+":"+default_port+"/"+default_project+"presentinfo";
   
	/**
	 * ����֪ͨ��url
	 */
	public static String send_msg_url = "http://"+default_ip+":"+default_port+"/"+default_project+"mylogin!login";
	
	/**
	 * ���֪ͨ��url
	 */
	public static String get_msg_url = "http://"+default_ip+":"+default_port+"/"+default_project+"mylogin!login";
	/**
     * �ϴ����Ƭurl
     */
	public static String upload_activity_photo_url = "";
	/**
	 * ��û��Ƭurl
	 */
	public static String get_activity_photo_url = "";
	/**
	 * �ϴ�ͷ��url
	 */
	public static String upload_profile_url = "";
	
	public static String default_username = "liuhaodong";
	
	public static String default_password = "liuhaodong";
}
