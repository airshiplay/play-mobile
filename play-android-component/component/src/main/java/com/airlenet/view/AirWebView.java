package com.airlenet.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author lig
 * 
 */
public class AirWebView extends WebView implements DownloadListener ,View.OnClickListener{
	private Context mContext;
	// private ProgressDialog mProgressDialog;
	private UrlHandler urlHandler;
	private ProgressBar mProgressBar;
	private TextView mTextView;
	private String mUrl;
	public AirWebView(Context context) {
		super(context);
		init();
	}

	public AirWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AirWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	private void init() {
		mContext = getContext();
		// mProgressDialog = new ProgressDialog(mContext,
		// com.certusnet.icity.browser.R.style.CustomProgressDialog);
		// mProgressDialog.setMessage("加载中...");
		mProgressBar = new ProgressBar(mContext);
		mTextView = new TextView(mContext);
		mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
		// mProgressBar.setIndeterminateDrawable(getResources().getDrawable(
		// R.drawable.image_progress));
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 240, 400);
		params.width = 60;
		params.height = 60;
		this.addView(mProgressBar, params);
		LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 240, 400);
		this.addView(mTextView,textParams);
		this.setWebViewClient(new MyWebViewClient());
		this.setWebChromeClient(new MyWebChromeClient());
		// set WebView
		WebSettings webSettings = this.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDefaultTextEncodingName("UTF-8");
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		// forbid gestures scale
		webSettings.setBuiltInZoomControls(false);
		setBackgroundColor(0);
		webSettings.setUseWideViewPort(false);
		webSettings.setLoadWithOverviewMode(true);
		mTextView.setOnClickListener(this);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		int w = (r - l - mProgressBar.getMeasuredWidth()) / 2;
		int h = (b - t - mProgressBar.getMeasuredHeight()) / 2;
		mProgressBar.layout(w, h, w + mProgressBar.getMeasuredWidth(), h + mProgressBar.getMeasuredHeight());

		int tw = (r - l - mTextView.getMeasuredWidth()) / 2;
		int th = (b - t - mTextView.getMeasuredHeight()) / 2;
		mTextView.layout(tw, th, tw + mTextView.getMeasuredWidth(), th + mTextView.getMeasuredHeight());

	}

	private class MyWebChromeClient extends WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
		}
		@Override
		public void onReceivedTitle(WebView view, String title) {
			super.onReceivedTitle(view, title);
		}
	}

	private class MyWebViewClient extends WebViewClient {
		boolean loadError;
		@Override
		public void onLoadResource(WebView view, String url) {
			System.out.println("onLoadResource" + url);
			super.onLoadResource(view, url);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			System.out.println("shouldOverrideUrlLoading" + url);
			return urlHandler == null ? false : urlHandler.shouldOverrideUrlLoading(view, url);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			// mProgressDialog.show();
			System.out.println("onPageStarted" + url);
			mProgressBar.setVisibility(View.VISIBLE);
			mTextView.setVisibility(View.INVISIBLE);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			// mProgressDialog.dismiss();
			System.out.println("onPageFinished" + url);
			mProgressBar.setVisibility(View.GONE);
			if(url.contains("about:blank")){
				mTextView.setVisibility(View.VISIBLE);
				mTextView.setText("加载失败,请点击重试");
			}else{
				mUrl=url;
			}
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			// view.loadData("<html><body><h1>" + description +
			// "</h1></body></html>", "text/html;charset=UTF-8", "UTF-8");
			view.loadDataWithBaseURL(null, "", "text/html;charset=UTF-8", "UTF-8", null);
		}
	}

	@Override
	public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
			long contentLength) {
		Uri uri = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		mContext.startActivity(intent);
	}

	public void setUrlHandler(UrlHandler urlHandler) {
		this.urlHandler = urlHandler;
	}

	@Override
	public void loadUrl(String url) {
		super.loadUrl(url);
		mUrl=url;
	}

	@Override
	public void onClick(View v) {
		this.loadUrl(mUrl);
	}
}
