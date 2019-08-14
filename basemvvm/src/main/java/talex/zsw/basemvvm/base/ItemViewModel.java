package talex.zsw.basemvvm.base;

import android.support.annotation.NonNull;

/**
 * 作用：RecycleView使用 bindingcollectionadapter2 时的ItemViewModel封装
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ItemViewModel<VM extends BaseViewModel>
{
	protected VM viewModel;

	public ItemViewModel(@NonNull VM viewModel)
	{
		this.viewModel = viewModel;
	}
}
