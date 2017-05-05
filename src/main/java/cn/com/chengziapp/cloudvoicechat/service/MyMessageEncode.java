package cn.com.chengziapp.cloudvoicechat.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class MyMessageEncode{
	public enum headType {VP2PNS,HostMashine,MobileTerminal};
	
	public static ByteBuf messageEncode(String headtype, int lenth , String content){
		
		byte[] headByte = headtype.getBytes();
		byte[] lenthByte = intToByte4(lenth);
		byte[] contentByte = content.getBytes();		
		
		return Unpooled.copiedBuffer(headByte,lenthByte,contentByte);			
	}
	
	public static byte[] intToByte4(int i) {  
        byte[] targets = new byte[4];  
        targets[3] = (byte) (i & 0xFF);  
        targets[2] = (byte) (i >> 8 & 0xFF);  
        targets[1] = (byte) (i >> 16 & 0xFF);  
        targets[0] = (byte) (i >> 24 & 0xFF);
        
        return targets;  
    } 
}
