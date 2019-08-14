package talex.zsw.sample.module.main

import android.os.Bundle
import org.greenrobot.eventbus.Subscribe
import talex.zsw.basecore.util.LogTool
import talex.zsw.basemvvm.base.BaseActivity
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.ActivityMainBinding

/**
 * 作用：使用大纲目录
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class MainActivity  : BaseActivity<ActivityMainBinding, MainViewModel>()
{
    override fun initContentView(savedInstanceState: Bundle?): Int
    {
        return R.layout.activity_main
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }

    @Subscribe
    fun onEvent(event: String)
    {
        LogTool.nv("event = ${event}")
    }

}