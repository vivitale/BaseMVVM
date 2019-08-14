package talex.zsw.sample.module.personal

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import talex.zsw.basemvvm.base.BaseActivity
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.ActivityLoginBinding
import talex.zsw.sample.mvvm.AppViewModelFactory

/**
 * 作用：登录
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>()
{
    override fun initContentView(savedInstanceState: Bundle?): Int
    {
        return R.layout.activity_login
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }

    override fun initViewModel(): LoginViewModel
    {
        return ViewModelProviders.of(this, AppViewModelFactory.getInstance())
                .get(LoginViewModel::class.java)
    }

    override fun initViewObservable()
    {
        //监听ViewModel中pSwitchObservable的变化, 当ViewModel中执行【uc.pSwitchObservable.set(!uc.pSwitchObservable.get());】时会回调该方法
        viewModel.uco.pSwitchEvent.observe(this, Observer {
            if (viewModel.uco.pSwitchEvent.value!!)
            {
                //密码可见
                binding.ivSwichPasswrod.setImageResource(R.mipmap.show_psw)
                binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else
            {
                //密码不可见
                binding.ivSwichPasswrod.setImageResource(R.mipmap.show_psw_press)
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        })
    }
}
