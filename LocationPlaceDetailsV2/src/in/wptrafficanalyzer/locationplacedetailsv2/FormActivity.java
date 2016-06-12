package in.wptrafficanalyzer.locationplacedetailsv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
public class FormActivity extends Activity
{
	SessionManager session;
	RadioButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
	Button submit;
	String lat,lng;
	double traffic, weather, safe;
	double rating;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query);
		Intent i = getIntent();
		session=new SessionManager(getApplicationContext());
		lat = i.getStringExtra("lat");
		lng = i.getStringExtra("lng");
		b1 =(RadioButton) findViewById(R.id.radioButton1);
		b2=(RadioButton) findViewById(R.id.radioButton2);
		b3=(RadioButton) findViewById(R.id.radioButton3);
		b4=(RadioButton) findViewById(R.id.radioButton4);
		b5=(RadioButton) findViewById(R.id.radioButton5);
		b6=(RadioButton) findViewById(R.id.radioButton6);
		b7=(RadioButton) findViewById(R.id.radioButton7);
		b8=(RadioButton) findViewById(R.id.radioButton8);
		b9=(RadioButton) findViewById(R.id.radioButton9);
		submit=(Button) findViewById(R.id.submit);

		submit.setOnClickListener( new OnClickListener(){
			@Override
			public void onClick(View v) {
				new RatingTask().execute();

			}
		});

	}
	
	public void onClicked1(View view) {
	    // Is the button now checked?
		 Random randomno = new Random();
		 
		   // check next float value  
		
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radioButton1:
	            if (checked)
	                traffic=randomno.nextFloat()+randomno.nextInt(3)+6;
	                break;
	        case R.id.radioButton2:
	            if (checked)
	                traffic=randomno.nextFloat()+randomno.nextInt(3)+3;
	            break;
	        case R.id.radioButton3:
	            if (checked)
	                traffic=randomno.nextFloat()+randomno.nextInt(3);
	            break;
	    }
	}
	public void onClicked2(View view) {
	    // Is the button now checked?
		Random randomno = new Random();
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radioButton4:
	            if (checked)
	                weather=randomno.nextFloat()+randomno.nextInt(3)+6;
	                break;
	        case R.id.radioButton5:
	            if (checked)
	            	weather=randomno.nextFloat()+randomno.nextInt(3)+3;
	            break;
	        case R.id.radioButton6:
	            if (checked)
	            	weather=randomno.nextFloat()+randomno.nextInt(3);
	            break;
	    }
	}
	public void onClicked3(View view) {
	    // Is the button now checked?
		Random randomno = new Random();
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radioButton7:
	            if (checked)
	                safe=randomno.nextFloat()+randomno.nextInt(3)+6;
	                break;
	        case R.id.radioButton8:
	            if (checked)
	                safe=randomno.nextFloat()+randomno.nextInt(3)+3;
	            break;
	        case R.id.radioButton9:
	            if (checked)
	                safe=randomno.nextFloat()+randomno.nextInt(3);
	            break;
	    }
	}

	
	public class RatingTask extends AsyncTask<Void,Void,Void>{
		String result;
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				
				 try{
				     
				         String link = "http://www.cse.iitd.ac.in/lbs/shell.php?security="+safe+"&traffic="+traffic+"&weather="+weather;
				         
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
			protected void onPostExecute(Void para)
			{
	         //   Toast.makeText(getApplicationContext(), "hey" +result, Toast.LENGTH_LONG).show();
				
				Log.e("on post",result);
				rating=Double.valueOf(result);
				new FormTask().execute();
				finish();
			}

		}
	
public class FormTask extends AsyncTask<Void,Void,Void>{
String result;
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		 try{
		       //  String password = (String)arg0[1];
		  		Log.e("Or in receiving.....",lat+" "+lng);

		         String link = "http://www.cse.iitd.ac.in/lbs/form.php?lat="+lat+"&lng="+lng+"&traffic="+traffic+"&weather="+weather+"&safe="+safe+"&rating="+rating;
		         
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
	protected void onPostExecute(Void para)
	{
	}

}

}