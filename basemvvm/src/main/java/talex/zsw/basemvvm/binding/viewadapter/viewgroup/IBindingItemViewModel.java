package talex.zsw.basemvvm.binding.viewadapter.viewgroup;

import android.databinding.ViewDataBinding;

/**
 * 作用：ViewGroup 子类绑定
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface IBindingItemViewModel<V extends ViewDataBinding>
{
	void injecDataBinding(V binding);
}
