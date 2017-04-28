package cn.com.chengziapp.cloudvoicechat.pushserver.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class PushServerChannelInit extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {		
		ChannelPipeline channelPipeline = ch.pipeline();
		channelPipeline.addLast(new PushServerOutboundChannelHandler());
		channelPipeline.addLast(new PushServerInboundChannelHandler());
	}
	
}
