package com.read.study.chapter04.exceptions;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/23 11:37
 * @since 1.0
 */
public class MyNoOptionException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public MyNoOptionException(String message) {
        super(message);
    }
}
