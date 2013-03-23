package com.onsalelocal.bestbuy.dao;

import com.gaoshin.onsalelocal.api.StoreList;
import common.db.dao.GenericDao;

public interface BestbuyDao extends GenericDao {

	StoreList listStores();

}
