package talex.zsw.sample.mvvm;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import talex.zsw.sample.base.MyApplication;
import talex.zsw.sample.module.main.recycle.BaseRecycleViewModel;
import talex.zsw.sample.module.main.recycle.NetWorkViewModel;
import talex.zsw.sample.module.network.DownloadViewModel;
import talex.zsw.sample.module.network.WeatherViewModel;
import talex.zsw.sample.module.personal.LoginViewModel;

/**
 * 作用：ViewModel工厂类,用到AppRepository仓库中方法的ViewModel需要在此注册,
 * 且实现 initViewModel() 方法
 *
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory
{
	@SuppressLint("StaticFieldLeak") private static volatile AppViewModelFactory INSTANCE;
	private final AppRepository mRepository;

	public static AppViewModelFactory getInstance()
	{
		if(INSTANCE == null)
		{
			synchronized(AppViewModelFactory.class)
			{
				if(INSTANCE == null)
				{
					INSTANCE = new AppViewModelFactory(Injection.provideDemoRepository());
				}
			}
		}
		return INSTANCE;
	}

	@VisibleForTesting public static void destroyInstance()
	{
		INSTANCE = null;
	}

	private AppViewModelFactory(AppRepository repository)
	{
		this.mRepository = repository;
	}

	@NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
	{
		if(modelClass.isAssignableFrom(LoginViewModel.class))
		{
			return (T) new LoginViewModel(MyApplication.getInstance(), mRepository);
		}
		else if(modelClass.isAssignableFrom(WeatherViewModel.class))
		{
			return (T) new WeatherViewModel(MyApplication.getInstance(), mRepository);
		}
		else if(modelClass.isAssignableFrom(DownloadViewModel.class))
		{
			return (T) new DownloadViewModel(MyApplication.getInstance(), mRepository);
		}
		else if(modelClass.isAssignableFrom(NetWorkViewModel.class))
		{
			return (T) new NetWorkViewModel(MyApplication.getInstance(), mRepository);
		}
		else if(modelClass.isAssignableFrom(BaseRecycleViewModel.class))
		{
			return (T) new BaseRecycleViewModel(MyApplication.getInstance(), mRepository);
		}
		throw new IllegalArgumentException("Unknown ViewModel class: "+modelClass.getName());
	}
}
