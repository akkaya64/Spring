package com.tpe.security;

import com.tpe.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private String jwtSecret = "sboot";

    private long jwtExpirationMs = 86400000;   // 24*60*60*1000 ( 1 gun )

    // !!! ********* GENERATE JWT TOKEN *************

   public String generateToken(Authentication authentication){

       // anlik olarak login islemini gerceklestiren kullanici bilgisine ulastik :
       UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

       // jwt tokeni userNAme field'i, jwtSecret ve jwtExpirationMs bilgilerini kullanarak olusturuyoruz
       return Jwts.builder().
               setSubject(userDetails.getUsername()).
               setIssuedAt(new Date()).
               setExpiration(new Date(new Date().getTime() + jwtExpirationMs)).
               signWith(SignatureAlgorithm.HS512, jwtSecret).
               compact();

   }


    // !!! ********* VALIDATE JWT TOKEN **************

    public boolean validateToken(String token){

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false ;

    }


    // !!! ********* GET UserName FROm JWT TOKEN **********
    public String getUserNameFromJwtToken(String token){
       return Jwts.parser().
               setSigningKey(jwtSecret).
               parseClaimsJws(token).
               getBody().
               getSubject();
    }


}
