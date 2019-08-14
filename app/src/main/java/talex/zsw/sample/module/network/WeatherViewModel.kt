package talex.zsw.sample.module.network

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.sendinfo.posycy.entitys.BaseRequest
import talex.zsw.basecore.util.JsonTool
import talex.zsw.basecore.util.LogTool
import talex.zsw.basemvvm.binding.command.BindingAction
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.basemvvm.binding.command.BindingConsumer
import talex.zsw.sample.entitys.BaseResponse
import talex.zsw.sample.mvvm.AppRepository
import talex.zsw.sample.mvvm.AppViewModel
import talex.zsw.sample.mvvm.http.HttpDto
import talex.zsw.sample.util.Constant

/**
 * 作用：网络请求测试
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class WeatherViewModel(application: Application, model: AppRepository) : AppViewModel(application, model)
{
    var cityName = ObservableField("")
    var info = ObservableField("")
    var clearBtnVisib = ObservableBoolean()
    var clearUserNameOnClickCommand = BindingCommand<Void>(BindingAction { cityName.set("") })
    var onFocusChangeCommand = BindingCommand(BindingConsumer<Boolean> { hasFocus ->
        clearBtnVisib.set(hasFocus)
    })

    init
    {
        cityName.set("杭州")
        clearBtnVisib.set(true)
    }

    var checkClick = BindingCommand<Void>(BindingAction {
        val body = BaseRequest()
        body.key = "26802ee608152"
        body.city = cityName.get()
        getData(HttpDto(Constant.WEATHER, body).setType(HttpDto.GET))
    })

    override fun bindResponse(response: BaseResponse)
    {
        dismissDialog()
        info.set(LogTool.formatJson(JsonTool.getJsonString(response.result)))
    }
}