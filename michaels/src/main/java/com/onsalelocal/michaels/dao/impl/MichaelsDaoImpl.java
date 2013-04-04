package com.onsalelocal.michaels.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoshin.onsalelocal.api.Store;
import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.michaels.dao.Cities;
import com.onsalelocal.michaels.dao.MichaelsDao;

import common.db.dao.impl.GenericDaoImpl;

@Repository("michaelsDao")
public class MichaelsDaoImpl extends GenericDaoImpl implements MichaelsDao {

	@Override
    public StoreList listStores() {
		String sql = "select * from Store";
		List<Store> list = queryBySql(Store.class, null, sql);
		StoreList data = new StoreList();
		data.setItems(list);
	    return data;
    }
    
  public List<Cities> getcities()	  
  
  {
	  String sql = "select city,state from geo.uscities";
		List<Cities> list = queryBySql(Cities.class, null, sql);
		for(Cities city:list){
		 System.out.println("city:"+city.getCity());
		 System.out.println("state:"+city.getState());

	  
		}
	return list;
	  
	  
	  
  }
 
	  
	  
	
}
