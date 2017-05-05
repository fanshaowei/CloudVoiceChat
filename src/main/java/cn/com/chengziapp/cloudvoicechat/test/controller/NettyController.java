package cn.com.chengziapp.cloudvoicechat.test.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.chengziapp.cloudvoicechat.commons.utils.CommonUtils;
import cn.com.chengziapp.cloudvoicechat.pushserver.beans.Vp2VpnsBean;
import cn.com.chengziapp.cloudvoicechat.pushserver.netty.PushNettyServer;

@Controller
@RequestMapping("/nettyController")
public class NettyController {

	@Autowired
	PushNettyServer pushNettyServer;		
	
	@RequestMapping(value="/sendMsg",method=RequestMethod.POST)	
	public @ResponseBody String sendMsg(@RequestBody Vp2VpnsBean vp2VpnsBean){		
		String str = JSONObject.fromObject(vp2VpnsBean).toString();	
		pushNettyServer.writeMsg("PUSH_SERVER",str);
		
		return "success";
	}
	
	
	@RequestMapping(value="/pushclientwrite", method=RequestMethod.POST)
	public @ResponseBody String pushclientwrite(HttpServletRequest request){
		String reqStr = CommonUtils.ReqtoString(request);
		//PushNettyClient.writeMsg(reqStr);
		
		return "success";
	}
}
