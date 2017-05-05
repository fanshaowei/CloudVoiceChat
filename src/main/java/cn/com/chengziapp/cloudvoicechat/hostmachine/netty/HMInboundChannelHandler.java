package cn.com.chengziapp.cloudvoicechat.hostmachine.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.com.chengziapp.cloudvoicechat.hostmachine.beans.HostMashineGenericBean;
import cn.com.chengziapp.cloudvoicechat.hostmachine.beans.HostMashineRequest;
import cn.com.chengziapp.cloudvoicechat.hostmachine.beans.HostMashineSendData;
import cn.com.chengziapp.cloudvoicechat.hostmachine.enums.HostMachineStateCode;
import cn.com.chengziapp.cloudvoicechat.service.NettyService;


public class HMInboundChannelHandler extends ChannelInboundHandlerAdapter{
	private static final Logger logger = Logger.getLogger(HMInboundChannelHandler.class);
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {	
		logger.info("---------------HostMashine Registered---------------------------");
		//ctx.read();
		//NettyService.addNettyChannel(key, (SocketChannel)ctx.channel());
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//logger.info("---------------HostMashine channelRead---------------------------");
		/*ByteBuf bf = (ByteBuf)msg;
		byte[] byteRec = new byte[bf.readableBytes()];		
		bf.readBytes(byteRec);		
		String strRec = new String(byteRec, "UTF-8");*/
		System.out.println(msg);
		JSONObject jsonRec = JSONObject.fromObject(msg);
		long stateCode = jsonRec.getLong("stateCode");
		JSONObject data = jsonRec.getJSONObject("data");
		if(stateCode == HostMachineStateCode.HM_REQ_SVR.getStateCode()){
			HostMashineRequest hostRequest = (HostMashineRequest)JSONObject.toBean(data, HostMashineRequest.class);
			HostMashineGenericBean<HostMashineRequest> hostGenericBean = new HostMashineGenericBean<HostMashineRequest>(hostRequest);
			hostGenericBean.setStateCode(stateCode);
			
			/****在发起呼叫请求时先保存主机长连接通道*****/
			NettyService nettyService = new NettyService();
			nettyService.createHostMashineTcpChannelKey(hostGenericBean.getE().getHostId(), 
					hostGenericBean.getE().getEstateCode(), hostGenericBean.getE().getHourseCode());
			if(!nettyService.isContainChannel()){
				nettyService.setSocketChannel((SocketChannel)ctx.channel())
					.addSocketChannelToMap();
			}
			
		}else if(stateCode == HostMachineStateCode.HM_LEAVE_VOICE.getStateCode() ||
				stateCode == HostMachineStateCode.HM_LEAVE_IMG.getStateCode() || 
				stateCode == HostMachineStateCode.HM_CALLING_VOICE.getStateCode() ||
				stateCode == HostMachineStateCode.HM_CALLING_IMG.getStateCode()) {
			HostMashineSendData hostMashineSendData = (HostMashineSendData)JSONObject.toBean(data, HostMashineSendData.class);
			HostMashineGenericBean<HostMashineSendData> hostGenericBean = 
					new HostMashineGenericBean<HostMashineSendData>(hostMashineSendData);
			hostGenericBean.setStateCode(stateCode);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {	
		//logger.info("---------------HostMashine channelReadComplete---------------------------");
	} 
}
