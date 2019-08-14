package talex.zsw.sample.module.main.textview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import talex.zsw.basemvvm.base.BaseFragment
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.FragmentTextviewBinding

/**
 * 作用：TextView的使用说明
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class TextViewFragment : BaseFragment<FragmentTextviewBinding, TextViewViewModel>()
{
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int
    {
        return R.layout.fragment_textview
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }
}