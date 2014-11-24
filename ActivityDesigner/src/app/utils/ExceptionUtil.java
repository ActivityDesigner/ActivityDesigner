package app.utils;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.content.Context;

public class ExceptionUtil {

	/**
	 * ¥Ú”°“Ï≥£,Õ¯¬Á“Ï≥£
	 * 
	 * @param ex
	 * @param context
	 */
	public static void catchException(Exception ex, Context context) {
//		MobclickAgent.reportError(context.getApplicationContext(),
//				printException(ex).toString());
	}

	public static void catchException(String errorMessage, Context context) {
//		if (!"".equals(errorMessage))
//			MobclickAgent.reportError(context.getApplicationContext(),
//					errorMessage);
	}

	public static StringBuffer printException(Exception ex) {
		StringBuffer ret = new StringBuffer();
		ret.append(ex.toString());
		// Don't use getStackTrace() as it calls clone()
		// Get stackTrace, in case stackTrace is reassigned
		StackTraceElement[] stack = ex.getStackTrace();
		for (java.lang.StackTraceElement element : stack) {
			ret.append("\tat " + element);
		}
		ret.append("\n");
		return ret;
	}

	public static StringBuffer printParams(ArrayList<NameValuePair> values) {
		StringBuffer ret = new StringBuffer();
		if (values != null) {
			for (NameValuePair nameValuePair : values) {
				ret.append("&");
				ret.append(nameValuePair.toString());
			}
		}
		ret.append("\n");
		return ret;
	}
}
