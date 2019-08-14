package talex.zsw.sample.module.main.viewgroup

import android.databinding.ObservableField
import talex.zsw.basemvvm.binding.viewadapter.viewgroup.IBindingItemViewModel
import talex.zsw.sample.databinding.ViewLinearlayoutBinding
import talex.zsw.sample.test.TestDto

/**
 * 作用：ViewGroup中子View的ViewModel
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class ViewLinearViewModel(viewModel: ViewGroupViewModel, data: TestDto)  : IBindingItemViewModel<ViewLinearlayoutBinding>
{
    var entity = ObservableField<TestDto>()

    override fun injecDataBinding(binding: ViewLinearlayoutBinding?)
    {

    }

    init
    {
        entity.set(data)
    }
}