package talex.zsw.sample.module.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import talex.zsw.basecore.util.WebViewTool;
import talex.zsw.basecore.view.other.TitleBar;
import talex.zsw.basecore.view.textview.RichText;
import talex.zsw.sample.R;

/**
 * 作用：通用的Web页面来展示富文本等
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class WebActivity extends AppCompatActivity
{
	@BindView(R.id.mTitleBar) TitleBar mTitleBar;
	@BindView(R.id.mProgressBar) ProgressBar mProgressBar;
	@BindView(R.id.mWebView) WebView mWebView;
	@BindView(R.id.mRichText) RichText mRichText;

	private String title, data;

	@Override protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		title = getIntent().getStringExtra("title");
		data = getIntent().getStringExtra("data");

		setContentView(R.layout.activity_web);
		ButterKnife.bind(this);

		mTitleBar.setTitle(title);
		WebViewTool.setWebData(data, mWebView, mRichText, mProgressBar);
	}
}
