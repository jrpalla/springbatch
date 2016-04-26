/**
 * 
 */
package com.ansri;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author jrpalla
 *
 */
public class DbPaginatedItemWriter<T> implements ItemWriter<T>, InitializingBean{
	private SessionFactory sessionFactory;
	private int batchUpdateSize;//TODO check with Sam if this is required	
	public synchronized void write(List<? extends T> items) throws Exception {
		//System.out.println("Item Writer: Thread Id:"+Thread.currentThread().getId());
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//System.out.println("**** saving "+items.size()+" records ****");
		int count=1;	
		for(T item:items){
			//System.out.println("writing report "+item);
			session.save(item);
			count++;
			if(count==batchUpdateSize){
				session.flush();
				session.clear();
				count=1;
			}
		}
		//flush any remaining records
		session.flush();
		session.clear();


		tx.commit();
		session.close();
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int getBatchUpdateSize() {
		return batchUpdateSize;
	}
	public void setBatchUpdateSize(int batchUpdateSize) {
		this.batchUpdateSize = batchUpdateSize;
	}
	public void afterPropertiesSet() throws Exception {
		if(sessionFactory==null){
			throw new IllegalArgumentException("sessionFactory is null");
		}
	}

}
