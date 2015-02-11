/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.mapreduce;

import com.epam.training.hadoop.homework1.mapreduce.counter.Browsers;
import com.epam.training.hadoop.homework1.mapreduce.counter.Errors;
import com.epam.training.hadoop.homework1.mapreduce.writable.MyFinalWritable;
import com.epam.training.hadoop.homework1.mapreduce.writable.MyIntermediateWritable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
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
public class MapReduceTest {

    private static final Mapper MAPPER = new MyMapper();
    private static final MyCombiner COMBINER = new MyCombiner();
    private static final Reducer REDUCER = new MyReducer();

    private static MapReduceDriver<LongWritable, Text, Text, MyIntermediateWritable, Text, MyFinalWritable> mapReduceDriver;

    public MapReduceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(MAPPER, REDUCER, COMBINER);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMyMapReduceWithoutErrors() {
        List<Pair<Text, MyFinalWritable>> expectedOutput = new LinkedList<>();
        expectedOutput.add(new Pair<>(new Text("ip1"), new MyFinalWritable(96956L, 48478L)));
        expectedOutput.add(new Pair<>(new Text("ip2"), new MyFinalWritable(318L, 318L)));
        expectedOutput.add(new Pair<>(new Text("ip3"), new MyFinalWritable(72209L, 72209L)));

        try {
            mapReduceDriver
                    .withInput(new LongWritable(0L), new Text("ip1 - - [24/Apr/2011:04:06:01 -0400] \"GET /~strabal/grease/photo9/927-3.jpg HTTP/1.1\" 200 40028 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\""))
                    .withInput(new LongWritable(1L), new Text("ip1 - - [24/Apr/2011:04:10:19 -0400] \"GET /~strabal/grease/photo1/97-13.jpg HTTP/1.1\" 200 56928 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\""))
                    .withInput(new LongWritable(2L), new Text("ip2 - - [24/Apr/2011:04:20:20 -0400] \"GET /favicon.ico HTTP/1.1\" 200 318 \"-\" \"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.16) Gecko/20110319 Firefox/3.6.16\""))
                    .withInput(new LongWritable(3L), new Text("ip3 - - [24/Apr/2011:04:22:45 -0400] \"GET /personal/vanagon_1.jpg HTTP/1.1\" 200 72209 \"http://www.inetgiant.in/addetails/1985-vw-vanagon-transporter-single-cab-sinka-diesel-truck-2wd-rhd/3235819\" \"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; WOW64; Trident/4.0; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C)\""))
                    .withAllOutput(expectedOutput)
                    .withCounter(Errors.ERRORS, 0)
                    .withCounter(Browsers.FIREFOX, 1)
                    .withCounter(Browsers.MSIE, 1)
                    .runTest();
        } catch (IOException ex) {
            fail("IOException should not be thrown");
        }

    }

    @Test
    public void testMyMapReduceWithErrors() {
        List<Pair<Text, MyFinalWritable>> expectedOutput = new LinkedList<>();
        expectedOutput.add(new Pair<>(new Text("ip1"), new MyFinalWritable(96956L, 48478L)));
        expectedOutput.add(new Pair<>(new Text("ip2"), new MyFinalWritable(318L, 318L)));

        try {
            mapReduceDriver
                    .withInput(new LongWritable(0L), new Text("ip1 - - [24/Apr/2011:04:06:01 -0400] \"GET /~strabal/grease/photo9/927-3.jpg HTTP/1.1\" 200 40028 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\""))
                    .withInput(new LongWritable(1L), new Text("ip1 - - [24/Apr/2011:04:10:19 -0400] \"GET /~strabal/grease/photo1/97-13.jpg HTTP/1.1\" 200 56928 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\""))
                    .withInput(new LongWritable(2L), new Text("ip2 - - [24/Apr/2011:04:20:20 -0400] \"GET /favicon.ico HTTP/1.1\" 200 318 \"-\" \"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.16) Gecko/20110319 Firefox/3.6.16\""))
                    .withInput(new LongWritable(3L), new Text("i1p3 - - [24/Apr/2011:04:22:45 -0400] \"GET /personal/vanagon_1.jpg HTTP/1.1\" 200 72209 \"http://www.inetgiant.in/addetails/1985-vw-vanagon-transporter-single-cab-sinka-diesel-truck-2wd-rhd/3235819\" \"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; WOW64; Trident/4.0; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C)\""))
                    .withAllOutput(expectedOutput)
                    .withCounter(Errors.ERRORS, 1)
                    .withCounter(Browsers.FIREFOX, 1)
                    .runTest();
        } catch (IOException ex) {
            fail("IOException should not be thrown");
        }

    }

}
