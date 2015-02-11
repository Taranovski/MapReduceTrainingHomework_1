/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class MyIntermediateWritable implements Writable {

    private Long times;
    private Long bytes;

    @Override
    public void write(DataOutput d) throws IOException {
        d.writeLong(times);
        d.writeLong(bytes);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        times = di.readLong();
        bytes = di.readLong();
    }

    public MyIntermediateWritable(Long times, Long bytes) {
        this.times = times;
        this.bytes = bytes;
    }

    public MyIntermediateWritable() {
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public Long getBytes() {
        return bytes;
    }

    public void setBytes(Long bytes) {
        this.bytes = bytes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.times != null ? this.times.hashCode() : 0);
        hash = 71 * hash + (this.bytes != null ? this.bytes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MyIntermediateWritable other = (MyIntermediateWritable) obj;
        if (this.times != other.times && (this.times == null || !this.times.equals(other.times))) {
            return false;
        }
        if (this.bytes != other.bytes && (this.bytes == null || !this.bytes.equals(other.bytes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MyIntermediateWritable{" + "times=" + times + ", bytes=" + bytes + '}';
    }

}
