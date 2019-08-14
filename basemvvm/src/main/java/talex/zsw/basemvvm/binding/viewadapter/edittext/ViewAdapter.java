package talex.zsw.basemvvm.binding.viewadapter.edittext;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import talex.zsw.basemvvm.binding.command.BindingCommand;

/**
 * 作用：EditText 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewAdapter
{
	/**
	 * EditText重新获取焦点时光标移动到末尾
	 *
	 * @param needRequestFocus 是否开启
	 */
	@BindingAdapter(value = {"requestFocus"}, requireAll = false)
	public static void requestFocusCommand(EditText editText, final Boolean needRequestFocus)
	{
		if(needRequestFocus)
		{
			editText.setSelection(editText.getText().length());
			editText.requestFocus();
			InputMethodManager imm = (InputMethodManager) editText
				.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
		}
		editText.setFocusableInTouchMode(needRequestFocus);
	}

	/**
	 * @param textChanged EditText输入文字改变的监听
	 */
	@BindingAdapter(value = {"textChanged"}, requireAll = false)
	public static void addTextChangedListener(EditText editText, final BindingCommand<String> textChanged)
	{
		editText.addTextChangedListener(new TextWatcher()
		{
			@Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
			{

			}

			@Override public void onTextChanged(CharSequence text, int i, int i1, int i2)
			{
				if(textChanged != null)
				{
					textChanged.execute(text.toString());
				}
			}

			@Override public void afterTextChanged(Editable editable)
			{

			}
		});
	}
}
