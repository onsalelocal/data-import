package com.onsalelocal.kroger.service;

import com.gaoshin.onsalelocal.api.StoreList;

public interface KrogerService {

	StoreList listStores();

	void fetchStores();

}
