/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.parser;

import com.epam.training.hadoop.homework1.entity.RecordEntity;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
public class RecordParserImplTest {

    private final static String EMPTY_RECORD = "";
    private final static String NULL_RECORD = null;
    private final static String INVALID_RECORD = "if54p1 - - [24/Apr/2011:04:10:19 -0400] \"Gdf5434524523ET /~strabal/grease/photo1/97-13.jpg HTTP/1.1\" 20d0 fghsgafds56928 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\"";
    private final static String VALID_RECORD = "ip1 - - [24/Apr/2011:04:10:19 -0400] \"GET /~strabal/grease/photo1/97-13.jpg HTTP/1.1\" 200 56928 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\"";

    private final static RecordEntity RECORD_ENTITY = new RecordEntity();

    public RecordParserImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        RECORD_ENTITY.setIp("ip1");
        RECORD_ENTITY.setSomeUnknownFields1("- -");
        RECORD_ENTITY.setDateTime(new DateTime(2011, 4, 24, 4, 10, 19).withZoneRetainFields(DateTimeZone.forOffsetHoursMinutes(-4, 0)));
        RECORD_ENTITY.setMethodName("GET");
        RECORD_ENTITY.setLocalLink("/~strabal/grease/photo1/97-13.jpg");
        RECORD_ENTITY.setProtocolName("HTTP/1.1");
        RECORD_ENTITY.setStatus(200);
        RECORD_ENTITY.setBytesTransfered(56928L);
        RECORD_ENTITY.setHostLink("-");
        RECORD_ENTITY.setMiscInfo("Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)");
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
     * Test of parseRecord method, of class RecordParserImpl.
     */
    @Test
    public void testParseRecord() {
        System.out.println("parseRecord");
        String record = VALID_RECORD;
        RecordParserImpl instance = new RecordParserImpl();
        RecordEntity expResult = RECORD_ENTITY;
        RecordEntity result = instance.parseRecord(record);

        assertEquals(expResult.getIp(), result.getIp());
        assertEquals(expResult.getSomeUnknownFields1(), result.getSomeUnknownFields1());
        assertEquals(expResult.getDateTime(), result.getDateTime());
        assertEquals(expResult.getMethodName(), result.getMethodName());
        assertEquals(expResult.getLocalLink(), result.getLocalLink());
        assertEquals(expResult.getProtocolName(), result.getProtocolName());
        assertEquals(expResult.getStatus(), result.getStatus());
        assertEquals(expResult.getBytesTransfered(), result.getBytesTransfered());
        assertEquals(expResult.getHostLink(), result.getHostLink());
        assertEquals(expResult.getMiscInfo(), result.getMiscInfo());
    }

    /**
     * Test of isValidRecord method with empty record, of class
     * RecordParserImpl.
     */
    @Test
    public void testIsValidRecordWithEmptyRecord() {
        System.out.println("testIsValidRecordWithEmptyRecord");
        String record = EMPTY_RECORD;
        RecordParserImpl instance = new RecordParserImpl();
        boolean expResult = false;
        boolean result = instance.isValidRecord(record);
        assertEquals("empty record should not be a valid record", expResult, result);
    }

    /**
     * Test of isValidRecord method with empty record, of class
     * RecordParserImpl.
     */
    @Test
    public void testIsValidRecordWithNullRecord() {
        System.out.println("testIsValidRecordWithNullRecord");
        String record = NULL_RECORD;
        RecordParserImpl instance = new RecordParserImpl();
        boolean expResult = false;
        boolean result = instance.isValidRecord(record);
        assertEquals("null record should not be a valid record", expResult, result);
    }

    /**
     * Test of isValidRecord method with empty record, of class
     * RecordParserImpl.
     */
    @Test
    public void testIsValidRecordWithInvalidRecord() {
        System.out.println("testIsValidRecordWithInvalidRecord");
        String record = INVALID_RECORD;
        RecordParserImpl instance = new RecordParserImpl();
        boolean expResult = false;
        boolean result = instance.isValidRecord(record);
        assertEquals("invalid record should not be a valid record", expResult, result);
    }

    /**
     * Test of isValidRecord method with empty record, of class
     * RecordParserImpl.
     */
    @Test
    public void testIsValidRecordWithValidRecord() {
        System.out.println("testIsValidRecordWithValidRecord");
        String record = VALID_RECORD;
        RecordParserImpl instance = new RecordParserImpl();
        boolean expResult = true;
        boolean result = instance.isValidRecord(record);
        assertEquals("valid record should be a valid record", expResult, result);
    }

}
