package com.example.alawan;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestAddUser {
    private FragmentSignup fragmentSignup;

    @Before
    public void setUp() {
        // Initialize LoginManager or mock dependencies
        fragmentSignup = new FragmentSignup();
    }

    @Test
    public void testLoginWithValidCredentials() {
        assertTrue(fragmentSignup.);
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        assertFalse(fragmentSignup.login("invalidUsername", "invalidPassword"));
    }

    @Test
    public void testLoginWithEmptyUsername() {
        assertFalse(fragmentSignup.login("", "password"));
    }

    @Test
    public void testLoginWithEmptyPassword() {
        assertFalse(fragmentSignup.login("username", ""));
}
