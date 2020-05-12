package com.fundooNotes.message;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fundooNotes.exception.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
	public class JwtToken {
	
	@Autowired
	MessageInfo message;
	
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
				throw new InvalidTokenException(400,message.Invalide_Token);
			}
			return claim.getId();
		}
	}



