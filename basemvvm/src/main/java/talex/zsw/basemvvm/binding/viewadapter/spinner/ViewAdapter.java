package talex.zsw.basemvvm.binding.viewadapter.spinner;

import android.databinding.BindingAdapter;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import talex.zsw.basecore.view.other.nicespinner.NiceSpinner;
import talex.zsw.basemvvm.binding.command.BindingCommand;

/**
 * 作用：Spinner 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewAdapter
{
	/**
	 * @param itemDatas        下拉条目的集合
	 * @param valueReply       回显的value
	 * @param resource         指定选中项目的显示样式 layout resID 且 android:id="@android:id/text1"
	 * @param dropDownResource 指定下拉时显示的item的样式 layout resID 且 android:id="@android:id/text1"
	 * @param bindingCommand   条目点击的监听
	 */
	@BindingAdapter(value = {"itemDatas", "valueReply", "resource", "dropDownResource",
		"onItemSelectedCommand"}, requireAll = false)
	public static void onSpinnerCommand(final Spinner spinner, final List<IKeyAndValue> itemDatas, String valueReply, int resource, int dropDownResource, final BindingCommand<IKeyAndValue> bindingCommand)
	{
		if(itemDatas == null)
		{
			throw new NullPointerException("this itemDatas parameter is null");
		}
		List<String> lists = new ArrayList<>();
		for(IKeyAndValue iKeyAndValue : itemDatas)
		{
			lists.add(iKeyAndValue.getKey());
		}
		if(resource == 0)
		{
			resource = android.R.layout.simple_spinner_item;
		}
		if(dropDownResource == 0)
		{
			dropDownResource = android.R.layout.simple_spinner_dropdown_item;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter(spinner.getContext(), resource, lists);
		adapter.setDropDownViewResource(dropDownResource);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				IKeyAndValue iKeyAndValue = itemDatas.get(position);
				//将IKeyAndValue对象交给ViewModel
				bindingCommand.execute(iKeyAndValue);
			}

			@Override public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
		//回显选中的值
		if(!TextUtils.isEmpty(valueReply))
		{
			for(int i = 0; i < itemDatas.size(); i++)
			{
				IKeyAndValue iKeyAndValue = itemDatas.get(i);
				if(valueReply.equals(iKeyAndValue.getValue()))
				{
					spinner.setSelection(i);
					return;
				}
			}
		}
	}

	/**
	 * @param itemDatas        下拉条目的集合
	 * @param valueReply       回显的value
	 * @param resource         指定选中项目的显示样式 layout resID 且 android:id="@android:id/text1"
	 * @param dropDownResource 指定下拉时显示的item的样式 layout resID 且 android:id="@android:id/text1"
	 * @param bindingCommand   条目点击的监听
	 */
	@BindingAdapter(value = {"itemDatas", "valueReply", "resource", "dropDownResource",
		"onItemSelectedCommand"}, requireAll = false)
	public static void onAppCompatSpinnerCommand(final AppCompatSpinner spinner, final List<IKeyAndValue> itemDatas, String valueReply, int resource, int dropDownResource, final BindingCommand<IKeyAndValue> bindingCommand)
	{
		if(itemDatas == null)
		{
			throw new NullPointerException("this itemDatas parameter is null");
		}
		List<String> lists = new ArrayList<>();
		for(IKeyAndValue iKeyAndValue : itemDatas)
		{
			lists.add(iKeyAndValue.getKey());
		}
		if(resource == 0)
		{
			resource = android.R.layout.simple_spinner_item;
		}
		if(dropDownResource == 0)
		{
			dropDownResource = android.R.layout.simple_spinner_dropdown_item;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter(spinner.getContext(), resource, lists);
		adapter.setDropDownViewResource(dropDownResource);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				IKeyAndValue iKeyAndValue = itemDatas.get(position);
				//将IKeyAndValue对象交给ViewModel
				bindingCommand.execute(iKeyAndValue);
			}

			@Override public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
		//回显选中的值
		if(!TextUtils.isEmpty(valueReply))
		{
			for(int i = 0; i < itemDatas.size(); i++)
			{
				IKeyAndValue iKeyAndValue = itemDatas.get(i);
				if(valueReply.equals(iKeyAndValue.getValue()))
				{
					spinner.setSelection(i);
					return;
				}
			}
		}
	}

	/**
	 * @param itemDatas 下拉条目的集合
	 * @param valueReply 回显的value
	 * @param paddings padding值
	 * @param bindingCommand 条目点击的监听
	 */
	@BindingAdapter(value = {"itemDatas", "valueReply", "paddings", "onItemSelectedCommand"}, requireAll = false)
	public static void onNiceSpinnerCommand(final NiceSpinner spinner, final List<IKeyAndValue> itemDatas, String valueReply, NiceSpinnerPaddings paddings, final BindingCommand<IKeyAndValue> bindingCommand)
	{
		if(itemDatas == null)
		{
			throw new NullPointerException("this itemDatas parameter is null");
		}
		ArrayList<String> lists = new ArrayList<>();
		for(IKeyAndValue iKeyAndValue : itemDatas)
		{
			lists.add(iKeyAndValue.getKey());
		}

		spinner.attachDataSource(lists);

		if(paddings != null)
		{
			spinner.setPadding(paddings.getLeft(), paddings.getTop(), paddings.getRight(), paddings.getBottom());
		}

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				IKeyAndValue iKeyAndValue = itemDatas.get(position);
				//将IKeyAndValue对象交给ViewModel
				bindingCommand.execute(iKeyAndValue);
			}

			@Override public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
		//回显选中的值
		if(!TextUtils.isEmpty(valueReply))
		{
			for(int i = 0; i < itemDatas.size(); i++)
			{
				IKeyAndValue iKeyAndValue = itemDatas.get(i);
				if(valueReply.equals(iKeyAndValue.getValue()))
				{
					spinner.setSelectedIndex(i);
					return;
				}
			}
		}
	}
}
