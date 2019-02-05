package com.application.model;

import com.application.security.AESEncryption;
import com.application.security.Hashing;
import com.example.demo.RegistrationDetails;

public class Model {
	private static final String encryptionKey = "S0M33N(RYPT!0N";
	
	public final RegistrationDetails encryptAndHashRegistrationDetails(RegistrationDetails registrationDetails) {
		RegistrationDetails details = registrationDetails;
		
		AESEncryption aesEncryption = new AESEncryption(Model.encryptionKey);
		
		aesEncryption.encrypt(details.getUserName());
		details.setUserName(aesEncryption.getEncryptedString());
		
		aesEncryption.encrypt(details.getFirstName());
		details.setFirstName(aesEncryption.getEncryptedString());
		
		aesEncryption.encrypt(details.getLastName());
		details.setLastName(aesEncryption.getEncryptedString());
		
		aesEncryption.encrypt(details.getDateOfBirth());
		details.setDateOfBirth(aesEncryption.getEncryptedString());
		
		aesEncryption.encrypt(details.getEmail());
		details.setEmail(aesEncryption.getEncryptedString());
		
		details.setPassword(new Hashing().hashPassword(details.getPassword()));
		
		return details;
	}
	
	public final String encryptField(String data) {
		AESEncryption aesEncryption = new AESEncryption(Model.encryptionKey);
		
		aesEncryption.encrypt(data);
		data = aesEncryption.getEncryptedString();
		
		return data;
	}
	
	public final String decryptField(String data) {
		AESEncryption aesEncryption = new AESEncryption(Model.encryptionKey);
		
		aesEncryption.decrypt(data);
		data = aesEncryption.getDecryptedString();
		
		return data;
	}
}
