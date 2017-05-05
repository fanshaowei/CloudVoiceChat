package cn.com.chengziapp.cloudvoicechat.pushserver.netty;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class PushServerOutboundChannelHandler extends ChannelOutboundHandlerAdapter{
	private static final Logger logger = Logger.getLogger(PushServerOutboundChannelHandler.class);
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		logger.info("----------------PushServer write--------------------------------");
		ByteBuf buf = (ByteBuf) msg;
		
		ctx.writeAndFlush(buf);
	}
}
