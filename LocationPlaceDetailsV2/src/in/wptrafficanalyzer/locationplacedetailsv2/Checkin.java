package in.wptrafficanalyzer.locationplacedetailsv2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;





import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Checkin extends Activity implements LocationListener{
Menu menu;
RadioButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
	Button pic,audio,form,submit;

	double lat,lng;
	//Spinner spin;
	CheckinManager checkin;
	LinearLayout ll;
	//ArrayAdapter<String> adapter;
	double mLatitude=0;
	double mLongitude=0;
	 LocationManager lm;
	    LocationListener locationListener;
SessionManager session;
	    private RecordButton mRecordButton = null;
	    private MediaRecorder mRecorder = null;
		private static final String TAG = MainActivity.class.getSimpleName();
		
		 
	    // Camera activity request codes
	    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
	    private static final int AUDIO_RECORD_AUDIO_REQUEST_CODE = 200;
	    public static final int MEDIA_TYPE_IMAGE = 1;
	    public static final int MEDIA_TYPE_AUDIO = 2;
	    private Uri fileUri; // file url to store image/video
	    
	    private Button btnCapturePicture;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);		
	/*	places= getResources().getStringArray(R.array.place_type);
		List<String> weekForecast = new ArrayList<String>(Arrays.asList(places));
		//weekForecast.setVisibility(View.GONE); */
        btnCapturePicture = (Button) findViewById(R.id.btnCapturePicture);
        btnCapturePicture.setVisibility(View.GONE);  
        checkin=new CheckinManager(getApplicationContext());
    	ll = new LinearLayout(this);
		   mRecordButton = new RecordButton(this);
        pic= (Button) findViewById(R.id.Picture);
        audio= (Button) findViewById(R.id.Audio);
        session=new SessionManager(getApplicationContext());
       
	/*	spin=(Spinner)findViewById(R.id.place_type); 
		spin.setVisibility(View.GONE);
		adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item,weekForecast); 
		spin.setAdapter(adapter);
		check=(Button) findViewById(R.id.checkin);  */
		form=(Button) findViewById(R.id.form);
		lm = (LocationManager)
	                getSystemService(Context.LOCATION_SERVICE);
	        locationListener = new MyLocationListener();

		// Array of place types
	/*	mPlaceType = getResources().getStringArray(R.array.place_type);
		
		// Array of place type names
		mPlaceTypeName = getResources().getStringArray(R.array.place_type_name);
		
		// Creating an array adapter with an array of Place types
		// to populate the spinner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mPlaceTypeName);
		
		// Getting reference to the Spinner 
		mSprPlaceType = (Spinner) findViewById(R.id.spr_place_type);
		check.setVisibility(View.GONE);
Button btnFind;
		
		// Getting reference to Find Button
		btnFind = ( Button ) findViewById(R.id.btn_find);
		
		if(!checkin.isCheckedIn())
		{
			//mSprPlaceType.setVisibility(View.GONE);
			form.setVisibility(View.GONE);
			 pic.setVisibility(View.GONE);
		        audio.setVisibility(View.GONE);
		}
		if(checkin.isCheckedIn())
		{
			mSprPlaceType.setVisibility(View.GONE);
			btnFind.setVisibility(View.GONE);
			HashMap<String,String> user=checkin.getLocationDetails();
			lat=Double.parseDouble(user.get(CheckinManager.KEY_LAT));
			lng=Double.parseDouble(user.get(CheckinManager.KEY_LNG));
		}
		// Setting adapter on Spinner to set place types
		mSprPlaceType.setAdapter(adapter);
		*/
		
	    	// Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
 
          Boolean  isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
           Boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
           Location location = null;
            if (!isGPSEnabled && !isNetworkEnabled) {
                // no GPS Provider and no network provider is enabled
            } 
            else 
            {   // Either GPS provider or network provider is enabled

                // First get location from Network Provider
                if (isNetworkEnabled) 
                {
                    locationManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 2000, 0, this);
                    if (locationManager != null) 
                    {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) 
                        {
                            lat = location.getLatitude();
                            lng = location.getLongitude();
                          //  this.canGetLocation = true;
                        }
                    }
                }// End of IF network enabled

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) 
                {
                    locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 2000,0, this);
                    if (locationManager != null) 
                    {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) 
                        {
                            lat = location.getLatitude();
                            lng = location.getLongitude();
                          //  this.canGetLocation = true;
                        }
                    }

                }// End of if GPS Enabled
            }
            
            if(location!=null)
            Toast.makeText(getApplicationContext(), "I am at " + location.getLatitude() + "and " + location.getLongitude(), Toast.LENGTH_LONG).show();

			new CheckinTask().execute();

	/*		btnFind.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {	
					
					
					int selectedPosition = mSprPlaceType.getSelectedItemPosition();
					String type = mPlaceType[selectedPosition];
										
					Log.e(String.valueOf(mLatitude), String.valueOf(mLongitude));
					StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
					sb.append("location="+mLatitude+","+mLongitude);
					sb.append("&radius=5000");
					sb.append("&types="+type);
					sb.append("&sensor=true");
					sb.append("&key=AIzaSyAMEAFugw1tKqWtcB1KBPULRkFiDFF8K-c");
					
					
					// Creating a new non-ui thread task to download Google place json data 
			        PlacesTask placesTask = new PlacesTask();		        			        
			        
					// Invokes the "doInBackground()" method of the class PlaceTask
			        placesTask.execute(sb.toString());
					
					
				}
			});
	    	*/
			pic.setOnClickListener(new OnClickListener()
			{
			

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
					Editor editor = sharedpreferences.edit();
					editor.putString("key", String.valueOf(lat));
					editor.putString("key2", String.valueOf(lng));					
					editor.commit();
					captureImage();
				}
			});
			form.setOnClickListener(new OnClickListener()
			{
			
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						//Log.e(traffic+weather,safe);
						Intent i= new Intent(Checkin.this,FormActivity.class);
						i.putExtra("lat", String.valueOf(lat));
						i.putExtra("lng", String.valueOf(lng));
						startActivity(i);
					
				}
			});
			
			audio.setOnClickListener(new OnClickListener()
			{
			

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			        fileUri = getOutputMediaFileUri(MEDIA_TYPE_AUDIO);
				        ll.addView(mRecordButton,
				            new LinearLayout.LayoutParams(
				                ViewGroup.LayoutParams.WRAP_CONTENT,
				                ViewGroup.LayoutParams.WRAP_CONTENT,
				                0));
				   
				        setContentView(ll);
				}
			});
 		
	}
	
	/*
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
                URL url = new URL(strUrl);                
                

                // Creating an http connection to communicate with url 
                urlConnection = (HttpURLConnection) url.openConnection();                

                // Connecting to url 
                urlConnection.connect();                

                // Reading data from url 
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb  = new StringBuffer();

                String line = "";
                while( ( line = br.readLine())  != null){
                        sb.append(line);
                }

                data = sb.toString();

                br.close();

        }catch(Exception e){
                Log.d("Exception while downloading url", e.toString());
        }finally{
                iStream.close();
                urlConnection.disconnect();
        }

        return data;
    }         

	
	
	private class PlacesTask extends AsyncTask<String, Integer, String>{

		String data = null;
		
		// Invoked by execute() method of this object
		@Override
		protected String doInBackground(String... url) {
			try{
				data = downloadUrl(url[0]);
			}catch(Exception e){
				 Log.d("Background Task",e.toString());
			}
			return data;
		}
		
		// Executed after the complete execution of doInBackground() method
		@Override
		protected void onPostExecute(String result){			
			ParserTask parserTask = new ParserTask();
			
			// Start parsing the Google places in JSON format
			// Invokes the "doInBackground()" method of the class ParseTask
			parserTask.execute(result);
		}
		
	}
	
	
	private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

		JSONObject jObject;
		
		// Invoked by execute() method of this object
		@Override
		protected List<HashMap<String,String>> doInBackground(String... jsonData) {
		
			List<HashMap<String, String>> places = null;			
			PlaceJSONParser placeJsonParser = new PlaceJSONParser();
        
	        try{
	        	jObject = new JSONObject(jsonData[0]);
	        	
	            places = placeJsonParser.parse(jObject);
	            
	        }catch(Exception e){
	                Log.d("Exception",e.toString());
	        }
	        return places;
		}
		
		// Executed after the complete execution of doInBackground() method
		@Override
		protected void onPostExecute(final List<HashMap<String,String>> list){			
			
		
			adapter.clear();
			for(int i=0;i<list.size();i++){
			
	            HashMap<String, String> hmPlace = list.get(i);
	
	            String name = hmPlace.get("place_name");
	            Log.e("Place",name);
	           
	            adapter.add(name);
	         
			}
			spin.setVisibility(View.VISIBLE);
			check.setVisibility(View.VISIBLE);
			check.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {	
							int Position = spin.getSelectedItemPosition();
							 HashMap<String, String> Place = list.get(Position);
					             lat = Double.parseDouble(Place.get("lat"));	      
					             lng = Double.parseDouble(Place.get("lng"));
							Log.e("LatLong", String.valueOf(lat)+"  "+String.valueOf(lng));
							new CheckinTask().execute();
							pic.setVisibility(View.VISIBLE);
							audio.setVisibility(View.VISIBLE);
							form.setVisibility(View.VISIBLE);

							//  captureImage();
						}
					});
			
		}
		
	}
	*/
	  @Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	 
	        // save file url in bundle as it will be null on screen orientation
	        // changes
	        outState.putParcelable("file_uri", fileUri);
	    }
	 
	    @Override
	    protected void onRestoreInstanceState(Bundle savedInstanceState) {
	        super.onRestoreInstanceState(savedInstanceState);
	 
	        // get the file url
	        fileUri = savedInstanceState.getParcelable("file_uri");
	    }
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        // if the result is capturing Image
	        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
	            if (resultCode == RESULT_OK) {
	                
	            	// successfully captured the image
	                // launching upload activity
	            	launchUploadActivity(true);
	            	
	            	
	            } else if (resultCode == RESULT_CANCELED) {
	                
	            	// user cancelled Image capture
	                Toast.makeText(getApplicationContext(),
	                        "User cancelled image capture", Toast.LENGTH_SHORT)
	                        .show();
	            
	            } else {
	                // failed to capture image
	                Toast.makeText(getApplicationContext(),
	                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
	                        .show();
	            }
	        
	        }
	        else if(requestCode== AUDIO_RECORD_AUDIO_REQUEST_CODE)
	        {
	        		if (resultCode == RESULT_OK) {
	                
	            	// successfully captured the image
	                // launching upload activity
	            	launchUploadActivity(false);
	            	
	            	
	            } else if (resultCode == RESULT_CANCELED) {
	                
	            	// user cancelled Image capture
	                Toast.makeText(getApplicationContext(),
	                        "User cancelled audio record", Toast.LENGTH_SHORT)
	                        .show();
	            
	            } else {
	                // failed to capture image
	                Toast.makeText(getApplicationContext(),
	                        "Sorry! Failed to record audio", Toast.LENGTH_SHORT)
	                        .show();
	            }
	        }
	    }
	    
	    private void launchUploadActivity(boolean isImage){
	    	String latt="0.0",lngg="0.0";
	    	SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE); 
		       
	          latt = prefs.getString("key", "0.0");//"No name defined" is the default value.
	          lngg = prefs.getString("key2", "0.0"); //0 is the default value.
	        
	    	Intent i = new Intent(Checkin.this, UploadActivity.class);
	        i.putExtra("filePath", fileUri.getPath());
	        Log.e("filePath", fileUri.getPath());
	        i.putExtra("isImage", isImage);
	        
	      //  Log.e("Latlong34",String.valueOf(lat)+" "+String.valueOf(lng));
	        i.putExtra("lat", latt);
	        i.putExtra("lng", lngg);
	        startActivity(i);
	    }
	     
	    
	   private void captureImage() {
	        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	 
	        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
	 
	        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	 
	        // start the image capture Intent
	        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
	    }
	  
	   private void onRecord(boolean start) {
	        if (start) {
	            startRecording();
	        } else {
	            stopRecording();
	        }
	    }

	   private void startRecording()
	   {
	    mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(fileUri.getPath());
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e("Audio Test", "prepare() failed");
        }

        mRecorder.start();
	   }
	   
	   private void stopRecording() {
	        mRecorder.stop();
	        mRecorder.release();
	        mRecorder = null;
        	launchUploadActivity(false);

	    }
	   
	   class RecordButton extends Button {
	        boolean mStartRecording = true;

	        OnClickListener clicker = new OnClickListener() {
	            public void onClick(View v) {
	                onRecord(mStartRecording);
	                if (mStartRecording) {
	                    setText("Stop recording");
	                } else {
	                    setText("Start recording");
	                }
	                mStartRecording = !mStartRecording;
	            }
	        };
	        public RecordButton(Context ctx) {
	            super(ctx);
	            setText("Start recording");
	            setOnClickListener(clicker);
	        }

	      
	    }
	   
	   
	   public Uri getOutputMediaFileUri(int type) {
	        return Uri.fromFile(getOutputMediaFile(type));
	    }
	 
	    /**
	     * returning image / video
	     */
	    private static File getOutputMediaFile(int type) {
	 
	        // External sdcard location
	        File mediaStorageDir = new File(
	                Environment
	                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
	                Config.IMAGE_DIRECTORY_NAME);
	 
	        // Create the storage directory if it does not exist
	        if (!mediaStorageDir.exists()) {
	            if (!mediaStorageDir.mkdirs()) {
	                Log.d(TAG, "Oops! Failed create "
	                        + Config.IMAGE_DIRECTORY_NAME + " directory");
	                return null;
	            }
	        }
	 
	        // Create a media file name
	        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
	                Locale.getDefault()).format(new Date());
	        File mediaFile;
	        if (type == MEDIA_TYPE_IMAGE) {
	            mediaFile = new File(mediaStorageDir.getPath() + File.separator
	                    + "IMG_" + timeStamp + ".jpg");
	        }
	        else if(type == MEDIA_TYPE_AUDIO)
	        {
	        	mediaFile = new File(mediaStorageDir.getPath() + File.separator
	                    + "AUD_" + timeStamp + ".mp3");
	        }
	        
	        else {
	            return null;
	        }
	 
	        return mediaFile;
	    }



	@Override
	public void onLocationChanged(Location location) {
	
		lat=location.getLatitude();
		lng=location.getLongitude();
		new CheckinTask().execute();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub		
	}
	
	 private class MyLocationListener implements LocationListener
	    {
	        @Override
	        public void onLocationChanged(Location loc) {
	            if (loc != null) {
	                Toast.makeText(getBaseContext(),
	                        "Location changed : Lat: " + loc.getLatitude() +
	                        " Lng: " + loc.getLongitude(),
	                        Toast.LENGTH_SHORT).show();
	            }
	        }
	        @Override
	        public void onProviderDisabled(String provider) {
	            Toast.makeText(getBaseContext(),
	                    "Provider: " + provider + " disabled",
	                    Toast.LENGTH_SHORT).show();
	        }
	        @Override
	        public void onProviderEnabled(String provider) {
	            Toast.makeText(getBaseContext(),
	                    "Provider: " + provider + " enabled",
	                    Toast.LENGTH_SHORT).show();
	        }
	        @Override
	        public void onStatusChanged(String provider, int status,
	                Bundle extras) {
	            String statusString = "";
	            switch (status) {
	                case android.location.LocationProvider.AVAILABLE:
	                    statusString = "available";
	                case android.location.LocationProvider.OUT_OF_SERVICE:
	                    statusString = "out of service";
	                case
	                    android.location.LocationProvider.TEMPORARILY_UNAVAILABLE:
	                    statusString = "temporarily unavailable";
	            }
	            Toast.makeText(getBaseContext(),
	                    provider + " " + statusString,
	                    Toast.LENGTH_SHORT).show();
	        }
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
		public class CheckinTask extends AsyncTask<Void,Void,Void>{
			String result;
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					
					 try{
					       //  String password = (String)arg0[1];
					  		//Log.e("Or in receiving.....",lat+" "+lng);
						 HashMap<String,String> user= session.getUserDetails();
						String email= user.get(SessionManager.KEY_EMAIL);
						
						checkin.createCheckinSession(String.valueOf(lat), String.valueOf(lng));
					         String link = "http://www.cse.iitd.ac.in/lbs/checkin.php?lat="+lat+"&lng="+lng+"&email="+email;
					         
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

