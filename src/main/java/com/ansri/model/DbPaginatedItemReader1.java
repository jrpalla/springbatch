/**
 * 
 */
package com.ansri.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ScrollableResults;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;

import com.ansri.SourceDataManager;
import com.ansri.SourceItemMapper;

/**
 * @author jrpalla
 *
 */
public class DbPaginatedItemReader1<T> implements ItemReader<List<T>>, InitializingBean{
	private int readLimit=3;
	private SourceItemMapper<T> sourceItemMapper;
	public synchronized List<T> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		//System.out.println("Item Reader: Thread Id:"+Thread.currentThread().getId());
		List<T> items= new ArrayList<T>(readLimit);
		ScrollableResults results = SourceDataManager.getInstance().getScrollableResults();
		int i=0;
		while (i<readLimit) {
			if(results.next()){
				T item=this.sourceItemMapper.map(results.get(0));
				//System.out.println(" reading report "+item);
				items.add(item);
				i++;
			}else{
				return null;
			}
		}
		return items;
	}

	public int getReadSize() {
		return readLimit;
	}
	public void setReadSize(int readSize) {
		this.readLimit = readSize;
	}

	public SourceItemMapper<T> getSourceItemMapper() {
		return sourceItemMapper;
	}

	public void setSourceItemMapper(SourceItemMapper<T> sourceItemMapper) {
		this.sourceItemMapper = sourceItemMapper;
	}
	

	public int getReadLimit() {
		return readLimit;
	}

	public void setReadLimit(int readLimit) {
		this.readLimit = readLimit;
	}

	public void afterPropertiesSet() throws Exception {
		if(sourceItemMapper==null){
			throw new IllegalArgumentException("SourceItemMapper is null");
		}
	}	

}
