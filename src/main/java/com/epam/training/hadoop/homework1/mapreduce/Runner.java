/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.mapreduce;

import com.epam.training.hadoop.homework1.mapreduce.writable.MyFinalWritable;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class Runner {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        JobConf conf = new JobConf();

        conf.set("mapred.output.compression.codec", "org.apache.hadoop.io.compress.SnappyCodec");
        
        Job job = Job.getInstance(conf, "Log analyse");

        job.setJarByClass(Runner.class);

        job.setMapperClass(MyMapper.class);
        job.setCombinerClass(MyCombiner.class);
        job.setReducerClass(MyReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(MyFinalWritable.class);
        job.setNumReduceTasks(2);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        
        TextInputFormat.addInputPath(job, new Path(""));
        SequenceFileOutputFormat.setOutputPath(job, new Path(""));
        
        SequenceFileOutputFormat.setOutputCompressionType(job, CompressionType.BLOCK);
        SequenceFileOutputFormat.setCompressOutput(job, true);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
