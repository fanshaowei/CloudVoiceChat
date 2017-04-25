package cn.com.chengziapp.cloudvoicechat.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class CloudVoiceChatChannelInit extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline= ch.pipeline();
		
		channelPipeline.addLast(new HostMachineChannelHandler());
		
	}

}
