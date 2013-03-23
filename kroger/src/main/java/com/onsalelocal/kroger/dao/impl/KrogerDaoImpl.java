package com.onsalelocal.kroger.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoshin.onsalelocal.api.Store;
import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.kroger.dao.KrogerDao;
import common.db.dao.impl.GenericDaoImpl;

@Repository("krogerDao")
public class KrogerDaoImpl extends GenericDaoImpl implements KrogerDao {

	@Override
    public StoreList listStores() {
		String sql = "select * from Store";
		List<Store> list = queryBySql(Store.class, null, sql);
		StoreList data = new StoreList();
		data.setItems(list);
	    return data;
    }

}
