package com.onsalelocal.michaels.service.impl;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;

import com.gaoshin.onsalelocal.api.Store;
import com.onsalelocal.michaels.dao.Cities;



import common.crawler.CrawlerBase;

public class MichaelsCrawler extends CrawlerBase {
	public List<Store>   fetchstores(Cities city) throws Exception {
		
		System.out.println("start fetching!");
		String url = "http://m.weeklyad.michaels.com/main/stores/?o=0&st="+city.getCity()+"%2C"+city.getState()+"&sc=US";
		List<Store> list = new ArrayList<Store>();
		
		String  content = getContent(url);
		
		String s="";
		BufferedReader br = new BufferedReader(new StringReader(content));
		while((s = br.readLine())!= null){
			if(s.endsWith("&nbsp;")){
				 

				
				Store store = new Store();
			    s= s.substring(0,s.indexOf("&nbsp;"));
				store.setCity(city.getCity());
				store.setState(city.getState());
				String s1 = br.readLine();
				int index1 = s1.indexOf("<em>"); 
			    int index2 = s1.indexOf("</em>");
			    s1 = s1.substring(index1+4, index2);
			    String address = s1.substring(0,s1.lastIndexOf(" "));
			    String zipcode = s1.substring(s1.lastIndexOf(" ")+1);
			    store.setAddress(address);
			    store.setZipcode(zipcode);
			    System.out.println("get information for store  address:"+address+"   zipcode  "+zipcode);
			    list.add(store);
		
			
			}

			
		}
		
		
		
		
		return list;
}
}