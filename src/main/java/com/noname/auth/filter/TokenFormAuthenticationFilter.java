package com.noname.auth.filter;

import com.github.panchitoboy.shiro.jwt.filter.JWTAuthenticationToken;
import com.nimbusds.jose.JWSObject;
import com.noname.bo.user.CSSubject;
import com.noname.constant.CSSubjectConst;
import com.noname.constant.HeaderConst;
import com.noname.util.JWTUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

public class TokenFormAuthenticationFilter extends AuthenticatingFilter {

    public static final String USER_ID = "userId";
    public static final String PASSWORD = "password";
    protected static final String AUTHORIZATION_HEADER = "token";

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println(((HttpServletRequest)request).getRequestURI());
        String token = ((HttpServletRequest) request).getHeader(HeaderConst.param.TOKEN);
        System.out.println("token -> " + token );


        if(StringUtils.isBlank(token)){
            //response 4010
            System.out.println("token is blank?");
            return false;
        }

        if(!!JWTUtils.isTokenLegal(token)){
            //response 4011
            System.out.println("token is legal?");
            return false;
        }

        if(JWTUtils.isTokenExpired(token)){
            //response 4012
            System.out.println("token is expired?");
            return false;
        }

        CSSubject csSubject = JWTUtils.getSubject(token);
        if(null == csSubject){
            if(!CSSubjectConst.type.ACCESS_TOKEN.equals(csSubject.getTokenType())){
                //response
                return false;
            }

            if(csSubject.getId() == null){
                //
                return false;
            }

            //不是管理员
            if(!csSubject.getClientOrManage().equals(CSSubjectConst.ClientOrManage.MANAGE)){
                return false;
            }
        }

        boolean loggedIn = false;
        if(isLoginRequest(request, response) || isLoggedAttempt(request, response)){
            try {
                loggedIn = executeLogin(request, response);
            }catch (Exception e){
                e.printStackTrace();
                return loggedIn;
            }
        }

        return loggedIn;
    }


    protected boolean isLoggedAttempt(ServletRequest request, ServletResponse response) {
        String authzHeader = getAuthzHeader(request);
        return authzHeader != null;
    }

    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(AUTHORIZATION_HEADER);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws IOException {

        if (isLoginRequest(request, response)) {
            String json = IOUtils.toString(request.getInputStream());

            if (json != null && !json.isEmpty()) {

                try (JsonReader jr = Json.createReader(new StringReader(json))) {
                    JsonObject object = jr.readObject();
                    String username = object.getString(USER_ID);
                    String password = object.getString(PASSWORD);
                    return new UsernamePasswordToken(username, password);
                }

            }
        }

        if (isLoggedAttempt(request, response)) {
            String jwtToken = getAuthzHeader(request);
            if (jwtToken != null) {
                return createToken(jwtToken);
            }
        }

        return new UsernamePasswordToken();
    }

    public JWTAuthenticationToken createToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            String decrypted = jwsObject.getPayload().toString();

            try (JsonReader jr = Json.createReader(new StringReader(decrypted))) {
                JsonObject object = jr.readObject();

                String userId = object.getString("sub", null);
                return new JWTAuthenticationToken(userId, token);
            }

        } catch (ParseException ex) {
            throw new AuthenticationException(ex);
        }

    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
       // responseUnauthorized(request, response);
        return false;
    }

}
