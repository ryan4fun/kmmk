package com.gps.util;

import javax.crypto.*;
import java.io.*;
import javax.crypto.spec.*;
import java.security.spec.*;

public class DesEncrypter {
	private static DesEncrypter desEncrypter;
	static{
		desEncrypter = new DesEncrypter();
	}
	private static Cipher ecipher;
	private static Cipher dcipher;
	
	// 8-byte Salt
	byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
			(byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03 };
	// Iteration count 
	int iterationCount = 19;
	
	DesEncrypter(){
		this("MKGPS_PRIVATE_KEY");
	}

	DesEncrypter(String passPhrase) {
		try {
			//Create the key 
			KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt,
					iterationCount);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);
			ecipher = Cipher.getInstance(key.getAlgorithm());
			dcipher = Cipher.getInstance(key.getAlgorithm());

			// Prepare the parameter to the ciphers 
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

			// Create the ciphers 
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		} catch (java.security.InvalidAlgorithmParameterException e) {
		} catch (java.security.spec.InvalidKeySpecException e) {
		} catch (javax.crypto.NoSuchPaddingException e) {
		} catch (java.security.NoSuchAlgorithmException e) {
		} catch (java.security.InvalidKeyException e) {
		}
	}

	public static String encrypt(String str) {
		try {
			//Encode the string into bytes using utf-8 
			byte[] utf8 = str.getBytes("UTF8");
			// Encrypt 
			byte[] enc = ecipher.doFinal(utf8);

			//Encode bytes to base64 to get a string 
			return new sun.misc.BASE64Encoder().encode(enc);
		} catch (javax.crypto.BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (java.io.IOException e) {
		}
		return null;
	}

	public static String decrypt(String str) {
		try {
			// Decode base64 to get bytes 
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
			// Decrypt 
			byte[] utf8 = dcipher.doFinal(dec);
			// Decode using utf-8 
			return new String(utf8, "UTF8");
		} catch (javax.crypto.BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (java.io.IOException e) {
		}
		return null;
	}

	// Here is an example that uses the class 

	public static void main(String s[]) {
		try {
			// Encrypt 
			String encrypted = DesEncrypter.encrypt("111111");
			System.out.println(encrypted);
			// Decrypt 
			String decrypted = DesEncrypter.decrypt(encrypted);
			System.out.println(decrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
