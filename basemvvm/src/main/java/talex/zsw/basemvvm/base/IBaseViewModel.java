package talex.zsw.basemvvm.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * 作用：ViewModel基类
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface IBaseViewModel extends LifecycleObserver
{

	@OnLifecycleEvent(Lifecycle.Event.ON_ANY) void onAny(LifecycleOwner owner, Lifecycle.Event event);

	@OnLifecycleEvent(Lifecycle.Event.ON_CREATE) void onCreate();

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) void onDestroy();

	@OnLifecycleEvent(Lifecycle.Event.ON_START) void onStart();

	@OnLifecycleEvent(Lifecycle.Event.ON_STOP) void onStop();

	@OnLifecycleEvent(Lifecycle.Event.ON_RESUME) void onResume();

	@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE) void onPause();
}
