package talex.zsw.basemvvm.base;

/**
 * 作用：Model基类
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface IModel
{
	/**
	 * ViewModel销毁时清除Model，与ViewModel共消亡。Model层同样不能持有长生命周期对象
	 */
	void onCleared();
}
