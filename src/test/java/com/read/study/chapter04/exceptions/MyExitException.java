package com.read.study.chapter04.exceptions;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/23 13:58
 * @since 1.0
 */
public class MyExitException extends RuntimeException{
    private static final long serialVersionUID = 7115602500030264392L;

    public MyExitException(String message) {
        super(message);
    }
}
