package com.ObjectInputStreamTest;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/29 12:02
 * @since 1.0
 */
public class Company implements Serializable{
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
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
//        out.defaultWriteObject();
        out.writeInt(id);
//        out.writeUTF(name);
        System.out.println("my company writeObject");
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
//        in.defaultReadObject();
        this. id = in.readInt();
//        this.name = in.readUTF();
        System.out.println("my company ReadObject");
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
