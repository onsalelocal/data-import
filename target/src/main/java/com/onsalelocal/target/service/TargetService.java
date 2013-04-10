package com.onsalelocal.target.service;

import com.gaoshin.onsalelocal.api.StoreList;

public interface TargetService {

	StoreList listStores();

	void fetchStores();

	void geo();

	void fetchOffers(String city, String state, String zipcode) throws Exception;

}
