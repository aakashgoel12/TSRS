package in.wptrafficanalyzer.locationplacedetailsv2;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class PlaceDetailsActivity extends Activity {
	WebView mWvPlaceDetails;
	String lat,lng,name,icon,vicinity,formatted_address,formatted_phone,website,rating,international_phone_number,url;
	String result,arr[];
	double lati,lngi;
	SessionManager session;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_details);
		session=new SessionManager(getApplicationContext());
		Intent i=getIntent();
		
		// Getting reference to WebView ( wv_place_details ) of the layout activity_place_details
		mWvPlaceDetails = (WebView) findViewById(R.id.wv_place_details);
		
		mWvPlaceDetails.getSettings().setUseWideViewPort(false);
		
		// Getting place reference from the map	
		String reference = i.getStringExtra("reference");
		 lati=i.getDoubleExtra("lat", 0);
		lngi=i.getDoubleExtra("lng", 0);
		Log.e("hello", reference);
		
		StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json?");
		sb.append("reference="+reference);
		sb.append("&sensor=true");
		sb.append("&key=AIzaSyAMEAFugw1tKqWtcB1KBPULRkFiDFF8K-c");
		
		
		// Creating a new non-ui thread task to download Google place details 
        PlacesTask placesTask = new PlacesTask();		        			        
        
		// Invokes the "doInBackground()" method of the class PlaceTask
        placesTask.execute(sb.toString());	
        //arr=result.split(";");
      //  Log.e("reasult",result);
    //    Log.e("traffic",arr[0]);
     //   Log.e("weather",arr[1]);
      //  Log.e("safe",arr[2]);
        
		
	};
	
	
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

	private class GetData extends AsyncTask<Void,Void,Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			 
		      try{
		       //  String password = (String)arg0[1];
		  		Log.e("Or in receiving.....",lat+" "+lng);

		         String link = "http://www.cse.iitd.ac.in/lbs/getdata.php?lat="+lat+"&lng="+lng;
		         
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
		             Log.e("Exception", e.getMessage());
		             return null;
		         }
		}
		protected void onPostExecute(Void params)
		{
			Log.e("hey", result);
			String extra[]={"-NA-","-NA-","-NA-","-NA-"};
		
			if(result != null)
			{
				Log.e("res",result);
			String extra2[] = result.split(",");
			
			int len=extra2.length;
			Log.e("Let us see",String.valueOf(len));
			if(len>2)
			{
				
				extra[0]=extra2[0];
				extra[1]=extra2[1];
				extra[2]=extra2[2];
				extra[3]=extra2[3];
				Log.e("inside","length");
			}
			else if(len==2)
			{
				extra[0]=extra2[0];

			}
			}
			
			String mimeType = "text/html";
			String encoding = "utf-8";
			
			String data = 	"<html>"+							
							"<body><img style='float:left' src="+icon+" /><h1><center>"+name+"</center></h1>" +
							"<br style='clear:both' />" +
							"<hr  />"+
							"<p>Vicinity : " + vicinity + "</p>" +
							"<p>Location : " + lat + "," + lng + "</p>" +
							"<p>Traffic : " + extra[1] + "</p>" +
							"<p>Weather : " + extra[2] + "</p>" +
							"<p>Safe : " + extra[3] + "</p>" +
							"<p>Rating : " + extra[0] + "</p>" +
							"<p>Address : " + formatted_address + "</p>" +
							"<p>Phone : " + formatted_phone + "</p>" +
							"<p>Website : " + website + "</p>" +
							"<p>Gooogle Rating : " + rating + "</p>" +
							"<p>International Phone  : " + international_phone_number + "</p>" +
							"<p>URL  : <a href='" + url + "'>" + url + "</p>" +			
							"</body></html>";
			
			// Setting the data in WebView
			mWvPlaceDetails.loadDataWithBaseURL("", data, mimeType, encoding, "");	
		}
		
	}
	/** A class, to download Google Place Details */
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
			
			// Start parsing the Google place details in JSON format
			// Invokes the "doInBackground()" method of the class ParseTask
			parserTask.execute(result);
		}
	}
	
	
	/** A class to parse the Google Place Details in JSON format */
	private class ParserTask extends AsyncTask<String, Integer, HashMap<String,String>>{

		JSONObject jObject;
		
		// Invoked by execute() method of this object
		@Override
		protected HashMap<String,String> doInBackground(String... jsonData) {
		
			HashMap<String, String> hPlaceDetails = null;
			PlaceDetailsJSONParser placeDetailsJsonParser = new PlaceDetailsJSONParser();
        
	        try{
	        	jObject = new JSONObject(jsonData[0]);
	        	
	            // Start parsing Google place details in JSON format
	            hPlaceDetails = placeDetailsJsonParser.parse(jObject);
	            
	        }catch(Exception e){
	                Log.d("Exception",e.toString());
	        }
	        return hPlaceDetails;
		}
		
		// Executed after the complete execution of doInBackground() method
		@Override
		protected void onPostExecute(HashMap<String,String> hPlaceDetails){			
			
			
			 name = hPlaceDetails.get("name");
			 icon = hPlaceDetails.get("icon");
			vicinity = hPlaceDetails.get("vicinity");
			 lat = hPlaceDetails.get("lat");
			 lng = hPlaceDetails.get("lng");
			formatted_address = hPlaceDetails.get("formatted_address");
			formatted_phone = hPlaceDetails.get("formatted_phone");
			website = hPlaceDetails.get("website");
			rating = hPlaceDetails.get("rating");
			 international_phone_number = hPlaceDetails.get("international_phone_number");
			 url = hPlaceDetails.get("url");
	        new GetData().execute();

			
				
			Button view=(Button) findViewById(R.id.Get_image);
			Button listen =(Button) findViewById(R.id.Listen);	
			Button contact=(Button) findViewById(R.id.Contact);
			Button route=(Button) findViewById(R.id.route);
			view.setOnClickListener(new View.OnClickListener() {
	        
	            @Override
	            public void onClick(View v) {
	                
	            	Intent i = new Intent(PlaceDetailsActivity.this, ImgActivity.class);
	            	Log.e("Problem in displaying",lat+"  "+lng);
	                i.putExtra("lat", lat);
	                i.putExtra("lng", lng);
	                i.putExtra("img", "true");
	            	startActivity(i);
	            }
	        });
			listen.setOnClickListener(new View.OnClickListener() {
		        
	            @Override
	            public void onClick(View v) {
	                
	            	Intent i = new Intent(PlaceDetailsActivity.this, ImgActivity.class);
	            	Log.e("Problem in displaying",lat+"  "+lng);
	                i.putExtra("lat", lat);
	                i.putExtra("lng", lng);
	                i.putExtra("img", "false");
	            	startActivity(i);
	            }
	        });
			contact.setOnClickListener(new View.OnClickListener() {
		        
	            @Override
	            public void onClick(View v) {
	                
	            	Intent i = new Intent(PlaceDetailsActivity.this, ContactActivity.class);
	            	Log.e("Problem in displaying",lat+"  "+lng);
	                i.putExtra("lat", lat);
	                i.putExtra("lng", lng);
	            	startActivity(i);
	            }
	        });
			route.setOnClickListener(new View.OnClickListener() {
		        
	            @Override
	            public void onClick(View v) {
	                
	            	Intent i = new Intent(PlaceDetailsActivity.this, RouteActivity.class);
	                i.putExtra("lat", lat);
	                i.putExtra("lng", lng);
	                i.putExtra("latsource", String.valueOf(lati));
	                i.putExtra("lngsource", String.valueOf(lngi));
	            	startActivity(i);
	            }
	        });
		}
	}

}