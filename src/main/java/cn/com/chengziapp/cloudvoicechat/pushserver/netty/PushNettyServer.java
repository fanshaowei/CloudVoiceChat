package cn.com.chengziapp.cloudvoicechat.pushserver.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.com.chengziapp.cloudvoicechat.service.NettyService;

/**
 * 用来连接推送服务器
 * @author fanshaowei
 *
 */
@Component
public class PushNettyServer {
	private static Logger logger = LoggerFactory.getLogger(PushNettyServer.class);
	
	private Thread pushNettyClientThread;
	
	private EventLoopGroup pushBoosGroup ;	
	private EventLoopGroup pushworkGroup ;
	
	private ServerBootstrap pushServerBootstrap;
	private PushServerChannelInit pushServerChannelInit;
	private ChannelFuture pushChannelFuture;
	
	public PushNettyServer(){
		pushBoosGroup = new NioEventLoopGroup();
		pushworkGroup = new NioEventLoopGroup();
	}
	
	@PostConstruct
	public void initPushServer(){
		
		pushNettyClientThread = new Thread(){
			@Override
			public void run() {
				super.run();				
				pushServerBootstrap = new ServerBootstrap();
				pushServerChannelInit = new PushServerChannelInit();	
				
				pushServerBootstrap.group(pushBoosGroup, pushworkGroup)
					.channel(NioServerSocketChannel.class)
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(pushServerChannelInit);
						
				try {
					pushChannelFuture = pushServerBootstrap.bind(20002).sync();
					pushChannelFuture.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					pushBoosGroup.shutdownGracefully();
					pushworkGroup.shutdownGracefully();
				}					
			}
		};
		
		pushNettyClientThread.start();
			
	}
	
	
	/*public PushNettyServer getPushNettyServer(){		
		PushNettyServer pushNettyServer = new PushNettyServer();
		pushNettyServer.initPushServer();
		
		return pushNettyServer;
	}*/
	
	public void writeMsg(String send){		
		ByteBuf byteBuf = Unpooled.copiedBuffer(send.getBytes());
		SocketChannel socketChannel = NettyService.getNettyChannel(NettyService.channelKey);
		
		if(socketChannel != null){
			socketChannel.writeAndFlush(byteBuf);
		}else{
			logger.info("-----------------推送服务器没有连接----------------------------------");
		}		
	}
}
