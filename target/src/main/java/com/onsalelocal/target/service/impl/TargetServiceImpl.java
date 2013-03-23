package com.onsalelocal.target.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.target.dao.TargetDao;
import com.onsalelocal.target.service.TargetService;

@Service("targetService")
@Transactional(readOnly=true)
public class TargetServiceImpl implements TargetService {
	@Autowired private TargetDao targetDao; 

	@Override
    public StoreList listStores() {
	    return targetDao.listStores();
    }

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void fetchStores() {
		// get list of stores for Target, save to database
    }

}
