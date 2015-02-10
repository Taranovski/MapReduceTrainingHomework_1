package com.epam.training.hadoop.homework1.validation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class RecordValidatorImplTest {

    private final static String EMPTY_RECORD = "";
    private final static String NULL_RECORD = null;
    private final static String INVALID_RECORD = "";
    private final static String VALID_RECORD = "";
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test of isValidRecord method with empty record, of class RecordValidatorImpl.
     */
    @Test
    public void testIsValidRecordWithEmptyRecord() {
        System.out.println("testIsValidRecordWithEmptyRecord");
        String record = EMPTY_RECORD;
        RecordValidatorImpl instance = new RecordValidatorImpl();
        boolean expResult = false;
        boolean result = instance.isValidRecord(record);
        assertEquals("empty record should not be a valid record", expResult, result);
    }
    
    /**
     * Test of isValidRecord method with empty record, of class RecordValidatorImpl.
     */
    @Test
    public void testIsValidRecordWithNullRecord() {
        System.out.println("testIsValidRecordWithNullRecord");
        String record = NULL_RECORD;
        RecordValidatorImpl instance = new RecordValidatorImpl();
        boolean expResult = false;
        boolean result = instance.isValidRecord(record);
        assertEquals("null record should not be a valid record", expResult, result);
    }
    
    /**
     * Test of isValidRecord method with empty record, of class RecordValidatorImpl.
     */
    @Test
    public void testIsValidRecordWithInvalidRecord() {
        System.out.println("testIsValidRecordWithInvalidRecord");
        String record = INVALID_RECORD;
        RecordValidatorImpl instance = new RecordValidatorImpl();
        boolean expResult = false;
        boolean result = instance.isValidRecord(record);
        assertEquals("invalid record should not be a valid record", expResult, result);
    }
    
    /**
     * Test of isValidRecord method with empty record, of class RecordValidatorImpl.
     */
    @Test
    public void testIsValidRecordWithValidRecord() {
        System.out.println("testIsValidRecordWithValidRecord");
        String record = VALID_RECORD;
        RecordValidatorImpl instance = new RecordValidatorImpl();
        boolean expResult = true;
        boolean result = instance.isValidRecord(record);
        assertEquals("valid record should be a valid record", expResult, result);
    }
}
