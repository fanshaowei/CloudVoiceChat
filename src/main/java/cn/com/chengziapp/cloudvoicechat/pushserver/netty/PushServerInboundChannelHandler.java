package cn.com.chengziapp.cloudvoicechat.pushserver.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import org.apache.log4j.Logger;

import cn.com.chengziapp.cloudvoicechat.service.MyMessageEncode;
import cn.com.chengziapp.cloudvoicechat.service.NettyService;

public class PushServerInboundChannelHandler extends ChannelInboundHandlerAdapter{
	private static final Logger logger = Logger.getLogger(PushServerInboundChannelHandler.class);
	
	private final String CHANNEL_KEY = "PUSH_SERVER";
	private final String READ_COMP = "READ COMPLETE";
	private final String REGISTERED = "REGISTERED OK";
	private NettyService nettyService;
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logger.info("------------------PushServer channelRegistered----------------------");
		nettyService = new NettyService();		
		nettyService.addSocketChannelToMap(CHANNEL_KEY, (SocketChannel)ctx.channel());			
		
		ByteBuf sendByteBuf = MyMessageEncode.messageEncode(MyMessageEncode.headType.VP2PNS.toString(), 
				REGISTERED.length(), REGISTERED);
		ctx.writeAndFlush(sendByteBuf);		
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		logger.info("------------------PushServer channelUnregistered----------------------");
		SocketChannel sc = NettyService.getNettyChannel(CHANNEL_KEY);
		if(sc != null){
			NettyService.removeNettyChannel(CHANNEL_KEY);
		}		
	}	

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {			
		logger.info("------------------PushServer channelRead----------------------");	
		ByteBuf buf = (ByteBuf) msg;
		
		byte[] read = new byte[buf.readableBytes()];
		buf.readBytes(read);
		String str = new String(read,"UTF-8");
		logger.info(str);			
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.info("------------------PushServer read complete--------------------");
		
		ByteBuf sendByteBuf = MyMessageEncode.messageEncode(MyMessageEncode.headType.VP2PNS.toString(), 
				READ_COMP.length(), READ_COMP);
		ctx.writeAndFlush(sendByteBuf);	
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		ctx.close();
	}
}
