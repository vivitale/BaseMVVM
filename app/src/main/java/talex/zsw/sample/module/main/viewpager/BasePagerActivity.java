package talex.zsw.sample.module.main.viewpager;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import java.util.List;

import talex.zsw.basemvvm.BR;
import talex.zsw.basemvvm.base.BaseActivity;
import talex.zsw.basemvvm.base.BaseViewModel;
import talex.zsw.sample.R;
import talex.zsw.sample.databinding.ActivityBasePagerBinding;
import talex.zsw.sample.module.main.adapter.BaseActivityPagerAdapter;

/**
 * 作用：ViewPager + Fragment 的基础实现类
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BasePagerActivity extends BaseActivity<ActivityBasePagerBinding, BaseViewModel>
{
	private List<Fragment> mFragments;
	private List<String> titlePager;

	protected abstract List<Fragment> pagerFragment();

	protected abstract List<String> pagerTitleString();

	@Override public int initContentView(Bundle savedInstanceState)
	{
		return R.layout.activity_base_pager;
	}

	@Override public int initVariableId()
	{
		return BR.viewModel;
	}

	@Override public void initData()
	{
		mFragments = pagerFragment();
		titlePager = pagerTitleString();
		//设置Adapter
		BaseActivityPagerAdapter pagerAdapter
			= new BaseActivityPagerAdapter(getSupportFragmentManager(), mFragments, titlePager);
		binding.viewPager.setAdapter(pagerAdapter);
		binding.tabs.setupWithViewPager(binding.viewPager);
		binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
	}

	@Override public void initViewObservable()
	{

	}
}
