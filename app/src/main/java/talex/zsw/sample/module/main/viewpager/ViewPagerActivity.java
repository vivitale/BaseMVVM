package talex.zsw.sample.module.main.viewpager;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import talex.zsw.basecore.view.other.RxToast;
import talex.zsw.basemvvm.base.BaseActivity;
import talex.zsw.sample.BR;
import talex.zsw.sample.R;
import talex.zsw.sample.databinding.ActivityViewpagerBinding;
import talex.zsw.sample.module.main.adapter.ViewPagerBindingAdapter;

/**
 * 作用：ViewPager 基于 BaseRecyclerViewAdapterHelper 的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewPagerActivity extends BaseActivity<ActivityViewpagerBinding, ViewPagerViewModel>
{

	@Override public int initContentView(Bundle savedInstanceState)
	{
		return R.layout.activity_viewpager;
	}

	@Override public int initVariableId()
	{
		return BR.viewModel;
	}


	@Override public void initData()
	{
		binding.tabs.setupWithViewPager(binding.viewPager);
		binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
		//给ViewPager设置adapter
		binding.setAdapter(new ViewPagerBindingAdapter());
	}

	@Override public void initViewObservable()
	{
		viewModel.itemClickEvent.observe(this, new Observer<String>()
		{
			@Override public void onChanged(@Nullable String text)
			{
				RxToast.normal("position："+text);
			}
		});
	}
}
