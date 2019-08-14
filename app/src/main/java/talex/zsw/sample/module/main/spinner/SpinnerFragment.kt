package talex.zsw.sample.module.main.spinner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import talex.zsw.basemvvm.base.BaseFragment
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.FragmentSpinnerBinding

/**
 * 作用：Spinner的使用说明
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class SpinnerFragment : BaseFragment<FragmentSpinnerBinding, SpinnerViewModel>()
{
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int
    {
        return R.layout.fragment_spinner
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }
}