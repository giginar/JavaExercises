package kucukcinar.yigit.hw2.utils;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class HashUtil {
	
	
	public static String hash(String text)
	{
		try 
		{
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(text.getBytes());
		    byte[] digest = md.digest();
		    String myHash = DatatypeConverter
		      .printHexBinary(digest).toUpperCase();
		    return myHash;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
