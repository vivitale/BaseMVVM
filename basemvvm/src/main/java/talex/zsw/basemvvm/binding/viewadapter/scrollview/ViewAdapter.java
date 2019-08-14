package talex.zsw.basemvvm.binding.viewadapter.scrollview;

import android.databinding.BindingAdapter;
import android.support.v4.widget.NestedScrollView;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import talex.zsw.basemvvm.binding.command.BindingCommand;

/**
 * 作用：ScrollView 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public final class ViewAdapter
{
	/**
	 * @param onScrollChangeCommand NestedScrollView的滑动监听
	 */
	@SuppressWarnings("unchecked") @BindingAdapter({"onScrollChangeCommand"})
	public static void onScrollChangeCommand(final NestedScrollView nestedScrollView, final BindingCommand<NestScrollDataWrapper> onScrollChangeCommand)
	{
		nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener()
		{
			@Override
			public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY)
			{
				if(onScrollChangeCommand != null)
				{
					onScrollChangeCommand.execute(new NestScrollDataWrapper(scrollX, scrollY, oldScrollX, oldScrollY));
				}
			}
		});
	}

	/**
	 * @param onScrollChangeCommand ScrollView的滑动监听
	 */
	@SuppressWarnings("unchecked") @BindingAdapter({"onScrollChangeCommand"})
	public static void onScrollChangeCommand(final ScrollView scrollView, final BindingCommand<ScrollDataWrapper> onScrollChangeCommand)
	{
		scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener()
		{
			@Override public void onScrollChanged()
			{
				if(onScrollChangeCommand != null)
				{
					onScrollChangeCommand.execute(new ScrollDataWrapper(scrollView.getScrollX(), scrollView.getScrollY()));
				}
			}
		});
	}

	public static class ScrollDataWrapper
	{
		/** X 上的滑动距离*/
		public float scrollX;
		/** Y 上的滑动距离*/
		public float scrollY;

		public ScrollDataWrapper(float scrollX, float scrollY)
		{
			this.scrollX = scrollX;
			this.scrollY = scrollY;
		}
	}

	public static class NestScrollDataWrapper
	{
		public int scrollX;
		public int scrollY;
		public int oldScrollX;
		public int oldScrollY;

		public NestScrollDataWrapper(int scrollX, int scrollY, int oldScrollX, int oldScrollY)
		{
			this.scrollX = scrollX;
			this.scrollY = scrollY;
			this.oldScrollX = oldScrollX;
			this.oldScrollY = oldScrollY;
		}
	}
}
