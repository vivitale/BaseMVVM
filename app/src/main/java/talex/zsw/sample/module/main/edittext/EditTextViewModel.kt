package talex.zsw.sample.module.main.edittext

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import talex.zsw.basemvvm.binding.command.BindingAction
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.basemvvm.binding.command.BindingConsumer
import talex.zsw.sample.mvvm.AppViewModel

/**
 * 作用：EditText的使用说明
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class EditTextViewModel(application: Application) : AppViewModel(application)
{
    var userName = ObservableField("")

    var clearBtnVisibility = ObservableInt()

    var clearBtnVisib = ObservableBoolean()

    var clearUserNameOnClickCommand = BindingCommand<Void>(BindingAction { userName.set("") })

    var onFocusChangeCommand = BindingCommand(BindingConsumer<Boolean> { hasFocus ->
//        if (hasFocus!!)
//        {
//            clearBtnVisibility.set(View.VISIBLE)
//        }
//        else
//        {
//            clearBtnVisibility.set(View.INVISIBLE)
//        }
        clearBtnVisib.set(hasFocus)
    })

    var info = ObservableField("")

    var text = ObservableField("")

    var textChangedCommand = BindingCommand(BindingConsumer<String> { s ->
        text.set("文字变更为：${s}")
    })

    init
    {
        info.set("文字双向绑定")
        text.set("文字变更为：")
        clearBtnVisib.set(true)
    }
}