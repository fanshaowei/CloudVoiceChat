package cn.com.chengziapp.cloudvoicechat.service;

import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyService {
	//public final static String channelKey = "PUSH_SERVER";
	
	protected static Map<String, SocketChannel> map = new ConcurrentHashMap<>();
	protected String channelKey;
	protected SocketChannel socketChannel;
		
	public NettyService setChannelKey(String channelKey) {
		this.channelKey = channelKey;
		
		return this;
	}
	public NettyService setSocketChannel(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
		
		return this;
	}
	
	public NettyService addSocketChannelToMap(){
		if(this.channelKey != null  && this.socketChannel != null){
			map.put(this.channelKey, this.socketChannel);
		}
		
		return this;
	}
	
	public NettyService addSocketChannelToMap(String channelKey, SocketChannel socketChannel){
		if(channelKey != null && socketChannel != null){
			this.channelKey = channelKey;
			this.socketChannel = socketChannel;
			map.put(channelKey, socketChannel);
		}
		
		return this;
	}
		 	
	/**
	 * 根据channelKey获取一个长边接通道
	 * @param channelKey
	 * @return
	 */
	public static SocketChannel getNettyChannel(String channelKey){
		return map.get(channelKey);
	}
	
	/**
	 * 根据channelKey删除一个长连接通道
	 * @param key
	 */
	public static void removeNettyChannel(String channelKey){
		map.remove(channelKey);
	}
	
	/**
	 * 获取所有长连接通道
	 * @return
	 */
	public static Map<String, SocketChannel> getChannels(){
		return map;
	}	
	
	public boolean isContainChannel(String channelKey){			
		if(getNettyChannel(channelKey) != null)
			return true;
		
		return false;
	}
	
	public boolean isContainChannel(){
		if(this.channelKey != null){
			if(getNettyChannel(this.channelKey) != null)
				return true;
		}
			
		return false;
	}
	
	/**
	 * 根据主机号，小区号，房号 生成长连接通道索引
	 * @param hostId
	 * @param estateCode
	 * @param hourseCode
	 * @return
	 */
	public NettyService createHostMashineTcpChannelKey(String hostId, String estateCode, String hourseCode){
		StringBuilder sb = new StringBuilder();
		sb.append(hostId);
		sb.append(estateCode);		
		sb.append(hourseCode);
		
		this.setChannelKey(sb.toString()); 
		
		return this;
	}
	
}
