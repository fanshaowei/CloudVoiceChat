package cn.com.chengziapp.cloudvoicechat.hostmachine.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class HostMachineChannelInit extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline= ch.pipeline();
		channelPipeline.addLast(new StringDecoder());
		//输出处理通道
		channelPipeline.addLast(new HMOutboundChannelHandler());
		//输入处理通道
		channelPipeline.addLast(new HMInboundChannelHandler());
		
	}

}
