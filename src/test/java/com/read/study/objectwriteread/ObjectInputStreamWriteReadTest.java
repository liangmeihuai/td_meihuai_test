package com.read.study.objectwriteread;

import com.ObjectInputStreamTest.Company;

import java.io.*;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/29 12:06
 * @since 1.0
 */
public class ObjectInputStreamWriteReadTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Company company = new Company();
        company.setId(1);
        company.setName("luke");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(company);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Company company1 = (Company) ois.readObject();
        System.out.println(company1);
    }
}
