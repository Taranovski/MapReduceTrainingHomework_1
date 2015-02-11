/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.mapreduce;

import com.epam.training.hadoop.homework1.mapreduce.writable.MyIntermediateWritable;
import com.epam.training.hadoop.homework1.mapreduce.writable.MyFinalWritable;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class MyReducer extends Reducer<Text, MyIntermediateWritable, Text, MyFinalWritable> {
    
    @Override
    protected void reduce(Text key, Iterable<MyIntermediateWritable> values, Context context) throws IOException, InterruptedException {
        
        long counter = 0;
        long totalBytes = 0;
        long averageBytes = 0;
        
        for (MyIntermediateWritable intermediateWritable : values) {
            counter += intermediateWritable.getTimes();
            totalBytes += intermediateWritable.getBytes();
        }
        
        averageBytes = totalBytes / counter;
        
        context.write(key, new MyFinalWritable(totalBytes, averageBytes));
        
    }
    
}
