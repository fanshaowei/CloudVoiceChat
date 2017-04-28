package cn.com.chengziapp.cloudvoicechat.pushserver.netty;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class PushClientInboundChannelHandler extends ChannelInboundHandlerAdapter{
	private static final Logger logger = Logger.getLogger(PushServerInboundChannelHandler.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {	
		ByteBuf buf = (ByteBuf) msg;
		byte[] receiveByte = new byte[buf.readableBytes()];
		buf.readBytes(receiveByte);
		String str = new String(receiveByte,"UTF-8");
		logger.info("------client read--------" +str);
	}
}
