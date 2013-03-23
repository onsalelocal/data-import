package com.onsalelocal.cvs.service;

import com.gaoshin.onsalelocal.api.StoreList;

public interface CvsService {

	StoreList listStores();

	void fetchStores();

}
