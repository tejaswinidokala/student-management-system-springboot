package com.demo.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Simple SHA-256 based password hashing.
//Note: for a real production app, use Spring Security + BCrypt instead.
//This project intentionally avoids adding new Maven dependencies.
public class PasswordUtil {

	private PasswordUtil() {
	}

	public static String hash(String rawPassword) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Unable to hash password", e);
		}
	}
}
