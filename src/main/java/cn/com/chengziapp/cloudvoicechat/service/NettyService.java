package cn.com.chengziapp.cloudvoicechat.service;

import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyService {
	public final static String channelKey = "PUSH_SERVER";
	
	private static Map<String, SocketChannel> map = new ConcurrentHashMap<>();
	
	public static void addNettyChannel(String key, SocketChannel socketChannel){
		map.put(key, socketChannel);
	}
	
	public static SocketChannel getNettyChannel(String key){
		return map.get(key);
	}
	
	public static void removeNettyChannel(String key){
		map.remove(key);
	}
	
	public static Map<String, SocketChannel> getChannels(){
		return map;
	}
	
	
}
