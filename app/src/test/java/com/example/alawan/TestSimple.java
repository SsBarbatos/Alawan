package com.example.alawan;

import static org.junit.Assert.assertTrue;

import com.example.alawan.Class.Tests;
import org.junit.Before;
import org.junit.Test;

public class TestSimple {

    Tests tests;

    @Before
    public void SetUp() {
        tests = new Tests();
    }

    @Test
    public void TestExistingEmail() { assertTrue(tests.TestEmail("alex.carle@hotmail.com")); }

    @Test
    public void TestWrongEmail() { assertTrue(tests.TestEmail("wrong@wrong.com")); }
}
