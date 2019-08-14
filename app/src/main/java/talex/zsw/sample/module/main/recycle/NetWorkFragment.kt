package talex.zsw.sample.module.main.recycle

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import talex.zsw.basecore.view.dialog.sweetalertdialog.SweetAlertDialog
import talex.zsw.basemvvm.base.BaseFragment
import talex.zsw.basemvvm.base.DialogEvent
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.FragmentNetworkBinding
import talex.zsw.sample.mvvm.AppViewModelFactory

/**
 * 作用：RecyclerView 基于 bindingcollectionadapter2 的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class NetWorkFragment : BaseFragment<FragmentNetworkBinding, NetWorkViewModel>()
{
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int
    {
        return R.layout.fragment_network
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }

    override fun initViewModel(): NetWorkViewModel
    {
        return ViewModelProviders.of(this, AppViewModelFactory.getInstance())
                .get(NetWorkViewModel::class.java)
    }

    override fun initData()
    {
        viewModel.onRefreshCommand.execute()
    }

    override fun initViewObservable()
    {
        viewModel.uc.finishRefreshing.observe(this, Observer {
            binding.mSwipeToLoadLayout.isRefreshing = false
        })

        viewModel.uc.finishLoadmore.observe(this, Observer {
            binding.mSwipeToLoadLayout.isLoadingMore = false
        })
        viewModel.deleteItemLiveData.observeForever { netWorkItemViewModel ->
            val index = viewModel.getItemPosition(netWorkItemViewModel!!)
            showDialog(DialogEvent(SweetAlertDialog.NORMAL_TYPE,
                                   "提示",
                                   "是否删除【" + netWorkItemViewModel.entity.get()!!.title + "】？ position：" + index,
                                   "确定",
                                   "取消",
                                   {
                                       dismissDialog()
                                       viewModel.deleteItem(netWorkItemViewModel)
                                   },
                                   { dismissDialog() }))
        }
    }
}