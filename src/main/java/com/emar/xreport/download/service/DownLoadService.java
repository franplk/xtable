package com.emar.xreport.download.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.emar.xreport.web.domain.JTableData;
import com.emar.xreport.download.domain.ExcelHeader;
import com.emar.exception.BussinessException;
import com.emar.xreport.download.ExcelUtil;

/**
 * @author Franplk
 * @service Data Service
 * @function Get Table Data, Chart Data; Download Data
 */
@Service
public class DownLoadService extends BaseService {

	/**
	 * Download The Data To Excel
	 */
	@Override
	public void downLoad(HttpServletResponse response, JTableData tableData) {
		// Organize The DownLoad Data
		List<ExcelHeader> headers = getHeaders(tableData);
		List<Map<String, Object>> dataList = getExportData(tableData);
		
		// Deploy The DownLoad
		try {
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename='report.xls'");
			OutputStream out = response.getOutputStream();
			ExcelUtil.exportCompExcel(headers, dataList, out, "report");
		} catch (IOException e) {
			throw new BussinessException("[DownLoad Failed]", e);
		}
	}
}
