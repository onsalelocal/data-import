package com.onsalelocal.cvs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.cvs.dao.CvsDao;
import com.onsalelocal.cvs.service.CvsService;

@Service("cvsService")
@Transactional(readOnly=true)
public class CvsServiceImpl implements CvsService {
	@Autowired private CvsDao cvsDao; 

	@Override
    public StoreList listStores() {
	    return cvsDao.listStores();
    }

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void fetchStores() {
		// get list of stores for Cvs, save to database
    }

}
