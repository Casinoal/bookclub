package arw.apps.barcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

	private static final int ZBAR_SCANNER_REQUEST = 1;
	public final static String apiURL = "http://www.isbndb.com/api/books.xml?access_key=Q79555UD&index1=isbn&value1=";
	String urlstring;
	String responseString;
	String bum;
	String ISBN;
	Button button1, button2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);    
		
		
		Button button = (Button) findViewById(R.id.scan_btn);
		button.setOnClickListener(this); 
		
		

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, ZBarScannerActivity.class);
		startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
	
		startActivity(intent);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{    
		if (resultCode == RESULT_OK) 
		{
			// Scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT)
			// Type of the scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT_TYPE)
			//String ISBN = data.getStringExtra(ZBarConstants.SCAN_RESULT);
			//Toast.makeText(this, ISBN, Toast.LENGTH_SHORT).show();

			urlstring = data.getStringExtra(ZBarConstants.SCAN_RESULT);
			
				
      		Toast.makeText(this, urlstring, Toast.LENGTH_SHORT).show();
			
      		new RequestTask().execute(apiURL+urlstring);
    		
		} else if(resultCode == RESULT_CANCELED) {
			Toast.makeText(this, "Camera unavailable", Toast.LENGTH_SHORT).show();
			
		}
		
				
	      

	}
	 public void listing (View view) { 
		 Intent launchlisting= new Intent(getApplicationContext(), Lookup.class);                               
    	 startActivity(launchlisting);
	 
    
	 }
	 
	 public void setup (View view) { 
		 Intent launchsetup= new Intent(getApplicationContext(), QuickPrefsActivity.class);                               
    	 startActivity(launchsetup);
	 
    
	 }
	       
	private class RequestTask extends AsyncTask<String, String, String>{

	    @Override
	    protected String doInBackground(String... uri) {
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpResponse response;
	        String responseString = null;
	        try {
	            response = httpclient.execute(new HttpGet(uri[0]));
	            StatusLine statusLine = response.getStatusLine();
	            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	                ByteArrayOutputStream out = new ByteArrayOutputStream();
	                response.getEntity().writeTo(out);
	                out.close();
	                responseString = out.toString();
	            } else{
	                //Closes the connection.
	                response.getEntity().getContent().close();
	                throw new IOException(statusLine.getReasonPhrase());
	            }
	        } catch (ClientProtocolException e) {
	            //TODO Handle problems..
	        } catch (IOException e) {
	            //TODO Handle problems..
	        }
	        return responseString;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	        super.onPostExecute(result);
	        //Do anything with response..
	        	       
	     //   Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
	        
	        Intent i = new Intent(getApplicationContext(), AndroidXMLParsingActivity.class);
	        i.putExtra(bum, urlstring);
	        startActivity(i);
	    }
	    
	}
	
	    public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
   
	    }
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case (R.id.action_settings):
	                startActivity(new Intent(this, QuickPrefsActivity.class));
	                return true;
	        }
	        return false;
	    
	   
}}
    
	    
	    
	

	    
	
	    
	   
		

	
















