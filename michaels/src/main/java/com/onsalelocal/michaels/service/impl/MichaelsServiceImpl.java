package com.onsalelocal.michaels.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoshin.onsalelocal.api.Store;
import com.gaoshin.onsalelocal.api.StoreList;
import com.onsalelocal.michaels.dao.Cities;
import com.onsalelocal.michaels.dao.MichaelsDao;
import com.onsalelocal.michaels.service.MichaelsService;

@Service("michaelsService")
@Transactional(readOnly=true)
public class MichaelsServiceImpl implements MichaelsService {
	@Autowired private MichaelsDao michaelsDao; 

	@Override
    public StoreList listStores() {
	    return michaelsDao.listStores();
    }

	@Override
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
    public void fetchStores() {
		// get list of stores for Michaels, save to database
		
		
		
		List<Cities> cities =	michaelsDao.getcities();
		List<Store> stores1 = new ArrayList<Store>();

		 try {
		
		for(Cities city:cities){
			MichaelsCrawler crawler = new MichaelsCrawler();
			
			List<Store> stores =crawler.fetchstores(city);
			
		     		     stores1.addAll(stores);
		     		     stores1 = removeDuplicate(stores1);
			 		}
		
		for(Store store:stores1){
			 
			 System.out.println("insert store to database address:"+store.getAddress());
			 System.out.println("insert store to database city:"+store.getCity());
			 System.out.println("insert store to database state:"+store.getState());
			 System.out.println("insert store to database zipcode:"+store.getZipcode());
		 michaelsDao.insert(store, true);
		 
		 }

		 }
			 catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
 public    List<Store>  removeDuplicate(List<Store> stores1)  {
	        HashSet<Store>  h  =   new  HashSet<Store> (stores1);
	        stores1.clear();
	        stores1.addAll(h);
	        return stores1;
	     } 	
   
/**@PostConstruct
public void init() {
	
	fetchStores();
	
}

**/
}
