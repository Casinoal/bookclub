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

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SingleMenuItemActivity  extends Activity implements OnClickListener {

	private static final String URL = null;

	Button button1, button2, button3, button4, button5;
	
	// XML node keys
	//static final String KEY_ID = "name";
	//static final String KEY_DESC = "description";
	String var_one;
	String var_two;
	String buttonText;
	String name;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        name = in.getStringExtra(var_one);
      //  String description = in.getStringExtra(var_two);
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
      //  TextView lblDesc = (TextView) findViewById(R.id.description_label);
        
        lblName.setText(name);
      //  lblDesc.setText(description);
    


	Button button1 = (Button) findViewById(R.id.button1);
	button1.setOnClickListener(this); 
	Button button2 = (Button) findViewById(R.id.button2);
	button2.setOnClickListener(this); 
	Button button3 = (Button) findViewById(R.id.button3);
	button3.setOnClickListener(this); 
	Button button4 = (Button) findViewById(R.id.button4);
	button4.setOnClickListener(this); 
	Button button5 = (Button) findViewById(R.id.button5);
	button5.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
					
		Button b = (Button)v;
        buttonText = b.getText().toString();
        Toast.makeText(getBaseContext(), buttonText , Toast.LENGTH_SHORT).show();
       
        new postData().execute();
    }
			
	private class postData extends AsyncTask<String, String, String>{
	
		@Override
		protected String doInBackground(String... params) {
		
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://alexwhyatt.com/bookclub_api.php");{

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("bookname", name));
	        nameValuePairs.add(new BasicNameValuePair("username", buttonText));
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
	
	
	
	
