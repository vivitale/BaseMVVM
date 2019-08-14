package talex.zsw.sample.mvvm;

import talex.zsw.sample.mvvm.http.HttpData;
import talex.zsw.sample.mvvm.http.HttpDataImpl;

/**
 * 作用：全局注入AppRepository数据仓库
 *
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class Injection
{
	public static AppRepository provideDemoRepository()
	{
		// OkGo网络数据源
		HttpData httpDataSource = HttpDataImpl.getInstance();

		return AppRepository.getInstance(httpDataSource);
	}
}
