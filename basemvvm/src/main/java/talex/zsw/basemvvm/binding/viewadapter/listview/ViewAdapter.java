package talex.zsw.basemvvm.binding.viewadapter.listview;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import talex.zsw.basemvvm.binding.command.BindingCommand;

/**
 * 作用：ListView 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public final class ViewAdapter
{

	/**
	 * @param onScrollChangeCommand       ListView的滚动状态改变
	 *                                    SCROLL_STATE_TOUCH_SCROLL：开始滚动的时候调用，调用一次
	 *                                    SCROLL_STATE_IDLE：滚动事件结束的时候调用，调用一次
	 *                                    SCROLL_STATE_FLING：当手指离开屏幕，并且产生惯性滑动的时候调用，可能会调用<=1次
	 * @param onScrollStateChangedCommand ListViewScrollDataWrapper(滑动状态,第一个Item,可见的Item数量,Item总数量)
	 *                                    firstVisibleItem： 当前屏幕显示的第一个item的位置（下标从0开始）
	 *                                    visibleItemCount：当前屏幕可以见到的item总数，包括没有完整显示的item
	 *                                    totalItemCount：Item的总数，包括通过addFooterView添加的那个item
	 */
	@SuppressWarnings("unchecked")
	@BindingAdapter(value = {"onScrollChangeCommand", "onScrollStateChangedCommand"}, requireAll = false)
	public static void onScrollChangeCommand(final ListView listView, final BindingCommand<ListViewScrollDataWrapper> onScrollChangeCommand, final BindingCommand<Integer> onScrollStateChangedCommand)
	{
		listView.setOnScrollListener(new AbsListView.OnScrollListener()
		{
			private int scrollState;

			@Override public void onScrollStateChanged(AbsListView view, int scrollState)
			{
				this.scrollState = scrollState;
				if(onScrollStateChangedCommand != null)
				{
					onScrollStateChangedCommand.execute(scrollState);
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
			{
				if(onScrollChangeCommand != null)
				{
					onScrollChangeCommand.execute(new ListViewScrollDataWrapper(scrollState, firstVisibleItem, visibleItemCount, totalItemCount));
				}
			}
		});
	}


	/**
	 * @param onItemClickCommand Item点击事件
	 */
	@BindingAdapter(value = {"onItemClickCommand"}, requireAll = false)
	public static void onItemClickCommand(final ListView listView, final BindingCommand<Integer> onItemClickCommand)
	{
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				if(onItemClickCommand != null)
				{
					onItemClickCommand.execute(position);
				}
			}
		});
	}


	/**
	 * @param onLoadMoreCommand 为ListView增加滑动到底部自动调用加载更多的方法
	 */
	@BindingAdapter({"onLoadMoreCommand"})
	public static void onLoadMoreCommand(final ListView listView, final BindingCommand<Integer> onLoadMoreCommand)
	{
		listView.setOnScrollListener(new OnScrollListener(listView, onLoadMoreCommand));
	}

	public static class OnScrollListener implements AbsListView.OnScrollListener
	{
		private PublishSubject<Integer> methodInvoke = PublishSubject.create();
		private BindingCommand<Integer> onLoadMoreCommand;
		private ListView listView;

		public OnScrollListener(ListView listView, final BindingCommand<Integer> onLoadMoreCommand)
		{
			this.onLoadMoreCommand = onLoadMoreCommand;
			this.listView = listView;
			methodInvoke.throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Integer>()
			{
				@Override public void accept(Integer integer) throws Exception
				{
					onLoadMoreCommand.execute(integer);
				}
			});
		}

		@Override public void onScrollStateChanged(AbsListView view, int scrollState)
		{

		}

		@Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
		{
			if(firstVisibleItem+visibleItemCount >= totalItemCount && totalItemCount != 0 &&
				totalItemCount != listView.getHeaderViewsCount()+listView.getFooterViewsCount())
			{
				if(onLoadMoreCommand != null)
				{
					methodInvoke.onNext(totalItemCount);
				}
			}
		}
	}

	public static class ListViewScrollDataWrapper
	{
		/** 当前屏幕显示的第一个item的位置（下标从0开始） */
		public int firstVisibleItem;
		/** 当前屏幕可以见到的item总数，包括没有完整显示的item */
		public int visibleItemCount;
		/** Item的总数，包括通过addFooterView添加的那个item */
		public int totalItemCount;
		/**
		 * 滑动状态
		 * SCROLL_STATE_TOUCH_SCROLL：开始滚动的时候调用，调用一次
		 * SCROLL_STATE_IDLE：滚动事件结束的时候调用，调用一次
		 * SCROLL_STATE_FLING：当手指离开屏幕，并且产生惯性滑动的时候调用，可能会调用<=1次
		 */
		public int scrollState;

		public ListViewScrollDataWrapper(int scrollState, int firstVisibleItem, int visibleItemCount, int totalItemCount)
		{
			this.firstVisibleItem = firstVisibleItem;
			this.visibleItemCount = visibleItemCount;
			this.totalItemCount = totalItemCount;
			this.scrollState = scrollState;
		}
	}
}
