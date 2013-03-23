package com.onsalelocal.kroger.dao;

import com.gaoshin.onsalelocal.api.StoreList;
import common.db.dao.GenericDao;

public interface KrogerDao extends GenericDao {

	StoreList listStores();

}
