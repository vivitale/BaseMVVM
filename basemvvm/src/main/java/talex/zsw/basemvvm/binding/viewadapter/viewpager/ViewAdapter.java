package talex.zsw.basemvvm.binding.viewadapter.viewpager;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;

import talex.zsw.basemvvm.binding.command.BindingCommand;

/**
 * 作用：ViewPager 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewAdapter
{
	/**
	 * @param onPageScrolledCommand           页面滚动事件
	 * @param onPageSelectedCommand           页面选中事件
	 * @param onPageScrollStateChangedCommand 页面滚动状态改变事件
	 */
	@BindingAdapter(value = {"onPageScrolledCommand", "onPageSelectedCommand",
		"onPageScrollStateChangedCommand"}, requireAll = false)
	public static void onScrollChangeCommand(final ViewPager viewPager, final BindingCommand<ViewPagerDataWrapper> onPageScrolledCommand, final BindingCommand<Integer> onPageSelectedCommand, final BindingCommand<Integer> onPageScrollStateChangedCommand)
	{
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			private int state;

			@Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
				if(onPageScrolledCommand != null)
				{
					onPageScrolledCommand.execute(new ViewPagerDataWrapper(position, positionOffset, positionOffsetPixels, state));
				}
			}

			@Override public void onPageSelected(int position)
			{
				if(onPageSelectedCommand != null)
				{
					onPageSelectedCommand.execute(position);
				}
			}

			@Override public void onPageScrollStateChanged(int state)
			{
				this.state = state;
				if(onPageScrollStateChangedCommand != null)
				{
					onPageScrollStateChangedCommand.execute(state);
				}
			}
		});
	}

	public static class ViewPagerDataWrapper
	{
		/**
		 * 当前页面
		 */
		public int position;
		/**
		 * 偏移比例
		 */
		public float positionOffset;
		/**
		 * 滑动像素
		 */
		public int positionOffsetPixels;
		/**
		 * 有三个状态：
		 * 1：按下时调用
		 * 2：抬起时如果发生了滑动值会变为（不发生滑动不会有2），
		 * 0：滑动结束时变为
		 */
		public int state;

		public ViewPagerDataWrapper(int position, float positionOffset, int positionOffsetPixels, int state)
		{
			this.positionOffset = positionOffset;
			this.position = position;
			this.positionOffsetPixels = positionOffsetPixels;
			this.state = state;
		}
	}
}
