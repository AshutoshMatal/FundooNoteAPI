package com.MyFirstApplication.message;
import java.util.Date;
import org.springframework.stereotype.Component;

import com.MyFirstApplication.exception.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
	public class JwtToken {
	
		SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
		
		// SECREAT KEY
		static String secretKey = "iamsecretkey";

		// GENERATE TOKEN
		public String generateToken(String emailId) {
			return Jwts.builder().setId(emailId).setIssuedAt(new Date(System.currentTimeMillis()))
					.signWith(algorithm, secretKey).compact();
		}

		// DECODE TOKEN
		public String getToken(String token) {
			Claims claim = null;
			try {
				claim = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			} catch (Exception e) {
				throw new InvalidTokenException(400,"Error");
			}
			return claim.getId();
		}
	}



