package arw.apps.barcode; 

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Listing extends Activity {
	

	
	 public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.listing_new);
	
 		getData();
 		
 		
	 }
	
	
        public static Object[] getData(){
   
      String lent_to = "Tessa";  	
      String db_url = "http://alexwhyatt.com/bookclub_list_api.php";
    InputStream is = null;
    String line = null;
    ArrayList<NameValuePair> request = new ArrayList<NameValuePair>();
    request.add(new BasicNameValuePair("bookname",lent_to));
    Object returnValue[] = new Object[1];
    try
    {
        HttpClient httpclient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpPost httppost = new HttpPost(db_url);
        httppost.setEntity(new UrlEncodedFormEntity(request));
        HttpResponse response = httpclient.execute(httppost, localContext);
        HttpEntity entity = response.getEntity();
        is = entity.getContent();
    }catch(Exception e){
        Log.e("log_tag", "Error in http connection" +e.toString());
    }
    String result = "";
    try
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
        StringBuilder sb = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        result=sb.toString();
    }catch(Exception e){
        Log.e("log_tag", "Error in http connection" +e.toString());
    }
	return returnValue;
        }
          
    
    protected void onPostExecute(String result){
    	TextView textview  = (TextView) findViewById(R.id.booklist);
		textview.setText(result);
}
       
    
}
