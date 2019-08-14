package talex.zsw.basemvvm.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import talex.zsw.basecore.util.ActivityTool;

/**
 * 作用：基础的Application,项目的Application继承自该类
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("Registered")
public class BaseApplication extends MultiDexApplication
{
	private static Application sInstance;

	@Override public void onCreate()
	{
		setApplication(this);
		EventBus.getDefault().register(sInstance);
		super.onCreate();
	}

	@Override protected void attachBaseContext(Context base)
	{
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	/**
	 * 退出时调用
	 */
	public void exit()
	{
		EventBus.getDefault().unregister(sInstance);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	/**
	 * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
	 *
	 * @param application
	 */
	public static synchronized void setApplication(@NonNull Application application)
	{
		sInstance = application;
		//注册监听每个activity的生命周期,便于堆栈式管理
		application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks()
		{

			@Override public void onActivityCreated(Activity activity, Bundle savedInstanceState)
			{
				ActivityTool.addActivity(activity);
			}

			@Override public void onActivityStarted(Activity activity)
			{
			}

			@Override public void onActivityResumed(Activity activity)
			{
			}

			@Override public void onActivityPaused(Activity activity)
			{
			}

			@Override public void onActivityStopped(Activity activity)
			{
			}

			@Override public void onActivitySaveInstanceState(Activity activity, Bundle outState)
			{
			}

			@Override public void onActivityDestroyed(Activity activity)
			{
				ActivityTool.removeActivity(activity);
			}
		});
	}


	/**
	 * 获得当前app运行的Application
	 */
	public static Application getInstance()
	{
		if(sInstance == null)
		{
			throw new NullPointerException("please inherit BaseApplication or call setApplication.");
		}
		return sInstance;
	}

	@Subscribe public void onEvent(NotingEvent event)
	{
	}

	private class NotingEvent{}
}
