package cn.com.chengziapp.cloudvoicechat.pushserver.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class PushNettyClient {

	public void initPushNettyClient() {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new PushClientInboundChannelHandler());
				}				
			});
		ChannelFuture channelFuture;
		try {
			channelFuture = bootstrap.connect("127.0.0.1", 20002).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
		
	}
	
	public static void main(String[] args){
		PushNettyClient pushNettyClient = new PushNettyClient();
		pushNettyClient.initPushNettyClient();
	}
}
