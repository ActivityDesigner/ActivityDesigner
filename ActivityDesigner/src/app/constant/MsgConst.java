package app.constant;

import com.caldroid.R.string;

public class MsgConst {

	
	public static String name;
	public static String date;
	public static String time;
	public static String location;
	public static String contact;
	public static String type_1;
	public static String type_2;
	public static String type_3;
	public static String type_4;
	public static String type_5;
	public static void dataInitial(int id){
		//name = NewActivityConst.mLastModel.getName();
		//date = NewActivityConst.mLastModel.getDate();
		//time = NewActivityConst.mLastModel.getTimeStart();
		//location = NewActivityConst.mLastModel.getLocation();
		//contact = NewActivityConst.mLastModel.getAllContactName();
		setType(id);
	}
	public static void setType(int type){
		switch (type) {
		case 1:
			 type_1 =  "���ѽ�����æɶ�ء������и�"+name+" " +
						"���ϣ�����ܲ��룬" +
						"�ʱ��"+date+" "+time+"  "+
						"����ص��� "+location+" ��" 
								+"���ǵ�С�����: "+contact+"��������ɢŶ�����˸���";
			break;
		case 2:
			type_2 =
			 "֪ͨ�� ������  " +date+" "+time+" "+
			         "�� "+ location+" �ٿ� "+name+" ����׼ʱ�μ�Ϊ�ɡ�"+
					+'\n'+"����"+'\n'
					+"�ټ���: me"+
					'\n'+"����ˣ� "+contact;
			break;
		case 3:
			type_3 =
			 "�ף���׼�����������ǽ����� �� �� ��������л���С���ǣ��ص㣩"+
			"�μӻ��С�����:xx��xxx��xx"+
			"���Ҫ׼ʱ��Ŷ ~ ôô�գ�";
		case 4:
			type_4 =
			"ͬ־��ã���֯�ϰ�����������ǹ��ٵ� ���ΰ������ë��ϯ˵�����¶����ģ������������ų����ѣ�ȥ��ȡʤ�����������۶�����ѣ���������� �� �� �������ص㣩��������֯�ϻῼ����ı��ֵġ�" 
			+
			"����ͬ־��xx"+
			"����ͬ־��xxx��xx��xxx"+
			"ΰ������ë��ϯ���꣡";
		case 5:
			 type_5 =
			"Hey����լ�ڼ������������𣿱��ģ����μ����ǵ� ��ɡ� " +
			"�� �� �գ��ԣ��������죡���ص㣩���ԣ�������������ǳ������⣬����Զ������������Ŀ�⣬" +
			"xx��xxx��xxx��΢Ц�����Ա߳�ɡ ~";
			break;
		default:
			break;
		}
	}
	
	public static String getType(int type){
		String tmp;
		switch (type) {
		case 1: 
			tmp = type_1;
			break;
		case 2: 
			tmp = type_2;
			break;
		case 3: 
			tmp = type_3;
			break;
		case 4: 
			tmp = type_4;
			break;
		case 5: 
			tmp = type_5;
			break;
		default:
			tmp = "";
			break;
		}
		return tmp;
	}
	
	public static void sendMsg(){
		
	}
}
