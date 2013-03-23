package com.onsalelocal.kroger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.kroger.dao.KrogerDao;
import com.onsalelocal.kroger.service.KrogerService;

@Service("krogerService")
@Transactional(readOnly=true)
public class KrogerServiceImpl implements KrogerService {
	@Autowired private KrogerDao krogerDao; 

	@Override
    public StoreList listStores() {
	    return krogerDao.listStores();
    }

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void fetchStores() {
		// get list of stores for Kroger, save to database
    }

}
