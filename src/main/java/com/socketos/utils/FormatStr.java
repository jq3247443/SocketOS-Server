package com.socketos.utils;

public class FormatStr {

	public static String hexIp( byte[] buffer, int offset, int len) {
		
		StringBuffer sb = new StringBuffer();
		
		for( int i = offset ; i <= len ; i++ ) {
			sb.append(  0xff & buffer[i] );
			
			if( i != len ) {
				sb.append( "." );
			}
		}
		
		return sb.toString();
	}
	
    public static int hexPort(byte[] bArray, int begin, int end) {  
        int port = 0;  
        for (int i = begin; i <= end; i++) {  
            port <<= 16;  
            port += bArray[i];  
        }  
        return port;  
    }  
    
    
    public static String toStr( byte[] bArray, int offset, int end ) {
    	  StringBuffer sb = new StringBuffer();  
          for (int i = offset; i < end; i++) {  
        	  sb.append( Integer.toHexString( 0xFF & bArray[i] ) );
              sb.append( ',' );  
          }  
          return sb.toString();  
    }
    
    public static String hexChar(  byte[] buffer, int offset, int len ) {
    	StringBuffer sb = new StringBuffer();  
    	for( int i = offset ; i <= len ; i++ ) {
			sb.append(  (char)buffer[i] );
		}
    	return sb.toString();
    }
}
