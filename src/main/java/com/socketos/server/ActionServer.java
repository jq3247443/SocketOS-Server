package com.socketos.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.apache.log4j.Logger;

import com.socketos.utils.FormatStr;

public class ActionServer extends Thread {

	private static final Logger log = Logger.getLogger( ActionServer.class );
	
	private Socket socketIn;
	
	private Socket socketOut;
	
	private InputStream is;
	
	private OutputStream os;
	
	private OutputStream osOut;
	
	private byte[] buffer = new byte[1024];
	
	private byte[] VER = new byte[]{ 0x05, 0x00};
	
	private byte[] CONNECT_OK = new byte[]{  0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	public ActionServer(Socket socket) {
		super();
		this.socketIn = socket;
	}

	@Override
	public void run() {
			
		log.info( "客户端ip:" + socketIn.getInetAddress() + ':' + socketIn.getPort() );
		try {
			is = socketIn.getInputStream();
			os = socketIn.getOutputStream();
			int len = is.read( buffer );
			log.info( '<' + FormatStr.toStr(buffer, 0, len) );
			
			os.write(VER);
			os.flush();
			log.info( '>' + FormatStr.toStr( VER, 0, VER.length ) );
			
			len = is.read(buffer);
			log.info( '<' + FormatStr.toStr(buffer, 0, len) );
			
			for( int i = 3 ; i <= 9 ; i ++ ){
				CONNECT_OK[i] = buffer[i];
			}
			
			os.write(CONNECT_OK);
			os.flush();
			log.info( '>' + FormatStr.toStr( CONNECT_OK, 0, CONNECT_OK.length ) );
			
			len = is.read(buffer);
			log.info( '<' + FormatStr.hexChar(buffer, 0, len) );
			
			String ip = FormatStr.hexIp(CONNECT_OK, 4, 7);
			int port = FormatStr.hexPort(CONNECT_OK, 8, 9);
			log.info( "远程端ip:" + ip + ':' + port );
			socketOut = new Socket(ip, port);
			
			osOut = socketOut.getOutputStream();
			
			osOut.write( Arrays.copyOf(buffer, len) );
			osOut.flush();
			
			new TargetSocket(socketIn, socketOut).start();
		} catch (IOException e) {
			log.error( e.getMessage(), e );
		}
	}
	  
}
