package com.specialtroops.chapter04.socket.client.sender;
import com.specialtroops.chapter04.socket.SocketWrapper;

import java.io.IOException;
public interface Sendable {
	
	public byte getSendType();

	public void sendContent(SocketWrapper socketWrapper) throws IOException;
}
