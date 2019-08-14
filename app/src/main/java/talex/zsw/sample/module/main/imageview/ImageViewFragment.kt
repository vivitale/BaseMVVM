package talex.zsw.sample.module.main.imageview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import talex.zsw.basemvvm.base.BaseFragment
import talex.zsw.basemvvm.base.BaseViewModel
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.FragmentImageviewBinding

/**
 * 作用：ImageView的使用说明
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class ImageViewFragment : BaseFragment<FragmentImageviewBinding, BaseViewModel<*>>()
{
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int
    {
        return R.layout.fragment_imageview
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }

    override fun initData()
    {
        binding.imageurl = "http://img2.imgtn.bdimg.com/it/u=4228074750,1769537472&fm=26&gp=0.jpg"
    }
}