package com.noname.auth;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWTClaimsSet;

public class MACVerifierEnhance extends MACVerifier {

    private final JWTClaimsSet claimsSet;

    public MACVerifierEnhance(final byte[] sharedSecret, JWTClaimsSet claimsSet) throws JOSEException {
        super(sharedSecret);
        this.claimsSet = claimsSet;
    }

    public MACVerifierEnhance(final String sharedSecretString, JWTClaimsSet claimsSet) throws JOSEException {
        super(sharedSecretString);
        this.claimsSet = claimsSet;
    }

    /**
     * token是否已经过期
     * @return
     */
    public boolean isExpired() {
        long time = System.currentTimeMillis();
        return !(claimsSet.getNotBeforeTime().getTime() <= time && time < claimsSet.getExpirationTime().getTime());
    }

    /**
     * 是否是一个合法的token
     * @param header
     * @param signingInput
     * @param signature
     * @return
     * @throws JOSEException
     */
    public boolean isLegal(final JWSHeader header, final byte[] signingInput, final Base64URL signature) throws JOSEException {
        boolean value = super.verify(header, signingInput, signature);
        return value;
    }


    @Override
    public boolean verify(final JWSHeader header, final byte[] signingInput, final Base64URL signature) throws JOSEException {
        return isLegal(header, signingInput, signature) && !(isExpired());
    }




}