package com.application.security;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class AESEncryption {
	private SecretKeySpec secretKey ;
    private byte[] key ;
    
	private String decryptedString;
	private String encryptedString;
 
	public AESEncryption(String myKey) {
		this.setKey(myKey);
	}
	
    public void setKey(String myKey) {
    	MessageDigest sha = null;
    	
		try {
			this.key = myKey.getBytes("UTF-8");
			
			sha = MessageDigest.getInstance("SHA-1");
			
			this.key = sha.digest(key);
			this.key = Arrays.copyOf(key, 16);

	    	this.secretKey = new SecretKeySpec(key, "AES");    
		}
		catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			noSuchAlgorithmException.printStackTrace();
		}
		catch (UnsupportedEncodingException unsupportedEncodingException) {
			unsupportedEncodingException.printStackTrace();
		}
    }
    
    public final String getDecryptedString() {
		return this.decryptedString;
	}

    public final String getEncryptedString() {
		return this.encryptedString;
	}
    
	public void setDecryptedString(String decryptedString) {
		this.decryptedString = decryptedString;
	}

	public void setEncryptedString(String encryptedString) {
		this.encryptedString = encryptedString;
	}

	public boolean encrypt(String encryptingString) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
            this.setEncryptedString(Base64.encodeBase64String(cipher.doFinal(encryptingString.getBytes("UTF-8"))));
            return true;
        }
        catch (Exception exception) {
            System.out.println("Error while encrypting");
        }
        return false;
    }

    public boolean decrypt(String decryptingString) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            this.setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(decryptingString)))); 
            return true;
        }
        catch (Exception exception) {
            System.out.println("Error while decrypting");
        }
        return false;
    }
}
