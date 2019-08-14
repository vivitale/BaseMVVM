package talex.zsw.basemvvm.binding.command;

/**
 * 作用：带泛型参数的命令
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface BindingConsumer<T>
{
	void call(T t);
}
