package talex.zsw.sample.module.main.checkbox

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.basemvvm.binding.command.BindingConsumer
import talex.zsw.sample.mvvm.AppViewModel

/**
 * 作用：CheckBox的使用说明
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class CheckBoxViewModel(application: Application) : AppViewModel(application)
{
    var info = ObservableField("")
    var check = ObservableBoolean()
    var onCheckedChangedCommand = BindingCommand(BindingConsumer<Boolean> { checked ->
        if (checked)
        {
            info.set("已选中")
        }
        else
        {
            info.set("未选中")
        }
        check.set(checked)
    })

    init
    {
        info.set("未选中")
        check.set(false)
    }
}