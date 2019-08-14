package talex.zsw.sample.mvvm.http;

import com.lzy.okgo.model.Response;

import io.reactivex.Observable;

/**
 * 作用：OkGo网络请求数据返回
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface HttpData
{
	Observable<Response<String>> getData(HttpDto http);
}
