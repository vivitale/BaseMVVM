package talex.zsw.basemvvm.binding.viewadapter.mswitch;

import android.databinding.BindingAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;

import talex.zsw.basemvvm.binding.command.BindingCommand;


/**
 * 作用：Switch 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewAdapter
{
	/**
	 * @param isChecked 设置开关状态
	 */
	@BindingAdapter("switchState") public static void setSwitchState(Switch mSwitch, boolean isChecked)
	{
		mSwitch.setChecked(isChecked);
	}

	/**
	 * @param changeListener Switch的状态改变事件绑定命令
	 */
	@BindingAdapter("onCheckedChangedCommand")
	public static void onCheckedChangeCommand(final Switch mSwitch, final BindingCommand<Boolean> changeListener)
	{
		if(changeListener != null)
		{
			mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
			{
				@Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
				{
					changeListener.execute(isChecked);
				}
			});
		}
	}
}
