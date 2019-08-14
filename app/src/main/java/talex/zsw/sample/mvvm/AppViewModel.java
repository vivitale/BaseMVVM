package talex.zsw.sample.mvvm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.NonNull;

import com.lzy.okgo.model.Response;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import talex.zsw.basecore.util.JsonTool;
import talex.zsw.basecore.util.LogTool;
import talex.zsw.basecore.view.dialog.sweetalertdialog.SweetAlertDialog;
import talex.zsw.basemvvm.base.BaseViewModel;
import talex.zsw.basemvvm.base.DialogEvent;
import talex.zsw.basemvvm.util.RxUtils;
import talex.zsw.sample.entitys.BaseResponse;
import talex.zsw.sample.mvvm.http.HttpDto;

/**
 * 作用：App层的ViewModel,处理AppRepository数据仓库的部分返回
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AppViewModel extends BaseViewModel<AppRepository>
{
	public AppViewModel(@NonNull Application application)
	{
		super(application);
	}

	public AppViewModel(@NonNull Application application, AppRepository model)
	{
		super(application, model);
	}

	@SuppressWarnings({"unchecked", "ResultOfMethodCallIgnored"}) @SuppressLint("CheckResult")
	public void getData(final HttpDto httpDto)
	{
		model
			.getData(httpDto)
			.compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
			.compose(RxUtils.schedulersTransformer())
			.doOnSubscribe(new Consumer<Disposable>()
			{
				@Override public void accept(Disposable disposable) throws Exception
				{
					if(!httpDto.isSilence())
					{
						showDialog();
					}
				}
			})
			.subscribe(new Observer<Response<String>>()
			{
				@Override public void onSubscribe(Disposable d)
				{
					addSubscribe(d);
				}

				@Override public void onNext(Response<String> stringResponse)
				{
					try
					{
						String s = stringResponse.body();
						LogTool.json(s);
						BaseResponse baseResponse = JsonTool.getObject(s, BaseResponse.class);
						if(baseResponse == null)
						{
							baseResponse = new BaseResponse();
						}
						baseResponse.setHttp(httpDto);
						bindResponse(baseResponse);
					}
					catch(Exception e)
					{// 处理解析失败的问题
						e.printStackTrace();
						requestError("解析错误", httpDto);
					}
				}

				@Override public void onError(Throwable e)
				{// 处理网络请求失败等情况
					e.printStackTrace();
					requestError(e.getLocalizedMessage(), httpDto);
				}

				@Override public void onComplete()
				{
				}
			});
	}

	public void bindResponse(BaseResponse response)
	{

	}

	public void requestError(String msg, final HttpDto httpDto)
	{
		disLoading();
		LogTool.e(msg);
		String title = "";
		String info = "";
		if(msg.contains("Timeout"))
		{
			title = "连接服务器超时";
			info = "数据加载失败，请重试！";
		}
		else if(msg.contains("504"))
		{
			title = "无网络服务";
			info = "请检查网络后重试！";
		}
		else if(msg.contains("Failed to connect"))
		{
			title = "服务器异常";
			info = "请稍后重试！";
		}
		else if(msg.contains("网络请求失败"))
		{
			title = "网络请求失败";
			info = "请稍后重试！";
		}
		else
		{
			title = "对不起，访问出错了";
			info = "请稍后重试！";
		}
		if(httpDto.isShowError())
		{
			if(httpDto.isTryAgain())
			{
				if(httpDto.isFinish())
				{
					showDialog(new DialogEvent(SweetAlertDialog.ERROR_TYPE, title, info, "重试", "取消", new SweetAlertDialog.OnSweetClickListener()
					{
						@Override public void onClick(SweetAlertDialog sweetAlertDialog)
						{
							getData(httpDto);
						}
					}, new SweetAlertDialog.OnSweetClickListener()
					{
						@Override public void onClick(SweetAlertDialog sweetAlertDialog)
						{
							dismissDialog();
							finish();
						}
					}));
				}
				else
				{
					showDialog(new DialogEvent(SweetAlertDialog.ERROR_TYPE, title, info, "重试", "取消", new SweetAlertDialog.OnSweetClickListener()
					{
						@Override public void onClick(SweetAlertDialog sweetAlertDialog)
						{
							getData(httpDto);
						}
					}, null));
				}
			}
			else if(httpDto.isFinish())
			{
				showDialog(new DialogEvent(SweetAlertDialog.ERROR_TYPE, title, info, "确定", null, new SweetAlertDialog.OnSweetClickListener()
				{
					@Override public void onClick(SweetAlertDialog sweetAlertDialog)
					{
						dismissDialog();
						finish();
					}
				}, null));
			}
			else
			{
				showDialog(new DialogEvent(SweetAlertDialog.ERROR_TYPE, title, info));
			}
		}
		else
		{
			if(httpDto.isTryAgain())
			{
				if(httpDto.isFinish())
				{
					showDialog(new DialogEvent(SweetAlertDialog.ERROR_TYPE, title, info, "重试", "取消", new SweetAlertDialog.OnSweetClickListener()
					{
						@Override public void onClick(SweetAlertDialog sweetAlertDialog)
						{
							getData(httpDto);
						}
					}, new SweetAlertDialog.OnSweetClickListener()
					{
						@Override public void onClick(SweetAlertDialog sweetAlertDialog)
						{
							dismissDialog();
							finish();
						}
					}));
				}
				else
				{
					showDialog(new DialogEvent(SweetAlertDialog.ERROR_TYPE, title, info, "重试", "取消", new SweetAlertDialog.OnSweetClickListener()
					{
						@Override public void onClick(SweetAlertDialog sweetAlertDialog)
						{
							getData(httpDto);
						}
					}, null));
				}
			}
			else if(httpDto.isFinish())
			{
				showDialog(new DialogEvent(SweetAlertDialog.ERROR_TYPE, title, info, "确定", null, new SweetAlertDialog.OnSweetClickListener()
				{
					@Override public void onClick(SweetAlertDialog sweetAlertDialog)
					{
						dismissDialog();
						finish();
					}
				}, null));
			}
		}
	}
}
