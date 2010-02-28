package com.gps.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileItem;

import com.gps.servlet.MKgpsServlet;

public class Util {
	public final static String DATE_FORMAT_SHORT = "yyyy/MM/dd";
	public final static String DATE_FORMAT_MID = "yyyy/MM/dd HH点";
	public final static String DATE_FORMAT_LONG = "yyyy/MM/dd HH:mm:ss";
	
	public final static int DAY_MILSEC = 24 * 60 * 60 *1000;
	public final static int HOUR_MILSEC = 60 * 60 *1000;
	public final static int MIN_MILSEC = 60 * 1000;
	
	public final static double EARTH_RADIUS = 6378.137;
//	public final static double EARTH_CIRCUMFERENCE = 2 * EARTH_RADIUS * Math.PI;
	
//	the lat and long offset of ditu.google.cn , use properties instead
	public final static Double CN_OFFSET_LAT = -0.003061370023;
	public final static Double CN_OFFSET_LON = 0.001292824745;
	
	// the lat and long of kun ming
	public final static Double CENTER_LAT = 25.05211;
	public final static Double CENTER_LON = 102.693908;
	// the lat and long of yun nan
	public final static Double MIN_LAT = 21.125497636606276;
	public final static Double MAX_LAT = 28.5941685062326;
	public final static Double MIN_LON = 96.83349609375;
	public final static Double MAX_LON = 105.732421875;
	// the lat and long of china
//	public final static Double MIN_LAT = 53.55;
//	public final static Double MAX_LAT = 3.87;
//	public final static Double MIN_LON = 135.04;
//	public final static Double MAX_LON = 73.67;
	
	public static Date parseDate(String dateStr){
		if(dateStr != null){
			SimpleDateFormat sdf = null;
			if (((String)dateStr).length() == Util.DATE_FORMAT_SHORT.length()) {
				sdf = new SimpleDateFormat(Util.DATE_FORMAT_SHORT);
			} else if (((String)dateStr).length() == Util.DATE_FORMAT_LONG.length()) {
				sdf = new SimpleDateFormat(Util.DATE_FORMAT_LONG);
			}
			
			try{
				if (sdf != null)
					return sdf.parse(dateStr);
			} catch(Exception e){
			}
		}
		return null;
	}
	
	public static Date getCurrentDateTime(){
		return new Date(Calendar.getInstance().getTimeInMillis());
	}
	
