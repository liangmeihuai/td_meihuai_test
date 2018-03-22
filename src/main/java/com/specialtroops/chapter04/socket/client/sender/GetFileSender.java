package com.specialtroops.chapter04.socket.client.sender;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import chapter04.socket.SocketWrapper;
import chapter04.socket.client.exceptions.DirectNotExistsException;

import static chapter04.socket.Commons.*;
/**
 * ����Ϊ�����ļ���������
 * @author Administrator
 *
 */
public class GetFileSender implements Sendable {
	
	private String saveFilePath;
	
	private String getFileName;

	@Override
	public byte getSendType() {
		return GET_FILE;
	}
	
	public GetFileSender(String []tokens) {
		if (tokens.length >= 3) {
			saveFilePath = tokens[2];
			File file = new File(saveFilePath);
			if(file.exists() && file.isDirectory()) {
				this.getFileName = tokens[1];
				this.saveFilePath = file.getAbsolutePath() + File.separator;
			}else {
				throw new DirectNotExistsException(saveFilePath);
			}
		}else {
			throw new RuntimeException("��Ϣ��ʽ�������⣬��ʹ��help����鿴�����ʽ��");
		}
	}

	@Override
	public void sendContent(SocketWrapper socketWrapper) throws IOException {
		println("׼�������ļ���" + getFileName);
		byte[] fileNameBytes = getFileName.getBytes(DEFAULT_MESSAGE_CHARSET);
		socketWrapper.write((short) fileNameBytes.length);
		socketWrapper.write(fileNameBytes);
		int status = socketWrapper.readInt();
		if(status != 1) {
			processErrorStatus(status);
		}else {
			long fileLength = socketWrapper.readLong();
			int readLength = 0 , i = 0;
			FileOutputStream out = new FileOutputStream(saveFilePath + getFileName);
			try {
				byte []bytes = new byte[DEFAULT_BUFFER_LENGTH];
				println("��ʼ�����ļ����ļ�����Ϊ��" + fileLength);
				while(readLength < fileLength) {
					int len = socketWrapper.read(bytes);
					readLength += len;
					out.write(bytes, 0, len);
					if(++i % 1024 == 0) {
						print(".");
					}
				}
				println("��ʼ�������.......");
			}finally {
				closeStream(out);
				println("");
			}
		}
	}
	
	private void processErrorStatus(int status) {
		if(status == -1) {
			println("ERROR���ļ�����ʧ�ܣ�ʧ��ԭ��Ϊ��������û���ҵ�ָ�����ļ�....");
		}else {
			println("ERROR���ļ�����ʧ�ܣ�ʧ��ԭ��ȷ��������ʧ�ܴ����Ϊ��" + status);
		}
	}

}
