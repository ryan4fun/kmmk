package com.gps.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.HibernateException;

import com.gps.orm.Role;
import com.gps.ui.Accordion;
import com.gps.ui.Link;
import com.gps.ui.Tab;

public class RoleService extends AbstractService {
	static Logger logger = Logger.getLogger(RoleService.class);
	
	public final static Integer ROLE_PROHIBIT = -1;
	
//	ƽ̨����Ա
	public final static Integer ROLE_SYS_ADMIN = 1;
//	��ҵ����Ա
	public final static Integer ROLE_ORG_ADMIN = 2;
//	�������Ա
	public final static Integer ROLE_RULE_ADMIN = 3;
//	��ѯ����Ա
	public final static Integer ROLE_DISPATCHER = 4;
//	����������ҵ������
	public final static Integer ROLE_OTHER_ORG_ADMIN = 10;
//	����������
	public final static Integer ROLE_VEHICLE_OWNER = 11;
//	����û�
	public final static Integer ROLE_WATCHER = 12;
	
	public static Map<Integer, String> roleNames = new HashMap<Integer, String>();
	static {
		roleNames.put(ROLE_SYS_ADMIN, "平台管理员");
		roleNames.put(ROLE_ORG_ADMIN, "企业管理员");
		roleNames.put(ROLE_RULE_ADMIN, "规则管理员");
		roleNames.put(ROLE_DISPATCHER, "查询调度员");
		roleNames.put(ROLE_OTHER_ORG_ADMIN, "其他运输企业管理者");
		roleNames.put(ROLE_VEHICLE_OWNER, "车辆所有者");
		roleNames.put(ROLE_WATCHER, "监管用户");
	}
	
	static List<Tab> tabs;
	static List<Tab> tzTabs;
	
	public static boolean isAllowed(String role, String roles){
		if(roles.equals("*"))
			return true;
		
		if(!roles.contains(",")){
			return role.equals(roles);			
		} else {
			for(String r:roles.split(",")){
				if(role.equals(r)) return true;
			}
		}
		return false;
	}
	
	public static List<Tab> getTabs() {
		return tabs;
	}
	
	public static List<Tab> getTzTabs() {
		return tzTabs;
	}
	
	public static void initRolePageMap(String rolePath) throws DocumentException{
		tabs = parseRolePageMap(rolePath);
	}

	public void addRole(Role r){		
		try {
			beginTransaction();
			getDAOLocator().getRoleHome().persist(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public void deleteRole(Role r){		
		try {
			beginTransaction();
			getDAOLocator().getRoleHome().delete(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public void updateRole(Role r){		
		try {
			beginTransaction();
			getDAOLocator().getRoleHome().attachDirty(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public Role findById(int id){
		try {
//			beginTransaction();
			Role r = getDAOLocator().getRoleHome().findById(id);
//			commitTransaction();
			return r;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	
	public static void initTzRolePageMap(String rolePath) throws DocumentException{
		tzTabs = parseRolePageMap(rolePath);
	}

	private static List<Tab> parseRolePageMap(String rolePath) throws DocumentException{
		List<Tab> tabs = new ArrayList<Tab>();
		Document doc = loadDocument(rolePath);
		List groups = doc.getRootElement().elements();
		for(int i=0; i<groups.size(); i++){
			Element group = (Element)groups.get(i);
			String groupRoles = group.attributeValue("roles");
			String gName = group.attributeValue("name");
			String folder = group.attributeValue("folder");
			String img = group.attributeValue("img");
			Tab tab = new Tab(gName, folder, groupRoles, img);
			tabs.add(tab);
			List functions = group.elements();
			for(int j=0; j<functions.size(); j++){
				Element function = (Element)functions.get(j);
				String functionRoles = function.attributeValue("roles");
				String fName = function.attributeValue("name");
				String fImg = function.attributeValue("img");
				Accordion accordion = new Accordion(fName, functionRoles, fImg);
				tab.accordions.add(accordion);
				List links = function.elements();
				for(int k=0; k<links.size(); k++){
					Element _link = (Element)links.get(k);
					String linkRoles = _link.attributeValue("roles");
					String lName = _link.attributeValue("name");
					String url = _link.attributeValue("url");
					String lImg = _link.attributeValue("img");
					Link link = new Link(lName, url, linkRoles, lImg);
					accordion.links.add(link);
				}
			}
		}
		return tabs;
	}
	
	private static Document loadDocument(String filePath) throws DocumentException {		
		final SAXReader saxReader = new SAXReader();
		Document result;
		try {
			result = saxReader.read(new File(filePath));			
		} catch (final DocumentException e) {
			logger.error(filePath+" can't load successfully", e);
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
