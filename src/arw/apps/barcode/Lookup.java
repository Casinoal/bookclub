package arw.apps.barcode;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import android.widget.Toast;

public class Lookup extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent);

		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String name = sharedPrefs.getString("username", null);

		String postData = "username="+ name;

		String url = "http://www.alexwhyatt.com/bookclub_list_api.php";

		WebView myWebView = (WebView) findViewById(R.id.webView1);
		myWebView.postUrl(url,EncodingUtils.getBytes(postData, "BASE64"));

		Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
		Toast.makeText(this, postData, Toast.LENGTH_SHORT).show();


	}
}

