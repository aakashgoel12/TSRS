package in.wptrafficanalyzer.locationplacedetailsv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

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
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
Button b1;
EditText name,email,pass,cpass;
String n,em,p,cp,result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		 b1=(Button) findViewById(R.id.button1);
		 name=(EditText) findViewById(R.id.editText1);
		email=(EditText) findViewById(R.id.editText2);
		pass=(EditText) findViewById(R.id.editText3);
		cpass=(EditText) findViewById(R.id.editText4);
		
		b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 n=name.getText().toString();
				em=email.getText().toString();
				p=pass.getText().toString();
				cp=cpass.getText().toString();
				if(n.isEmpty() | em.isEmpty() | p.isEmpty() | cp.isEmpty())
				{
					Toast.makeText(getApplicationContext(), "Please enter all the fiels", Toast.LENGTH_SHORT).show();
				}
				else if(!p.equals(cp))
				{
					Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Log.e("in","else");
					new Register().execute();
					
				}
			}
			
		});
		
	}
	
	class Register extends AsyncTask<Void,Void,Void>
	{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			 try{
			       
			         String link = "http://www.cse.iitd.ac.in/lbs/registration.php?name="+n+"&email="+em+"&password="+p;
			         
			        // URL url = new URL(link);
			         HttpClient client = new DefaultHttpClient();
			         HttpGet request = new HttpGet();
			         request.setURI(new URI(link));
			         Log.e("Before","execution");
			         Log.e(n,em);
			         Log.e(p,cp);
			         HttpResponse response = client.execute(request);
			         Log.e("is someting","happening");
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
			         }
			         
			         catch(Exception e){
			             new String("Exception: " + e.getMessage());
			         }
			return null;
		}
		protected void onPostExecute(Void para)
		{
			Intent i= new Intent(RegisterActivity.this,LoginActivity.class);
			startActivity(i);
			finish();
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
