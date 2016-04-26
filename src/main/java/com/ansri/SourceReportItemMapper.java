/**
 * 
 */
package com.ansri;

import com.ansri.model.Report;
import com.ansri.model.SourceReport;

/**
 * @author jrpalla
 *
 */
public class SourceReportItemMapper implements SourceItemMapper<Report> {

	public Report map(Object item) {
	
		SourceReport sourceReport=(SourceReport)item;
		// TODO Auto-generated method stubSourceReport sourceReport=(SourceReport)results.get(0);
		Report report= new Report();
		report.setId(sourceReport.getId());
		report.setQty(sourceReport.getQty());
		report.setSales(sourceReport.getSales());
		report.setStaffName(sourceReport.getStaffName());
		return report;
	}

}
