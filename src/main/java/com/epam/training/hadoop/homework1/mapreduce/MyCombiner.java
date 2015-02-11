/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.mapreduce;

import com.epam.training.hadoop.homework1.mapreduce.writable.MyIntermediateWritable;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class MyCombiner extends Reducer<Text, MyIntermediateWritable, Text, MyIntermediateWritable> {

    @Override
    protected void reduce(Text key, Iterable<MyIntermediateWritable> values, Context context) throws IOException, InterruptedException {

        long counter = 0;
        long totalBytes = 0;

        for (MyIntermediateWritable intermediateWritable : values) {
            counter += intermediateWritable.getTimes();
            totalBytes += intermediateWritable.getBytes();
        }

        context.write(key, new MyIntermediateWritable(counter, totalBytes));

    }

}
