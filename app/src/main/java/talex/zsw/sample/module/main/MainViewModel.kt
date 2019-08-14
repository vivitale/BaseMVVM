package talex.zsw.sample.module.main

import android.app.Application
import talex.zsw.basemvvm.binding.command.BindingAction
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.sample.module.main.checkbox.CheckBoxFragment
import talex.zsw.sample.module.main.edittext.EditTextFragment
import talex.zsw.sample.module.main.imageview.ImageViewFragment
import talex.zsw.sample.module.main.radiogroup.RadioGroupFragment
import talex.zsw.sample.module.main.recycle.BaseRecycleFragment
import talex.zsw.sample.module.main.recycle.MultiRecycleViewFragment
import talex.zsw.sample.module.main.recycle.NetWorkFragment
import talex.zsw.sample.module.main.spinner.SpinnerFragment
import talex.zsw.sample.module.main.tab.TabBarActivity
import talex.zsw.sample.module.main.textview.TextViewFragment
import talex.zsw.sample.module.main.viewgroup.ViewGroupFragment
import talex.zsw.sample.module.main.viewpager.ViewPagerActivity
import talex.zsw.sample.module.main.viewpager.ViewPagerFragmentActivity
import talex.zsw.sample.module.network.DownloadActivity
import talex.zsw.sample.module.network.WeatherActivity
import talex.zsw.sample.mvvm.AppViewModel

/**
 * 作用：使用大纲目录
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class MainViewModel(application: Application) : AppViewModel(application)
{
    var textViewClick = BindingCommand<Void>(BindingAction {
        startContainerActivity(TextViewFragment::class.java.canonicalName)
    })

    var imageViewClick = BindingCommand<Void>(BindingAction {
        startContainerActivity(ImageViewFragment::class.java.canonicalName)
    })

    var editTextClick = BindingCommand<Void>(BindingAction {
        startContainerActivity(EditTextFragment::class.java.canonicalName)
    })

    var checkboxClick = BindingCommand<Void>(BindingAction {
        startContainerActivity(CheckBoxFragment::class.java.canonicalName)
    })

    var radioClick = BindingCommand<Void>(BindingAction {
        startContainerActivity(RadioGroupFragment::class.java.canonicalName)
    })

    var spinnerClick = BindingCommand<Void>(BindingAction {
        startContainerActivity(SpinnerFragment::class.java.canonicalName)
    })

    var viewgroupClick = BindingCommand<Void>(BindingAction {
        startContainerActivity(ViewGroupFragment::class.java.canonicalName)
    })

    var bottomBarClick = BindingCommand<Void>(BindingAction {
        startActivity(TabBarActivity::class.java)
    })

    var viewpagerViewClick = BindingCommand<Void>(BindingAction {
        startActivity(ViewPagerActivity::class.java)
    })

    var viewpagerFragmentClick = BindingCommand<Void>(BindingAction {
        startActivity(ViewPagerFragmentActivity::class.java)
    })

    var recyclerBindingClick = BindingCommand<Void>(BindingAction {
        startContainerActivity(NetWorkFragment::class.java.canonicalName)
    })

    var recyclerBindingMultiClick = BindingCommand<Void>(BindingAction {
        startContainerActivity(MultiRecycleViewFragment::class.java.canonicalName)
    })

    var recyclerHelperClick = BindingCommand<Void>(BindingAction {
        startContainerActivity(BaseRecycleFragment::class.java.canonicalName)
    })

    var networkClick = BindingCommand<Void>(BindingAction {
        startActivity(WeatherActivity::class.java)
    })

    var downloadClick = BindingCommand<Void>(BindingAction {
        startActivity(DownloadActivity::class.java)
    })
}