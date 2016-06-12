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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class LoginActivity extends Activity {
     
    // Email, password edittext
    EditText txtUsername, txtPassword;
     String username,password,result;
    // login button
    Button btnLogin;
    Button register;
     
    // Alert Dialog Manager
   // AlertDialogManager alert = new AlertDialogManager();
     
    // Session Manager Class
    SessionManager session;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); 
         
        // Session Manager
        session = new SessionManager(getApplicationContext());                
         
        // Email, Password input text
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword); 
         
      //  Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
         
         
        // Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);
        register = (Button) findViewById(R.id.registerr);
        register.setVisibility(View.GONE);
         
        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                 username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                 
                // Check if username, password is filled                
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    new Login().execute();
                           
                }else{
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    Toast.makeText(getApplicationContext(), "Login failed..Please enter username and password", Toast.LENGTH_SHORT).show();
                }
                 
            }
        });
    }
    class Login extends AsyncTask<Void,Void,Void>
	{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			 try{
			       
			         String link = "http://www.cse.iitd.ac.in/lbs/loginn.php?email="+username+"&password="+password;
			         
			        // URL url = new URL(link);
			         HttpClient client = new DefaultHttpClient();
			         HttpGet request = new HttpGet();
			         request.setURI(new URI(link));
			         Log.e("Before","execution");
			      
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
			 if(!result.equals("0")){
                 
                 session.createLoginSession("Name", username);
                 Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                 Intent i= new Intent(LoginActivity.this,MidActivity.class);
                 startActivity(i);
                 finish();

                  
             }else{
                 // username / password doesn't match
                 Toast.makeText(getApplicationContext(), "Login failed..Username/Password is incorrect", Toast.LENGTH_SHORT).show();
                 register.setVisibility(View.VISIBLE);
                 register.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
						startActivity(i);
						finish();
					}
                	 
                 });

             }       
		}
		
	}
}
