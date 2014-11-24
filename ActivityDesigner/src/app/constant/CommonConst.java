package app.constant;

import java.util.ArrayList;
import java.util.List;


public class CommonConst {

	public static  String current_id = "-1";
	
	public static final String current_username = "current_username";
	/**
	 * 
	 */
	public static final String LOGIN_SUCCESS = "��¼�ɹ�";
	/**
	 * 
	 */
	public static final String LOGIN_FAIL = "��¼ʧ��";
	/**
	 * ��ʼ��״̬����
	 */
	public static final int STATUS_INIT = 1;

	/**
	 * ͼƬ�Ŵ�״̬����
	 */
	public static final int STATUS_OUT = 2;

	/**
	 * ͼƬ��С״̬����
	 */
	public static final int STATUS_IN = 3;

	/**
	 * ͼƬ�϶�״̬����
	 */
	public static final int STATUS_MOVE = 4;
	
	protected final static String TAG = CommonConst.class.getSimpleName();

	public final static String NetworkExceptionMessage = "��,�������粻������";

	public final static String SystemExceptionMessage = "ϵͳ����";

	protected final static String ConnectExceptionMessage = "��,�������粻������";

	protected final static String ServerExceptionMessage = "�������������";

	protected final static String UnsupportedEncodingExceptionMessage = "�޷�ʶ��ı���";

	protected final static String IOExceptionMessage = "��,�������粻������";

	protected final static String JSONExceptionMessage = "���ݽ���ʧ��,������";

	public final static int SystemExceptionCode = -2;

	public final static int NetworkExceptionCode = -1;

	public final static int ConnectExceptionCode = 1;

	public final static int ServerExceptionCode = 2;

	public final static int UnsupportedEncodingExceptionCode = 3;

	public final static int IOExceptionCode = 4;

	public final static int JSONExceptionCode = 5;

	// ���ʳɹ�ֵ���
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
