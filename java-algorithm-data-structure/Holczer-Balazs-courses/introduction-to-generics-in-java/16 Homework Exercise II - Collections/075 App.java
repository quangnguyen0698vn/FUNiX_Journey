package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.List;

class Pair {

	private String url;
	private String data;
	
	public Pair() {
		
	}
	
	public Pair(String url, String data) {
		this.url = url;
		this.data = data;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getData() {
		return data;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public boolean equals(Object object) {
		Pair pair = (Pair) object;
		return pair.getUrl().contentEquals(url);
	}
	
	@Override
	public String toString() {
		return "Pair: ["+url+","+data+"]";
	}
}

class Cache {
	
	private static final int CAPACITY = 5;
	private List<Pair> cache;
	
	public Cache() {
		this.cache = new ArrayList<>();
	}
	
	public void search(String url) {
		
		Pair searchedItem = null;
		
		// we check if the item is already present in the data structure
		for(int i=0;i<cache.size();++i) {
			System.out.println("HEY: "+cache.get(i).getUrl());
			if(cache.get(i).getUrl().equals(url)) {
				searchedItem = cache.get(i);
			}
		}
		
		// we have it in the cache already
		if(searchedItem!=null) {
			System.out.println("It is in the cache ... "+url);
			cache.remove(searchedItem);
			cache.add(0, searchedItem);
		} else {
			// insert to the beginning of the cache
			System.out.println("Not in the cache so insert it ... "+url);
			
			Pair newPair = new Pair(url,"Random data for a given URL...");
			
			// we have to deal with the bound (capacity) - remove the last item if cache is full
			if(cache.size()>=CAPACITY) cache.remove(CAPACITY-1);
			
			// if it is the first item
			if(cache.size()==0) 
				cache.add(newPair);
			else
				// if it is not the first item then we can insert to the first slot (index 0)
				cache.add(0, newPair);
		}
	}
	
	public void showCache() {
		for(Pair pair : cache)
			System.out.println(pair);
	}
}

public class App {

	public static void main(String[] args) {
		
		Cache cache = new Cache();
		
		cache.search("www.facebook.com");
		cache.search("www.google.com");
		cache.search("www.amazon.com");
		cache.search("www.google.com");
		cache.search("www.facebook.com");
		cache.search("www.twitter.com");
		cache.search("www.globalsoftwaresuppot.com");
		cache.search("www.bbc.com");
		cache.search("www.cnn.com");
		cache.search("www.google.com");
		cache.search("www.amazon.com");
		cache.search("www.google.com");
		cache.search("www.facebook.com");
		
		cache.showCache();
		
	}
}
