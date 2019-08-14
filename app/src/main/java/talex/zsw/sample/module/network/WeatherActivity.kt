package talex.zsw.sample.module.network

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import talex.zsw.basemvvm.base.BaseActivity
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.ActivityWeatherBinding
import talex.zsw.sample.mvvm.AppViewModelFactory

/**
 * 作用：网络请求测试
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class WeatherActivity : BaseActivity<ActivityWeatherBinding, WeatherViewModel>()
{
    override fun initContentView(savedInstanceState: Bundle?): Int
    {
        return R.layout.activity_weather
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }

    override fun initViewModel(): WeatherViewModel
    {
            return ViewModelProviders.of(this, AppViewModelFactory.getInstance())
                    .get(WeatherViewModel::class.java)
    }
}