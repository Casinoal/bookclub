package arw.apps.barcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class QuickPrefsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener	{

	String email;
	String clubcode;
	String username;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {    	
		super.onCreate(savedInstanceState);        
		addPreferencesFromResource(R.xml.preferences);        
	
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }
	
		
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, 0, 0, "Show current settings");
    	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		case 0:
    			startActivity(new Intent(this, ShowSettingsActivity.class));
    			return true;
    	}
    	return false;
    }


	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		
		if ( key.equals("email") ){
		
		//Toast.makeText(getApplicationContext(), "email changed", Toast.LENGTH_SHORT).show();
		
		 SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
 		clubcode = sharedPrefs.getString("clubcode", null);
 		username = sharedPrefs.getString("username", null);
 		 email = sharedPrefs.getString("email", null);
		
		new postData().execute(email, username, clubcode);
		
	}
	}
 
	private class postData extends AsyncTask<String, String, String>{
    	
    	@Override
    	protected String doInBackground(String... params) {
    	
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://alexwhyatt.com/bookclub_api_email.php");{

            	
        	
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("email", email));
            nameValuePairs.add(new BasicNameValuePair("username", username));
            nameValuePairs.add(new BasicNameValuePair("clubcode", clubcode));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        
        }
    	
    		// TODO Auto-generated method stub
    		return null;
    	}
    }
	
}


