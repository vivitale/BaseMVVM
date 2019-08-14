package talex.zsw.sample.entitys.response;

import talex.zsw.basemvvm.binding.viewadapter.spinner.IKeyAndValue;

/**
 * 作用：Spinner 测试数据
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SpinnerVo implements IKeyAndValue
{
	private String key;
	private String value;

	public SpinnerVo(String key, String value)
	{
		this.key = key;
		this.value = value;
	}

	@Override public String getKey()
	{
		return key;
	}

	@Override public String getValue()
	{
		return value;
	}
}