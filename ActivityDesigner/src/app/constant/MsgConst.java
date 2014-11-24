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
			 type_1 =  "你好呀，最近忙啥呢。我们有个"+name+" " +
						"活动，希望你能参与，" +
						"活动时间"+date+" "+time+"  "+
						"，活动地点是 "+location+" ，" 
								+"我们的小伙伴有: "+contact+"。不见不散哦，白了个白";
			break;
		case 2:
			type_2 =
			 "通知： 谨定于  " +date+" "+time+" "+
			         "于 "+ location+" 召开 "+name+" ，请准时参加为荷。"+
					+'\n'+"此致"+'\n'
					+"召集人: me"+
					'\n'+"与会人： "+contact;
			break;
		case 3:
			type_3 =
			 "亲！你准备好了吗？我们将会在 年 月 日这天进行活动，活动小窝是（地点）"+
			"参加活动的小伙伴有:xx，xxx，xx"+
			"大家要准时到哦 ~ 么么哒！";
		case 4:
			type_4 =
			"同志你好，组织上安排你加入我们光荣的 活动，伟大领袖毛主席说过：下定决心，不怕牺牲，排除万难，去争取胜利，所以无论多大困难，请你务必于 年 月 日来（地点）报道，组织上会考察你的表现的。" 
			+
			"负责同志：xx"+
			"参与同志：xxx，xx，xxx"+
			"伟大领袖毛主席万岁！";
		case 5:
			 type_5 =
			"Hey！还宅在家里无所事事吗？别担心，来参加我们的 活动吧。 " +
			"年 月 日，对，就是这天！（地点），对，就是这里！让我们乘着阳光，向着远方，吸引她的目光，" +
			"xx，xxx，xxx会微笑在你旁边撑伞 ~";
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
