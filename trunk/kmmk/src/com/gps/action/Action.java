package com.gps.action;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gps.bean.LoginInfo;
import com.gps.service.ServiceLocator;
import com.gps.util.Util;



public abstract class Action {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected final static String SUCCESS = "success";
	
	private FileItemFactory factory;
	private ServletFileUpload upload;
	List<FileItem> items;
	
	boolean isMultipart = false;
	
	public void initMultiPart(List items) throws Exception{
		this.items = items;
		isMultipart = true;
	}
	
	protected FileItem getFileItem(String name){
		return Util.getFileItem(items, name);
	}
	
	protected List<FileItem> getFileItems(String name){
		return Util.getFileItems(items, name);
	}
	
	protected String getFileItemValue(String name){
		try {
			if (getFileItem(name)!=null)
				return getFileItem(name).getString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected String[] getFileItemValues(String name){
		try {
			List<String> values = new ArrayList<String>();
			List<FileItem> items = getFileItems(name);
			if (items!=null){
				for(FileItem item: items){
					values.add(item.getString("UTF-8"));
				}
			}
			return (String[])values.toArray(new String[0]);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public void setRequest(HttpServletRequest request){
		this.request = request;
	}
	public void setResponse(HttpServletResponse response){
		this.response = response;
	}
	protected String get(String name){
		if(request == null)
			return null;
		String v = isMultipart? getFileItemValue(name):request.getParameter(name);
		if(v == null)
			v = (String)request.getAttribute(name);
		return v;
	}
	protected String getWithDecode(String name) {
		String value = get(name);
		if(value != null) {
			try {
				value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				value = null;
			}
		}
		return value;
	}
	protected boolean getBoolean(String name){
		String p = get(name);
		if(p != null)
			return p.equalsIgnoreCase("true");
		return false;
	}
	protected Byte getByte(String name){
		String p = get(name);
		if(p != null && p.length()>0)
			return Byte.parseByte(p);
		return null;
	}
	protected Short getShort(String name){
		String p = get(name);
		if(p != null && p.length()>0)
			return Short.parseShort(p);
		return null;
	}
	protected Integer getInteger(String name){
		String p = get(name);
		if(p != null && p.length()>0)
			return Integer.parseInt(p);
		return null;
	}
	protected Long getLong(String name){
		String p = get(name);
		if(p != null && p.length()>0)
			return Long.parseLong(p);
		return null;
	}
	protected Float getFloat(String name){
		String p = get(name);
		if(p != null && p.length()>0)
			return Float.parseFloat(p);
		return null;
	}
	protected Double getDouble(String name){
		String p = get(name);
		if(p != null && p.length()>0)
			return Double.parseDouble(p);
		return null;
	}
	protected BigDecimal getBigDecimal(String name){
		String p = get(name);
		if(p != null && p.length()>0)
			return new BigDecimal(p);
		return null;
	}
	protected Date getDate(String name){
		String p = get(name);
		if(p != null){
			try{
				SimpleDateFormat sdf;
				if (p.length() == Util.DATE_FORMAT_SHORT.length()) {
					sdf = new SimpleDateFormat(Util.DATE_FORMAT_SHORT);
				} else if (p.length() == Util.DATE_FORMAT_LONG.length()) {
					sdf = new SimpleDateFormat(Util.DATE_FORMAT_LONG);
				} else if(p.length() == 23){
					p=p.substring(0,p.length()-4);
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				}else{
					return null;
				}
				return sdf.parse(p);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
	protected String[] getArray(String name){
		return isMultipart? getFileItemValues(name): request.getParameterValues(name);
	}
	protected static ServiceLocator getServiceLocator(){
		return ServiceLocator.getInstance();
	}
	
	protected void generateAllSimpleProp(Object o){
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(o);
		for(PropertyDescriptor pd:pds){
			Class c = pd.getPropertyType();
			Object tmps = null;
			if (c.getName().equals("java.lang.String")) {
				tmps = get(pd.getName());
			} else if (c.getName().equals("java.lang.Byte") || c.getName().equals("byte")) {
				tmps = this.getByte(pd.getName());
			} else if (c.getName().equals("java.lang.Short") || c.getName().equals("short")) {
				tmps = this.getShort(pd.getName());
			}else if (c.getName().equals("java.lang.Integer") || c.getName().equals("int")) {
				tmps = this.getInteger(pd.getName());
			} else if (c.getName().equals("java.lang.Long") || c.getName().equals("long")) {
				tmps = this.getLong(pd.getName());
			}else if (c.getName().equals("java.lang.Float") || c.getName().equals("float")) {
				tmps = this.getFloat(pd.getName());
			} else if (c.getName().equals("java.lang.Double") || c.getName().equals("double")) {
				tmps = this.getDouble(pd.getName());
			} else if (c.getName().equals("java.util.Date")) {
				tmps = this.getDate(pd.getName());
			} else if (c.getName().equals("java.math.BigDecimal")) {
				tmps = this.getBigDecimal(pd.getName());
			}
			
			if (tmps == null)
				continue;
			try {
				PropertyUtils.setProperty(o, pd.getName(), tmps);
			} catch (Exception e) {
				
			}
		}
	}
	
	protected int getCurrentUserId(){
		LoginInfo login = (LoginInfo)request.getSession().getAttribute("login");
		return login.getUserId();
	}
	
	
	public abstract void doAction() throws Exception;
	
}
