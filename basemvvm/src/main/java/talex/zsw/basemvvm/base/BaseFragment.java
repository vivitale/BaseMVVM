package talex.zsw.basemvvm.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import talex.zsw.basecore.util.KeyboardTool;
import talex.zsw.basecore.util.RegTool;
import talex.zsw.basecore.view.dialog.sweetalertdialog.SweetAlertDialog;
import talex.zsw.basemvvm.messenger.Messenger;

/**
 * 作用：基于MVVM的基础Fragment
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends RxFragment
	implements IBaseView, IBaseViewFragment
{
	protected V binding;
	protected VM viewModel;
	private int viewModelId;
	private SweetAlertDialog mSweetAlertDialog;

	@Override public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initArgs();
	}

	@Override public void onDestroy()
	{
		super.onDestroy();
	}

	@Nullable @Override public View onCreateView(LayoutInflater inflater,
		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		binding
			= DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, false);
		return binding.getRoot();
	}

	@Override public void onDestroyView()
	{
		super.onDestroyView();
		// 解除Messenger注册
		Messenger.getDefault().unregister(viewModel);
		if(binding != null)
		{
			binding.unbind();
		}
		if(mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
		{
			mSweetAlertDialog.dismiss();
			mSweetAlertDialog = null;
		}
		// 解除EventBus的注册
		EventBus.getDefault().unregister(this);
//		ViewGroup parent = (ViewGroup) binding.getRoot().getParent();
//		if(null != parent)
//		{
//			parent.removeView(binding.getRoot());
//		}
	}

	@Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initViewDataBinding();
		registorUIChangeLiveDataCallBack();
		initData();
		initViewObservable();
		// 注册EventBus
		EventBus.getDefault().register(this);
		isPrepared = true;
		if(getUserVisibleHint())
		{
			isVisible = true;
			onVisible();
		}
	}

	/**
	 * 私有的初始化DataBinding和ViewModel方法
	 */
	private void initViewDataBinding()
	{
		viewModelId = initVariableId();
		viewModel = initViewModel();
		if(viewModel == null)
		{
			// 如果没有特殊设定ViewModel 则尝试获取类的泛型参数来初始化ViewModel
			Class modelClass = null;
			Type type = getClass().getGenericSuperclass();
			if(type instanceof ParameterizedType)
			{
				try
				{
					modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			//如果没有指定泛型参数，则默认使用BaseViewModel
			if(modelClass == null)
			{
				modelClass = BaseViewModel.class;
			}
			viewModel = (VM) createViewModel(this, modelClass);
		}
		//关联ViewModel
		binding.setVariable(viewModelId, viewModel);
		//让ViewModel拥有View的生命周期感应
		getLifecycle().addObserver(viewModel);
		//注入RxLifecycle生命周期
		viewModel.initLifecycleProvider(this);
	}

	// --------------- Fragment是否可见监听事件 --------------
	public boolean isVisible;
	public boolean isPrepared;

	/**
	 * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
	 *
	 * @param isVisibleToUser 是否显示出来了
	 */
	@Override public void setUserVisibleHint(boolean isVisibleToUser)
	{
		super.setUserVisibleHint(isVisibleToUser);
		if(!isPrepared)
		{
			return;
		}
		if(getUserVisibleHint())
		{
			isVisible = true;
			onVisible();
		}
		else
		{
			isVisible = false;
			onInvisible();
		}
	}

	/**
	 * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
	 * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
	 *
	 * @param hidden hidden True if the fragment is now hidden, false if it is not
	 *               visible.
	 */
	@Override public void onHiddenChanged(boolean hidden)
	{
		super.onHiddenChanged(hidden);
		if(!isPrepared)
		{
			return;
		}
		if(!hidden)
		{
			isVisible = true;
			onVisible();
		}
		else
		{
			isVisible = false;
			onInvisible();
		}
	}

	@Override public void onVisible()
	{

	}

	@Override public void onInvisible()
	{

	}

	// ------------------ 注册ViewModel与View的契约UI回调事件 --------------------
	/**
	 * 初始化BaseViewModel与View的契约事件回调逻辑
	 */
	protected void registorUIChangeLiveDataCallBack()
	{
		//加载对话框显示
		viewModel.getUC().getShowDialogEvent().observe(this, new Observer<DialogEvent>()
		{
			@Override public void onChanged(@Nullable DialogEvent event)
			{
				showDialog(event);
			}
		});

		//加载对话框消失
		viewModel.getUC().getDismissDialogEvent().observe(this, new Observer<Void>()
		{
			@Override public void onChanged(@Nullable Void v)
			{
				dismissDialog();
			}
		});

		//显示软键盘
		viewModel.getUC().getShowKeyBoardEventEvent().observe(this, new Observer<EditText>()
		{
			@Override public void onChanged(@Nullable EditText editText)
			{
				showKeyBoard(editText);
			}
		});

		//隐藏软键盘
		viewModel.getUC().getDismissKeyBoardEventEvent().observe(this, new Observer<Void>()
		{
			@Override public void onChanged(@Nullable Void v)
			{
				dismissKeyBoard();
			}
		});

		//跳入新页面
		viewModel.getUC().getStartActivityEvent().observe(this, new Observer<Map<String, Object>>()
		{
			@Override public void onChanged(@Nullable Map<String, Object> params)
			{
				Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
				Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
				Intent intent = (Intent) params.get(BaseViewModel.ParameterField.INTENT);
				int requestCode = (int) params.get(BaseViewModel.ParameterField.REQUEST_CODE);
				if(requestCode == -1)
				{
					if(intent != null)
					{
						startActivity(intent);
					}
					else
					{
						startActivity(clz, bundle);
					}
				}
				else
				{
					if(intent != null)
					{
						startActivityForResult(intent, requestCode);
					}
					else
					{
						Intent i = new Intent(getActivity(), clz);
						if(bundle != null)
						{
							i.putExtras(bundle);
						}
						startActivityForResult(i, requestCode);
					}
				}
			}
		});
		//跳入ContainerActivity
		viewModel.getUC().getStartContainerActivityEvent().observe(this, new Observer<Map<String, Object>>()
		{
			@Override public void onChanged(@Nullable Map<String, Object> params)
			{
				String canonicalName = (String) params.get(BaseViewModel.ParameterField.CANONICAL_NAME);
				Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
				startContainerActivity(canonicalName, bundle);
			}
		});
		//关闭界面
		viewModel.getUC().getFinishEvent().observe(this, new Observer<Void>()
		{
			@Override public void onChanged(@Nullable Void v)
			{
				getActivity().finish();
			}
		});
		//关闭上一层
		viewModel.getUC().getOnBackPressedEvent().observe(this, new Observer<Void>()
		{
			@Override public void onChanged(@Nullable Void v)
			{
				getActivity().onBackPressed();
			}
		});
		// 取消加载更多,刷新更多
		viewModel.getUC().getDisLoadingEvent().observe(this, new Observer<Void>()
		{
			@Override public void onChanged(@Nullable Void v)
			{
				disLoading();
			}
		});
	}

	// ------------------ SweetAlertDialog相关方法 --------------------

	public void showDialog(DialogEvent event)
	{
		if(mSweetAlertDialog == null)
		{
			mSweetAlertDialog = new SweetAlertDialog(getContext());
			mSweetAlertDialog.setCancelable(false);
		}
		if(event == null)
		{
			if(mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
			{
				mSweetAlertDialog.setTitleText("正在加载数据");
				mSweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
			}
			else
			{
				mSweetAlertDialog
					= new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE).setTitleText("正在加载数据");
				mSweetAlertDialog.setCancelable(false);
				mSweetAlertDialog.show();
			}
		}
		else
		{
			if(mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
			{
				mSweetAlertDialog.changeAlertType(event.getType());
			}
			else
			{
				mSweetAlertDialog = new SweetAlertDialog(getContext(), event.getType());
				mSweetAlertDialog.setCancelable(false);
			}
			// Title
			if(!RegTool.isNullString(event.getTitle()))
			{
				mSweetAlertDialog.setTitleText(event.getTitle());
			}
			else
			{
				mSweetAlertDialog.setTitleText("");
			}
			// content
			if(!RegTool.isNullString(event.getContent()))
			{
				mSweetAlertDialog.setContentText(event.getContent());
			}
			else
			{
				mSweetAlertDialog.showContentText(false);
			}
			// confirmText
			if(!RegTool.isNullString(event.getConfirmText()))
			{
				mSweetAlertDialog.setConfirmText(event.getConfirmText());
			}
			// cancelText
			if(!RegTool.isNullString(event.getCancelText()))
			{
				mSweetAlertDialog.setCancelText(event.getCancelText());
			}
			else
			{
				mSweetAlertDialog.showCancelButton(false);
			}
			// confirmListener
			if(event.getConfirmListener() != null)
			{
				mSweetAlertDialog.setConfirmClickListener(event.getConfirmListener());
			}
			else
			{
				mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
				{
					@Override public void onClick(SweetAlertDialog sweetAlertDialog)
					{
						sweetAlertDialog.dismiss();
					}
				});
			}
			// cancelListener
			if(event.getCancelListener() != null)
			{
				mSweetAlertDialog.setCancelClickListener(event.getCancelListener());
			}
			else
			{
				mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener()
				{
					@Override public void onClick(SweetAlertDialog sweetAlertDialog)
					{
						sweetAlertDialog.dismiss();
					}
				});
			}
			mSweetAlertDialog.show();
		}
	}

	public void dismissDialog()
	{
		if(mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
		{
			mSweetAlertDialog.dismiss();
		}
	}

	public void disLoading()
	{

	}

	/**
	 * 显示软键盘
	 */
	public void showKeyBoard(EditText editText)
	{
		KeyboardTool.showSoftInput(getContext(), editText);
	}

	/**
	 * 隐藏软键盘
	 */
	public void dismissKeyBoard()
	{
		KeyboardTool.hideSoftInput(getActivity());
	}

	/**
	 * 跳转页面
	 *
	 * @param clz 所跳转的目的Activity类
	 */
	public void startActivity(Class<?> clz)
	{
		startActivity(new Intent(getContext(), clz));
	}

	// ------------------ 跳转页面 --------------------

	/**
	 * 跳转页面
	 *
	 * @param clz    所跳转的目的Activity类
	 * @param bundle 跳转所携带的信息
	 */
	public void startActivity(Class<?> clz, Bundle bundle)
	{
		Intent intent = new Intent(getContext(), clz);
		if(bundle != null)
		{
			intent.putExtras(bundle);
		}
		startActivity(intent);
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
		Intent intent = new Intent(getContext(), ContainerActivity.class);
		intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
		if(bundle != null)
		{
			intent.putExtra(ContainerActivity.BUNDLE, bundle);
		}
		startActivity(intent);
	}

	// ------------------ 周期方法 --------------------

	/**
	 * 刷新布局
	 */
	public void refreshLayout()
	{
		if(viewModel != null)
		{
			binding.setVariable(viewModelId, viewModel);
		}
	}

	/**
	 * 页面接受的参数方法
	 */
	@Override public void initArgs()
	{

	}

	/**
	 * 初始化根布局
	 *
	 * @return 布局layout的id
	 */
	public abstract int initContentView(LayoutInflater inflater,
		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

	/**
	 * 初始化ViewModel的id
	 *
	 * @return BR的id
	 */
	public abstract int initVariableId();

	/**
	 * 初始化ViewModel
	 *
	 * @return 继承BaseViewModel的ViewModel
	 */
	public VM initViewModel()
	{
		return null;
	}

	/**
	 * 页面数据初始化方法
	 */
	@Override public void initData()
	{

	}

	/**
	 * 页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
	 */
	@Override public void initViewObservable()
	{
	}

	public boolean isBackPressed()
	{
		return false;
	}

	/**
	 * 创建ViewModel
	 *
	 * @param cls
	 * @param <T>
	 * @return
	 */
	public <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> cls)
	{
		return ViewModelProviders.of(fragment).get(cls);
	}

	@Subscribe public void onEvent(NotingEvent event)
	{
	}

	private class NotingEvent{}

}
