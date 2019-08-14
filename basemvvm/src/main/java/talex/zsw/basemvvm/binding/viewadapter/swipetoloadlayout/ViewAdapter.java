package talex.zsw.basemvvm.binding.viewadapter.swipetoloadlayout;

import android.databinding.BindingAdapter;

import talex.zsw.basecore.view.other.swipetoloadlayout.OnLoadMoreListener;
import talex.zsw.basecore.view.other.swipetoloadlayout.OnRefreshListener;
import talex.zsw.basecore.view.other.swipetoloadlayout.SwipeToLoadLayout;
import talex.zsw.basemvvm.binding.command.BindingCommand;

/**
 * 作用：SwipeToLoadLayout 上拉,下拉的 命令实现
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
	public static void onRefreshCommand(SwipeToLoadLayout swipeToLoadLayout, final BindingCommand onRefreshCommand)
	{
		swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener(){
			@Override public void onRefresh()
			{
				onRefreshCommand.execute();
			}
		});
	}

	/**
	 * @param onLoadMoreCommand 上拉加载命令
	 */
	@BindingAdapter({"onLoadMoreCommand"})
	public static void onLoadMoreCommand(SwipeToLoadLayout swipeToLoadLayout, final BindingCommand onLoadMoreCommand)
	{
		swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener(){
			@Override public void onLoadMore()
			{
				onLoadMoreCommand.execute();
			}
		});
	}
}
