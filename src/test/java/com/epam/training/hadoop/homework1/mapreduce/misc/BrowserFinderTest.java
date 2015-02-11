/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.mapreduce.misc;

import com.epam.training.hadoop.homework1.mapreduce.counter.Browsers;
import java.util.EnumSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class BrowserFinderTest {
    
    public BrowserFinderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBrowsers method, of class BrowserFinder.
     */
    @Test
    public void testGetBrowsersOneMSIE() {
        System.out.println("testGetBrowsersOneMSIE");
        String info = "MSIE";
        EnumSet<Browsers> expResult = EnumSet.of(Browsers.MSIE);
        EnumSet<Browsers> result = BrowserFinder.getBrowsers(info);
        assertEquals(expResult, result);
    }
    /**
     * Test of getBrowsers method, of class BrowserFinder.
     */
    @Test
    public void testGetBrowsersTwoMSIE() {
        System.out.println("testGetBrowsersTwoMSIE");
        String info = "MSIE MSIE";
        EnumSet<Browsers> expResult = EnumSet.of(Browsers.MSIE);
        EnumSet<Browsers> result = BrowserFinder.getBrowsers(info);
        assertEquals(expResult, result);
    }
    /**
     * Test of getBrowsers method, of class BrowserFinder.
     */
    @Test
    public void testGetBrowsersTwoMSIEFirefox() {
        System.out.println("testGetBrowsersTwoMSIEFirefox");
        String info = "MSIE MSIEFirefox";
        EnumSet<Browsers> expResult = EnumSet.of(Browsers.MSIE, Browsers.FIREFOX);
        EnumSet<Browsers> result = BrowserFinder.getBrowsers(info);
        assertEquals(expResult, result);
    }
    /**
     * Test of getBrowsers method, of class BrowserFinder.
     */
    @Test
    public void testGetBrowsersTwoMSIEFirefoxThreeOpera() {
        System.out.println("testGetBrowsersTwoMSIEFirefoxThreeOpera");
        String info = "MSIE OperaMSIEFirefox    Opera Opera";
        EnumSet<Browsers> expResult = EnumSet.of(Browsers.MSIE, Browsers.FIREFOX, Browsers.OPERA);
        EnumSet<Browsers> result = BrowserFinder.getBrowsers(info);
        assertEquals(expResult, result);
    }
    
}
