package com.onsalelocal.bestbuy.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoshin.onsalelocal.api.Store;
import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.bestbuy.api.BestbuyCrawler;
import com.onsalelocal.bestbuy.api.Utils;
import com.onsalelocal.bestbuy.dao.BestbuyDao;
import com.onsalelocal.bestbuy.service.BestbuyService;

@Service("bestbuyService")
@Transactional(readOnly = true)
public class BestbuyServiceImpl implements BestbuyService {
	@Autowired
	private BestbuyDao bestbuyDao;

	@Override
	public StoreList listStores() {
		return bestbuyDao.listStores();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Throwable.class)
	public void fetchStores() {
		// get list of stores for Bestbuy, save to database

		BestbuyCrawler bestbuycrawler = new BestbuyCrawler();
		List<Store> stores = new ArrayList<Store>();

		File f = new File("upackStoreXml");

		URL url = null;
		try {

			url = new URL(
					"http://api.remix.bestbuy.com/v1/stores.xml.zip?apiKey=hahsf7qh8z492ukbhy3z5v8c");

			Utils.unpackArchive(url, f);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String storesXml = null;
		for (File ff : f.listFiles()) {
			if (ff.getName().endsWith("zip")) {
				ff.delete();
			} else {
				storesXml = ff.getAbsolutePath();
			}
		}

		stores = bestbuycrawler.listStores(storesXml);

		// System.out.println("Sum of stores :" + stores.size());

		for (Store s : stores) {

			// System.out.println(s.getId().toString());
			bestbuyDao.insert(s);

		}

	}

	@PostConstruct
	public void init() {

		fetchStores();

	}

}
