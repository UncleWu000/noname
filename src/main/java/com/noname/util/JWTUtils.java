package com.noname.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.noname.auth.MACVerifierEnhance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;


public class JWTUtils {

    private static final String ISSUER = "zqucs";

    private static String SHARE_KEY = "!@#$%^";

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
            JWSHeader header = new JWSHeader(JWSAlgorithm.ES256);
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
}
