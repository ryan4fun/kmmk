package com.gps.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;



public class BeanUtils {
	public static void copyProperties(Object dest, Object orig){
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}
	}
	
	public static String describe(Object bean) {
		StringBuffer buf = new StringBuffer();
		try {
			Map map = org.apache.commons.beanutils.BeanUtils.describe(bean);
			Set keySet = map.keySet();
			for (Iterator iter = keySet.iterator(); iter.hasNext();) {
				Object element = (Object) iter.next();
				buf.append("[Key Class:" + element.getClass().getName());
				buf.append(" Value Class:" + map.get(element).getClass().getName()).append("] ");
				buf.append("["+element + "=");
				buf.append(map.get(element)).append("]");
				buf.append("\r");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
	
	public static void generateAllSimpleProp(Object o){
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(o);
		for(PropertyDescriptor pd:pds){
			Class c = pd.getPropertyType();
			
			try {
				if(PropertyUtils.getProperty(o, pd.getName()) == null) {
					Object tmps = null;
					if (c.getName().equals("java.lang.String")) {
						tmps = "";
					} else if (c.getName().equals("java.lang.Byte") || c.getName().equals("byte")) {
						tmps = 0;
					} else if (c.getName().equals("java.lang.Short") || c.getName().equals("short")) {
						tmps = 0;
					}else if (c.getName().equals("java.lang.Integer") || c.getName().equals("int")) {
						tmps = 0;
					} else if (c.getName().equals("java.lang.Long") || c.getName().equals("long")) {
						tmps = 0;
					}else if (c.getName().equals("java.lang.Float") || c.getName().equals("float")) {
						tmps = 0;
					} else if (c.getName().equals("java.lang.Double") || c.getName().equals("double")) {
						tmps = 0;
					} else if (c.getName().equals("java.util.Date")) {
						tmps = new Date();
					} else if (c.getName().equals("java.math.BigDecimal")) {
						tmps = new BigDecimal("0");
					}
					
					if (tmps == null)
						continue;
					try {
						PropertyUtils.setProperty(o, pd.getName(), tmps);
					} catch (Exception e) {
						
					}
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
