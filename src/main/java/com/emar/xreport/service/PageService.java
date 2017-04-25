package com.emar.xreport.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.emar.model.domain.Template;
import com.emar.sso.bean.User;
import com.emar.xreport.domain.EntryPage;

@Service
public class PageService {
	
	public EntryPage getEntrypage(User user) {
		return new EntryPage("全平台统计", "/board/5");
	}
	
	public EntryPage getEntrypage_new(User user) {
		Map<String, Template> templates = user.getTemplates();
		if (templates == null || templates.isEmpty()) {
			return new EntryPage("全平台统计", "/board/5");
		} else {
			Template topTemplate = templates.get(0);
			String name = topTemplate.getTitle();
			return new EntryPage(name, "/template/" + topTemplate.getTid());
		}
	}
}
