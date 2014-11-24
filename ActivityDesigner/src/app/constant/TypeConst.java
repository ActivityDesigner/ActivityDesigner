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
			name = "Լ��";
			break;
		case 1:
			name = "�˶�";
			break;
		case 2:
			name = "����";
			break;
		case 3:
			name = "����";
			break;
		case 4:
			name = "��Ϸ";
			break;
		case 5:
			name = "����Ӱ";
			break;
		case 6:
			name = "����";
			break;
		case 7:
			name = "ѧϰ";
			break;
		case 8:
			name = "������";
			break;
		case 9:
			name = "����";
			break;
		case 10:
			name = "����";
			break;
		case 11:
			name = "�Զ���";
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
			//Լ��
			String []name1 = new String[]{"������"};
			name = name1;
			break;
		case 0:
			//�˶�
			String []name2 = new String[]{"����", "����","��ë��","ƹ����","����"};
			name = name2;
			break;
		case 1:
			//����
			String []name3 = new String[]{"����","�в�", 
					"���","ͬѧ�ۻ�","�����ž�","����","���վۻ�",
					"������","��ѧ��","��ȥ��һ��","�¹���"};
			name = name3;
			break;
		case 2:
			//����
			String []name21 = new String[]{"����","ѧ�������","���Ż����","��ʽ����","����һ��"};
		case 3:
			//��Ϸ
			String []name4 = new String[]{"PS3", "Xbox","PC��Ϸ","RPG��","FTP��"};
			name = name4;
			break;
		case 4:
			//����Ӱ
			String []name5 = new String[]{"ϲ��Ƭ", "���Ƭ","����Ƭ","�ֲ�Ƭ","ս��Ƭ"};
			name = name5;
			break;
		case 5:
			//����
			String []name6 = new String[]{"���н�shopping", "�洦����","�̳�shopping"};
			name = name6;
			break;
		case 6:
			//ѧϰ
			String []name7 = new String[]{"��ϰ��ѧϰ","ͼ���ѧϰ","����ѧϰ","ѧ����","ѧ�ߴ�"};
			name = name7;
			break;
		case 7:
			//���ֻ
			String []name8 = new String[]{"���ֻ�", "ҡ��","�������ֻ�","�������ֻ�","���־�"};
			name = name8;
			break;
		case 8:
			//����
			String []name9 = new String[]{"�Լ���","����","����","������"};
			name = name9;
			break;
		case 9:
			//����
			String []name10 = new String[]{"�Ӱ�","��������","��������","���۲߻�"};
			name = name10;
			break;
		case 10:
			//�Զ���
			String []name12 = new String[]{"û���"};
			name = name12;
		break;
		default:
			String []name13 = new String[]{"��"};
			name = name13;
			break;
		}
		return name;
	}
}
