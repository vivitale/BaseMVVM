package talex.zsw.basemvvm.binding.viewadapter.spinner;

import talex.zsw.basecore.util.DimenTool;
import talex.zsw.basemvvm.R;

/**
 * 作用：NiceSpinner的Padding设置
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NiceSpinnerPaddings
{
	private int top;
	private int left;
	private int right;
	private int bottom;

	public NiceSpinnerPaddings()
	{
		int defaultPadding = DimenTool.getPxById(R.dimen.dp_12);
		int halfPadding = DimenTool.getPxById(R.dimen.dp_6);
		this.left = defaultPadding;
		this.top = halfPadding;
		this.right = defaultPadding;
		this.bottom = halfPadding;
	}

	public NiceSpinnerPaddings(int def)
	{
		this.top = def;
		this.bottom = def;
		this.left = def;
		this.right = def;
	}

	public NiceSpinnerPaddings(int top, int bottom)
	{
		this.top = top;
		this.bottom = bottom;
		int defaultPadding = DimenTool.getPxById(R.dimen.dp_12);
		this.left = defaultPadding;
		this.right = defaultPadding;
	}

	public NiceSpinnerPaddings(int left, int top,  int right, int bottom)
	{
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	public int getTop()
	{
		return top;
	}

	public void setTop(int top)
	{
		this.top = top;
	}

	public int getLeft()
	{
		return left;
	}

	public void setLeft(int left)
	{
		this.left = left;
	}

	public int getRight()
	{
		return right;
	}

	public void setRight(int right)
	{
		this.right = right;
	}

	public int getBottom()
	{
		return bottom;
	}

	public void setBottom(int bottom)
	{
		this.bottom = bottom;
	}
}
