package talex.zsw.sample.module.main.recycle

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.sendinfo.posycy.entitys.BaseRequest
import me.tatarka.bindingcollectionadapter2.ItemBinding
import talex.zsw.basecore.interfaces.OnSimpleListener
import talex.zsw.basecore.util.Tool
import talex.zsw.basecore.view.other.RxToast
import talex.zsw.basemvvm.base.SingleLiveEvent
import talex.zsw.basemvvm.binding.command.BindingAction
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.entitys.BaseResponse
import talex.zsw.sample.mvvm.AppRepository
import talex.zsw.sample.mvvm.AppViewModel
import talex.zsw.sample.mvvm.http.HttpDto
import talex.zsw.sample.test.TestData
import talex.zsw.sample.test.TestDto
import talex.zsw.sample.util.Constant

/**
 * 作用：RecyclerView 基于 bindingcollectionadapter2 的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class NetWorkViewModel(application: Application, model: AppRepository) : AppViewModel(application, model)
{
    var deleteItemLiveData: SingleLiveEvent<NetWorkItemViewModel> = SingleLiveEvent()
    //封装一个界面发生改变的观察者
    var uc = UIChangeObservable()

    inner class UIChangeObservable
    {
        //下拉刷新完成
        var finishRefreshing = SingleLiveEvent<Void>()
        //上拉加载完成
        var finishLoadmore = SingleLiveEvent<Void>()
    }

    //给RecyclerView添加ObservableList
    var observableList: ObservableList<NetWorkItemViewModel> = ObservableArrayList()
    //给RecyclerView添加ItemBinding
    var itemBinding = ItemBinding.of<NetWorkItemViewModel>(BR.viewModel, R.layout.item_network)
    //下拉刷新
    var onRefreshCommand = BindingCommand<Void>(BindingAction {
        RxToast.normal("下拉刷新")
        val body = BaseRequest()
        body.key = "26802ee608152"
        body.city = "杭州"
        getData(HttpDto(Constant.WEATHER, body).setType(HttpDto.GET))
    })
    //上拉加载
    var onLoadMoreCommand = BindingCommand<Void>(BindingAction {
        if (observableList.size > 50)
        {
            RxToast.normal("没有更多数据啦")
            uc.finishLoadmore.call()
            return@BindingAction
        }
        showDialog()
        //模拟网络上拉加载更多
        Tool.delayToDo(1000, OnSimpleListener {
            for (i in 1..15)
            {
                observableList.add(NetWorkItemViewModel(this@NetWorkViewModel,
                                                        TestDto("新增数据" + i)))
            }
            dismissDialog()
            uc.finishLoadmore.call()
            uc.finishRefreshing.call()
        })
    })

    /**
     * 删除条目
     */
    fun deleteItem(netWorkItemViewModel: NetWorkItemViewModel)
    {
        //点击确定，在 observableList 绑定中删除，界面立即刷新
        observableList.remove(netWorkItemViewModel)
    }

    /**
     * 获取条目下标
     */
    fun getItemPosition(netWorkItemViewModel: NetWorkItemViewModel): Int
    {
        return observableList.indexOf(netWorkItemViewModel)
    }

    override fun bindResponse(response: BaseResponse)
    {
        //清除列表
        observableList.clear()
        dismissDialog()
        TestData.getGoods(10)
                .forEach {
                    observableList.add(NetWorkItemViewModel(this@NetWorkViewModel,
                                                            it))
                }
        uc.finishLoadmore.call()
        uc.finishRefreshing.call()
    }
}