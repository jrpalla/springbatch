/**
 * 
 */
package com.ansri;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author jrpalla
 *
 */
public class SourceDataManager {
	private ScrollableResults scrollableResults;
	private static SourceDataManager sourceDataManager= new SourceDataManager();
	private SourceDataManager(){
	}
	public ScrollableResults initializeScrollableResults(SessionFactory sessionFactory, String table){
		Session session = sessionFactory.openSession();
		String hql = "from " + table;
		Query query = session.createQuery(hql);
		query.setReadOnly(true);
		//query.setFetchSize(Integer.MIN_VALUE);
		scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
		return scrollableResults;
	}
	public boolean closeScrollableResults(){
		if(scrollableResults!=null){
			scrollableResults.close();
			return true;
		}
		return false;
	}
	public ScrollableResults getScrollableResults(){
		if(scrollableResults==null){
			throw new IllegalArgumentException("ScrollableResults was not not initialized");
		}
		return scrollableResults;
	}
	
	public static SourceDataManager getInstance(){
		return sourceDataManager;
	}
}
