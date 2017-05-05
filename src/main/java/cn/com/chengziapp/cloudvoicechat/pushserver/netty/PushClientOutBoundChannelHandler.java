package cn.com.chengziapp.cloudvoicechat.pushserver.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

import org.apache.log4j.Logger;

public class PushClientOutBoundChannelHandler extends ChannelOutboundHandlerAdapter{
	private static Logger logger = Logger.getLogger(PushClientOutBoundChannelHandler.class);
	private static final String WRITESTR = "{\"data\":{\"hostId\":\"85566\",\"estateCode\":\"100120\",\"hourseCode\":\"11010101\",\"callingState\":\"1\"},\"stateCode\":\"4000\"}";
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		logger.info("-------------PushClientOutBoundChannelHandler write---------------------------");	
		byte[] writestrbyte = WRITESTR.getBytes();
		ByteBuf bf = Unpooled.buffer(writestrbyte.length);
		bf.writeBytes(writestrbyte);		
		
		ctx.channel().writeAndFlush(bf);		
	}
	
	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,
			SocketAddress localAddress, ChannelPromise promise)
			throws Exception {
		logger.info("-------------PushClientOutBoundChannelHandler connect---------------------------");			
	}
	
	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise)
			throws Exception {
		logger.info("-------------PushClientOutBoundChannelHandler disconnect---------------------------");
	}	
	
	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		logger.info("-------------PushClientOutBoundChannelHandler read---------------------------");
	}

	
}
