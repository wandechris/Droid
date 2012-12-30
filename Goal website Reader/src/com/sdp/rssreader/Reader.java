package com.sdp.rssreader;

import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Reader  {
	
	private String url;
	
	public Reader (String url){
		this.url =  url;
	}
	public List<Feed> getItems() throws Exception{
		SAXParserFactory factory  = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		Handler han = new Handler();
		saxParser.parse(url, han);

		return han.getItems();
		
	}
}
