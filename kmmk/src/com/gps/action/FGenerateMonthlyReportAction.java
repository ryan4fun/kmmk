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

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

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

public class FGenerateMonthlyReportAction extends Action{

	public static final String basePath = MKgpsServlet.WEB_ROOT_ABSOLUTE_PATH + "WEB-INF"+File.separator+"reports" +File.separator;
	  
	@Override
	public void doAction() throws Exception{
		
//		String rptName = get("reportName");
		String vehicleId = get("vehicleId");
		String yearMonth = get("yearMonth");
		
		if(vehicleId != null && yearMonth != null){

			SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
			Date startDate = df.parse(yearMonth);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.MONTH, 1);		
			Date endDate = cal.getTime();
			
			File reportFile = new File(basePath+"vehicle_monthly.jasper");  
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String conStr =  "jdbc:sqlserver://localhost:1433;databaseName=mkgps1";
			Connection connection = DriverManager.getConnection(conStr,"sa","1234");
		
			Map parameters = new HashMap();  
			
			parameters.put("vehicleId", Integer.parseInt(vehicleId));
			parameters.put("startDate", startDate);
			parameters.put("endDate", endDate);
			
			parameters.put("SUBREPORT_DIR",basePath);
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath()); 
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,connection);  
			response.setContentType("application/pdf");  
			
			FileOutputStream of = new FileOutputStream(basePath+"test.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint,of);
			of.flush();
			of.close();
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//			byte[] generatedPDF =  JasperExportManager.exportReportToPdf(jasperPrint);
			
//			FileOutputStream  os = new FileOutputStream(basePath+"test.pdf");
//			JasperExportManager.exportReportToPdfStream(jasperPrint, os);
//
//			os.flush();
//			os.close();
			 
//			 if (jasperPrint != null) {  
//		            response.setContentType("application/pdf");  
//		            OutputStream ouputStream = response.getOutputStream();  
//		            ouputStream.write(generatedPDF);
//
//		            ouputStream.flush();  
//		            ouputStream.close();  
//			 }
		}
	       
	}
}
