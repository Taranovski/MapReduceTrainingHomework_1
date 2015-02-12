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

    private static final String INPUT_PATH_CONFIG = "mapreduce.homework1.inputpath";
    private static final String OUTPUT_PATH_CONFIG = "mapreduce.homework1.outputpath";
    
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        JobConf conf = new JobConf();

        conf.set("mapred.output.compression.codec", "org.apache.hadoop.io.compress.SnappyCodec");
        
        String inputPath = conf.get(INPUT_PATH_CONFIG);
        String outputPath = conf.get(OUTPUT_PATH_CONFIG);
        
        if (inputPath == null) {
            throw new RuntimeException("no input path set, try to set it by adding -D" + INPUT_PATH_CONFIG + "=<path>");
        }
        if (outputPath == null) {
            throw new RuntimeException("no output path set, try to set it by adding -D" + OUTPUT_PATH_CONFIG + "=<path>");
        }
        
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
        
        TextInputFormat.addInputPath(job, new Path(inputPath));
        SequenceFileOutputFormat.setOutputPath(job, new Path(outputPath));
        
        SequenceFileOutputFormat.setOutputCompressionType(job, CompressionType.BLOCK);
        SequenceFileOutputFormat.setCompressOutput(job, true);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
