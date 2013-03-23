package com.onsalelocal.target.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoshin.onsalelocal.api.Store;
import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.target.dao.TargetDao;
import common.db.dao.impl.GenericDaoImpl;

@Repository("targetDao")
public class TargetDaoImpl extends GenericDaoImpl implements TargetDao {

	@Override
    public StoreList listStores() {
		String sql = "select * from Store";
		List<Store> list = queryBySql(Store.class, null, sql);
		StoreList data = new StoreList();
		data.setItems(list);
	    return data;
    }

}
