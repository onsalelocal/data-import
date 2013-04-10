package com.onsalelocal.target.api;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import com.gaoshin.onsalelocal.api.Offer;
import com.gaoshin.onsalelocal.api.Store;
import common.crawler.CrawlerBase;
import common.crawler.Link;
import common.geo.PostalAddress;
import common.geo.PostalAddressParser;
import common.util.JacksonUtil;
import common.util.LineReader;
import common.util.LineReader.LineProcessor;
import common.util.MD5;
import common.util.PhoneNumberUtil;

public class TargetCrawler extends CrawlerBase {
	private static final Logger logger = Logger.getLogger(TargetCrawler.class);
	
	public List<Offer> crawleOffers(String city, String state, String zipcode) throws Exception {
		String location = city.toLowerCase() + "-" + state.toLowerCase() + "-" + zipcode;
		location = location.replace(' ', '-');
		String url = "http://m.weeklyad.target.com/" + location + "/categories";
		logger.info("get categories from " + url);
		Document doc = getDocumentFromUrl(url);
		
		List<Link> categories = new ArrayList<Link>();
		List<Element> elements = selectElements(doc, "A");
		for(Element ele : elements) {
			Link link = getLink(ele);
			if(link != null && link.href != null && link.href.indexOf(location)!=-1) {
				categories.add(link);
				logger.info("got category " + link.title + ": " + link.href);
			}
		}
		
		List<Offer> offers = new ArrayList<Offer>();
		for(Link cat : categories) {
			logger.info("get weekly ads for category " + cat.href);
			doc = getDocumentFromUrl("http://m.weeklyad.target.com" + cat.href);
			List<Element> prices = selectElements(doc, "P", "class", "price");
			logger.info("found " + prices.size());
			for(Element ele : prices) {
				String price = ele.getTextTrim();
				String desc = selectElement(ele.getParent(), "DIV", "class", "wa_proddesc").getTextTrim();
				Element parent = ele.getParent();
				Element imgElem = selectElement(parent, "IMG");
				String img = imgElem.attribute("src").getStringValue();
				String link = selectElement(ele.getParent().getParent().getParent(), "A").attribute("href").getStringValue();
				if(!link.startsWith("http"))
					link = "http://m.weeklyad.target.com" + link;
				Offer offer = new Offer();
				offer.setCity(city);
				offer.setState(state);
				offer.setZipcode(zipcode);
				offer.setCategory(cat.title);
				offer.setTitle(desc);
				offer.setUrl(link);
				offer.setPrice(price);
				offer.setSmallImg(img);
				offer.setCreated(System.currentTimeMillis());
				offer.setStart(offer.getCreated());
				offer.setEnd(offer.getCreated() + 7*24*3600000);
				offer.setSourceId(MD5.md5(link));
				offers.add(offer);
			}
		}
		
		return offers;
	}
	
	public static void getInfoFromHtml() throws Exception {
		File[] files = new File("./src/main/resources/store-info").listFiles();
		for(File f : files) {
			String name = f.getName();
			if(name.endsWith(".html")) {
				final String storeId = name.substring(0, name.length() - 5);
				LineReader reader = new LineReader(new FileInputStream(f));
				reader.start(new LineProcessor() {
					@Override
					public void process(String line) {
						String pattern = "<a title=\"View Map\" class=\"store-link\" href=\"http://m.target.com/store-locator/map?";
						int pos = line.indexOf(pattern);
						if(pos != -1) {
							pos += pattern.length();
							int pos2 = line.indexOf("\"", pos);
							String[] param = line.substring(pos, pos2).split("&");
							Map<String, String> map = new HashMap<String, String>();
							for(String s : param) {
								String[] split = s.split("=");
								if(split.length == 1)
									continue;
								map.put(split[0], URLDecoder.decode(split[1]));
							}
							Store store = new Store();
							store.setChainStoreId(storeId);
							store.setAddress(map.get("streetName"));
							
							try {
	                            PostalAddress address = PostalAddressParser.parseCityStateZip(map.get("displayAddress"));
	                            store.setCity(address.getCity());
	                            store.setState(address.getState());
	                            store.setZipcode(address.getZipcode());
	                            store.setPhone(PhoneNumberUtil.formatPhone(map.get("storePhoneNo")));
	                            
	                            store.setLatitude(new BigDecimal(map.get("latitude")));
	                            store.setLongitude(new BigDecimal(map.get("longitude")));
	                            
	                            System.out.println(JacksonUtil.obj2Json(store));
                            } catch (Exception e) {
	                            e.printStackTrace();
                            }
						}
					}
				});
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<Offer> offers = new TargetCrawler().crawleOffers("mountain view", "ca", "94040");
		for(Offer o : offers) {
			System.out.println(JacksonUtil.obj2Json(o));
		}
    }
}
