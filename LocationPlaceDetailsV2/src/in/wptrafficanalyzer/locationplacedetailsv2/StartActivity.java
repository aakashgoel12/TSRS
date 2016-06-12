package in.wptrafficanalyzer.locationplacedetailsv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity{
	 SessionManager session;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
	                
		Button register=(Button) findViewById(R.id.register);
		Button login=(Button) findViewById(R.id.login);
        session = new SessionManager(getApplicationContext());                
     //   session.logoutUser();
		if(session.isLoggedIn())
		{
			//Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();
			Intent i=new Intent(StartActivity.this,MidActivity.class);
			startActivity(i);
			finish();
		}
		else
		{
			//Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_SHORT).show();
		}
		register.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(StartActivity.this,RegisterActivity.class);
				startActivity(i);
				finish();
			}
			
		});
		login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(StartActivity.this,LoginActivity.class);
				startActivity(i);
				finish();
			}
			
		});
		
	}

}
