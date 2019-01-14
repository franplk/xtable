package com.emar.xreport.download.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.emar.xreport.download.domain.ExcelHeader;
import com.emar.xreport.model.domain.TableColumn;
import com.emar.xreport.web.domain.JTableData;

/**
 * @author Franplk
 *
 */
public abstract class BaseService {

	public abstract void downLoad(HttpServletResponse res, JTableData tableData);

	/**
	 * Combine The Data To DownLaod
	 */
	protected List<Map<String, Object>> getExportData(JTableData tableData) {
		List<Map<String, Object>> dataList = new ArrayList<>();

		List<Map<String, Object>> listData = tableData.getRows();
		if (listData != null) {
			dataList.addAll(listData);
		}

		List<Map<String, Object>> sumData = tableData.getFooter();
		if (sumData != null) {
			dataList.addAll(sumData);
		}
		return dataList;
	}

	/**
	 * Combine The Excel Headers
	 */
	protected List<ExcelHeader> getHeaders(JTableData tableData) {
		List<ExcelHeader> headers = new ArrayList<ExcelHeader>();

		// Add Columns
		List<TableColumn> columnList = tableData.getColumns();
		for (TableColumn column : columnList) {
			if (column != null) {
				headers.add(new ExcelHeader(column.getField(), column.getTitle()));
			}
		}

		return headers;
	}
}
