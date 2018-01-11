package com.noname.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.noname.auth.MACVerifierEnhance;
import com.noname.bo.user.CSSubject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;


public class JWTUtils {

    private static final String ISSUER = "zqucs";

    private static String SHARE_KEY = "!@#$%^!!CoursessELection**Hello,Stu)))";

    private static final Logger log = LoggerFactory.getLogger(JWTUtils.class);

    @PostConstruct
    public void init(){
        SHARE_KEY = SHARE_KEY.concat("HelloWorld");
        log.debug("share_key:"+SHARE_KEY);
    }

    public static JWTClaimsSet.Builder getBuilder(Object userId, long expireInMillisecond){
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

        builder.issuer(ISSUER);
        builder.subject(userId.toString());
        builder.issueTime(new Date());
        builder.notBeforeTime(new Date());
        builder.expirationTime(new Date(new Date().getTime()+expireInMillisecond));
        builder.jwtID(UUID.randomUUID().toString());
        return builder;
    }

    /**
     * 创建token
     */
    public static String createToken(Object userId, long expireInMillisecond){

        try {
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.issuer(ISSUER);
            builder.subject(userId.toString());
            builder.issueTime(new Date());
            builder.notBeforeTime(new Date());
            builder.expirationTime(new Date(new Date().getTime() + expireInMillisecond));
            builder.jwtID(UUID.randomUUID().toString());
            JWTClaimsSet claimsSet = builder.build();
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            Payload payload = new Payload(claimsSet.toJSONObject());
            JWSObject jwsObject = new JWSObject(header, payload);
            JWSSigner signer = new MACSigner(SHARE_KEY.getBytes());
            jwsObject.sign(signer);

            return jwsObject.serialize();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证token是否合法
     *
     */
    public static boolean validateToken(String token){
        try{
            if(token == null || token.equals("")){
                return false;
            }
            return verifyToken(token);
        }catch (ParseException e) {
            e.printStackTrace();
            return false;
        } catch (JOSEException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 验证当前token是否有效
     * @param token
     * @return
     * @throws ParseException
     * @throws JOSEException
     */
    public static boolean verifyToken(String token) throws ParseException, JOSEException {
        SignedJWT signed = SignedJWT.parse(token);
        MACVerifierEnhance verifier = new MACVerifierEnhance(SHARE_KEY, signed.getJWTClaimsSet());
        return signed.verify(verifier);
    }

    /**
     *  验证token是否过期
     * @param token
     * @return
     */
    public static  boolean isTokenExpired(String token){
        SignedJWT signed = null;

        try{
         signed = SignedJWT.parse(token);
         MACVerifierEnhance verifier = new MACVerifierEnhance(SHARE_KEY.getBytes(), signed.getJWTClaimsSet());
         return verifier.isExpired();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 验证token是否合法
     * @param token
     * @return
     */
    public static boolean isTokenLegal(String token){
        SignedJWT signed = null;

        try {
            signed = SignedJWT.parse(token);
            MACVerifierEnhance verifier = new MACVerifierEnhance(SHARE_KEY.getBytes(), signed.getJWTClaimsSet());
            return verifier.isLegal(signed.getHeader(), signed.getSigningInput(), signed.getSignature());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 从token 获取sub信息
     * @param token
     * @return
     * @throws ParseException
     */
    public static String getFromToken(String token) throws ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        String decrypted = jwsObject.getPayload().toString();

        try (javax.json.JsonReader jr = Json.createReader(new StringReader(decrypted))) {
            JsonObject object = jr.readObject();

            String userId = object.getString("sub", null);
            return userId;
        }
    }

    /**
     * 获取token过期时间
     * @param token
     * @return
     * @throws ParseException
     */
    public static Date getExpiredDate(String token) throws ParseException {
        SignedJWT signed = SignedJWT.parse(token);
        return signed.getJWTClaimsSet().getExpirationTime() ;
    }

    public static String getFromTokenInfo(String token) {
        String json = null;
        try {
            json = getFromToken(token);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 获取登陆用户的信息
     * @param token
     * @return
     */
    public static CSSubject getSubject(String token) {
        String json = getFromTokenInfo(token);
        if (StringUtils.isNotBlank(json)) {
            return JsonUtil.fromJson(json, CSSubject.class);
        }
        return null;
    }


}
