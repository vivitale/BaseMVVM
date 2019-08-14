package talex.zsw.sample.module.main.viewpager;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import talex.zsw.basecore.view.other.RxToast;
import talex.zsw.basemvvm.base.BaseViewModel;
import talex.zsw.basemvvm.base.SingleLiveEvent;
import talex.zsw.basemvvm.binding.command.BindingCommand;
import talex.zsw.basemvvm.binding.command.BindingConsumer;
import talex.zsw.sample.BR;
import talex.zsw.sample.R;

/**
 * 作用：ViewPager 基于 BaseRecyclerViewAdapterHelper 的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewPagerViewModel extends BaseViewModel
{
	public SingleLiveEvent<String> itemClickEvent = new SingleLiveEvent<>();

	public ViewPagerViewModel(@NonNull Application application)
	{
		super(application);
		//模拟3个ViewPager页面
		for(int i = 1; i <= 3; i++)
		{
			ViewPagerItemViewModel itemViewModel = new ViewPagerItemViewModel(this, "第"+i+"个页面");
			items.add(itemViewModel);
		}
	}

	//给ViewPager添加ObservableList
	public ObservableList<ViewPagerItemViewModel> items = new ObservableArrayList<>();
	//给ViewPager添加ItemBinding
	public ItemBinding<ViewPagerItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_viewpager);
	//给ViewPager添加PageTitle
	public final BindingViewPagerAdapter.PageTitles<ViewPagerItemViewModel> pageTitles
		= new BindingViewPagerAdapter.PageTitles<ViewPagerItemViewModel>()
	{
		@Override public CharSequence getPageTitle(int position, ViewPagerItemViewModel item)
		{
			return "条目"+position;
		}
	};
	//ViewPager切换监听
	public BindingCommand<Integer> onPageSelectedCommand = new BindingCommand<>(new BindingConsumer<Integer>()
	{
		@Override public void call(Integer index)
		{
			RxToast.normal("ViewPager切换："+index);
		}
	});
}