package cn.com.chengziapp.cloudvoicechat.test.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.chengziapp.cloudvoicechat.pushserver.netty.PushNettyServer;

@Controller
@RequestMapping("/nettyController")
public class NettyController {

	@Autowired
	PushNettyServer pushNettyServer;
	
	@RequestMapping(value="/sendMsg",method=RequestMethod.GET)	
	public @ResponseBody String sendMsg(@PathParam("msg") String msg){
		pushNettyServer.writeMsg(msg);
		
		return "success";
	}
}
