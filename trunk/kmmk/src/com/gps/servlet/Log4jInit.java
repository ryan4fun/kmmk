package com.gps.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class Log4jInit extends HttpServlet {
	static Logger logger = Logger.getLogger(Log4jInit.class);

	public Log4jInit() {
	}

	public void init(ServletConfig config) throws ServletException {
		String prefix = config.getServletContext().getRealPath("/");
		String file = config.getInitParameter("log4j");
		String filePath = prefix + file;
		Properties props = new Properties();
		try {
			FileInputStream istream = new FileInputStream(filePath);
			props.load(istream);
			istream.close();

			props.setProperty("log4j.appender.error.File", prefix + props.getProperty("log4j.appender.error.File"));
			props.setProperty("log4j.appender.rolling_file.File", prefix + props.getProperty("log4j.appender.rolling_file.File"));
			props.setProperty("log4j.appender.rolling_file_order.File", prefix + props.getProperty("log4j.appender.rolling_file_order.File"));

			PropertyConfigurator.configure(props);
		} catch (IOException e) {
			toPrint("Could not read configuration file [" + filePath + "].");
			toPrint("Ignoring configuration file [" + filePath + "].");
		}

	}

	public static void toPrint(String content) {
		System.out.println(content);
	}

}
