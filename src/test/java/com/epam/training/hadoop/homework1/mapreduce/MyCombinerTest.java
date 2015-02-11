/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.mapreduce;

import com.epam.training.hadoop.homework1.mapreduce.writable.MyIntermediateWritable;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
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
public class MyCombinerTest {

    private static final Reducer REDUCER = new MyCombiner();
    private static ReduceDriver<Text, MyIntermediateWritable, Text, MyIntermediateWritable> reduceDriver;

    public MyCombinerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        reduceDriver = ReduceDriver.newReduceDriver(REDUCER);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMyCombiner() {
        List<Pair<Text, MyIntermediateWritable>> expectedOutput = new LinkedList<>();
        expectedOutput.add(new Pair<>(new Text("ip1"), new MyIntermediateWritable(2L, 96956L)));
        expectedOutput.add(new Pair<>(new Text("ip2"), new MyIntermediateWritable(1L, 318L)));
        expectedOutput.add(new Pair<>(new Text("ip3"), new MyIntermediateWritable(1L, 72209L)));

        try {
            reduceDriver
                    .withInput(new Pair<>(new Text("ip1"), Arrays.asList(new MyIntermediateWritable(1L, 40028L), new MyIntermediateWritable(1L, 56928L))))
                    .withInput(new Pair<>(new Text("ip2"), Arrays.asList(new MyIntermediateWritable(1L, 318L))))
                    .withInput(new Pair<>(new Text("ip3"), Arrays.asList(new MyIntermediateWritable(1L, 72209L))))
                    .withAllOutput(expectedOutput)
                    .runTest();
        } catch (IOException ex) {
            fail("IOException should not be thrown");
        }

    }
}
