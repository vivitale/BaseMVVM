package talex.zsw.basemvvm.binding.viewadapter.view;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import talex.zsw.basemvvm.binding.command.BindingCommand;

/**
 * 作用：View 通用的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewAdapter
{
	//防重复点击间隔(秒)
	public static final int CLICK_INTERVAL = 1;

	/**
	 * @param clickCommand 绑定的点击命令
	 * @param isThrottleFirst 是否开启防止过快点击
	 */
	@SuppressLint("CheckResult") @BindingAdapter(value = {"onClickCommand", "isThrottleFirst"}, requireAll = false)
	public static void onClickCommand(View view, final BindingCommand clickCommand, final boolean isThrottleFirst)
	{
		if(isThrottleFirst)
		{
			RxView.clicks(view).subscribe(new Consumer<Object>()
			{
				@Override public void accept(Object object) throws Exception
				{
					if(clickCommand != null)
					{
						clickCommand.execute();
					}
				}
			});
		}
		else
		{
			RxView.clicks(view).throttleFirst(CLICK_INTERVAL, TimeUnit.SECONDS)//1秒钟内只允许点击1次
			      .subscribe(new Consumer<Object>()
			      {
				      @Override public void accept(Object object) throws Exception
				      {
					      if(clickCommand != null)
					      {
						      clickCommand.execute();
					      }
				      }
			      });
		}
	}

	/**
	 * @param clickCommand 绑定的长按命令
	 */
	@BindingAdapter(value = {"onLongClickCommand"}, requireAll = false)
	public static void onLongClickCommand(View view, final BindingCommand clickCommand)
	{
		RxView.longClicks(view).subscribe(new Consumer<Object>()
		{
			@Override public void accept(Object object) throws Exception
			{
				if(clickCommand != null)
				{
					clickCommand.execute();
				}
			}
		});
	}

	/**
	 * @param bindingCommand 回调控件本身的事件
	 */
	@BindingAdapter(value = {"currentView"}, requireAll = false)
	public static void replyCurrentView(View currentView, BindingCommand bindingCommand)
	{
		if(bindingCommand != null)
		{
			bindingCommand.execute(currentView);
		}
	}

	/**
	 * @param needRequestFocus view是否需要获取焦点
	 */
	@BindingAdapter({"requestFocus"}) public static void requestFocusCommand(View view, final Boolean needRequestFocus)
	{
		if(needRequestFocus)
		{
			view.setFocusableInTouchMode(true);
			view.requestFocus();
		}
		else
		{
			view.clearFocus();
		}
	}

	/**
	 * @param onFocusChangeCommand view的焦点发生变化的事件绑定
	 */
	@BindingAdapter({"onFocusChangeCommand"})
	public static void onFocusChangeCommand(View view, final BindingCommand<Boolean> onFocusChangeCommand)
	{
		view.setOnFocusChangeListener(new View.OnFocusChangeListener()
		{
			@Override public void onFocusChange(View v, boolean hasFocus)
			{
				if(onFocusChangeCommand != null)
				{
					onFocusChangeCommand.execute(hasFocus);
				}
			}
		});
	}

	/**
	 * @param visibility view的显示隐藏
	 */
	@BindingAdapter(value = {"isVisible"}, requireAll = false)
	public static void isVisible(View view, final Boolean visibility)
	{
		if(visibility)
		{
			view.setVisibility(View.VISIBLE);
		}
		else
		{
			view.setVisibility(View.GONE);
		}
	}
	//    @BindingAdapter({"onTouchCommand"})
	//    public static void onTouchCommand(View view, final ResponseCommand<MotionEvent, Boolean> onTouchCommand) {
	//        view.setOnTouchListener(new View.OnTouchListener() {
	//            @Override
	//            public boolean onTouch(View v, MotionEvent event) {
	//                if (onTouchCommand != null) {
	//                    return onTouchCommand.execute(event);
	//                }
	//                return false;
	//            }
	//        });
	//    }


	/**
	 * @param text text属性是否进行视图刷新的限制
	 */
	@BindingAdapter("android:text") public static void setText(TextView view, CharSequence text)
	{
		CharSequence oldText = view.getText();

		if(!haveContentsChanged(text, oldText))
		{
			return; // 数据没有变化不进行刷新视图
		}
		view.setText(text);
	}

	/**
	 * 判断数据是否发生了变化
	 *
	 * @param str1 新字符串
	 * @param str2 原字符串
	 */
	private static boolean haveContentsChanged(CharSequence str1, CharSequence str2)
	{
		if((str1 == null) != (str2 == null))
		{
			return true;
		}
		else if(str1 == null)
		{
			return false;
		}
		final int length = str1.length();
		if(length != str2.length())
		{
			return true;
		}
		for(int i = 0; i < length; i++)
		{
			if(str1.charAt(i) != str2.charAt(i))
			{
				return true;
			}
		}
		return false;
	}
}
