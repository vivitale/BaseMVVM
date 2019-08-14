package talex.zsw.sample.module.main.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_bar.*
import talex.zsw.basemvvm.base.BaseFragment
import talex.zsw.basemvvm.base.BaseViewModel
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.FragmentTabBarBinding

/**
 * 作用：底部导航栏的页面
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class TabBarFragment : BaseFragment<FragmentTabBarBinding, BaseViewModel<*>>()
{
    var pos = -1

    override fun initArgs()
    {
        arguments?.let {
            pos = it.getInt("data", -1)
        }
    }

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int
    {
        return R.layout.fragment_tab_bar
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }

    override fun initData()
    {
        mTvTitle.text = pos.toString()
    }
}
