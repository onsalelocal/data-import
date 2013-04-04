package com.gaoshin.onsalelocal.safeway.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoshin.onsalelocal.api.Store;
import com.gaoshin.onsalelocal.safeway.dao.SafewayDao;
import com.gaoshin.onsalelocal.safeway.service.SafewayService;
import common.db.dao.GeoDao;
import common.geo.GeoService;
import common.geo.Geocode;
import common.geo.Location;
import common.geo.google.GoogleGeoService;

@Service("safewayService")
@Transactional(readOnly=true)
public class SafewayServiceImpl extends ServiceBaseImpl implements SafewayService {
	@Autowired private SafewayDao safewayDao;
	@Autowired private GeoDao geoDao;
	private GeoService geoService = new GoogleGeoService();

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	public void geo() {
		List<Store> stores = safewayDao.getNoGeoStores();
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
			safewayDao.updateEntity(store, "latitude", "longitude", "address", "city", "state", "zipcode");
		}
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void updateTasks() {
		safewayDao.updateTasks();
    }

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void seedTasks() {
		safewayDao.seedTasks();
    }

}
