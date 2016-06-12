package in.wptrafficanalyzer.locationplacedetailsv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ImgActivity extends Activity {
String result,lat,lng,imag;
SessionManager session;
WebView myWebView;
	@JavascriptInterface
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_img);
		session=new SessionManager(getApplicationContext());
		Intent i = getIntent();
		lat = i.getStringExtra("lat");
		lng = i.getStringExtra("lng");
		imag=i.getStringExtra("img");
		Log.e("Or in receiving",lat+" "+lng);
		new Getpath(this).execute();
		myWebView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		myWebView.setWebChromeClient(new WebChromeClient());
		//myWebView.loadUrl("https://www.bing.com/maps/#Y3A9MjguNjIxNjgyfjc3LjIzNjU4MyZsdmw9MTQmc3R5PXImcnRwPWFkci5+YWRyLiZtb2RlPUQmcnRvcD0wfjB+MH4=");
	}
		 
	 public class Getpath  extends AsyncTask<Void,Void,Void>{
		 //  private TextView statusField,roleField;
		   private Context context;
		 //  private int byGetOrPost = 0; 
		   
		   //flag 0 means get and 1 means post.(By default it is get.)
		   public Getpath(Context context) {
		      this.context = context;
		    
		   }
		   
		   protected void onPreExecute(){

		   }
		   
		   @Override
		   protected Void doInBackground(Void... params) {
		      //means by Get Method
		      
		      try{
		       //  String password = (String)arg0[1];
		  		Log.e("Or in receiving.....",lat+" "+lng);

		         String link = "http://www.cse.iitd.ac.in/lbs/getpath.php?lat="+lat+"&lng="+lng+"&img="+imag;
		         
		        // URL url = new URL(link);
		         HttpClient client = new DefaultHttpClient();
		         HttpGet request = new HttpGet();
		         request.setURI(new URI(link));
		         HttpResponse response = client.execute(request);
		         BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		         Log.e("Query","Executed");
		           StringBuffer sb = new StringBuffer("");
		           String line="";
		           
		           while ((line = in.readLine()) != null) {
		        	   Log.e("response","came");
		              sb.append(line);
		              break;
		            }
		            in.close();
		            result=sb.toString();
		            Log.e("Coming","here");
		            Log.e("check2",result);
		            return null;
		         }
		         
		         catch(Exception e){
		             new String("Exception: " + e.getMessage());
		             return null;
		         }
		     
		   }
		   @JavascriptInterface
		   protected void onPostExecute(Void params){
			   Log.e("coming","coming");
				 myWebView.addJavascriptInterface(new JavaScriptInterface(), "jsinterface");
				 Log.e("loading","loading");
			   if(imag.equals("true"))
			   {
			   
				myWebView.loadUrl("file:///android_asset/www/index.html");
				Log.e("loaded","loaded");
			   }
			   else
			   {
					myWebView.loadUrl("file:///android_asset/www/index2.html");

				   Log.e("Audio","waiting");
				   
			   }
		   }
		   final class JavaScriptInterface {
			    JavaScriptInterface () { }
				@JavascriptInterface
			    public String pth() {
					Log.e("check","http://www.cse.iitd.ac.in/lbs/"+result);
			      return result;
			      
			    }			

		   }
		}

}
