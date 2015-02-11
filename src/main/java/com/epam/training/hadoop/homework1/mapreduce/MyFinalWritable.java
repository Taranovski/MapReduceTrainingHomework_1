/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;
import org.apache.hadoop.io.Writable;

/**
 *
 * @author Oleksandr_Taranovsky
 */
class MyFinalWritable implements Writable {

    private Long totalBytes;
    private Long averageBytes;

    @Override
    public void write(DataOutput d) throws IOException {
        d.writeLong(totalBytes);
        d.writeLong(averageBytes);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        totalBytes = di.readLong();
        averageBytes = di.readLong();
    }

    public MyFinalWritable() {
    }

    public MyFinalWritable(Long totalBytes, Long averageBytes) {
        this.totalBytes = totalBytes;
        this.averageBytes = averageBytes;
    }

    public Long getAverageBytes() {
        return averageBytes;
    }

    public void setAverageBytes(Long averageBytes) {
        this.averageBytes = averageBytes;
    }

    public Long getTotalBytes() {
        return totalBytes;
    }

    public void setTotalBytes(Long totalBytes) {
        this.totalBytes = totalBytes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.totalBytes);
        hash = 17 * hash + Objects.hashCode(this.averageBytes);
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
        final MyFinalWritable other = (MyFinalWritable) obj;
        if (!Objects.equals(this.totalBytes, other.totalBytes)) {
            return false;
        }
        if (!Objects.equals(this.averageBytes, other.averageBytes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MyFinalWritable{" + "totalBytes=" + totalBytes + ", averageBytes=" + averageBytes + '}';
    }

}
