/**
 * 
 */
package com.ansri;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ansri.model.SourceReport;

/**
 * @author jrpalla
 *
 */
public class LoadTestData  {
	
	private	LoadTestData(SessionFactory sessionFactory,long loadLimit)throws Exception {
		int count=1;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Date date=new Date();
		for(int i=1;i<=loadLimit;i++){
			count++;
			SourceReport item= new SourceReport();
			item.setId(i);
			item.setQty(count);
			item.setSales(new BigDecimal(count));
			item.setStaffName("Palla");
			item.setDate(date);
			session.save(item);
			if(count==50){
				session.flush();
				session.clear();
				count=1;
			}
			
		}
		tx.commit();
		session.close();
		System.out.println("data load complete");
		
	}

}
