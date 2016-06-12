package in.wptrafficanalyzer.locationplacedetailsv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends FragmentActivity implements LocationListener{
	
	GoogleMap mGoogleMap;	
	Spinner mSprPlaceType;	
	SessionManager session;
	String[] mPlaceType=null;
	String[] mPlaceTypeName=null;
	String[] places=null;
	Spinner spin;
	ArrayAdapter<String> adapter;
	Map <String, String> pl = new HashMap<String, String>();
	Button btnSub;
	double mLatitude=0;
	double mLongitude=0;
	// LocationManager lm;
	    LocationListener locationListener;

	CheckinManager checkin;
	Menu menu;
	HashMap<String, String> mMarkerPlaceLink = new HashMap<String, String>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		session= new SessionManager(getApplicationContext());
		checkin= new CheckinManager(getApplicationContext());

		//lm = (LocationManager)
	      //          getSystemService(Context.LOCATION_SERVICE);
		places= getResources().getStringArray(R.array.place_type);
		List<String> weekForecast = new ArrayList<String>(Arrays.asList(places));
		spin=(Spinner)findViewById(R.id.place_type); 
		spin.setVisibility(View.GONE);
		adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item,weekForecast); 
		spin.setAdapter(adapter);
	        locationListener = new MyLocationListener();

		// Array of place types
		mPlaceType = getResources().getStringArray(R.array.place_type);
		
		// Array of place type names
		mPlaceTypeName = getResources().getStringArray(R.array.place_type_name);
		
		// Creating an array adapter with an array of Place types
		// to populate the spinner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mPlaceTypeName);
		
		// Getting reference to the Spinner 
		mSprPlaceType = (Spinner) findViewById(R.id.spr_place_type);
		
		// Setting adapter on Spinner to set place types
		mSprPlaceType.setAdapter(adapter);
		
		Button btnFind;
		
		// Getting reference to Find Button
		btnFind = ( Button ) findViewById(R.id.btn_find);
		btnSub=(Button)findViewById(R.id.sub);
		btnSub.setVisibility(View.GONE);
		
		// Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

        	int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else { // Google Play Services are available
        	
	    	// Getting reference to the SupportMapFragment
	    	SupportMapFragment fragment = ( SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
	    			
	    	// Getting Google Map
	    	mGoogleMap = fragment.getMap();
	    			
	    	// Enabling MyLocation in Google Map
	    	mGoogleMap.setMyLocationEnabled(true);
	    	
	    	
	    	
	    	// Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
          //  List<String> locationProviders = locationManager.getAllProviders();
           // for (String provider : locationProviders) {
            //    Log.d("LocationProviders", provider);
            //}

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            //---OR---
           // criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(Criteria.POWER_HIGH);

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);
            Log.d("BestProviders", provider);
            provider="network";
            // Getting Current Location From GPS
            Location location = locationManager.getLastKnownLocation(provider);
            if(location!=null)
            Toast.makeText(getApplicationContext(), "I am at " + location.getLatitude() + "and " + location.getLongitude(), Toast.LENGTH_LONG).show();

            if(location!=null){
                    onLocationChanged(location);
            }

            locationManager.requestLocationUpdates(provider, 20000, 0, this);
              
            if(location!=null)
                Toast.makeText(getApplicationContext(), "I am at " + location.getLatitude() + "and " + location.getLongitude(), Toast.LENGTH_LONG).show();

            
            mGoogleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
				
				@Override
				public void onInfoWindowClick(Marker arg0) {
					Intent intent = new Intent(getBaseContext(), PlaceDetailsActivity.class);
					String reference = mMarkerPlaceLink.get(arg0.getId());
					intent.putExtra("reference", reference);
					intent.putExtra("lat", mLatitude);
					intent.putExtra("lng", mLongitude);
					Log.e("reference", reference);
					// Starting the Place Details Activity
					startActivity(intent);
				}
			});
            
            
	    	
	    	// Setting click event lister for the find button
			btnFind.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {	
					
					
					int selectedPosition = mSprPlaceType.getSelectedItemPosition();
					String type = mPlaceType[selectedPosition];
										
					
					StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
					sb.append("location="+mLatitude+","+mLongitude);
					sb.append("&radius=1000");
					sb.append("&types="+type);
					sb.append("&sensor=true");
					sb.append("&key=AIzaSyAMEAFugw1tKqWtcB1KBPULRkFiDFF8K-c");
					
					
					// Creating a new non-ui thread task to download Google place json data 
			        PlacesTask placesTask = new PlacesTask();		        			        
			        
					// Invokes the "doInBackground()" method of the class PlaceTask
			        placesTask.execute(sb.toString());
					
					
				}
			});
	btnSub.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {	
					Log.e("list_refernce",pl.get(spin.getSelectedItem()));
					Intent intent = new Intent(getBaseContext(), PlaceDetailsActivity.class);
					String reference = pl.get(spin.getSelectedItem());
					intent.putExtra("reference", reference);
					intent.putExtra("lat", mLatitude);
					intent.putExtra("lng", mLongitude);
				//	Log.e("reference", reference);
					// Starting the Place Details Activity
					startActivity(intent);
				}
			});
	    	
        }		
 		
	}
	
	/** A method to download json data from url */
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

	
	/** A class, to download Google Places */
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
	
	/** A class to parse the Google Places in JSON format */
	private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

		JSONObject jObject;
		
		// Invoked by execute() method of this object
		@Override
		protected List<HashMap<String,String>> doInBackground(String... jsonData) {
		
			List<HashMap<String, String>> places = null;			
			PlaceJSONParser placeJsonParser = new PlaceJSONParser();
        
	        try{
	        	jObject = new JSONObject(jsonData[0]);
	        	
	            /** Getting the parsed data as a List construct */
	            places = placeJsonParser.parse(jObject);
	            
	        }catch(Exception e){
	                Log.d("Exception",e.toString());
	        }
	        return places;
		}
		
		// Executed after the complete execution of doInBackground() method
		@Override
		protected void onPostExecute(List<HashMap<String,String>> list){			
			
			// Clears all the existing markers 
			mGoogleMap.clear();
			adapter.clear();
			
			spin.setVisibility(View.VISIBLE);
			for(int i=0;i<list.size();i++){
			
				// Creating a marker
	            MarkerOptions markerOptions = new MarkerOptions();
	            
	            // Getting a place from the places list
	            HashMap<String, String> hmPlace = list.get(i);
	
	            // Getting latitude of the place
	            double lat = Double.parseDouble(hmPlace.get("lat"));	            
	            
	            // Getting longitude of the place
	            double lng = Double.parseDouble(hmPlace.get("lng"));
	            
	            // Getting name
	            String name = hmPlace.get("place_name");
	            Log.e("Place",name);
	            adapter.add((i+1)+"_" +name);
	            pl.put((i+1)+"_" +name, hmPlace.get("reference"));
	            // Getting vicinity
	            String vicinity = hmPlace.get("vicinity");
	            
	            LatLng latLng = new LatLng(lat, lng);
	            
	            // Setting the position for the marker
	            markerOptions.position(latLng);
	
	            // Setting the title for the marker. 
	            //This will be displayed on taping the marker
	            markerOptions.title(name + " : " + vicinity);	            
	
	            // Placing a marker on the touched position
	            Marker m = mGoogleMap.addMarker(markerOptions);	            

	            // Linking Marker id and place reference
	            mMarkerPlaceLink.put(m.getId(), hmPlace.get("reference"));	            
	         //  Log.e("hmPlace_reference",hmPlace.get("reference"));
	         //  Log.e("m.getID()",m.getId());
            
			}		
			spin.setVisibility(View.VISIBLE);
			btnSub.setVisibility(View.VISIBLE);

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

	@Override
	public void onLocationChanged(Location location) {
		mLatitude = location.getLatitude();
		mLongitude = location.getLongitude();
		LatLng latLng = new LatLng(mLatitude, mLongitude);
		
		mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
		
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

}