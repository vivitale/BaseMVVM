package talex.zsw.sample.mvvm;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.lzy.okgo.model.Response;

import io.reactivex.Observable;
import talex.zsw.basemvvm.base.BaseModel;
import talex.zsw.sample.mvvm.http.HttpData;
import talex.zsw.sample.mvvm.http.HttpDto;

/**
 * 作用：app的统一数据仓库(可以自行添加网络请求,数据库的相关操作,本示例使用OkGo)
 *
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AppRepository extends BaseModel implements HttpData
{
	private volatile static AppRepository INSTANCE = null;
	private final HttpData mHttpData;

	private AppRepository(@NonNull HttpData httpDataSource)
	{
		this.mHttpData = httpDataSource;
	}

	public static AppRepository getInstance(HttpData httpDataSource)
	{
		if(INSTANCE == null)
		{
			synchronized(AppRepository.class)
			{
				if(INSTANCE == null)
				{
					INSTANCE = new AppRepository(httpDataSource);
				}
			}
		}
		return INSTANCE;
	}

	@VisibleForTesting public static void destroyInstance()
	{
		INSTANCE = null;
	}


	/**
	 * 用OkGo实现http请求
	 */
	@Override public Observable<Response<String>> getData(HttpDto http)
	{
		return mHttpData.getData(http);
	}
}
