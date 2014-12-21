package arw.apps.barcode;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class AndroidXMLParsingActivity extends ListActivity {

	// All static variables

	static final String ROOT = "http://isbndb.com/api/books.xml?access_key=Q79555UD&index1=isbn&value1=";
	// XML node keys
	static final String KEY_ITEM = "BookList"; // parent node
	static final String KEY_ID = "Title";
	static final String KEY_NAME = "TitleLong";
	static final String KEY_COST = "AuthorsTest";
	static final String KEY_DESC = "PublisherText";
	String bum;
	String URL;
	ListAdapter adapter;
	String responseString;
	public String var_one;
	public String var_two;
	
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlist);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String	trfbum = extras.getString(bum);
			URL = ROOT+trfbum;
		}

	//	Toast.makeText(getApplicationContext(), URL, Toast.LENGTH_LONG).show();
		new APITask().execute(URL);
		
		
	}
	
	private class APITask extends AsyncTask<String, String, String>{

	    

		@Override
	    protected String doInBackground(String... uri) {
		
		ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML
		Document doc = parser.getDomElement(xml); // getting DOM element
		responseString = null;
		
		NodeList nl = doc.getElementsByTagName(KEY_ITEM);
		// looping through all item nodes <item>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_ID, parser.getValue(e, KEY_ID));
			map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
			map.put(KEY_COST, "Rs." + parser.getValue(e, KEY_COST));
			map.put(KEY_DESC, parser.getValue(e, KEY_DESC));

			// adding HashList to ArrayList
			menuItems.add(map);
			
			adapter = new SimpleAdapter(getApplicationContext(), menuItems,
					R.layout.list_item,
					new String[] { KEY_ID, KEY_DESC, }, new int[] {
					R.id.name, R.id.desciption, });
			
			
		}
		return responseString;

		
		
	}
	@Override
	protected void onPostExecute(String result) {
		// Adding menuItems to ListView
				 
		 setListAdapter(adapter);
	
		// selecting single ListView item
		ListView lv = getListView();
	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
			//	String description = ((TextView) view.findViewById(R.id.desciption)).getText().toString();

				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
				in.putExtra(var_one, name);
			//	in.putExtra(var_two, description);
				startActivity(in);


				;}}	)
				;	
	}
		

			
		
		
	    }
	public void back (View view) {

		finish();
	}
	
}

