package com.onsalelocal.bestbuy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.bestbuy.dao.BestbuyDao;
import com.onsalelocal.bestbuy.service.BestbuyService;

@Service("bestbuyService")
@Transactional(readOnly=true)
public class BestbuyServiceImpl implements BestbuyService {
	@Autowired private BestbuyDao bestbuyDao; 

	@Override
    public StoreList listStores() {
	    return bestbuyDao.listStores();
    }

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void fetchStores() {
		// get list of stores for Bestbuy, save to database
    }

}
