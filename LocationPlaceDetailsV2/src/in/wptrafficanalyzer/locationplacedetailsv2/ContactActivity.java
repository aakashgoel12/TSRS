package in.wptrafficanalyzer.locationplacedetailsv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactActivity extends ListActivity {
	String lat,lng,email;
ListView listView1;
SessionManager session;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_contact);
		Intent i = getIntent();
		lat = i.getStringExtra("lat");
		lng = i.getStringExtra("lng");
		session=new SessionManager(getApplicationContext());
		Log.e("For contact",lat+" "+lng);
	//	listView1 = (ListView) findViewById(R.id.listView1);
		new GetUserTask().execute();
		 HashMap<String,String> user= session.getUserDetails();
			 email= user.get(SessionManager.KEY_EMAIL);
	}
	
	
	public class GetUserTask extends AsyncTask<Void,Void,Void>{
		String result;
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				
				 try{
				       //  String password = (String)arg0[1];
				  		 String link = "http://www.cse.iitd.ac.in/lbs/getusers.php?lat="+lat+"&lng="+lng;
				         
				        // URL url = new URL(link);
				         HttpClient client = new DefaultHttpClient();
				         HttpGet request = new HttpGet();
				         request.setURI(new URI(link));
				         Log.e("I am","in");
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
				            if(result.isEmpty() || result.equals(""))
				            	Log.e("empty", "empty");
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
				String users[]=result.split(",");
				int len=users.length;
				Log.e("users",result);
				  List<String> your_array_list = new ArrayList<String>();
				  for(int i=0;i<len;i++)
				  {
			         your_array_list.add(users[i]);
			         Log.e(String.valueOf(i),users[i]);
				  }
				  ArrayAdapter<String> adapter = new ArrayAdapter<String>(ContactActivity.this,R.layout.list_item, your_array_list);  
		        ContactActivity.this.setListAdapter(adapter);
		        ListView lv=getListView();
		        lv.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
					String receiver=((TextView)view).getText().toString();
					Intent i=new Intent(ContactActivity.this,DecideActivity.class);
					i.putExtra("receiver",receiver);
					i.putExtra("email",email);
					startActivity(i);
					}
		        	
		        });
			}

		}
}
