package com.mediatrackr.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.directory.server.annotations.CreateLdapServer;
import org.apache.directory.server.annotations.CreateTransport;
import org.apache.directory.server.core.annotations.ApplyLdifFiles;
import org.apache.directory.server.core.annotations.CreateDS;
import org.apache.directory.server.core.annotations.CreatePartition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mediatrackr.SharedLDAPEngine;
import com.mediatrackr.dao.User;
import com.unboundid.ldap.sdk.LDAPSearchException;

@CreateLdapServer(transports = { @CreateTransport(protocol = "LDAP") })
@CreateDS(allowAnonAccess = true, partitions = {
		@CreatePartition(name = "Example Partition", suffix = "dc=mediatrackr,dc=com") })
@ApplyLdifFiles("users-import.ldif")

public class LDAPTest {

    SharedLDAPEngine ldapEngine;

    @BeforeAll
    public void setup(){
        ldapEngine = new SharedLDAPEngine();
    }

    @Test
    @DisplayName("Testing if we can find user correctly")
    public void testFindingUser() throws LDAPSearchException{
        String username = "wnick";
        String password = "password";

        String JWT = ldapEngine.findUser(username, password);
        assertNotEquals(JWT, "");
    }

    @Test
    @DisplayName("Testing whether a user can be created")
    public void testCreateUser(){
        User user = new User();
        user.setFirstname("William");

        user.setLastname("Nick");
        user.setEmail("wnick44@acme.com");

        user.setPassword("abc123");

        boolean success = ldapEngine.createUser(user);
        assertTrue(success);
    }
}
