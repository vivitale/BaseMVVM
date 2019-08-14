package talex.zsw.sample.module.main.recycle

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_base_recycler.*
import talex.zsw.basecore.view.recyleview.DividerItemDecoration
import talex.zsw.basemvvm.base.BaseFragment
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.FragmentBaseRecyclerBinding
import talex.zsw.sample.mvvm.AppViewModelFactory

/**
 * 作用：RecyclerView 基于 BaseRecyclerViewAdapterHelper 的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class BaseRecycleFragment : BaseFragment<FragmentBaseRecyclerBinding, BaseRecycleViewModel>()
{
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int
    {
        return R.layout.fragment_base_recycler
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }

    override fun initViewModel(): BaseRecycleViewModel
    {
        return ViewModelProviders.of(this, AppViewModelFactory.getInstance())
                .get(BaseRecycleViewModel::class.java)
    }

    override fun initData()
    {
        viewModel.onRefreshCommand.execute()
        with(swipe_target) {
            addItemDecoration(DividerItemDecoration(DividerItemDecoration.VERTICAL_LIST,R.color.line,true))
            layoutManager = LinearLayoutManager(activity)
            adapter = viewModel.adapter
        }
    }

    override fun initViewObservable()
    {
        viewModel.uc.finishRefreshing.observe(this, Observer {
            binding.mSwipeToLoadLayout.isRefreshing = false
        })

        viewModel.uc.finishLoadmore.observe(this, Observer {
            binding.mSwipeToLoadLayout.isLoadingMore = false
        })
    }
}