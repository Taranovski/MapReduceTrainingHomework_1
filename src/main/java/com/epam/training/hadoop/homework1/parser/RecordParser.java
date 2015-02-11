/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.parser;

import com.epam.training.hadoop.homework1.entity.RecordEntity;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public interface RecordParser {
    
    RecordEntity parseRecord(String record);
    boolean isValidRecord(String record);
}
