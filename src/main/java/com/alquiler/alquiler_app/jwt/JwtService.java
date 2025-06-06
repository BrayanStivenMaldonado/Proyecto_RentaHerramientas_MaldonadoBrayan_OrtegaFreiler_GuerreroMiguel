package com.alquiler.alquiler_app.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


    @Service
    public class JwtService {

        private static final String SECRET_KEY = "uKhwfE0Hhz4L5Kd3C7Gm8OpT2b9RqYVN3Ezx0rJzFvE=";

        public String getToken(UserDetails person) {
        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("roles", person.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()));
        return getToken(extraClaims, person);
    }

        private String getToken(HashMap<String,Object> extraClaims, UserDetails person) {
            return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(person.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        }

        private Key getKey() {
            byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
        }

        public String getUsernameFromToken(String token) {
            return getClaim(token, Claims::getSubject);
        }

        public boolean isTokenValid(String token, UserDetails userDetails) {
            final String username = getUsernameFromToken(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }

        private Claims getAllClaims(String token){
            return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        }

        public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
            final Claims claims = getAllClaims(token);
            return claimsResolver.apply(claims);
        }

        public Date getExpiration(String token){
            return getClaim(token, Claims::getExpiration);
        }

        private boolean isTokenExpired(String token){
            return getExpiration(token).before(new Date());
        }

    }
