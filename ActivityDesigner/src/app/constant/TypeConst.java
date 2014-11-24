package app.constant;

import java.util.ArrayList;
import java.util.List;

import com.example.activitydesigner.R;

import android.R.integer;

public class TypeConst {

	
	public static String getTypeName(int id){
	
		String name = "";
		switch (id) {
		case 0:
			name = "约会";
			break;
		case 1:
			name = "运动";
			break;
		case 2:
			name = "餐饮";
			break;
		case 3:
			name = "会议";
			break;
		case 4:
			name = "游戏";
			break;
		case 5:
			name = "看电影";
			break;
		case 6:
			name = "购物";
			break;
		case 7:
			name = "学习";
			break;
		case 8:
			name = "听音乐";
			break;
		case 9:
			name = "旅游";
			break;
		case 10:
			name = "工作";
			break;
		case 11:
			name = "自定义活动";
		break;
		default:
			break;
		}
		return name;
	}
	
	public static int getTypeDrawableId(int id){
		
		int type = 0;
		switch (id) {
		case 0:
			type = R.drawable.appointment;
			break;
		case 1:
			type = R.drawable.sports;
			break;
		case 2:
			type = R.drawable.eat; //can yin
			break;
		case 3:
			type = R.drawable.conference; //huiyi
			break;
		case 4:
			type = R.drawable.game;
			break;
		case 5:
			type = R.drawable.movie; //kan dian ying
			break;
		case 6:
			type = R.drawable.shopping;
			break;
		case 7:
			type = R.drawable.study;
			break;
		case 8:
			type = R.drawable.music;
			break;
		case 9:
			type = R.drawable.travel;//lv you
			break;
		case 10:
			type = R.drawable.work;
			break;
		case 11:
			type = R.drawable.more;
		break;
		default:
			break;
		}
		return type;
	}
	
	public static String [] getDefaultTypeNameArray(int id){
		String [] name;
		switch (id-1) {
		case -1:
			//约会
			String []name1 = new String[]{"见个面"};
			name = name1;
			break;
		case 0:
			//运动
			String []name2 = new String[]{"足球", "篮球","羽毛球","乒乓球","冰球"};
			name = name2;
			break;
		case 1:
			//餐饮
			String []name3 = new String[]{"西餐","中餐", 
					"火锅","同学聚会","家人团聚","婚宴","生日聚会",
					"百日宴","升学宴","出去搓一顿","下馆子"};
			name = name3;
			break;
		case 2:
			//会议
			String []name21 = new String[]{"例会","学生会会议","社团活动会议","正式会议","讨论一下"};
		case 3:
			//游戏
			String []name4 = new String[]{"PS3", "Xbox","PC游戏","RPG类","FTP类"};
			name = name4;
			break;
		case 4:
			//看电影
			String []name5 = new String[]{"喜剧片", "惊悚片","剧情片","恐怖片","战争片"};
			name = name5;
			break;
		case 5:
			//购物
			String []name6 = new String[]{"步行街shopping", "随处看看","商场shopping"};
			name = name6;
			break;
		case 6:
			//学习
			String []name7 = new String[]{"自习室学习","图书馆学习","寝室学习","学高数","学线代"};
			name = name7;
			break;
		case 7:
			//音乐活动
			String []name8 = new String[]{"音乐会", "摇滚","经典音乐会","流行音乐会","音乐剧"};
			name = name8;
			break;
		case 8:
			//旅游
			String []name9 = new String[]{"自驾游","穷游","富游","跟团游"};
			name = name9;
			break;
		case 9:
			//工作
			String []name10 = new String[]{"加班","讨论流程","讨论内容","讨论策划"};
			name = name10;
			break;
		case 10:
			//自定义活动
			String []name12 = new String[]{"没想好"};
			name = name12;
		break;
		default:
			String []name13 = new String[]{"无"};
			name = name13;
			break;
		}
		return name;
	}
}
