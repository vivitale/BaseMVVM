package talex.zsw.sample.module.personal

import android.app.Application
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.sendinfo.posycy.entitys.BaseRequest
import talex.zsw.basecore.util.DeviceTool
import talex.zsw.basecore.util.RegTool
import talex.zsw.basecore.util.SpTool
import talex.zsw.basecore.util.Tool
import talex.zsw.basecore.view.dialog.sweetalertdialog.SweetAlertDialog
import talex.zsw.basecore.view.other.RxToast
import talex.zsw.basemvvm.base.DialogEvent
import talex.zsw.basemvvm.base.SingleLiveEvent
import talex.zsw.basemvvm.binding.command.BindingAction
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.basemvvm.binding.command.BindingConsumer
import talex.zsw.sample.entitys.BaseResponse
import talex.zsw.sample.module.main.MainActivity
import talex.zsw.sample.mvvm.AppRepository
import talex.zsw.sample.mvvm.AppViewModel
import talex.zsw.sample.mvvm.http.HttpDto
import talex.zsw.sample.util.Constant

/**
 * 作用：登录
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class LoginViewModel(application: Application, model: AppRepository) : AppViewModel(application, model)
{
    var userName = ObservableField("")
    var password = ObservableField("")
    //用户名清除按钮的显示隐藏绑定
    var clearBtnVisibility = ObservableInt()
    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    var clearUserNameOnClickCommand = BindingCommand<Void>(BindingAction { userName.set("") })
    //密码显示开关
    var passwordShowSwitchOnClickCommand = BindingCommand<Void>(BindingAction {
        //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
        uco.pSwitchEvent.setValue(uco.pSwitchEvent.value == null || !uco.pSwitchEvent.value!!)
    })
    //用户名输入框焦点改变的回调事件
    var onFocusChangeCommand = BindingCommand(BindingConsumer<Boolean> { hasFocus ->
        if (hasFocus!!)
        {
            clearBtnVisibility.set(View.VISIBLE)
        }
        else
        {
            clearBtnVisibility.set(View.INVISIBLE)
        }
    })
    //登录按钮的点击事件
    var loginOnClickCommand = BindingCommand<Void>(BindingAction { login() })

    var uco = UIChangeObservable()
    inner class UIChangeObservable
    {
        //密码开关观察者
        var pSwitchEvent = SingleLiveEvent<Boolean>()
    }

    init
    {
        userName.set(SpTool.getString(Constant.STR_USER_NAME))
        password.set(SpTool.getString(Constant.STR_USER_PASS))
    }

    private fun login()
    {
        if (RegTool.isEmpty(userName.get()))
        {
            RxToast.error("请输入用户名")
        }
        else if (RegTool.isEmpty(password.get()))
        {
            RxToast.error("请输入密码")
        }
        else
        {
            val body = BaseRequest()
            body.loginName = userName.get()
            body.password = password.get()
            body.machineCode = DeviceTool.getIMEI(Tool.getContext())
            getData(HttpDto("http://192.168.200.76:6070/admin/api/login/logining", body).setType(HttpDto.GET))
        }
    }

    override fun requestError(msg: String?, httpDto: HttpDto?)
    {
        dismissDialog()
        SpTool.saveString(Constant.STR_USER_NAME, userName.get())
        SpTool.saveString(Constant.STR_USER_PASS, password.get())
        startActivity(MainActivity::class.java)
        finish()
    }

    override fun bindResponse(response: BaseResponse)
    {
        when (response.http.url)
        {
            "http://192.168.200.76:6070/admin/api/login/logining" ->
            {
                if (response.isSuccess)
                {
                    dismissDialog()
                    SpTool.saveString(Constant.STR_USER_NAME, userName.get())
                    SpTool.saveString(Constant.STR_USER_PASS, password.get())
                    startActivity(MainActivity::class.java)
                    finish()
                }
                else
                {
                    showDialog(DialogEvent(SweetAlertDialog.ERROR_TYPE, "登录失败", response.message))
                }
            }
        }
    }
}
