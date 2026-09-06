package com.mediatrackr.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.mediatrackr.SharedLDAPEngine;
import com.unboundid.ldap.sdk.MockableLDAPConnection;
import com.unboundid.ldap.sdk.SearchResult;

public class LDAPTest {
    @InjectMocks
    SharedLDAPEngine ldapEngine;

    @Mock
    MockableLDAPConnection connection;

    @Mock
    SearchResult searchResult;

    @Test
    @DisplayName("Testing if we can find user correctly")
    public void testFindingUser(){

    }

    @Test
    @DisplayName("Testing whether a user can be created")
    public void testCreateUser(){

    }
}
