package talex.zsw.basemvvm.base;

/**
 * 作用：View基类
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface IBaseView
{
    /**
     * 初始化界面传递参数
     */
    void initArgs();
    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化界面观察者的监听
     */
    void initViewObservable();
}
