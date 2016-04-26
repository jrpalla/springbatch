package com.ansri.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="SOURCE_REPORT")
public class SourceReport {
	@Id 
	@Column(name = "ID")
	private int id;
	
	@Column(name = "SALES")
	private BigDecimal sales;
	
	@Column(name = "QTY")
	private int qty;
	
	@Column(name = "STAFF_NAME")
	private String staffName;
	
	@Column(name = "DATE")
	private Date date;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public BigDecimal getSales() {
		return sales;
	}

	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}

	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}


	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", sales=" + sales 
                    + ", qty=" + qty + ", staffName=" + staffName + "]";
	}

}
