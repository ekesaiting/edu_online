package com.feng.servicebase.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseException  extends RuntimeException{
    private String type;
    private  int code;
    private  String msg;
    public BaseException(String msg){
        super(msg);
    }
}
