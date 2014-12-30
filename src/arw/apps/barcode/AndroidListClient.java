/**
 * Copyright (c) 2010 Mujtaba Hassanpur.
 * 
 
 */

package arw.apps.barcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Class: AndroidListClient - This is the main activity for this application.
 * This example shows how we can implement a simple client/server model using
 * an Android app as the client and a PHP script as the server. This example
 * should be used in conjunction with the AndroidListServer example.
 * 
 * @author Mujtaba Hassanpur
 */
public class AndroidListClient extends ListActivity implements OnClickListener{

	private static final String URL = null;
	

	// XML node keys
	//static final String KEY_ID = "name";
	//static final String KEY_DESC = "description";
	
	String var_one;
	String var_two;
	String buttonText;
	String name;
	String clubcode;
	String username;

	       
       
	
        // Instance Variables
        private AndroidListClient mainActivity = null;

        /** Called when the activity is first created. */
        @SuppressWarnings("unchecked")
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
                
     
                // we're going to need this later by the other threads
                mainActivity = this;

                // let's initialize the list, the real data will be filled in later
                String[] initialList = {""};

                // now we'll supply the data structure needed by this ListActivity
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, arw.apps.barcode.R.layout.single_list_item, arw.apps.barcode.R.id.userlist, initialList);
                this.setListAdapter(adapter);

                /*
                 * Let's set up an item click listener to retrieve the animal sound and
                 * display it to the user as a Toast.
                 */
                ListView lv = (ListView) findViewById(android.R.id.list);
       
                lv.setOnItemClickListener(new OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                
                         
                    
                        }

                });
        new GetListTask().execute((Object)null);
        }
        /**
         * Used to implement an asynchronous retrieval of the list from the web.
         * This uses the AsyncTask class as a starting point. For more info, see
         * http://android-developers.blogspot.com/2009/05/painless-threading.html.
         */
        @SuppressWarnings("unchecked")
        
        private class GetListTask extends AsyncTask {

                /**
                 * Let's make the http request and return the result as a String.
                 */
                protected String doInBackground(Object... args) {                       
                        return GetListofUsers.getAnimalList();
                }

                /**
                 * Parse the String result, and create a new array adapter for the list
                 * view.
                 */
        
        
        
                protected void onPostExecute(Object objResult) {
                        // check to make sure we're dealing with a string
                        if(objResult != null && objResult instanceof String) {                          
                                String result = (String) objResult;
                                // this is used to hold the string array, after tokenizing
                                String[] responseList;

                                // we'll use a string tokenizer, with "," (comma) as the delimiter
                                StringTokenizer tk = new StringTokenizer(result, ",");

                                // now we know how long the string array is
                                responseList = new String[tk.countTokens()];

                                // let's build the string array
                                int i = 0;
                                while(tk.hasMoreTokens()) {
                                        responseList[i++] = tk.nextToken();
                                }

                                // now we'll supply the data structure needed by this ListActivity
                                ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(mainActivity, arw.apps.barcode.R.layout.single_list_item, arw.apps.barcode.R.id.userlist, responseList);
                                mainActivity.setListAdapter(newAdapter);
                        }
                
   

        

		
			
		}
class postData extends AsyncTask<String, String, String>{
	
	@Override
	protected String doInBackground(String... params) {
	
    // Create a new HttpClient and Post Header
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost("http://alexwhyatt.com/bookclub_api.php");{

        	
    	
    try {
        // Add your data
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
        nameValuePairs.add(new BasicNameValuePair("bookname", name));
        nameValuePairs.add(new BasicNameValuePair("username", buttonText));
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

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
}