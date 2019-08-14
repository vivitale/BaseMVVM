package talex.zsw.sample.module.main.recycle

import android.app.Application
import talex.zsw.basecore.interfaces.OnSimpleListener
import talex.zsw.basecore.util.Tool
import talex.zsw.basecore.view.other.RxToast
import talex.zsw.basemvvm.base.SingleLiveEvent
import talex.zsw.basemvvm.binding.command.BindingAction
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.sample.entitys.BaseResponse
import talex.zsw.sample.module.main.adapter.RecordAdapter
import talex.zsw.sample.mvvm.AppRepository
import talex.zsw.sample.mvvm.AppViewModel
import talex.zsw.sample.test.TestData
import talex.zsw.sample.test.TestDto

/**
 * 作用：RecyclerView 基于 BaseRecyclerViewAdapterHelper 的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class BaseRecycleViewModel(application: Application, model: AppRepository) : AppViewModel(application, model)
{
    //封装一个界面发生改变的观察者
    var uc = UIChangeObservable()

    inner class UIChangeObservable
    {
        //下拉刷新完成
        var finishRefreshing = SingleLiveEvent<Void>()
        //上拉加载完成
        var finishLoadmore = SingleLiveEvent<Void>()
    }

    val adapter = RecordAdapter()
    //下拉刷新
    var onRefreshCommand = BindingCommand<Void>(BindingAction {
        RxToast.normal("下拉刷新")
//        val body = BaseRequest()
//        body.key = "26802ee608152"
//        body.city = "杭州"
//        getData(HttpDto(Constant.WEATHER, body).setType(HttpDto.GET))
        bindResponse(BaseResponse())
    })
    //上拉加载
    var onLoadMoreCommand = BindingCommand<Void>(BindingAction {
        if (adapter.itemCount > 50)
        {
            RxToast.normal("兄dei，你太无聊啦~崩是不可能的~")
            uc.finishLoadmore.call()
            return@BindingAction
        }
        showDialog()
        //模拟网络上拉加载更多
        Tool.delayToDo(1000, OnSimpleListener {
            for (i in 1..15)
            {
                adapter.addData( TestDto("新增数据" + i))
            }
            dismissDialog()
            uc.finishLoadmore.call()
            uc.finishRefreshing.call()
        })
    })

    init
    {
        adapter.setOnItemClickListener { adapter, view, position ->
            RxToast.normal((adapter.getItem(position) as TestDto).title)
        }
    }

    override fun bindResponse(response: BaseResponse)
    {
        //清除列表
        dismissDialog()
        adapter.replaceData(TestData.getGoods(10))
        uc.finishLoadmore.call()
        uc.finishRefreshing.call()
    }
}