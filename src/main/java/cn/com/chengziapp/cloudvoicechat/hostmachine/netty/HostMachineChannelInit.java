package cn.com.chengziapp.cloudvoicechat.hostmachine.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class HostMachineChannelInit extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline= ch.pipeline();
		
		//channelPipeline.addLast(new HMOutboundChannelHandler());
		//输入处理通道
		channelPipeline.addLast("decoder", new LengthFieldBasedFrameDecoder(10000, 
				6, 4, 0, 10));
		channelPipeline.addLast(new StringDecoder());
		channelPipeline.addLast(new HMInboundChannelHandler());
		
	}

}
