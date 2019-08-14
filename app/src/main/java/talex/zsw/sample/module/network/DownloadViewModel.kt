package talex.zsw.sample.module.network

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Progress
import com.lzy.okserver.OkDownload
import com.lzy.okserver.download.DownloadListener
import talex.zsw.basecore.view.other.RxToast
import talex.zsw.basemvvm.base.SingleLiveEvent
import talex.zsw.basemvvm.binding.command.BindingAction
import talex.zsw.basemvvm.binding.command.BindingCommand
import talex.zsw.sample.mvvm.AppRepository
import talex.zsw.sample.mvvm.AppViewModel
import java.io.File
import kotlin.math.roundToInt

/**
 * 作用：下载测试
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class DownloadViewModel(application: Application, model: AppRepository) : AppViewModel(application, model)
{
    var info = ObservableField("")
    var installVisib = ObservableBoolean()
    var pro = ObservableInt()
    var file: File? = null
    var installEvent: SingleLiveEvent<File> = SingleLiveEvent()

    init
    {
        installVisib.set(false)
        pro.set(0)
    }

    var downloadClick = BindingCommand<Void>(BindingAction {
        OkDownload.request("tag",
                           OkGo.get<File>("http://p.gdown.baidu.com/6fa7b23247cca47f5d490e1733f8eb51e7ee807484aa999fe4a9d8fb99cb8368ddd854e87dab5f12081d3b3378eb58c7879fd3253e8b8d5a95aaa8a8d910687c6e079c9f7416781fcc04ba4ca0854dc067ca1be347eeaa65d80e08e65007a1a152651f73c5d0e6346c1ed17fcfb1b80c63e383f783a7c98a868d60d44a54908d6c46c41cabdecdb26d9cbc7fbc5d99205b5a72aba027683a6736ad94d744f2181ce04b5249b7b0b712eb92e83d6bcce9336a843ebf9bc28f"))
                .priority(99)
                .save()
                .register(object : DownloadListener("")
                          {
                              override fun onFinish(t: File?, progress: Progress?)
                              {
                                  info.set(progress?.filePath + progress?.fileName + "\n下载完成")
                                  pro.set(100)
                                  installVisib.set(true)
                                  file = t
                              }

                              override fun onRemove(progress: Progress?)
                              {
                              }

                              override fun onProgress(progress: Progress?)
                              {
                                  progress?.let {
                                      info.set("下载进度 - " + it.fraction)
                                      pro.set((it.fraction * 100.00).roundToInt())
                                  }
                              }

                              override fun onError(progress: Progress?)
                              {
                                  RxToast.error("onError")
                                  info.set("下载失败")
                              }

                              override fun onStart(progress: Progress?)
                              {
                              }
                          })
                .start()
    })
    var installClick = BindingCommand<Void>(BindingAction {
        installEvent.value = file
    })
}