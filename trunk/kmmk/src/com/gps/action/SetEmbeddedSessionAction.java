package com.gps.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.hibernate.Criteria;
import org.hibernate.connection.ConnectionProviderFactory;
import org.hibernate.criterion.Restrictions;

import com.gps.Message;
import com.gps.bean.LoginInfo;
import com.gps.orm.FUser;
import com.gps.orm.HibernateUtil;
import com.gps.service.FUserService;
import com.gps.servlet.MKgpsServlet;
import com.gps.util.Util;

public class SetEmbeddedSessionAction extends Action{

	public static final String basePath = MKgpsServlet.WEB_ROOT_ABSOLUTE_PATH + "WEB-INF"+File.separator+"reports" +File.separator;
	  
	@Override
	public void doAction() throws Exception{
		String embedded = get("embedded");
		if(embedded.equals("true")){
			
		} else {
			request.getSession().removeAttribute("embedded");
			request.getSession().removeAttribute("vehicleId");
		}
	}
}
