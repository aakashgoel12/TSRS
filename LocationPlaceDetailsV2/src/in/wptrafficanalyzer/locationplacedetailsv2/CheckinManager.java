package in.wptrafficanalyzer.locationplacedetailsv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;
 
public class CheckinManager {
    // Shared Preferences
    SharedPreferences pref;
     SessionManager session;
    // Editor for Shared preferences
    Editor editor;
     
    // Context
    Context _context;
     
    // Shared pref mode
    int PRIVATE_MODE = 0;
     
    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHive";
     
    // All Shared Preferences Keys
    private static final String IS_CHECKIN = "IsCheckedIn";
     
    // User name (make variable public to access from outside)
    public static final String KEY_LAT = "lat";
     
    // Email address (make variable public to access from outside)
    public static final String KEY_LNG = "lng";
     
    // Constructor
    public CheckinManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        session=new SessionManager(context);
    }
     
    /**
     * Create login session
     * */
    public void createCheckinSession(String lat, String lng){
        // Storing login value as TRUE
        editor.putBoolean(IS_CHECKIN, true);
        editor.putString(KEY_LAT, lat);
        editor.putString(KEY_LNG, lng);
        Log.e("Manager",lat+" "+lng);
        editor.commit();
    }   
  
    public HashMap<String, String> getLocationDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_LAT, pref.getString(KEY_LAT, null));
         
        // user email id
        user.put(KEY_LNG, pref.getString(KEY_LNG, null));
         
        // return user
        return user;
    }
     
    /**
     * Clear session details
     * */
    public void checkoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        new CheckoutTask().execute();
    }
    public void checkoutwithLogout(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isCheckedIn(){
        return pref.getBoolean(IS_CHECKIN, false);
    }
    
	public class CheckoutTask extends AsyncTask<Void,Void,Void>{
		String result;
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				
				 try{
				       //  String password = (String)arg0[1];
				  		//Log.e("Or in receiving.....",lat+" "+lng);
					 HashMap<String,String> user= session.getUserDetails();
					String email= user.get(SessionManager.KEY_EMAIL);
				         String link = "http://www.cse.iitd.ac.in/lbs/checkout.php?email="+email;
				         
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
