package com.sdp.rssreader;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Handler extends DefaultHandler {
	
	private List<Feed> items;
	private Feed currentItem;
	private boolean title;
	private boolean urii;
	
	public Handler(){
		items = new ArrayList<Feed>();
	}
	
	public List<Feed> getItems(){
		return items;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("item".equals(qName)) {
			currentItem = new Feed();
		} else if ("title".equals(qName)) {
			title = true;
		} else if ("url".equals(qName)) {
			urii = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("item".equals(qName)) {
			items.add(currentItem);
			currentItem = null;
		} else if ("title".equals(qName)) {
			title = false;
		} else if ("link".equals(qName)) {
			urii = false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (title) {
			if (currentItem != null)
				currentItem.setTitle(new String(ch, start, length));
		} else if (urii) {
			if (currentItem != null) {
				currentItem.setUrl(new String(ch, start, length));
				
				urii = false;
			}
		}
	}


}
