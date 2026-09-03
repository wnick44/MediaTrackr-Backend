package com.mediatrackr;

import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchScope;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mediatrackr.dao.User;

@ApplicationScoped
public class SharedLDAPEngine {
    private LDAPConnection connection = null;
    private Algorithm algorithm = null;

    private String secret = "P3terp@rker1234";

    public SharedLDAPEngine(){
        try{
            connection = new LDAPConnection("localhost", 389, "uid=admin,ou=system", "P3terp@rker1234");
            algorithm = Algorithm.HMAC256(secret);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String findUser(String username, String password){
        Filter filter = Filter.createEqualityFilter("(uid=%s)", username);
        try {
            SearchResult searchResult = connection.search("ou=Users,dc=mediatrackr,dc=com", SearchScope.SUB, filter);
            
            if(searchResult.getEntryCount() != 1){
                return "";
            }

            long expiration = System.currentTimeMillis()*30*60*180;

            LDAPConnection userConn = new LDAPConnection("localhost", 389, "ou=Users,dc=mediatrackr,dc=com,uid="+username, password);
            if(userConn.isConnected()){
                userConn.close();
                return JWT.create()
                .withSubject(username)
                .withIssuer("mediatrackr")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(expiration))
                .sign(algorithm);
            }
            else{
                userConn.close();
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public boolean createUser(User user){
        StringBuilder userDNBuilder = new StringBuilder();
        userDNBuilder.append("ou=Users,dc=mediatrackr,dc=com,");

        userDNBuilder.append("uid="+user.getUserID()+",");
        userDNBuilder.append("givenName="+user.getFirstname()+",");

        userDNBuilder.append("sn="+user.getLastname()+",");
        userDNBuilder.append("mail="+user.getEmail()+",");

        userDNBuilder.append("userPassword="+user.getPassword());
        Entry entry = new Entry(userDNBuilder.toString());

        try {
            LDAPResult result = connection.add(entry);

            if(result.getResultCode() == ResultCode.SUCCESS){
                return true;
            }
            else{
                return false;
            }
        } catch (LDAPException e) {
            // TODO Auto-generated catch block
            return false;
        }
    }

}
