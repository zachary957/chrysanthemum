package com.eutopia.utils.helper;

import com.eutopia.constant.JWTConstants;
import com.eutopia.domain.JWTInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;

public class JWTHelper {

    public static String generateToken(JWTInfo jwtInfo, byte[] prikey, int expire) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, expire);
        return Jwts.builder()
                .setSubject(jwtInfo.getUserId())
                .claim(JWTConstants.JWT_KEY_USER_NAME, jwtInfo.getUserName())
                .claim(JWTConstants.JWT_KEY_PASSWORD, jwtInfo.getPassword())
                .setExpiration(cal.getTime())
                .signWith(SignatureAlgorithm.RS256, RSAKeyHelper.getPrivateKey(prikey))
                .compact();
    }

    public static JWTInfo getInfoFromToken(String token, byte[] pubKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(RSAKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return new JWTInfo(body.getSubject(), body.get(JWTConstants.JWT_KEY_USER_NAME).toString(), body.get(JWTConstants.JWT_KEY_PASSWORD).toString());
    }
}
