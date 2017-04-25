package com.emar.cache;

import org.springframework.jdbc.core.JdbcTemplate;

import com.emar.cache.domain.CodeCacheMap;
import com.emar.log.LogService;

public class CacheUpdate {

	private JdbcTemplate dataTemplate;
	
	public void initCache () {
		initTrade();
		initIndustry();
		initChannel();
		update();
	}
	
	public void update () {
		updateProject();
		updateCamp();
	}

	// update trade_type
	private void initTrade () {
		LogService.info("Trade Cache Update...", LogService.TYPE_UPDATE);
		String sql_trade = "SELECT code,name FROM xv_code_trade";
		CodeCacheMap.updateTrade(dataTemplate.queryForList(sql_trade));
	}

	// update industry
	private void initIndustry () {
		LogService.info("Industry Cache Update...", LogService.TYPE_UPDATE);
		String sql_industry = "SELECT id,type FROM xv_map_industry";
		CodeCacheMap.updateIndustry(dataTemplate.queryForList(sql_industry));
	}
	
	// update channel
	private void initChannel () {
		LogService.info("Channel Cache Update...", LogService.TYPE_UPDATE);
		String sql_channel = "SELECT id,name FROM xv_map_channel";
		CodeCacheMap.updateChannel(dataTemplate.queryForList(sql_channel));
	}
	
	// update project
	private void updateProject () {
		LogService.info("Project Cache Update...", LogService.TYPE_UPDATE);
		String sql_project = "SELECT id,name FROM project_info";
		CodeCacheMap.updateProject(dataTemplate.queryForList(sql_project));
	}
	
	// update campaign
	private void updateCamp () {
		LogService.info("Campaign Cache Update...", LogService.TYPE_UPDATE);
		String sql_camp = "SELECT id,name FROM campaign_info";
		CodeCacheMap.updateCamp(dataTemplate.queryForList(sql_camp));
	}
	
	public JdbcTemplate getDataTemplate() {
		return dataTemplate;
	}

	public void setDataTemplate(JdbcTemplate dataTemplate) {
		this.dataTemplate = dataTemplate;
	}
}
