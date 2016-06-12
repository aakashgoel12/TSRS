package in.wptrafficanalyzer.locationplacedetailsv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class RouteActivity extends Activity {
	String lats,lngs,latd,lngd;
	@JavascriptInterface
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route);
		Intent i=getIntent();
		lats=i.getStringExtra("latsource");
		lngs=i.getStringExtra("lngsource");
		latd=i.getStringExtra("lat");
		lngd=i.getStringExtra("lng");
		WebView myWebView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		myWebView.setWebChromeClient(new WebChromeClient());
		//String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
		 //myWebView.getSettings().setUserAgentString(newUA);
		 myWebView.addJavascriptInterface(new JavaScriptInterface(), "jsinterface");
		myWebView.loadUrl("file:///android_asset/www/index3.html");
		//myWebView.loadUrl("https://www.bing.com/maps/#Y3A9MjguNjIxNjgyfjc3LjIzNjU4MyZsdmw9MTQmc3R5PXImcnRwPWFkci5+YWRyLiZtb2RlPUQmcnRvcD0wfjB+MH4=");
	}
	 final class JavaScriptInterface {
		    JavaScriptInterface () { }
			@JavascriptInterface
		    public String arr1() {
		      return lats + ", " + lngs;
		    }
			
			@JavascriptInterface
		    public String arr2() {
			      return latd + ", " + lngd;
			    }
			
		  }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
