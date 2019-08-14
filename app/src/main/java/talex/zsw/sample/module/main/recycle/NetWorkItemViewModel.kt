package talex.zsw.sample.module.main.recycle

import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import talex.zsw.basecore.util.RegTool
import talex.zsw.basecore.view.other.RxToast
import talex.zsw.basemvvm.base.ItemViewModel
import talex.zsw.basemvvm.binding.command.BindingAction
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.sample.R
import talex.zsw.sample.test.TestDto

/**
 * 作用：RecyclerView 基于 bindingcollectionadapter2 的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class NetWorkItemViewModel(viewModel: NetWorkViewModel, data: TestDto) : ItemViewModel<NetWorkViewModel>(
        viewModel)
{
    var entity = ObservableField<TestDto>()
    var drawableImg: Drawable? = null

    init
    {
        this.entity.set(data)
        drawableImg = ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.ic_launcher)
    }

    /**
     * 获取position的方式有很多种,indexOf是其中一种，常见的还有在Adapter中、ItemBinding.of回调里
     */
    fun getPosition(): Int
    {
        return viewModel.getItemPosition(this)
    }

    //item的点击事件
    var itemClick = BindingCommand<Void>(BindingAction {
        //这里可以通过一个标识,做出判断，已达到跳入不同界面的逻辑
        if (RegTool.isEmpty(entity.get()!!.image))
        {
            viewModel.deleteItemLiveData.setValue(this@NetWorkItemViewModel)
        }
        else
        {
            val mBundle = Bundle()
            mBundle.putSerializable("entity", entity.get())
            RxToast.normal(entity.get()!!.title + "跳转详情")
        }
    })

    //item的长按事件
    var itemLongClick = BindingCommand<Void>(BindingAction {
        RxToast.normal(entity.get()!!.title)
    })
}
