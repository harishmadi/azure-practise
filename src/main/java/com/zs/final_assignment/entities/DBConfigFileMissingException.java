package com.zs.final_assignment.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class DBConfigFileMissingException extends Exception{
    public DBConfigFileMissingException(String msg){
        super(msg);
    }
}
