package cn.com.chengziapp.cloudvoicechat.pushserver.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import org.apache.log4j.Logger;

import cn.com.chengziapp.cloudvoicechat.service.NettyService;

public class PushServerInboundChannelHandler extends ChannelInboundHandlerAdapter{
	private static final Logger logger = Logger.getLogger(PushServerInboundChannelHandler.class);
	
	private final String channelKey = NettyService.channelKey;
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logger.info("------------------channelRegistered----------------------");
		NettyService.addNettyChannel(channelKey, (SocketChannel)ctx.channel());			
		
		String send = "register ok";
		ctx.writeAndFlush(Unpooled.copiedBuffer(send.getBytes()));		
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		logger.info("------------------channelUnregistered----------------------");
		SocketChannel sc = NettyService.getNettyChannel(channelKey);
		if(sc != null){
			NettyService.removeNettyChannel(channelKey);
		}		
	}	

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {		
		logger.info("------------------channelRead----------------------");	
		ByteBuf buf = (ByteBuf) msg;
		byte[] receiveByte = new byte[buf.readableBytes()];
		buf.readBytes(receiveByte);
		String str = new String(receiveByte,"UTF-8");
		logger.info(str);	
		
		String send = "read ok";
		ctx.writeAndFlush(Unpooled.copiedBuffer(send.getBytes()));	
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.info("------------------read complete--------------------");
		ctx.flush();
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		ctx.close();
	}
}
