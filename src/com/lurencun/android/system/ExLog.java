package com.lurencun.android.system;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ExLog {

	static Context context;
	
	static final String TAG = ExLog.class.getSimpleName();
	
	public static void init(Context c){
		context = c;
	}
	
	public static void v(String message){
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
	public static void l(String message){
		StackTraceElement ele = Thread.currentThread().getStackTrace()[3];
		int line = ele.getLineNumber();
		String clazz = ele.getClassName();
		System.out.println(":::: @"+clazz+" -> "+line+" :::: "+message);
	}
	
	public static String getCurrentMethodName(){
		// 0 getThreadStackTrce
		// 1 getStackTrace
		// 2 * this method: getCurrentMethodName
		// 3 your method
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}
	
	/**
	 * 输出方法调用链
	 * @param object 对象
	 */
	public static void logCurrentMethodChain(Object object){
		StackTraceElement[] es = Thread.currentThread().getStackTrace();
		long time = System.currentTimeMillis();
		Log.d(TAG, String.format("###### Object(%s) Method Chain ###### @Time( %d )", object.getClass().getSimpleName(), time));
		for(StackTraceElement e : es){
			String msg = String.format("### Method Chain ### Caller:%s  ->：%s", e.getClassName(),e.getMethodName());
			Log.d(TAG, msg);
		}
	}
	
	/**
	 * 输出当前方法调用
	 * @param object 对象
	 */
	public static void logCurrentMethod(Object object){
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
		long time = System.currentTimeMillis();
		String msg = String.format("###### Calling Method ###### Object(%s) -> %s @Time( %d )", object.getClass().getSimpleName(),methodName, time);
		Log.d(TAG, msg);
	}
	
}