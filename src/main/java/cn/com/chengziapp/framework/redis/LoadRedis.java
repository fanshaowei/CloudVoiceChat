package cn.com.chengziapp.framework.redis;

import java.util.ResourceBundle;

import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.telnet.TelnetClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class LoadRedis {
	private static final Log logger = LogFactory.getLog(LoadRedis.class);
	private JedisPool jedispool = null;
	
	@Bean
	public JedisPool initRedisPool(){		
		
		logger.info("--------------------初始化redis配置-------------------------");
    	
    	//读取 redis.properties 配置文件文件
    	ResourceBundle bundle = ResourceBundle.getBundle("redis");
    	if(bundle == null){
    		throw new IllegalArgumentException("[redis.properties] is not found!");
    	}
    	
    	JedisPoolConfig config = new JedisPoolConfig();
    	
    	config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxTotal")).intValue());
    	config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")).intValue());
    	config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWaitMillis")).longValue());
    	config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")).booleanValue());
    	config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")).booleanValue());
    	
    	TelnetClient telnet = new TelnetClient();
    	telnet.setConnectTimeout(5000);
    	try{
    		telnet.connect(bundle.getString("redis.ip"), Integer.parseInt(bundle.getString("redis.port")));
    		telnet.disconnect();
    		
    		jedispool = new JedisPool(config , bundle.getString("redis.ip"));
    		
    		logger.info("--------------------成功连接Redis服务器-------------------------");    		
    	}catch(Exception ex){
    		logger.info("--------------------连接Redis服务器失败,reids服务器未启动-------------------------");
    	}
    	
    	return jedispool;
	}
	
	@PreDestroy
	public void destoryRedisPool(){
		if(jedispool != null){
			jedispool.destroy();
			logger.info("----------------------关闭Reids连接池-------------------------------------");
		}		
	}
}
