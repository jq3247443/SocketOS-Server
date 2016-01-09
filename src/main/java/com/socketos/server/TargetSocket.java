package com.socketos.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TargetSocket {

	private Socket socketIn;
	private Socket socketOut;
	
	private InputStream is;
	private OutputStream os;
	
	
	public TargetSocket(Socket socketIn, Socket socketOut) {
		super();
		this.socketIn = socketIn;
		this.socketOut = socketOut;
	}


	void start() throws IOException {
		is = socketOut.getInputStream();
		os = socketIn.getOutputStream();
		
		int i;
		while( ( i = is.read() ) != -1 ) {
			os.write(i);
		}
		os.flush();
	}
}
