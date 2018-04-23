package com.common.dataoutputserialize;

import com.ObjectInputStreamTest.Company;

import java.io.*;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/13 14:59
 * @since 1.0
 */
public class DataOutputSerializeTest {
    static class Company2 implements Serializable{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
