package com.lurencun.android.system;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * 双击退出应用
 *
 * @author 桥下一粒砂 (chenyoca@gmail.com)
 * @date   2013-5-8
 */
public class DoubleClickExit {

	private static final String DEFAULT_EXIT_TIP = "再按一次返回键退出应用！";
	private static final int DEFAULT_EXIT_TIME  = 2000;

	private Activity activity;
	private AtomicBoolean allowToExit = new AtomicBoolean(false);
	private AtomicBoolean isTaskExcuting = new AtomicBoolean(false);

	private class ResetTask extends TimerTask{
		@Override
		public void run() {
			allowToExit.set(false);
			isTaskExcuting.set(false);
		}
	};

	private Timer resetTimer = new Timer("reset-timer", true);

	/**
	 * 需要执行退出提示的Activity对象引用 
	 * @param context Activity的对象引用
	 */
	public DoubleClickExit(Activity context) {
		this.activity = context;
	}

	/**
	 * 在给定时间内双击某按钮退出程序
	 * @param keyCode 按钮ID
	 * @param tip 提示信息
	 * @param waitTime 双击检测时间
	 * @return false
	 */
	public boolean onKeyClick(int keyCode, String tip, int waitTime) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(allowToExit.get()){
				activity.finish();
				android.os.Process.killProcess(android.os.Process.myPid());
			}else{
				Toast.makeText(activity, tip, Toast.LENGTH_SHORT).show();
				allowToExit.set(true);
				// 过一段时间后，恢复重新判定。
				if( !isTaskExcuting.get() ){
					isTaskExcuting.set(true);
					resetTimer.schedule(new ResetTask(), waitTime);
				}
			}

//			if (!allowToExit.get()) {
//				allowToExit.set(true);
//				Toast.makeText(activity, tip, Toast.LENGTH_SHORT).show();
//				if (!isTaskExcuting.get()) {
//					
//				}
//			} else {
//				
//			}
		}
		return false;
	}

	/**
	 * 在给定时间内双击某按钮退出程序
	 * @param keyCode
	 * @param tip
	 * @param waitTime
	 * @return
	 */
	public boolean onKeyClick(int keyCode,int tip,int waitTime){
		return onKeyClick(keyCode, activity.getResources().getString(tip), waitTime);
	}

	/**
	 * 在给定时间内双击某按钮退出程序
	 * @param keyCode 按钮ID
	 * @param waitTime 双击检测时间
	 * @return false
	 */
	public boolean onKeyClick(int keyCode,int waitTime){
		return onKeyClick(keyCode, DEFAULT_EXIT_TIP, waitTime);
	}

	/**
	 * 在给定时间内双击某按钮退出程序
	 * @param keyCode 按钮ID
	 * @return false
	 */
	public boolean onKeyClick(int keyCode){
		return onKeyClick(keyCode, DEFAULT_EXIT_TIP, DEFAULT_EXIT_TIME);
	}
}