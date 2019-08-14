package talex.zsw.sample.mvvm.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.DeleteRequest;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.HeadRequest;
import com.lzy.okgo.request.OptionsRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.PutRequest;
import com.lzy.okrx2.adapter.ObservableResponse;

import java.util.Map;

import io.reactivex.Observable;
import talex.zsw.basecore.util.RegTool;

/**
 * 作用：OkGo网络请求实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class HttpDataImpl implements HttpData
{
	private volatile static HttpDataImpl INSTANCE = null;

	public static HttpDataImpl getInstance()
	{
		if(INSTANCE == null)
		{
			synchronized(HttpDataImpl.class)
			{
				if(INSTANCE == null)
				{
					INSTANCE = new HttpDataImpl();
				}
			}
		}
		return INSTANCE;
	}

	@Override public Observable<Response<String>> getData(HttpDto http)
	{
		http.print();
		Observable<Response<String>> observable;
		if(http.getType() == HttpDto.GET)
		{
			GetRequest<String> getRequest = OkGo.<String>get(http.getFullUrl())
				.converter(new StringConvert())
				.params(http.getParams());
			if(http.getHeads() != null)
			{
				for(Map.Entry<String, String> entry : http.getHeads().entrySet())
				{
					getRequest.headers(entry.getKey(), entry.getValue());
				}
			}
			getRequest.tag(http.getTag());
			getRequest.cacheMode(http.getCacheMode());

			observable = getRequest.adapt(new ObservableResponse<String>());
		}
		else if(http.getType() == HttpDto.POST)
		{
			PostRequest<String> postRequest = OkGo.<String>post(http.getFullUrl())
				.converter(new StringConvert())
				.params(http.getParams());
			if(http.getHeads() != null)
			{
				for(Map.Entry<String, String> entry : http.getHeads().entrySet())
				{
					postRequest.headers(entry.getKey(), entry.getValue());
				}
			}
			if(!RegTool.isNullString(http.getBodyStr()))
			{
				postRequest.upString(http.getBodyStr());
			}
			if(!RegTool.isNullString(http.getBodyJson()))
			{
				postRequest.upJson(http.getBodyJson());
			}
			postRequest.tag(http.getTag());
			postRequest.cacheMode(http.getCacheMode());

			observable = postRequest.adapt(new ObservableResponse<String>());
		}
		else if(http.getType() == HttpDto.PUT)
		{
			PutRequest<String> putRequest = OkGo.<String>put(http.getFullUrl())
				.converter(new StringConvert())
				.params(http.getParams());
			if(http.getHeads() != null)
			{
				for(Map.Entry<String, String> entry : http.getHeads().entrySet())
				{
					putRequest.headers(entry.getKey(), entry.getValue());
				}
			}
			if(!RegTool.isNullString(http.getBodyStr()))
			{
				putRequest.upString(http.getBodyStr());
			}
			if(!RegTool.isNullString(http.getBodyJson()))
			{
				putRequest.upJson(http.getBodyJson());
			}
			putRequest.tag(http.getTag());
			putRequest.cacheMode(http.getCacheMode());

			observable = putRequest.adapt(new ObservableResponse<String>());
		}
		else if(http.getType() == HttpDto.DELETE)
		{
			DeleteRequest<String> deleteRequest = OkGo.<String>delete(http.getFullUrl())
				.converter(new StringConvert())
				.params(http.getParams());
			if(http.getHeads() != null)
			{
				for(Map.Entry<String, String> entry : http.getHeads().entrySet())
				{
					deleteRequest.headers(entry.getKey(), entry.getValue());
				}
			}
			if(!RegTool.isNullString(http.getBodyStr()))
			{
				deleteRequest.upString(http.getBodyStr());
			}
			if(!RegTool.isNullString(http.getBodyJson()))
			{
				deleteRequest.upJson(http.getBodyJson());
			}
			deleteRequest.tag(http.getTag());
			deleteRequest.cacheMode(http.getCacheMode());

			observable = deleteRequest.adapt(new ObservableResponse<String>());
		}
		else if(http.getType() == HttpDto.HEAD)
		{
			HeadRequest<String> headRequest = OkGo.<String>head(http.getFullUrl())
				.converter(new StringConvert())
				.params(http.getParams());
			if(http.getHeads() != null)
			{
				for(Map.Entry<String, String> entry : http.getHeads().entrySet())
				{
					headRequest.headers(entry.getKey(), entry.getValue());
				}
			}
			headRequest.tag(http.getTag());
			headRequest.cacheMode(http.getCacheMode());

			observable = headRequest.adapt(new ObservableResponse<String>());
		}
		else
		{
			OptionsRequest<String> optionsRequest = OkGo.<String>options(http.getFullUrl())
				.converter(new StringConvert())
				.params(http.getParams());
			if(http.getHeads() != null)
			{
				for(Map.Entry<String, String> entry : http.getHeads().entrySet())
				{
					optionsRequest.headers(entry.getKey(), entry.getValue());
				}
			}
			if(!RegTool.isNullString(http.getBodyStr()))
			{
				optionsRequest.upString(http.getBodyStr());
			}
			if(!RegTool.isNullString(http.getBodyJson()))
			{
				optionsRequest.upJson(http.getBodyJson());
			}
			optionsRequest.tag(http.getTag());
			optionsRequest.cacheMode(http.getCacheMode());

			observable = optionsRequest.adapt(new ObservableResponse<String>());
		}
		return observable;
	}
}
