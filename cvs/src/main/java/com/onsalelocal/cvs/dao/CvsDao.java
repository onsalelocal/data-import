package com.onsalelocal.cvs.dao;

import com.gaoshin.onsalelocal.api.StoreList;
import common.db.dao.GenericDao;

public interface CvsDao extends GenericDao {

	StoreList listStores();

}
