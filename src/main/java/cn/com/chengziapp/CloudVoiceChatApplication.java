package cn.com.chengziapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class CloudVoiceChatApplication extends SpringBootServletInitializer{		
	
	public static void main(String[] args){						
		SpringApplication.run(CloudVoiceChatApplication.class);		
	}
		
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CloudVoiceChatApplication.class);
    }
    
}
