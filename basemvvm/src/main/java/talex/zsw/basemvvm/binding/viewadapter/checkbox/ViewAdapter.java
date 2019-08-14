package talex.zsw.basemvvm.binding.viewadapter.checkbox;

import android.databinding.BindingAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import talex.zsw.basemvvm.binding.command.BindingCommand;

/**
 * 作用：CheckBox的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewAdapter
{
	/**
	 * @param bindingCommand 选中状态改变时的绑定监听
	 */
	@SuppressWarnings("unchecked") @BindingAdapter(value = {"onCheckedChangedCommand"}, requireAll = false)
	public static void setCheckedChanged(final CheckBox checkBox, final BindingCommand<Boolean> bindingCommand)
	{
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b)
			{
				bindingCommand.execute(b);
			}
		});
	}
}
