/**
 * 
 */
package com.gps.testmock;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gps.util.Util;

/**
 * @author Ryan
 *
 */
public class MockPropUtil {

	
	public static Byte getByte(String name){
		String p = name;
		if(p != null && p.length()>0)
			return Byte.parseByte(p);
		return null;
	}
	public static Short getShort(String name){
		String p = name;
		if(p != null && p.length()>0)
			return Short.parseShort(p);
		return null;
	}
	public static Integer getInteger(String name){
		String p = name;
		if(p != null && p.length()>0)
			return Integer.parseInt(p);
		return null;
	}
	public static Long getLong(String name){
		String p = name;
		if(p != null && p.length()>0)
			return Long.parseLong(p);
		return null;
	}
	public static Float getFloat(String name){
		String p = name;
		if(p != null && p.length()>0)
			return Float.parseFloat(p);
		return null;
	}
	public static Double getDouble(String name){
		String p = name;
		if(p != null && p.length()>0)
			return Double.parseDouble(p);
		return null;
	}
	public static BigDecimal getBigDecimal(String name){
		String p = name;
		if(p != null && p.length()>0)
			return new BigDecimal(p);
		return null;
	}
	public static Date getDate(String name){
		String p = name;
		if(p != null){
			try{
				SimpleDateFormat sdf;
				if (p.length() == Util.DATE_FORMAT_SHORT.length()) {
					sdf = new SimpleDateFormat(Util.DATE_FORMAT_SHORT);
				} else if (p.length() == Util.DATE_FORMAT_LONG.length()) {
					sdf = new SimpleDateFormat(Util.DATE_FORMAT_LONG);
				} else {
					return null;
				}
				return sdf.parse(p);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

}
