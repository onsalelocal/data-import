package com.onsalelocal.michaels.dao;

import java.util.List;

import com.gaoshin.onsalelocal.api.StoreList;
import common.db.dao.GenericDao;

public interface MichaelsDao extends GenericDao {

	StoreList listStores();
    List<Cities>  getcities();
}
