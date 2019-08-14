package talex.zsw.basemvvm.binding.viewadapter.recyclerview;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import talex.zsw.basemvvm.binding.command.BindingCommand;

/**
 * 作用：RecyclerView 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewAdapter
{

	/**
	 * @param lineManagerFactory 添加分隔线
	 */
	@BindingAdapter("lineManager")
	public static void setLineManager(RecyclerView recyclerView, LineManagers.LineManagerFactory lineManagerFactory)
	{
		recyclerView.addItemDecoration(lineManagerFactory.create(recyclerView));
	}


	/**
	 * @param onScrollChangeCommand       滚动事件
	 *                                    dx : 水平滚动距离
	 *                                    dy : 垂直滚动距离
	 *                                    <p>
	 *                                    dx > 0 时为手指向左滚动,列表滚动显示右面的内容
	 *                                    dx < 0 时为手指向右滚动,列表滚动显示左面的内容
	 *                                    dy > 0 时为手指向上滚动,列表滚动显示下面的内容
	 *                                    dy < 0 时为手指向下滚动,列表滚动显示上面的内容
	 * @param onScrollStateChangedCommand 监听RecycleView的滑动状态
	 *                                    SCROLL_STATE_IDLE = 0;（静止没有滚动）
	 *                                    SCROLL_STATE_DRAGGING = 1;正在被外部拖拽,一般为用户正在用手指滚动）
	 *                                    SCROLL_STATE_SETTLING = 2;（自动滚动）
	 */
	@BindingAdapter(value = {"onScrollChangeCommand", "onScrollStateChangedCommand"}, requireAll = false)
	public static void onScrollChangeCommand(final RecyclerView recyclerView, final BindingCommand<ScrollDataWrapper> onScrollChangeCommand, final BindingCommand<Integer> onScrollStateChangedCommand)
	{
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			private int state;

			@Override public void onScrolled(RecyclerView recyclerView, int dx, int dy)
			{
				super.onScrolled(recyclerView, dx, dy);
				if(onScrollChangeCommand != null)
				{
					onScrollChangeCommand.execute(new ScrollDataWrapper(dx, dy, state));
				}
			}

			@Override public void onScrollStateChanged(RecyclerView recyclerView, int newState)
			{
				super.onScrollStateChanged(recyclerView, newState);
				state = newState;
				if(onScrollStateChangedCommand != null)
				{
					onScrollStateChangedCommand.execute(newState);
				}
			}
		});
	}

	/**
	 * @param onLoadMoreCommand 滑动到底部自动调用加载更多的事件
	 */
	@SuppressWarnings("unchecked") @BindingAdapter({"onLoadMoreCommand"})
	public static void onLoadMoreCommand(final RecyclerView recyclerView, final BindingCommand<Integer> onLoadMoreCommand)
	{
		RecyclerView.OnScrollListener listener = new OnScrollListener(onLoadMoreCommand);
		recyclerView.addOnScrollListener(listener);
	}

	/**
	 * @param animator 设置item进场动画
	 */
	@BindingAdapter("itemAnimator")
	public static void setItemAnimator(RecyclerView recyclerView, RecyclerView.ItemAnimator animator)
	{
		recyclerView.setItemAnimator(animator);
	}

	public static class OnScrollListener extends RecyclerView.OnScrollListener
	{

		private PublishSubject<Integer> methodInvoke = PublishSubject.create();

		private BindingCommand<Integer> onLoadMoreCommand;

		public OnScrollListener(final BindingCommand<Integer> onLoadMoreCommand)
		{
			this.onLoadMoreCommand = onLoadMoreCommand;
			methodInvoke.throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Integer>()
			{
				@Override public void accept(Integer integer) throws Exception
				{
					onLoadMoreCommand.execute(integer);
				}
			});
		}

		@Override public void onScrolled(RecyclerView recyclerView, int dx, int dy)
		{
			LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
			int visibleItemCount = layoutManager.getChildCount();
			int totalItemCount = layoutManager.getItemCount();
			int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
			if((visibleItemCount+pastVisiblesItems) >= totalItemCount)
			{
				if(onLoadMoreCommand != null)
				{
					methodInvoke.onNext(recyclerView.getAdapter().getItemCount());
				}
			}
		}

		@Override public void onScrollStateChanged(RecyclerView recyclerView, int newState)
		{
			super.onScrollStateChanged(recyclerView, newState);
		}
	}

	public static class ScrollDataWrapper
	{
		/**
		 * 水平滚动距离
		 * scrollX > 0 时为手指向左滚动,列表滚动显示右面的内容
		 * scrollX < 0 时为手指向右滚动,列表滚动显示左面的内容
		 */
		public float scrollX;
		/**
		 * 垂直滚动距离
		 * scrollY > 0 时为手指向上滚动,列表滚动显示下面的内容
		 * scrollY < 0 时为手指向下滚动,列表滚动显示上面的内容
		 */
		public float scrollY;
		/**
		 * RecycleView的滑动状态
		 * SCROLL_STATE_IDLE = 0;（静止没有滚动）
		 * SCROLL_STATE_DRAGGING = 1;正在被外部拖拽,一般为用户正在用手指滚动）
		 * SCROLL_STATE_SETTLING = 2;（自动滚动）
		 */
		public int state;

		public ScrollDataWrapper(float scrollX, float scrollY, int state)
		{
			this.scrollX = scrollX;
			this.scrollY = scrollY;
			this.state = state;
		}
	}
}
