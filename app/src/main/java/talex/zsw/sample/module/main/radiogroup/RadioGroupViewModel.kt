package talex.zsw.sample.module.main.radiogroup

import android.app.Application
import android.databinding.ObservableField
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.basemvvm.binding.command.BindingConsumer
import talex.zsw.sample.mvvm.AppViewModel

/**
 * 作用：RadioGroup的使用说明
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class RadioGroupViewModel(application: Application) : AppViewModel(application)
{
    var info = ObservableField("")

    var onCheckedChangedCommand = BindingCommand(BindingConsumer<String> { string ->
        info.set("选中 - "+string)
    })
}