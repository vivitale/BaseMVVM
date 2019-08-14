package talex.zsw.basemvvm.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 作用：基础的ViewModel
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseViewModel<M extends BaseModel> extends AndroidViewModel implements IBaseViewModel, Consumer<Disposable>
{
	protected M model;
	// LiveData
	private UIChangeLiveData uc;
	// 弱引用持有Lifecycle
	private WeakReference<LifecycleProvider> lifecycle;
	// 管理RxJava，主要针对RxJava异步操作造成的内存泄漏
	private CompositeDisposable mCompositeDisposable;

	public BaseViewModel(@NonNull Application application)
	{
		this(application, null);
	}

	public BaseViewModel(@NonNull Application application, M model)
	{
		super(application);
		this.model = model;
		mCompositeDisposable = new CompositeDisposable();
	}

	// ------------------ Disposable管理 --------------------

	protected void addSubscribe(Disposable disposable)
	{
		if(mCompositeDisposable == null)
		{
			mCompositeDisposable = new CompositeDisposable();
		}
		mCompositeDisposable.add(disposable);
	}

	@Override public void accept(Disposable disposable) throws Exception
	{
		addSubscribe(disposable);
	}

	// ------------------ RxLifecycle --------------------

	/**
	 * 注入RxLifecycle生命周期
	 *
	 * @param lifecycle
	 */
	public void initLifecycleProvider(LifecycleProvider lifecycle)
	{
		this.lifecycle = new WeakReference<>(lifecycle);
	}

	public LifecycleProvider getLifecycleProvider()
	{
		return lifecycle.get();
	}

	// ------------------ onCleared --------------------

	@Override protected void onCleared()
	{
		super.onCleared();
		if(model != null)
		{
			model.onCleared();
		}
		//ViewModel销毁时会执行，同时取消所有异步任务
		if(mCompositeDisposable != null)
		{
			mCompositeDisposable.clear();
		}
	}

	// ------------------ UIChangeLiveData与具体方法实现 --------------------

	public UIChangeLiveData getUC()
	{
		if(uc == null)
		{
			uc = new UIChangeLiveData();
		}
		return uc;
	}

	public final class UIChangeLiveData extends SingleLiveEvent
	{
		private SingleLiveEvent<DialogEvent> showDialogEvent;
		private SingleLiveEvent<Void> dismissDialogEvent;
		private SingleLiveEvent<EditText> showKeyBoardEvent;
		private SingleLiveEvent<Void> dismissKeyBoardEvent;
		private SingleLiveEvent<Map<String, Object>> startActivityEvent;
		private SingleLiveEvent<Map<String, Object>> startContainerActivityEvent;
		private SingleLiveEvent<Void> finishEvent;
		private SingleLiveEvent<Void> onBackPressedEvent;
		private SingleLiveEvent<Void> disLoadingEvent;

		public SingleLiveEvent<DialogEvent> getShowDialogEvent()
		{
			return showDialogEvent = createLiveData(showDialogEvent);
		}

		public SingleLiveEvent<Void> getDismissDialogEvent()
		{
			return dismissDialogEvent = createLiveData(dismissDialogEvent);
		}

		public SingleLiveEvent<EditText> getShowKeyBoardEventEvent()
		{
			return showKeyBoardEvent = createLiveData(showKeyBoardEvent);
		}

		public SingleLiveEvent<Void> getDismissKeyBoardEventEvent()
		{
			return dismissKeyBoardEvent = createLiveData(dismissKeyBoardEvent);
		}

		public SingleLiveEvent<Map<String, Object>> getStartActivityEvent()
		{
			return startActivityEvent = createLiveData(startActivityEvent);
		}

		public SingleLiveEvent<Map<String, Object>> getStartContainerActivityEvent()
		{
			return startContainerActivityEvent = createLiveData(startContainerActivityEvent);
		}

		public SingleLiveEvent<Void> getFinishEvent()
		{
			return finishEvent = createLiveData(finishEvent);
		}

		public SingleLiveEvent<Void> getOnBackPressedEvent()
		{
			return onBackPressedEvent = createLiveData(onBackPressedEvent);
		}

		public SingleLiveEvent<Void> getDisLoadingEvent()
		{
			return disLoadingEvent = createLiveData(disLoadingEvent);
		}

		private SingleLiveEvent createLiveData(SingleLiveEvent liveData)
		{
			if(liveData == null)
			{
				liveData = new SingleLiveEvent();
			}
			return liveData;
		}

		@Override public void observe(LifecycleOwner owner, Observer observer)
		{
			super.observe(owner, observer);
		}
	}

	/**
	 * 显示加载框
	 */
	public void showDialog()
	{
		showDialog(null);
	}


	/**
	 * 显示具体Dialog
	 */
	public void showDialog(DialogEvent event)
	{
		uc.showDialogEvent.postValue(event);
	}

	/**
	 * 隐藏Dialog
	 */
	public void dismissDialog()
	{
		uc.dismissDialogEvent.call();
	}

	/**
	 * 显示软键盘
	 */
	public void showKeyBoard(EditText editText)
	{
		uc.showKeyBoardEvent.postValue(editText);
	}

	/**
	 * 隐藏软键盘
	 */
	public void dismissKeyBoard()
	{
		uc.dismissKeyBoardEvent.call();
	}

	/**
	 * 跳转页面
	 *
	 * @param clz 所跳转的目的Activity类
	 */
	public void startActivity(Class<?> clz)
	{
		startActivity(clz, null, -1);
	}

	public void startActivity(Class<?> clz, int requestCode)
	{
		startActivity(clz, null, requestCode);
	}

	/**
	 * 跳转页面
	 *
	 * @param clz    所跳转的目的Activity类
	 * @param bundle 跳转所携带的信息
	 */
	public void startActivity(Class<?> clz, Bundle bundle)
	{
		startActivity(clz, bundle, -1);
	}

	public void startActivity(Class<?> clz, Bundle bundle, int requestCode)
	{
		Map<String, Object> params = new HashMap<>();
		params.put(ParameterField.CLASS, clz);
		params.put(ParameterField.REQUEST_CODE, requestCode);
		if(bundle != null)
		{
			params.put(ParameterField.BUNDLE, bundle);
		}
		uc.startActivityEvent.postValue(params);
	}

	/**
	 * 跳转页面
	 *
	 * @param intent 意图
	 */
	public void startActivity(Intent intent)
	{
		startActivity(intent, -1);
	}

	public void startActivity(Intent intent, int requestCode)
	{
		Map<String, Object> params = new HashMap<>();
		params.put(ParameterField.INTENT, intent);
		params.put(ParameterField.REQUEST_CODE, requestCode);
		uc.startActivityEvent.postValue(params);
	}

	/**
	 * 跳转容器页面
	 *
	 * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
	 */
	public void startContainerActivity(String canonicalName)
	{
		startContainerActivity(canonicalName, null);
	}

	/**
	 * 跳转容器页面
	 *
	 * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
	 * @param bundle        跳转所携带的信息
	 */
	public void startContainerActivity(String canonicalName, Bundle bundle)
	{
		Map<String, Object> params = new HashMap<>();
		params.put(ParameterField.CANONICAL_NAME, canonicalName);
		if(bundle != null)
		{
			params.put(ParameterField.BUNDLE, bundle);
		}
		uc.startContainerActivityEvent.postValue(params);
	}

	/**
	 * 关闭界面
	 */
	public void finish()
	{
		uc.finishEvent.call();
	}

	/**
	 * 返回上一层
	 */
	public void onBackPressed()
	{
		uc.onBackPressedEvent.call();
	}

	/**
	 * 取消加载更多,刷新更多
	 */
	public void disLoading()
	{
		uc.disLoadingEvent.call();
	}

	public static final class ParameterField
	{
		public static String REQUEST_CODE = "REQUEST_CODE";
		public static String CLASS = "CLASS";
		public static String INTENT = "INTENT";
		public static String CANONICAL_NAME = "CANONICAL_NAME";
		public static String BUNDLE = "BUNDLE";
	}

	// ------------------ 其他生命周期方法 --------------------

	@Override public void onAny(LifecycleOwner owner, Lifecycle.Event event)
	{

	}

	@Override public void onCreate()
	{

	}

	@Override public void onDestroy()
	{

	}

	@Override public void onStart()
	{

	}

	@Override public void onStop()
	{

	}

	@Override public void onResume()
	{

	}

	@Override public void onPause()
	{

	}
}
