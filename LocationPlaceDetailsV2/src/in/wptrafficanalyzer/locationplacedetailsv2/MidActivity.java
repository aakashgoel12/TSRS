package in.wptrafficanalyzer.locationplacedetailsv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MidActivity extends Activity{
	SessionManager session;
	CheckinManager checkin;
    private Menu menu;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mid);
		Button view=(Button) findViewById(R.id.view);
        session = new SessionManager(getApplicationContext());                
        checkin = new CheckinManager(getApplicationContext());     
      new CheckForTask().execute();
       // checkin.checkoutUser();
		Button submit=(Button) findViewById(R.id.submit);
		Button nots=(Button) findViewById(R.id.notifications);

		view.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MidActivity.this,MainActivity.class);
				startActivity(i);
			//	finish();
			}
			
		});
		submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MidActivity.this,Checkin.class);
				startActivity(i);
			//	finish();

			}
			
		});
		nots.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String email;
				 HashMap<String,String> user= session.getUserDetails();
				 email= user.get(SessionManager.KEY_EMAIL);
				Intent i=new Intent(MidActivity.this,LoginActivitySinch.class);
				i.putExtra("email",email);
				i.putExtra("receiver", "");
				i.putExtra("request", "true");
				startActivity(i);
			//	finish();
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        updateMenuTitles();
		return true;
	}
	private void updateMenuTitles() {
        MenuItem bedMenuItem = menu.findItem(R.id.action_checkin);
        if (checkin.isCheckedIn()) {
            bedMenuItem.setTitle("Checkout");
        } else {
            bedMenuItem.setTitle("Checkin");
        }
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
		if (id == R.id.action_login) {
			session.logoutUser();
			finish();
			return true;
		}
		if (id == R.id.action_checkin) {
			checkin.checkoutUser();
			updateMenuTitles();
			//finish();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	public class CheckForTask extends AsyncTask<Void,Void,Void>{
		String result;
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				
				 try{
				       //  String password = (String)arg0[1];
					 HashMap<String,String> user=session.getUserDetails();
			        	String email=user.get(SessionManager.KEY_EMAIL);
				         String link = "http://www.cse.iitd.ac.in/lbs/checkfor.php?email="+email;
				         
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
				String array[]=result.split(",");
				if(array.length>1)
				{
					checkin.createCheckinSession(array[0], array[1]);
				}
			}

		}
}
