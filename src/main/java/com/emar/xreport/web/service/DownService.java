package com.emar.xreport.web.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.emar.xreport.model.domain.TableColumn;
import org.springframework.stereotype.Service;

import com.emar.xreport.web.domain.JTableData;
import com.emar.xreport.util.excel.ExcelHeader;
import com.emar.xreport.exception.BusinessException;
import com.emar.xreport.util.excel.ExcelUtil;

/**
 * @author Franplk
 * @service Data Service
 * @function Get Table Data, Chart Data; Download Data
 */
@Service
public class DownService {

	/**
	 * Download The Data To Excel
	 */
	public void downLoad(HttpServletResponse response, JTableData tableData) {
		try {
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename='report.xls'");
			OutputStream out = response.getOutputStream();
			List<ExcelHeader> headers = getHeaders(tableData);
			List<Map<String, Object>> dataList = getExportData(tableData);
			ExcelUtil.exportCompExcel(headers, dataList, out, "report");
		} catch (IOException e) {
			throw new BusinessException("[DownLoad Failed]");
		}
	}

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
		List<TableColumn> columnList = tableData.getColumns();
		for (TableColumn column : columnList) {
			if (column != null) {
				headers.add(new ExcelHeader(column.getField(), column.getTitle()));
			}
		}
		return headers;
	}
}
