package talex.zsw.sample.module.main.spinner

import android.app.Application
import android.databinding.ObservableField
import talex.zsw.basecore.util.DimenTool
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.basemvvm.binding.command.BindingConsumer
import talex.zsw.basemvvm.binding.viewadapter.spinner.IKeyAndValue
import talex.zsw.basemvvm.binding.viewadapter.spinner.NiceSpinnerPaddings
import talex.zsw.sample.R
import talex.zsw.sample.entitys.response.SpinnerVo
import talex.zsw.sample.mvvm.AppViewModel

/**
 * 作用：Spinner的使用说明
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class SpinnerViewModel(application: Application) : AppViewModel(application)
{
    var sex = ObservableField("男")
    var sexItemDatas: MutableList<IKeyAndValue> = arrayListOf()
    var onSexSelectorCommand: BindingCommand<IKeyAndValue> =
            BindingCommand(BindingConsumer<IKeyAndValue> { iKeyAndValue -> sex.set(iKeyAndValue.value) })

    var work = ObservableField("IT")
    var workItemDatas: MutableList<IKeyAndValue> = arrayListOf()
    var onWorkSelectorCommand: BindingCommand<IKeyAndValue> =
            BindingCommand(BindingConsumer<IKeyAndValue> { iKeyAndValue -> work.set(iKeyAndValue.value) })

    var age = ObservableField("20")
    var ageItemDatas: MutableList<IKeyAndValue> = arrayListOf()
    var paddings = NiceSpinnerPaddings(DimenTool.getPxById(R.dimen.dp_8))
    var onAgeSelectorCommand: BindingCommand<IKeyAndValue> =
            BindingCommand(BindingConsumer<IKeyAndValue> { iKeyAndValue -> age.set(iKeyAndValue.value) })

    init
    {
        sexItemDatas = arrayListOf()
        sexItemDatas.add(SpinnerVo("男", "1"))
        sexItemDatas.add(SpinnerVo("女", "2"))


        workItemDatas = arrayListOf()
        workItemDatas.add(SpinnerVo("IT", "1"))
        workItemDatas.add(SpinnerVo("美工", "2"))
        workItemDatas.add(SpinnerVo("产品", "3"))
        workItemDatas.add(SpinnerVo("测试", "4"))
        workItemDatas.add(SpinnerVo("运维", "5"))


        ageItemDatas = arrayListOf()
        ageItemDatas.add(SpinnerVo("20", "20"))
        ageItemDatas.add(SpinnerVo("21", "21"))
        ageItemDatas.add(SpinnerVo("22", "22"))
        ageItemDatas.add(SpinnerVo("23", "23"))
        ageItemDatas.add(SpinnerVo("24", "24"))
        ageItemDatas.add(SpinnerVo("25", "25"))
    }
}