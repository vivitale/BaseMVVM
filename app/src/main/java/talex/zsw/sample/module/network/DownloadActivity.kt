package talex.zsw.sample.module.network

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import talex.zsw.basecore.util.AppTool
import talex.zsw.basecore.util.PermissionHelper
import talex.zsw.basecore.util.PermissionTool
import talex.zsw.basemvvm.base.BaseActivity
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.ActivityDownloadBinding
import talex.zsw.sample.mvvm.AppViewModelFactory

/**
 * 作用：下载测试
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class DownloadActivity : BaseActivity<ActivityDownloadBinding, DownloadViewModel>()
{
    override fun initContentView(savedInstanceState: Bundle?): Int
    {
        return R.layout.activity_download
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }

    override fun initViewModel(): DownloadViewModel
    {
        return ViewModelProviders.of(this, AppViewModelFactory.getInstance())
                .get(DownloadViewModel::class.java)
    }

    override fun initData()
    {
        PermissionHelper.requestStorage(object :PermissionTool.FullCallback{
            override fun onGranted(permissionsGranted: MutableList<String>?)
            {
            }

            override fun onDenied(permissionsDeniedForever: MutableList<String>?, permissionsDenied: MutableList<String>?)
            {
                PermissionHelper.showOpenAppSettingDialog()
            }
        })
    }

    override fun initViewObservable()
    {
        viewModel.installEvent.observe(this, Observer {
            AppTool.installApp(this, it)
        })
    }
}