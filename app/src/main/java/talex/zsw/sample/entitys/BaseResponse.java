package talex.zsw.sample.entitys;

import java.io.Serializable;

import talex.zsw.sample.mvvm.http.HttpDto;

/**
 * 作用：基础返回类型
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseResponse implements Serializable
{
	private String message;
	private String flag;
	private boolean success;
	private Object data;
	private Object result;
	private HttpDto http;

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public HttpDto getHttp()
	{
		return http;
	}

	public void setHttp(HttpDto http)
	{
		this.http = http;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public Object getResult()
	{
		return result;
	}

	public void setResult(Object result)
	{
		this.result = result;
	}
}