	public static Date getYesterdayTime(){
		Calendar yesterday = Calendar.getInstance();
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE)-1);
		return new Date(yesterday.getTimeInMillis());
	}
	
	public static String FormatDateLong(Date date){
		if(date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_LONG);
		return sdf.format(date);
	}
	
	public static String FormatDateShort(Date date){
		if(date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SHORT);
		return sdf.format(date);
	}
	
	public static String FormatDateMid(Date date){
		if(date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_MID);
		return sdf.format(date);
	}
	
	public static Date getCurrentDate(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getYesterDay(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)-1);
		return cal.getTime();
	}
	
	public static Date getNextMonth(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)+30);
		return cal.getTime();
	}
	
	public static FileItem getFileItem(List items, String name){
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = (FileItem) iter.next();
		    if (item.getFieldName().equals(name)) {
		    	return item;
		    }
		}
		return null;
	}
	
	public static List<FileItem> getFileItems(List items, String name){
		List<FileItem> fis = new ArrayList<FileItem>();
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = (FileItem) iter.next();
		    if (item.getFieldName().equals(name)) {
		    	fis.add(item);
		    }
		}
		return fis;
	}
	
	private static String saveFile(String path, String postfix, byte[] content) throws IOException{		
		String name = getFileName(path, postfix);	
		File f = new File(path+name);
		FileOutputStream os = new FileOutputStream(f);
		os.write(content);
		os.close();		
		return name;
	}
	
	private static String getFileName(String path, String postfix){
		String name = String.valueOf(System.currentTimeMillis());
		if (!path.endsWith("/") && !path.endsWith("\\")){
			path += File.separator;
		}
		File folder = new File(path);
		if (!folder.exists()){
			folder.mkdirs();
		}
		if (!postfix.startsWith(".")){
			postfix = "."+postfix;
		}
		File test = new File(path+name + postfix);
		if(test.exists()){
			return getFileName(path, postfix);
		}
		return name + postfix;
	}
	
	public synchronized static String saveFile(FileItem imageItem, String folderName) throws IOException{
		if (imageItem != null && !imageItem.getString().equals("") && imageItem.get()!=null){
			String postfix = imageItem.getName().substring(imageItem.getName().lastIndexOf("."));
			String relativePath = "user_images"+File.separator+folderName+File.separator;
			String absolutePath = MKgpsServlet.WEB_ROOT_ABSOLUTE_PATH + relativePath;
			String imageName = relativePath+saveFile(absolutePath, postfix, imageItem.get());
			imageName = imageName.replaceAll("\\\\", "/");			
			return imageName;
		}
		return null;
	}
	
	public static void setNull2DefaultValue(Object o){
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(o);
		for(PropertyDescriptor pd:pds){
			try {
				if (PropertyUtils.getProperty(o, pd.getName()) == null) {
					Class c = pd.getPropertyType();
					Object tmps = null;
					if (c.getName().equals("java.lang.String")) {
						tmps = "";
//					} else if (c.getName().equals("java.lang.Byte")
//							|| c.getName().equals("byte")) {
//						tmps = (byte) 0;
//					} else if (c.getName().equals("java.lang.Short")
//							|| c.getName().equals("short")) {
//						tmps = (short) 0;
//					} else if (c.getName().equals("java.lang.Integer")
//							|| c.getName().equals("int")) {
//						tmps = 0;
//					} else if (c.getName().equals("java.lang.Long")
//							|| c.getName().equals("long")) {
//						tmps = (long) 0;
//					} else if (c.getName().equals("java.lang.Float")
//							|| c.getName().equals("float")) {
//						tmps = (float) 0;
//					} else if (c.getName().equals("java.lang.Double")
//							|| c.getName().equals("double")) {
//						tmps = (double) 0;
//					} else if (c.getName().equals("java.util.Date")) {
//						tmps = Calendar.getInstance().getTime();
//					} else if (c.getName().equals("java.math.BigDecimal")) {
//						tmps = new BigDecimal(0);
					}

					if (tmps == null)
						continue;

					PropertyUtils.setProperty(o, pd.getName(), tmps);
				}
			} catch (Exception e) {

			}
		}
	}
	
	
	
	public static void setNull2DefaultValue4Display(Object o){
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(o);
		for(PropertyDescriptor pd:pds){
			try {
				if (PropertyUtils.getProperty(o, pd.getName()) == null) {
					Class c = pd.getPropertyType();
					Object tmps = null;
					if (c.getName().equals("java.lang.String")) {
						tmps = "";
					} else if (c.getName().equals("java.lang.Byte")
							|| c.getName().equals("byte")) {
						tmps = (byte) 0;
					} else if (c.getName().equals("java.lang.Short")
							|| c.getName().equals("short")) {
						tmps = (short) 0;
					} else if (c.getName().equals("java.lang.Integer")
							|| c.getName().equals("int")) {
						tmps = 0;
					} else if (c.getName().equals("java.lang.Long")
							|| c.getName().equals("long")) {
						tmps = (long) 0;
					} else if (c.getName().equals("java.lang.Float")
							|| c.getName().equals("float")) {
						tmps = (float) 0;
					} else if (c.getName().equals("java.lang.Double")
							|| c.getName().equals("double")) {
						tmps = (double) 0;
					} else if (c.getName().equals("java.util.Date")) {
						tmps = Calendar.getInstance().getTime();
					} else if (c.getName().equals("java.math.BigDecimal")) {
						tmps = new BigDecimal(0);
					}

					if (tmps == null)
						continue;

					PropertyUtils.setProperty(o, pd.getName(), tmps);
				}
			} catch (Exception e) {

			}
		}
	}
	
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double CalculateLatLng2Distance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 100000d) / 100000d;
		return s;
	}

	public static double CalculateDistance2LatGap(double distance) {
//		return distance / (EARTH_CIRCUMFERENCE / 360);
		return distance / 111.133;
	}
	
	public static double CalculateDistance2LongGap(double centerLat, double distance){
//		return distance / (Math.cos(centerLat) * EARTH_CIRCUMFERENCE / 360);
		return -distance / (Math.cos(centerLat) * 111.413);
	}
	
	public static String writeOptions(Map options){
		return writeOptions(options, null);
	}
	
	public static String writeOptions(Map options, String first){
		StringBuffer out = new StringBuffer();
		if(first!=null && !first.equals("")){
			out.append("<option value=\"\">");
			out.append(first);
			out.append("</option>");
		}
		Iterator it = options.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();           
            out.append("<option value=\"").append(entry.getKey()).append("\">");
			out.append(entry.getValue());
			out.append("</option>");
        }	
		return out.toString();
	}
	
	public static String escape(String src) {
		if (src == null)
			return null;
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		if (src == null)
			return null;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	
	public static int getDays(long longValue){
		
		int result = (int) (longValue / DAY_MILSEC);
		
		return result;
	}
	
	public static int getHours(long longValue){
		
		int result = (int) (longValue % DAY_MILSEC);
		result = result / HOUR_MILSEC;
		return result;
	}
	
	public static int getMins(long longValue){
		
		int result = (int) (longValue % DAY_MILSEC);
		result = result % HOUR_MILSEC;
		result = result / MIN_MILSEC;
		return result;
	}
	
	
	public static String formateLongToDays(long longValue){
		
		StringBuffer buf = new StringBuffer(30);
		
		int days = getDays(longValue);
		if(days > 0) {
			buf.append(days);
			buf.append("天 ");
		}
		
		int hours = getHours(longValue);
		if(hours > 0) {
			buf.append(hours);
			buf.append("小时 ");
		}
		
		int mins = getMins(longValue);
		if(mins > 0) {
			buf.append(mins);
			buf.append("分钟 ");
		}
		
		return buf.toString();
	}
	
	
	public static void main(String[] args) {
		System.out.println(25.06148417706757-25.064545547090517);
		System.out.println(102.6527738571167-102.65148103237152);
		
		
		Date now = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)+1); 
		cal.set(Calendar.MINUTE, 33);
//		cal.set(Calendar.SECOND, 0);
//		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)-1);
		
		long val = now.getTime() - cal.getTime().getTime();
		
		String result = formateLongToDays(val);
		
//		double result = Util.CalculateLatLng2Distance(24.8568458, 102.9789733, 26.9564, 104.02532);
		System.out.print(" resul :" + result); 
	}

}
