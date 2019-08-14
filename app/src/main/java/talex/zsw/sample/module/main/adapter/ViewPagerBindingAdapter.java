package talex.zsw.sample.module.main.adapter;

import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;
import talex.zsw.sample.databinding.ItemViewpagerBinding;
import talex.zsw.sample.module.main.viewpager.ViewPagerItemViewModel;

/**
 * 作用：ViewPagerAdapter 基于 BaseRecyclerViewAdapterHelper 的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewPagerBindingAdapter extends BindingViewPagerAdapter<ViewPagerItemViewModel>
{
	@Override
	public void onBindBinding(final ViewDataBinding binding, int variableId, int layoutRes, final int position, ViewPagerItemViewModel item)
	{
		super.onBindBinding(binding, variableId, layoutRes, position, item);
		//这里可以强转成ViewPagerItemViewModel对应的ViewDataBinding，
		ItemViewpagerBinding _binding = (ItemViewpagerBinding) binding;
	}

	@Override public void destroyItem(ViewGroup container, int position, Object object)
	{
		super.destroyItem(container, position, object);
	}
}
