package techit.util;

import java.security.Key;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class UtilManager {
	private final static Key KEY = MacProvider.generateKey();
	private final static BCryptPasswordEncoder BCRYPT = new BCryptPasswordEncoder();
	
	public static String encodePassword(String rawPassword) {
		return BCRYPT.encode(rawPassword);
	}
	
	public static boolean checkPassword(String rawPassword, String encodedPassword) {
		return BCRYPT.matches(rawPassword, encodedPassword);
	}
	
	public static String createJwtToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.signWith(SignatureAlgorithm.HS512, KEY)
				.compact();
	}
	
	public static String matchToken(String token) {
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().getSubject();
	}
}
