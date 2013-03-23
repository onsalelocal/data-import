package com.onsalelocal.bestbuy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoshin.onsalelocal.api.Store;
import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.bestbuy.dao.BestbuyDao;
import common.db.dao.impl.GenericDaoImpl;

@Repository("bestbuyDao")
public class BestbuyDaoImpl extends GenericDaoImpl implements BestbuyDao {

	@Override
    public StoreList listStores() {
		String sql = "select * from Store";
		List<Store> list = queryBySql(Store.class, null, sql);
		StoreList data = new StoreList();
		data.setItems(list);
	    return data;
    }

}
