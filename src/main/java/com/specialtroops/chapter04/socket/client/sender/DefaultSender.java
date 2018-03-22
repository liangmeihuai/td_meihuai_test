package com.specialtroops.chapter04.socket.client.sender;

import static chapter04.socket.Commons.ERROR_MESSAGE_FORMAT;
import static chapter04.socket.Commons.EXIT_STR;
import static chapter04.socket.Commons.HELP_SHOW;
import static chapter04.socket.Commons.HELP_STR;
import static chapter04.socket.Commons.println;

import java.io.IOException;

import chapter04.socket.SocketWrapper;
import chapter04.socket.client.exceptions.ExitException;

public class DefaultSender implements Sendable {
	
	public DefaultSender(String []tokens) {
		String firstToken = tokens[0];
		if(HELP_STR.equalsIgnoreCase(firstToken)) {//����
			println(HELP_SHOW);
		}else if(EXIT_STR.equalsIgnoreCase(firstToken)) {//�˳�
			//System.exit(0);�÷���ֱ�ӹرս��̣�Ҳ����ʹ�ã��Զ����ExitException�ⲿ����socket���մ���
			throw new ExitException();
		}else {
			throw new RuntimeException(ERROR_MESSAGE_FORMAT);
		}
	}

	@Override
	public byte getSendType() {
		return 0;
	}

	@Override
	public void sendContent(SocketWrapper socketWrapper) throws IOException {
		/*�������κ���Ϣ*/
	}

}
