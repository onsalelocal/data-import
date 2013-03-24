package com.onsalelocal.michaels.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.michaels.dao.MichaelsDao;
import com.onsalelocal.michaels.service.MichaelsService;

@Service("michaelsService")
@Transactional(readOnly=true)
public class MichaelsServiceImpl implements MichaelsService {
	@Autowired private MichaelsDao michaelsDao; 

	@Override
    public StoreList listStores() {
	    return michaelsDao.listStores();
    }

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void fetchStores() {
		// get list of stores for Michaels, save to database
    }

}
