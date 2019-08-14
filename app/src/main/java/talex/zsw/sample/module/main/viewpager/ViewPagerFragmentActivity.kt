package talex.zsw.sample.module.main.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import talex.zsw.sample.module.main.tab.TabBarFragment
import java.util.*

/**
 * 作用：ViewPager + Fragment 的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class ViewPagerFragmentActivity : BasePagerActivity()
{
    override fun pagerFragment(): List<Fragment>
    {
        val list = ArrayList<Fragment>()
        list.add(getFragment(1))
        list.add(getFragment(2))
        list.add(getFragment(3))
        list.add(getFragment(4))
        return list
    }

    override fun pagerTitleString(): List<String>
    {
        val list = ArrayList<String>()
        list.add("page1")
        list.add("page2")
        list.add("page3")
        list.add("page4")
        return list
    }

    private fun getFragment(pos:Int):Fragment
    {
        val fragment = TabBarFragment()
        val bundle = Bundle()
        bundle.putInt("data", pos)
        fragment.arguments = bundle
        return fragment
    }
}
