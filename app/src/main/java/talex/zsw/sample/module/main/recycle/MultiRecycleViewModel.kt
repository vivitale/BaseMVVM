package talex.zsw.sample.module.main.recycle

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import me.tatarka.bindingcollectionadapter2.ItemBinding
import talex.zsw.basemvvm.base.MultiItemViewModel
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.mvvm.AppViewModel

/**
 * 作用：RecyclerView 基于 bindingcollectionadapter2 的多布局实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class MultiRecycleViewModel(application: Application) : AppViewModel(application)
{
    //给RecyclerView添加ObservableList
    var observableList: ObservableList<MultiItemViewModel<*>> = ObservableArrayList()
    //RecyclerView多布局添加ItemBinding
    var itemBinding: ItemBinding<MultiItemViewModel<*>> =
            ItemBinding.of<MultiItemViewModel<*>> { itemBinding, position, item ->
                //通过item的类型, 动态设置Item加载的布局
                val itemType = item.itemType as String
                if (MultiRecycleType_Head == itemType)
                {
                    //设置头布局
                    itemBinding.set(BR.viewModel, R.layout.item_multi_head)
                }
                else if (MultiRecycleType_Left == itemType)
                {
                    //设置左布局
                    itemBinding.set(BR.viewModel, R.layout.item_multi_rv_left)
                }
                else if (MultiRecycleType_Right == itemType)
                {
                    //设置右布局
                    itemBinding.set(BR.viewModel, R.layout.item_multi_rv_right)
                }
            }

    init
    {
        //模拟10个条目，数据源可以来自网络
        for (i in 0..19)
        {
            if (i == 0)
            {
                val item = MultiRecycleHeadViewModel(this)
                //条目类型为头布局
                item.multiItemType(MultiRecycleType_Head)
                observableList.add(item)
            }
            else
            {
                val text = "我是第" + i + "条"
                if (i % 2 == 0)
                {
                    val item = MultiRecycleLeftItemViewModel(this, text)
                    //条目类型为左布局
                    item.multiItemType(MultiRecycleType_Left)
                    observableList.add(item)
                }
                else
                {
                    val item = MultiRecycleRightItemViewModel(this, text)
                    //条目类型为右布局
                    item.multiItemType(MultiRecycleType_Right)
                    observableList.add(item)
                }
            }
        }
    }

    companion object
    {
        private val MultiRecycleType_Head = "head"
        private val MultiRecycleType_Left = "left"
        private val MultiRecycleType_Right = "right"
    }
}
