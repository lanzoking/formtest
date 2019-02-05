package com.application.security;

import org.apache.commons.codec.digest.DigestUtils;

public class Hashing {
	public final String hashPassword(String password) {
		String hashedPassword = "";
		
		hashedPassword = DigestUtils.md5Hex(password);
		
		return hashedPassword;
	}
}
