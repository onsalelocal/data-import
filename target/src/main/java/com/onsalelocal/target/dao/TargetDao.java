package com.onsalelocal.target.dao;

import com.gaoshin.onsalelocal.api.StoreList;
import common.db.dao.GenericDao;

public interface TargetDao extends GenericDao {

	StoreList listStores();

}
