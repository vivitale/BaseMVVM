package talex.zsw.basemvvm.binding.viewadapter.viewgroup;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 作用：ViewGroup 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public final class ViewAdapter
{
	/**
	 * @param itemBinding 子View的ItemBinding,例如 ItemBinding.of<NetWorkItemViewModel>(BR.viewModel, R.layout.view_linearlayout)
	 * @param viewModelList 子View的ViewModel列表
	 */
	@BindingAdapter({"itemView", "observableList"})
	public static void addViews(ViewGroup viewGroup, final ItemBinding itemBinding, final ObservableList<IBindingItemViewModel> viewModelList)
	{
		if(viewModelList != null && !viewModelList.isEmpty())
		{
			viewGroup.removeAllViews();
			for(IBindingItemViewModel viewModel : viewModelList)
			{
				ViewDataBinding binding
					= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), itemBinding.layoutRes(), viewGroup, true);
				binding.setVariable(itemBinding.variableId(), viewModel);
				viewModel.injecDataBinding(binding);
			}
		}
	}
}

