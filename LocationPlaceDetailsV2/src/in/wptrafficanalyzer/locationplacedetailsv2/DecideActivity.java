package in.wptrafficanalyzer.locationplacedetailsv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DecideActivity extends Activity{
	String receiver;
	String email;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decide);
		Button msg=(Button) findViewById(R.id.message);
		Button call=(Button) findViewById(R.id.call);
		Intent i=getIntent();
		receiver=i.getStringExtra("receiver");
		 email=i.getStringExtra("email");
		 
		
		msg.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(DecideActivity.this,LoginActivitySinch.class);
				i.putExtra("receiver",receiver);
				i.putExtra("email",email);
				i.putExtra("request", "false");
				startActivity(i);
			}
			
		});
		call.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(DecideActivity.this,LoginActivityCall.class);
				i.putExtra("receiver",receiver);
				i.putExtra("email",email);
				startActivity(i);
			}
			
		});
	}

}
