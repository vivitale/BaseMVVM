package talex.zsw.basemvvm.binding.viewadapter.image;


import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import talex.zsw.basecore.util.LogTool;
import talex.zsw.basecore.util.RegTool;
import talex.zsw.basecore.util.glide.GlideTool;

/**
 * 作用：ImageView 的自定义属性
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public final class ViewAdapter
{
	/**
	 * @param url 需要加载的图片的url地址
	 * @param placeholderRes 占位图的resId  需要在xml中  <import type="包名.R" />
	 */
	@BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
	public static void setImageUri(ImageView imageView, String url, int placeholderRes)
	{
		LogTool.nv("placeholderRes = "+placeholderRes);
		if(RegTool.isNotEmpty(url))
		{
			//使用Glide框架加载图片
			if(placeholderRes != 0)
			{
				Glide
					.with(imageView.getContext())
					.load(url)
					.apply(new RequestOptions().placeholder(placeholderRes))
					.into(imageView);
			}
			else
			{
				GlideTool.loadImg(imageView, url);
			}
		}
		else
		{
			if(placeholderRes != 0)
			{
				imageView.setImageResource(placeholderRes);
			}
		}
	}
}

