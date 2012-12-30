package com.sdp.rssreader;

import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ListListener  implements OnItemClickListener{
	
	List<Feed> liveFeeds;
	Activity activity;
	
	public ListListener(List<Feed> aListItems, Activity anActivity) {
		liveFeeds = aListItems;
		activity  = anActivity;
	}
	

	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(liveFeeds.get(pos).getUrl()));
	
		activity.startActivity(i);
		
	}
}
