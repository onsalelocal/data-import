package com.onsalelocal.target.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoshin.onsalelocal.api.Offer;
import com.gaoshin.onsalelocal.api.Store;
import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.target.api.TargetCrawler;
import com.onsalelocal.target.dao.TargetDao;
import com.onsalelocal.target.service.TargetService;
import common.db.dao.GeoDao;
import common.geo.GeoService;
import common.geo.Geocode;
import common.geo.Location;
import common.geo.google.GoogleGeoService;
import common.util.JacksonUtil;
import common.util.LineReader;
import common.util.LineReader.LineProcessor;
import common.util.web.BusinessException;
import common.util.web.ServiceError;

@Service("targetService")
@Transactional(readOnly=true)
public class TargetServiceImpl implements TargetService {
	@Autowired private TargetDao targetDao; 
	@Autowired private GeoDao geoDao;
	private GeoService geoService = new GoogleGeoService();

	@Override
    public StoreList listStores() {
	    return targetDao.listStores();
    }

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void fetchStores() {
		LineReader lr = new LineReader(this.getClass().getResourceAsStream("/store-json.txt"));
		try {
	        lr.start(new LineProcessor() {
	        	@Override
	        	public void process(String line) {
	        		try {
	                    Store store = JacksonUtil.json2Object(line, Store.class);
	                    targetDao.insert(store, true);
                    } catch (Exception e) {
            	        throw new BusinessException(ServiceError.Unknown, e);
                    }
	        	}
	        });
        } catch (IOException e) {
	        throw new BusinessException(ServiceError.Unknown, e);
        }
    }

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	public void geo() {
		List<Store> stores = targetDao.getNoGeoStores();
		for(Store store : stores) {
			String location = store.getAddress();
			try {
				Location loc = geoService.geo(location);
				Geocode geo = loc.getGeocode();
				store.setLatitude(new BigDecimal(geo.getLatitude()));
				store.setLongitude(new BigDecimal(geo.getLongitude()));
				store.setAddress(loc.getAddr().toString());
				store.setCity(loc.getAddr().getCity());
				store.setState(loc.getAddr().getState());
				store.setZipcode(loc.getAddr().getZipcode());
			} catch (Exception e) {
				e.printStackTrace();
				store.setLatitude(new BigDecimal(0));
				store.setLongitude(new BigDecimal(0));
			}
			targetDao.updateEntity(store, "latitude", "longitude", "address", "city", "state", "zipcode");
		}
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void fetchOffers(String city, String state, String zipcode) throws Exception {
		List<Offer> offers = new TargetCrawler().crawleOffers(city, state, zipcode);
		for(Offer offer : offers) {
			Map<String, Object> where = new HashMap<String, Object>();
			where.put("city", offer.getCity());
			where.put("state", offer.getState());
			where.put("zipcode", offer.getZipcode());
			offer.setSource("target");
			offer.setRootSource("target");
			Store store = targetDao.queryUniqueBySql(Store.class, where, "select * from Store where city=:city and state=:state and zipcode=:zipcode" );
			if(store != null) {
				offer.setLatitude(store.getLatitude().floatValue());
				offer.setLongitude(store.getLongitude().floatValue());
			}
			targetDao.insert(offer, true);
		}
    }

}
