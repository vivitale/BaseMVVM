package talex.zsw.basemvvm.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.ref.WeakReference;

import talex.zsw.basemvvm.R;


/**
 * 作用：盛装Fragment的一个容器(代理)Activity
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ContainerActivity extends RxAppCompatActivity
{
	private static final String FRAGMENT_TAG = "content_fragment_tag";
	public static final String FRAGMENT = "fragment";
	public static final String BUNDLE = "bundle";
	protected WeakReference<Fragment> mFragment;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = null;
		if(savedInstanceState != null)
		{
			fragment = fm.getFragment(savedInstanceState, FRAGMENT_TAG);
		}
		if(fragment == null)
		{
			fragment = initFromIntent(getIntent());
		}
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.replace(R.id.content, fragment);
		trans.commitAllowingStateLoss();
		mFragment = new WeakReference<>(fragment);
	}

	@Override protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, FRAGMENT_TAG, mFragment.get());
	}

	protected Fragment initFromIntent(Intent data)
	{
		if(data == null)
		{
			throw new RuntimeException("you must provide a page info to display");
		}
		try
		{
			String fragmentName = data.getStringExtra(FRAGMENT);
			if(fragmentName == null || "".equals(fragmentName))
			{
				throw new IllegalArgumentException("can not find page fragmentName");
			}
			Class<?> fragmentClass = Class.forName(fragmentName);
			Fragment fragment = (Fragment) fragmentClass.newInstance();
			Bundle args = data.getBundleExtra(BUNDLE);
			if(args != null)
			{
				fragment.setArguments(args);
			}
			return fragment;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(InstantiationException e)
		{
			e.printStackTrace();
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
		throw new RuntimeException("fragment initialization failed!");
	}

	@Override public void onBackPressed()
	{
		Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
		if(fragment instanceof BaseFragment)
		{
			if(!((BaseFragment) fragment).isBackPressed())
			{
				super.onBackPressed();
			}
		}
		else
		{
			super.onBackPressed();
		}
	}
}
