package com.sdp.rssreader;

import java.util.ArrayList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView Items;
	ProgressDialog showProgress;
	ArrayAdapter<Feed> adapter;
	Reader rssReader;
	ArrayList<Feed> list = new ArrayList<Feed>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Items = (ListView) findViewById(R.id.list);

		showProgress = ProgressDialog.show(MainActivity.this, "",
				"Loading. Please wait...", true);
		new loadFeed().execute("http://www.goal.com/en-ng/feeds/news?fmt=rss");
		//Items.setOnItemClickListener(new ListListener(list, MainActivity.this));
	}

	private class loadFeed extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... url) {
			try {
				rssReader = new Reader(url[0]);
			adapter = new ArrayAdapter<Feed>(MainActivity.this,
						android.R.layout.simple_list_item_1,
						rssReader.getItems());
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "";
		}

		protected void onPostExecute(String s) {
			Items.setAdapter(adapter);
			showProgress.dismiss();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
