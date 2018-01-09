package com.softwarelikeyou.facade;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.dao.DataAccessException;
//import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;

public class PasswordEncoder implements org.springframework.security.providers.encoding.PasswordEncoder {

	@Override
	public String encodePassword(String password, Object salt) throws DataAccessException {
		MessageDigest md = null;
    	try {
    		md = MessageDigest.getInstance("SHA-1");
    	} 
    	catch (NoSuchAlgorithmException ne) {
    		throw new RuntimeException(ne);
    	}
    	
        try {
        	md.update(password.getBytes("UTF-8")); 
        }
        catch(UnsupportedEncodingException e) {
        	throw new RuntimeException(e);
        }

        byte raw[] = md.digest(); 
        //String hash = (new BASE64Encoder()).encode(raw);
        return new String(Base64.encodeBase64(raw));
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {
		return encPass.equals(encodePassword(rawPass, salt));
	}


}
