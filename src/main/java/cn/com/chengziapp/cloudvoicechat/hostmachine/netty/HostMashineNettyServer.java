package cn.com.chengziapp.cloudvoicechat.hostmachine.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class HostMashineNettyServer {
	private Thread hmNettyServerThread;
	
	private EventLoopGroup boosGroup ;	
	private EventLoopGroup workGroup ;
	
	//对讲主机
	private ServerBootstrap hmServerBootstrap;
	private HostMachineChannelInit hmCloudVoiceChatChannelInit;
	private ChannelFuture hmChannelFuture;	
	
	public HostMashineNettyServer(){
		boosGroup = new NioEventLoopGroup();
		workGroup = new NioEventLoopGroup();
	}
	
	@PostConstruct
	public void initHostMashineServer(){		 		
		hmNettyServerThread = new Thread(){
			@Override
			public void run() {			
				super.run();
				hmServerBootstrap = new ServerBootstrap();
				hmCloudVoiceChatChannelInit = new HostMachineChannelInit();	
				
				hmServerBootstrap.group(boosGroup, workGroup)
					.channel(NioServerSocketChannel.class)
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(hmCloudVoiceChatChannelInit);
						
				try {
					hmChannelFuture = hmServerBootstrap.bind(20001).sync();
					hmChannelFuture.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					boosGroup.shutdownGracefully();
					workGroup.shutdownGracefully();
				}
			}
		};
		
		hmNettyServerThread.setName("hmNettyServerThread");
		hmNettyServerThread.start();							
	}
	
	@PreDestroy
	public void closeHmNettyServerThread(){
		if(hmNettyServerThread.isAlive()){
			hmNettyServerThread.interrupt();
		}
	}
	
}
