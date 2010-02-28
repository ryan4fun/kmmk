package com.gps.action;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.gps.bean.VerifyCode;

public class GetVarifyImgAction extends Action{

	@Override
	public void doAction() throws Exception{		
		response.setContentType("image/jpeg");		
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			VerifyCode v = new VerifyCode();
			String s = v.runVerifyCode(4);
			request.getSession().setAttribute("verifyCode", s);
			BufferedImage img = v.CreateImage(s);
			ImageIO.write(img, "JPEG", response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			request.setAttribute("forwarded", "true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
