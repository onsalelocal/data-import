package com.onsalelocal.bestbuy.service;

import com.gaoshin.onsalelocal.api.StoreList;

public interface BestbuyService {

	StoreList listStores();

	void fetchStores();

}
