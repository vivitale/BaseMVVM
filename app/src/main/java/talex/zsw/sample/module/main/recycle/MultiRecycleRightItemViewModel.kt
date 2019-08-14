package talex.zsw.sample.module.main.recycle

import android.databinding.ObservableField
import talex.zsw.basecore.view.other.RxToast
import talex.zsw.basemvvm.base.MultiItemViewModel
import talex.zsw.basemvvm.binding.command.BindingAction
import talex.zsw.basemvvm.binding.command.BindingCommand

/**
 * 作用：RecyclerView 基于 bindingcollectionadapter2 的多布局实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class MultiRecycleRightItemViewModel(viewModel: MultiRecycleViewModel, text: String) : MultiItemViewModel<MultiRecycleViewModel>(
        viewModel)
{
    var text = ObservableField("")
    //条目的点击事件
    var itemClick = BindingCommand<Void>(BindingAction {
        //拿到position
        val position = viewModel.observableList.indexOf(this@MultiRecycleRightItemViewModel)
        RxToast.normal("我是右侧布局 position：$position")
    })

    init
    {
        this.text.set(text)
    }
}
