/**
 * 
 */
package com.ansri;

import org.hibernate.ScrollableResults;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author jrpalla
 *
 */
public class DbPaginatedItemReader<T> implements ItemReader<T>, InitializingBean{
	private int readLimit=3;
	private SourceItemMapper<T> sourceItemMapper;
	public synchronized T read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		//System.out.println("Item Reader: Thread Id:"+Thread.currentThread().getId());
		ScrollableResults results = SourceDataManager.getInstance().getScrollableResults();
		if(results.next()){
			T item=this.sourceItemMapper.map(results.get(0));
			//System.out.println(" reading report "+item);
			return item;
		}else{
			return null;
		}
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
