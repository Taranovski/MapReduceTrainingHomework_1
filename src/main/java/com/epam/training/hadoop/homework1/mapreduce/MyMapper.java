/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.mapreduce;

import com.epam.training.hadoop.homework1.entity.RecordEntity;
import com.epam.training.hadoop.homework1.parser.RecordParser;
import com.epam.training.hadoop.homework1.parser.RecordParserImpl;
import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class MyMapper extends Mapper<LongWritable, Text, Text, MyIntermediateWritable> {

    private static final RecordParser RECORD_PARSER = new RecordParserImpl();
    private static final Text IP = new Text();
    private static final MyIntermediateWritable INTERMEDIATE_RESULT = new MyIntermediateWritable();
    private static final Long ONE = 1L;
    private static RecordEntity recordEntity = null;
    private static String record;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        record = value.toString();

        if (RECORD_PARSER.isValidRecord(record)) {
            recordEntity = RECORD_PARSER.parseRecord(record);

            IP.set(recordEntity.getIp());
            INTERMEDIATE_RESULT.setTimes(ONE);
            INTERMEDIATE_RESULT.setBytes(recordEntity.getBytesTransfered());
            
            context.write(IP, INTERMEDIATE_RESULT);
        } else {
            context.getCounter(Errors.ERRORS).increment(1);
        }

    }

}
