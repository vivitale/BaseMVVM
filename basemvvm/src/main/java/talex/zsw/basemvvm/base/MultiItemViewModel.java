package talex.zsw.basemvvm.base;

import android.support.annotation.NonNull;

/**
 * 作用：RecycleView使用 bindingcollectionadapter2 时的多布局ItemViewModel是封装
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MultiItemViewModel<VM extends BaseViewModel> extends ItemViewModel<VM>
{
	protected Object multiType;

	public Object getItemType()
	{
		return multiType;
	}

	public void multiItemType(@NonNull Object multiType)
	{
		this.multiType = multiType;
	}

	public MultiItemViewModel(@NonNull VM viewModel)
	{
		super(viewModel);
	}
}
