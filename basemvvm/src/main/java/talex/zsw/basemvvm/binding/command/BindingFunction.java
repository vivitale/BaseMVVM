package talex.zsw.basemvvm.binding.command;

/**
 * 作用：命令是否执行的判断
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface BindingFunction<T>
{
	T call();
}
