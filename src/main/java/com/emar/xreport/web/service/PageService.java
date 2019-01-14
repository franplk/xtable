package com.emar.xreport.web.service;

import com.emar.xreport.web.domain.EntryPage;
import org.springframework.stereotype.Service;

@Service
public class PageService {
	
	public EntryPage getEntryPage() {
		return new EntryPage("欢迎", "/main/welcome");
	}
}
