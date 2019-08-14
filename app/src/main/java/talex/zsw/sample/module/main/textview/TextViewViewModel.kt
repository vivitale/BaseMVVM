package talex.zsw.sample.module.main.textview

import android.app.Application
import android.databinding.ObservableField
import talex.zsw.basecore.util.TimeTool
import talex.zsw.basemvvm.binding.command.BindingAction
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.sample.mvvm.AppViewModel

/**
 * 作用：TextView的使用说明
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class TextViewViewModel(application: Application) : AppViewModel(application)
{
    var info = ObservableField("")

    init
    {
        info.set("请点击按钮")
    }

    var textViewClick = BindingCommand<Void>(BindingAction {
        info.set(TimeTool.getCurTimeString("HH:mm:ss.SSS")+"点击了按钮")
    })

    var textQuickViewClick = BindingCommand<Void>(BindingAction {
        info.set(TimeTool.getCurTimeString("HH:mm:ss.SSS")+"点击了按钮")
    })

    var longClick = BindingCommand<Void>(BindingAction {
        info.set(TimeTool.getCurTimeString("HH:mm:ss.SSS")+"长按了按钮")
    })
}