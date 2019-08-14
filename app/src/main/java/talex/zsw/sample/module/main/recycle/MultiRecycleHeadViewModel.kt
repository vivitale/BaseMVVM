package talex.zsw.sample.module.main.recycle

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
class MultiRecycleHeadViewModel(viewModel: MultiRecycleViewModel) : MultiItemViewModel<MultiRecycleViewModel>(viewModel)
{
    //条目的点击事件
    var itemClick = BindingCommand<Void>(BindingAction { RxToast.normal("我是头布局") })
}
