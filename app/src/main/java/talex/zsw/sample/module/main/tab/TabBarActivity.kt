package talex.zsw.sample.module.main.tab

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener
import talex.zsw.basemvvm.base.BaseActivity
import talex.zsw.basemvvm.base.BaseViewModel
import talex.zsw.sample.BR
import talex.zsw.sample.R
import talex.zsw.sample.databinding.ActivityTabBarBinding

/**
 * 作用：底部导航栏的通用实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class TabBarActivity : BaseActivity<ActivityTabBarBinding, BaseViewModel<*>>()
{
    override fun initContentView(savedInstanceState: Bundle?): Int
    {
        return R.layout.activity_tab_bar
    }

    override fun initVariableId(): Int
    {
        return BR.viewModel
    }

    override fun initData()
    {
        initBottomTab()

        changeToMain(false)
    }

    private fun initBottomTab()
    {
        val navigationController =
                binding.pagerBottomTab.material()
                        .addItem(R.mipmap.yingyong, "应用")
                        .addItem(R.mipmap.huanzhe, "工作")
                        .addItem(R.mipmap.xiaoxi_select, "消息")
                        .addItem(R.mipmap.wode_select, "我的")
                        .setDefaultColor(ContextCompat.getColor(this, R.color.text_cyan))
                        .build()
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(object : OnTabItemSelectedListener
                                                        {
                                                            override fun onSelected(index: Int, old: Int)
                                                            {
                                                                var fm = supportFragmentManager
                                                                var ft = fm.beginTransaction()
                                                                var fragment1: Fragment? = fm.findFragmentByTag(fragmentTag1)
                                                                var fragment2: Fragment? = fm.findFragmentByTag(fragmentTag2)
                                                                var fragment4: Fragment? = fm.findFragmentByTag(fragmentTag3)
                                                                var fragment5: Fragment? = fm.findFragmentByTag(fragmentTag4)
                                                                if (fragment1 != null)
                                                                {
                                                                    ft.hide(fragment1)
                                                                }
                                                                if (fragment2 != null)
                                                                {
                                                                    ft.hide(fragment2)
                                                                }
                                                                if (fragment4 != null)
                                                                {
                                                                    ft.hide(fragment4)
                                                                }
                                                                if (fragment5 != null)
                                                                {
                                                                    ft.hide(fragment5)
                                                                }
                                                                startFragment(index)
                                                            }

                                                            override fun onRepeat(index: Int)
                                                            {
                                                            }
                                                        })
    }

    // ======================== Fragment操作 ========================
    private val fragmentTag1 = "home"
    private val fragmentTag2 = "files"
    private val fragmentTag3 = "message"
    private val fragmentTag4 = "mine"
    private var selectId = -1

    override fun onRestoreInstanceState(savedInstanceState: Bundle)
    {
        super.onRestoreInstanceState(savedInstanceState)
        var list: MutableList<String>? = ArrayList()
        list!!.add(fragmentTag1)
        list.add(fragmentTag2)
        list.add(fragmentTag3)
        list.add(fragmentTag4)
        for (i in list.indices)
        {
            val fm = supportFragmentManager
            val fragment = fm.findFragmentByTag(list[i])
            val ft = fm.beginTransaction()
            if (fragment != null)
            {
                if (i==selectId)
                {
                    ft.hide(fragment)
                }
            }
            ft.commit()
        }
        list = null
    }

    override fun onNewIntent(intent: Intent)
    {
        super.onNewIntent(intent)
        val back = intent.getBooleanExtra("back", false)
        if (back)
        {
            selectId = 0
            changeToMain(true)
        }
    }

    public fun startFragment(pos: Int)
    {
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        var fragment1: Fragment? = fm.findFragmentByTag(fragmentTag1)
        var fragment2: Fragment? = fm.findFragmentByTag(fragmentTag2)
        var fragment3: Fragment? = fm.findFragmentByTag(fragmentTag3)
        var fragment4: Fragment? = fm.findFragmentByTag(fragmentTag4)
        if (fragment1 != null)
        {
            ft.hide(fragment1)
        }
        if (fragment2 != null)
        {
            ft.hide(fragment2)
        }
        if (fragment3 != null)
        {
            ft.hide(fragment3)
        }
        if (fragment4 != null)
        {
            ft.hide(fragment4)
        }
        selectId = pos
        when (pos)
        {
            0 ->
            {
                if (fragment1 == null)
                {
                    fragment1 = TabBarFragment()
                    val bundle = Bundle()
                    bundle.putInt("data", 1)
                    fragment1.arguments = bundle
                    ft.add(R.id.mFrameLayout, fragment1, fragmentTag1)
                }
                else
                {
                    ft.show(fragment1)
                }
            }
            1 ->
            {
                if (fragment2 == null)
                {
                    fragment2 = TabBarFragment()
                    val bundle = Bundle()
                    bundle.putInt("data", 2)
                    fragment2.arguments = bundle
                    ft.add(R.id.mFrameLayout, fragment2, fragmentTag2)
                }
                else
                {
                    ft.show(fragment2)
                }
            }
            2 ->
            {
                if (fragment3 == null)
                {
                    fragment3 = TabBarFragment()
                    val bundle = Bundle()
                    bundle.putInt("data", 3)
                    fragment3.arguments = bundle
                    ft.add(R.id.mFrameLayout, fragment3, fragmentTag3)
                }
                else
                {
                    ft.show(fragment3)
                }
            }
            3 ->
            {
                if (fragment4 == null)
                {
                    fragment4 = TabBarFragment()
                    val bundle = Bundle()
                    bundle.putInt("data", 4)
                    fragment4.arguments = bundle
                    ft.add(R.id.mFrameLayout, fragment4, fragmentTag4)
                }
                else
                {
                    ft.show(fragment4)
                }
            }
        }
        ft.commit()
    }

    fun changeToMain(close: Boolean)
    {
        selectId = 0
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        var fragment1: Fragment? = fm.findFragmentByTag(fragmentTag1)
        val fragment2 = fm.findFragmentByTag(fragmentTag2)
        val fragment3 = fm.findFragmentByTag(fragmentTag3)
        val fragment4 = fm.findFragmentByTag(fragmentTag4)
        if (fragment2 != null)
        {
            ft.hide(fragment2)
            if (close) ft.remove(fragment2)
        }
        if (fragment3 != null)
        {
            ft.hide(fragment3)
            if (close) ft.remove(fragment3)
        }
        if (fragment4 != null)
        {
            ft.hide(fragment4)
            if (close) ft.remove(fragment4)
        }
        if (fragment1 == null)
        {
            fragment1 = TabBarFragment()
            val bundle = Bundle()
            bundle.putInt("data", 1)
            fragment1.arguments = bundle
            ft.add(R.id.mFrameLayout, fragment1, fragmentTag1)
        }
        else
        {
            ft.show(fragment1)
        }
        ft.commit()
    }
}