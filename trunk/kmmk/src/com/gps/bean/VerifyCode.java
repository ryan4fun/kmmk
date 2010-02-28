package com.gps.bean;

import java.awt.*;
import java.awt.image.*;
import java.util.*;


//

public class VerifyCode {
	static Random r = new Random();
	static String ssource = "ABCDEFGHJKMNPQRSTUVWXYZ"
			+ "abcdefghjkmnpqrstuvwxyz" + "123456789";
	static char[] src = ssource.toCharArray();


	private static String randString(int length) {
		char[] buf = new char[length];
		int rnd;
		for (int i = 0; i < length; i++) {
			rnd = Math.abs(r.nextInt()) % src.length;

			buf[i] = src[rnd];
		}
		return new String(buf);
	}

	public String runVerifyCode(int i) {
		String VerifyCode = randString(i);
		return VerifyCode;
	}


	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public BufferedImage CreateImage(String sCode) {
		try {
			
			Font CodeFont = new Font("Arial Black", Font.PLAIN, 16);
			int iLength = sCode.length(); 
			int width = 22 * iLength, height = 20; 
			int CharWidth = (int) (width - 24) / iLength; 
			int CharHeight = 16; 

			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			
			Graphics g = image.getGraphics();

			
			Random random = new Random();

			
			g.setColor(getRandColor(200, 240));
			g.fillRect(0, 0, width, height);

			
			g.setFont(CodeFont);

			
			g.setColor(getRandColor(10, 50));
			g.drawRect(0, 0, width - 1, height - 1);

			
			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 155; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}

			for (int i = 0; i < iLength; i++) {
				String rand = sCode.substring(i, i + 1);
				
				g.setColor(new Color(20 + random.nextInt(60), 20 + random
						.nextInt(120), 20 + random.nextInt(180)));
				g.drawString(rand, CharWidth * i + 14, CharHeight);
			}
			
			g.dispose();
			return image;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}