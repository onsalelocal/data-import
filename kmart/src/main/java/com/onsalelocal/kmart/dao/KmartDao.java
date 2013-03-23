package com.onsalelocal.kmart.dao;

import com.gaoshin.onsalelocal.api.StoreList;
import common.db.dao.GenericDao;

public interface KmartDao extends GenericDao {

	StoreList listStores();

}
