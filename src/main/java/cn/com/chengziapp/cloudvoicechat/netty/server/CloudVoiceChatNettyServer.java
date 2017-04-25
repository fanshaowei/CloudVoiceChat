package cn.com.chengziapp.cloudvoicechat.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class CloudVoiceChatNettyServer {
	private EventLoopGroup boosGroup ;	
	private EventLoopGroup workGroup ;
	private ServerBootstrap serverBootstrap;
	private CloudVoiceChatChannelInit cloudVoiceChatChannelInit;
	
	public void initNettyServer(){
		boosGroup = new NioEventLoopGroup();
		workGroup = new NioEventLoopGroup(); 
		cloudVoiceChatChannelInit = new CloudVoiceChatChannelInit();
		
		serverBootstrap.group(boosGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 100)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(cloudVoiceChatChannelInit);
		
		ChannelFuture channelFuture;
		try {
			channelFuture = serverBootstrap.bind(10000).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			boosGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}		
		
	}
}
