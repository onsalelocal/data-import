package com.onsalelocal.kmart.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoshin.onsalelocal.api.Store;
import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.kmart.dao.KmartDao;
import common.db.dao.impl.GenericDaoImpl;

@Repository("kmartDao")
public class KmartDaoImpl extends GenericDaoImpl implements KmartDao {

	@Override
    public StoreList listStores() {
		String sql = "select * from Store";
		List<Store> list = queryBySql(Store.class, null, sql);
		StoreList data = new StoreList();
		data.setItems(list);
	    return data;
    }

}
