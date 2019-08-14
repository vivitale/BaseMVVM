package talex.zsw.sample.module.main.viewgroup

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import me.tatarka.bindingcollectionadapter2.ItemBinding
import talex.zsw.basemvvm.binding.viewadapter.viewgroup.IBindingItemViewModel
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.module.main.recycle.NetWorkItemViewModel
import talex.zsw.sample.mvvm.AppViewModel
import talex.zsw.sample.test.TestData

/**
 * 作用：ViewGroup的使用说明
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class ViewGroupViewModel(application: Application) : AppViewModel(application)
{
    var itemLinearLayoutBinding = ItemBinding.of<NetWorkItemViewModel>(BR.viewModel, R.layout.view_linearlayout)

    var observableList: ObservableList<IBindingItemViewModel<*>> = ObservableArrayList()

    init
    {
        TestData.getGoods(3).forEach {
            observableList.add(ViewLinearViewModel(this,it))
        }
    }

}