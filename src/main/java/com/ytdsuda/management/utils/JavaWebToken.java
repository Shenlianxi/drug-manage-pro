package com.ytdsuda.management.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JavaWebToken {
    private static Logger log = LoggerFactory.getLogger(JavaWebToken.class);
    //该方法使用HS256算法和Secret:bankgl生成signKey
    private static Key getKeyInstance() {
//        公钥加密签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("bankgl");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

//    登录成功创建JWT,token
public static String createJavaWebToken(JSONObject json, long maxAge) {
    Map<String, Object> claims = new HashMap<String, Object>();
    Date exp = null;
    for(Map.Entry<String, Object> keyval:json.entrySet()){
        claims.put(keyval.getKey(), keyval.getValue());
        }
    long nowMillis = System.currentTimeMillis();
    if (maxAge >= 0) {
        long expMillis = nowMillis + maxAge;
        exp = new Date(expMillis);
        }
    return Jwts.builder().setClaims(claims)
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS256, getKeyInstance())
            .compact();
    }

    //解析Token，同时也能验证Token，当验证失败返回null
    public static Map<String, Object> parserJavaWebToken(String jwt) {
        try {
            Map<String, Object> jwtClaims =
                    Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return jwtClaims;
        } catch (Exception e) {
            log.error("json web token verify failed");
            return null;
        }
    }

//    验证JWT
    public static void main(String[] args){
        Map<String, Object> ret = JavaWebToken.parserJavaWebToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyUGFzc3dvcmQiOiJlMTBhZGMzOTQ5YmE1OWFiYmU1NmUwNTdmMjBmODgzZSIsIm5pY2tOYW1lIjoi5pWw5o2u5aSn5L2sIiwicG9zaXRpb24iOiLmlbDmja7kuK3lv4MiLCJqb2IiOiLlvIDlj5Hlt6XnqIvluIgiLCJ1c2VyTmFtZSI6ImFkbWluIiwidXNlclJvbGUiOiLmma7pgJrnrqHnkIblkZgiLCJleHAiOjE1NDgxNzE5MzAsInVzZXJJZCI6NywiYWdlIjoyNH0.jFRrx3sdd8SywbVNzRkyBQGRlLDoSIiaWglJALWqcKs");
        for(Map.Entry<String, Object> e:ret.entrySet()){
            System.out.println(""+e.getKey()+":"+e.getValue());
        }
    }
}
