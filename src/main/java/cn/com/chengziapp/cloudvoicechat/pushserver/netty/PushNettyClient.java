package cn.com.chengziapp.cloudvoicechat.pushserver.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class PushNettyClient {	
	private EventLoopGroup group;
	private Bootstrap bootstrap;
	private static ChannelFuture channelFuture;		
	
	public PushNettyClient() {
		group = new NioEventLoopGroup();
		bootstrap = new Bootstrap();
	}
	
	public void contextInitialized() {												
		bootstrap.group(group)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new PushClientOutBoundChannelHandler());
					ch.pipeline().addLast(new PushClientInboundChannelHandler());
				}				
			});
		
		try {
			channelFuture = bootstrap.connect("127.0.0.1", 20001).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
	}

	public void contextDestroyed() {
		if(group!=null)
			group.shutdownGracefully();
	}		
	
	public static void main(String[] args){
		PushNettyClient pc = new PushNettyClient();
		pc.contextInitialized();				
	}
	
}
