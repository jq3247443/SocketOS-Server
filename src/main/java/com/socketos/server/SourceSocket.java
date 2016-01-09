package com.socketos.server;

import java.io.InputStream;
import java.io.OutputStream;

public class SourceSocket {
	
	private InputStream is;
	
	private OutputStream os;

	public SourceSocket(InputStream is, OutputStream os) {
		super();
		this.is = is;
		this.os = os;
	}
	
	public void run() {
		
	}
	
}
