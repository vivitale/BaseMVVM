package talex.zsw.basemvvm.binding.viewadapter.webview;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.webkit.WebView;

import talex.zsw.basecore.view.webview.MyWebView;

/**
 * 作用：WebView 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewAdapter
{
	/**
	 * @param html 网页地址
	 */
	@BindingAdapter({"render"}) public static void loadHtml(WebView webView, final String html)
	{
		if(!TextUtils.isEmpty(html))
		{
			webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
		}
	}

	@BindingAdapter({"render"}) public static void loadHtml2(MyWebView webView, final String html)
	{
		if(!TextUtils.isEmpty(html))
		{
			webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
		}
	}
}
