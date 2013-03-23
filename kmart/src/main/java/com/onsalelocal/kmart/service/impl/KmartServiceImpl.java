package com.onsalelocal.kmart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.kmart.dao.KmartDao;
import com.onsalelocal.kmart.service.KmartService;

@Service("kmartService")
@Transactional(readOnly=true)
public class KmartServiceImpl implements KmartService {
	@Autowired private KmartDao kmartDao; 

	@Override
    public StoreList listStores() {
	    return kmartDao.listStores();
    }

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void fetchStores() {
		// get list of stores for Kmart, save to database
    }

}
