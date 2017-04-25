package cn.com.chengziapp.cloudvoicechat.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class LoginController {

	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
	@RequestMapping("/home")
    public String home() {
        return "home";
    }
	
}
