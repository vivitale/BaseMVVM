package talex.zsw.basemvvm.binding.viewadapter.radiogroup;

import android.databinding.BindingAdapter;
import android.support.annotation.IdRes;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import talex.zsw.basemvvm.binding.command.BindingCommand;


/**
 * 作用：RadioGroup 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewAdapter
{
	/**
	 * @param bindingCommand 选中状态改变时的选中项目的
	 */
	@BindingAdapter(value = {"onCheckedChangedCommand"}, requireAll = false)
	public static void onCheckedChangedCommand(final RadioGroup radioGroup, final BindingCommand<String> bindingCommand)
	{
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
		{
			@Override public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
			{
				RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
				bindingCommand.execute(radioButton.getText().toString());
			}
		});
	}
}
