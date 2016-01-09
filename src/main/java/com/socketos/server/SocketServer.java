package com.socketos.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.socketos.server.ActionServer;

public class SocketServer {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket  ss = new ServerSocket( 8818 );
		
		while ( true ) {
			Socket socket =	ss.accept();
			
			new ActionServer(socket).start();
		}
	}
}
