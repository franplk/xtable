package com.emar.es;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.emar.model.domain.ConfigColumn;
import com.emar.query.domain.ESQuery;
import com.emar.query.domain.Pagination;
import com.emar.query.filter.ESFilter;
import com.emar.xreport.domain.DateSpan;
import com.emar.xreport.domain.JTableData;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:spring/esconfig.xml")
public class HsitoryTest extends AbstractJUnit4SpringContextTests {

	@Resource(name = "historyTemplate")
	private EsTemplate template;

	@Test
	public void test() {

		ESQuery query = new ESQuery(false);

		// Add Date Span
		query.setDateSpan(new DateSpan("2016-09-24", "2016-09-24"));

		// Add Dim Column
		query.setDimColumn(new ConfigColumn("domain"));

		// Add Index
		List<ConfigColumn> sumFields = new ArrayList<>();
		sumFields.add(new ConfigColumn("bid"));
		sumFields.add(new ConfigColumn("clk"));
		query.setIdxList(sumFields);

		// Add Sort
		query.setSortField(new ConfigColumn("clk"));
		query.setSortType("desc");

		// Add Query Filter
		List<ESFilter> queryFilter = new ArrayList<>();
		// queryFilter.add(new ESFilter("campaign_id", "eq", "31921"));
		query.setDimFilter(queryFilter);

		// Add Pagination
		Pagination page = new Pagination();
		query.setPage(page);

		long b_date = System.currentTimeMillis();
		try {
			JTableData tableData = template.queryTable(query);

			System.out.println("count = " + tableData.getTotal());

			List<Map<String, Object>> sumDatas = tableData.getFooter();
			for (Map<String, Object> sumData : sumDatas) {
				System.out.println(sumData);
			}

			List<Map<String, Object>> datas = tableData.getRows();
			for (Map<String, Object> data : datas) {
				System.out.println(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long e_date = System.currentTimeMillis();

		System.out.println("time = " + (e_date - b_date));
	}
}
