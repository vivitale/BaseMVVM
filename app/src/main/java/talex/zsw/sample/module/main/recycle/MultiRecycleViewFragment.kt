package talex.zsw.sample.module.main.recycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import talex.zsw.basemvvm.base.BaseFragment
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.FragmentMultiRvBinding

/**
 * 作用：RecyclerView 基于 bindingcollectionadapter2 的多布局实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class MultiRecycleViewFragment : BaseFragment<FragmentMultiRvBinding, MultiRecycleViewModel>()
{
    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int
    {
        return R.layout.fragment_multi_rv
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }

    override fun initData()
    {
        super.initData()
        binding.adapter = BindingRecyclerViewAdapter<Any>()
    }
}
