package talex.zsw.basemvvm.binding.viewadapter.swiperefresh;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import talex.zsw.basemvvm.binding.command.BindingCommand;


/**
 * 作用：SwipeRefreshLayout 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewAdapter
{
	/**
	 * @param onRefreshCommand 下拉刷新命令
	 */
	@BindingAdapter({"onRefreshCommand"})
	public static void onRefreshCommand(SwipeRefreshLayout swipeRefreshLayout, final BindingCommand onRefreshCommand)
	{
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
		{
			@Override public void onRefresh()
			{
				if(onRefreshCommand != null)
				{
					onRefreshCommand.execute();
				}
			}
		});
	}

	/**
	 * @param refreshing 是否刷新中
	 */
	@BindingAdapter({"refreshing"})
	public static void setRefreshing(SwipeRefreshLayout swipeRefreshLayout, boolean refreshing)
	{
		swipeRefreshLayout.setRefreshing(refreshing);
	}
}
