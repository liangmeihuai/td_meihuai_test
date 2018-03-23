package com.read.study.chapter04;
import com.specialtroops.chapter04.socket.Commons;
import org.apache.commons.lang.exception.ExceptionUtils;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import static com.specialtroops.chapter04.socket.Commons.closeStream;
import static com.specialtroops.chapter04.socket.Commons.print;
/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/22 16:38
 * @since 1.0
 */
public class MySocketWrapper implements Closeable {
    private Socket socket;
    private static final int PAGE_SIZE = 1024 * 1024;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public MySocketWrapper(Socket socket) throws IOException {
        this.socket = socket;
        processStreams();
    }

    public MySocketWrapper(String host, int port) throws IOException {
        this.socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), 1500);
        socket.setTcpNoDelay(true);
        socket.setKeepAlive(true);
        processStreams();
    }

    private void processStreams() throws IOException {
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void write(byte b) throws IOException {
        this.outputStream.write(b);
    }

    public void write(short s) throws IOException {
        this.outputStream.writeShort(s);
    }

    public void write(int i) throws IOException {
        this.outputStream.writeInt(i);
    }

    public void write(long l) throws IOException {
        this.outputStream.writeLong(l);
    }


    public void write(byte[] bytes) throws IOException {
        this.outputStream.write(bytes);
    }

    public void write(byte[] bytes, int length) throws IOException {
        this.outputStream.write(bytes, 0, length);
    }

    public void write(String value, String charset) throws IOException {
        if (value != null) {
            write(value.getBytes(charset));
        }
    }

    public void write(String value) throws IOException {
        write(value, Commons.DEFAULT_MESSAGE_CHARSET);
    }

    public byte readByte() throws IOException {
        return this.inputStream.readByte();
    }

    public short readShort() throws IOException {
        return this.inputStream.readShort();
    }

    public int readInt() throws IOException {
        return this.inputStream.readInt();
    }

    public long readLong() throws IOException {
        return this.inputStream.readLong();
    }

    public int readBytes(byte[] bytes) throws IOException {
        return this.inputStream.read(bytes);
    }

    public void readFully(byte[] bytes) throws IOException {
        this.inputStream.readFully(bytes);
    }

    public String readStr(int length, String charset) throws IOException {
        byte[] bytes = new byte[length];
        readBytes(bytes);
        return new String(bytes, charset);
    }

    /**
     * 这个是把文件里面的信息写出去
     *
     * @param filePath
     * @throws IOException
     */
    public void writeFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("file is not exists, filePath is [ " + filePath + " ]");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            long fileLength = file.length();
            if (fileLength > PAGE_SIZE) {
                int allLength = 0;
                byte[] bytes = new byte[PAGE_SIZE];
                int len = fileInputStream.read(bytes);
                while (len > 0) {
                    allLength += len;
                    write(bytes, len);
                    len = fileInputStream.read(bytes);
                    print(".");
                }
                print("实际发送文件长度为: " + allLength);
            } else {// 小于page_size直接写出去
                byte[] bytes = new byte[(int) fileLength];
                int len = fileInputStream.read(bytes);
                write(bytes, len);
            }
        } finally {
            closeStream(fileInputStream);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            print("close the socket error " + ExceptionUtils.getStackTrace(e));
        }
    }


    public void displayStatus() throws SocketException {
        // 实现自己的展示内容
    }
}
