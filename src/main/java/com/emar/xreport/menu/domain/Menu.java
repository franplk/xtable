package com.emar.xreport.menu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单类
 * 
 * @author jiaqiang 2015.01.08
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = 6401042065040003502L;

	private String name;
	private String url;
	private List<Menu> subMenuList;// child Menus
	
	public Menu(){
	}
	
	public Menu(String name){
		this.name = name;
	}
	
	public Menu(String name, String url){
		this.name = name;
		this.url = url;
	}
	
	public Menu(String name, List<Menu> subMenuList){
		this.name = name;
		this.subMenuList = subMenuList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Menu> getSubMenuList() {
		return subMenuList;
	}

	public void addSubMenu(Menu menu) {
		if (subMenuList == null) {
			subMenuList = new ArrayList<Menu>();
		}
		subMenuList.add(menu);
	}
	
	public void setSubMenu(List<Menu> subMenuList) {
		this.subMenuList = subMenuList;
	}

	public String toString() {
		StringBuffer info = new StringBuffer();
		
		info.append("[");
		info.append("Name=").append(name).append(";");
		info.append("URL=").append(url).append(";");
		info.append("]");
		
		return info.toString();
	}
}
