/**
 * 
 */
package com.ansri.model;

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
public class DbPaginatedItemWriter1<T> implements ItemWriter<List<T>>, InitializingBean{
	private SessionFactory sessionFactory;
		
	public synchronized void write(List<? extends List<T>> itemsCollection) throws Exception {
		//System.out.println("Item Writer: Thread Id:"+Thread.currentThread().getId());
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for(List<T> items:itemsCollection){
			//System.out.println("**** saving "+items.size()+" records ****");
			for(T item:items){
				//System.out.println("writing report "+item);
				session.save(item);
			}
			session.flush();
			session.clear();
		}
		tx.commit();
		session.close();
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void afterPropertiesSet() throws Exception {
		if(sessionFactory==null){
			throw new IllegalArgumentException("sessionFactory is null");
		}
			
	}

}
