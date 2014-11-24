package app.db;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainDB {

	//private static SQLiteDatabase mSqLiteDatabase;
	
	/**�����ݿ�**/
	public static void openDataBase(String tableName,Activity mActivity,SQLiteDatabase mSqLiteDatabase){
		mSqLiteDatabase = mActivity.openOrCreateDatabase("activitydesigner.db", Activity.MODE_PRIVATE,null);
		String create_table = "create table if not exists "+tableName
				+ "(_id INTEGER PRIMARY KEY,name TEXT,date TEXT," +
				"	time TEXT,location TEXT,contact TEXT,note Text)"+";";
		mSqLiteDatabase.execSQL(create_table);
	}
	
	/**���ݿ��ѯ**/
	
	/* ɾ��һ������ */
	private void delData(int recordid,SQLiteDatabase mSqLiteDatabase) {
		String DELETE_DATA = "DELETE FROM table1 WHERE _id=" + recordid + ";";
		mSqLiteDatabase.execSQL(DELETE_DATA);
		
		
	}
}
