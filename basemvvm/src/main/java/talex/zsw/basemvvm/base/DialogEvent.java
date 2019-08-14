package talex.zsw.basemvvm.base;

import java.io.Serializable;

import talex.zsw.basecore.view.dialog.sweetalertdialog.SweetAlertDialog;

/**
 * 作用：SweetAlertDialog弹窗事件
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DialogEvent implements Serializable
{
	private int type;
	private String title;
	private String content;
	private String confirmText;
	private String cancelText;
	private SweetAlertDialog.OnSweetClickListener confirmListener;
	private SweetAlertDialog.OnSweetClickListener cancelListener;

	public DialogEvent(String title)
	{
		this.type = SweetAlertDialog.NORMAL_TYPE;
		this.title = title;
	}

	public DialogEvent(String title, String confirmText, SweetAlertDialog.OnSweetClickListener confirmListener)
	{
		this.type = SweetAlertDialog.NORMAL_TYPE;
		this.title = title;
		this.confirmText = confirmText;
		this.confirmListener = confirmListener;
	}

	public DialogEvent(int type, String title)
	{
		this.type = type;
		this.title = title;
	}

	public DialogEvent(int type, String title, String confirmText, SweetAlertDialog.OnSweetClickListener confirmListener)
	{
		this.type = type;
		this.title = title;
		this.confirmText = confirmText;
		this.confirmListener = confirmListener;
	}

	public DialogEvent(int type, String title, String content)
	{
		this.type = type;
		this.title = title;
		this.content = content;
	}

	public DialogEvent(int type, String title, String content, String confirmText, SweetAlertDialog.OnSweetClickListener confirmListener)
	{
		this.type = type;
		this.title = title;
		this.content = content;
		this.confirmText = confirmText;
		this.confirmListener = confirmListener;
	}

	public DialogEvent(int type, String title, String content, String confirmText, String cancelText, SweetAlertDialog.OnSweetClickListener confirmListener, SweetAlertDialog.OnSweetClickListener cancelListener)
	{
		this.type = type;
		this.title = title;
		this.content = content;
		this.confirmText = confirmText;
		this.cancelText = cancelText;
		this.confirmListener = confirmListener;
		this.cancelListener = cancelListener;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getConfirmText()
	{
		return confirmText;
	}

	public void setConfirmText(String confirmText)
	{
		this.confirmText = confirmText;
	}

	public String getCancelText()
	{
		return cancelText;
	}

	public void setCancelText(String cancelText)
	{
		this.cancelText = cancelText;
	}

	public SweetAlertDialog.OnSweetClickListener getConfirmListener()
	{
		return confirmListener;
	}

	public void setConfirmListener(SweetAlertDialog.OnSweetClickListener confirmListener)
	{
		this.confirmListener = confirmListener;
	}

	public SweetAlertDialog.OnSweetClickListener getCancelListener()
	{
		return cancelListener;
	}

	public void setCancelListener(SweetAlertDialog.OnSweetClickListener cancelListener)
	{
		this.cancelListener = cancelListener;
	}
}
