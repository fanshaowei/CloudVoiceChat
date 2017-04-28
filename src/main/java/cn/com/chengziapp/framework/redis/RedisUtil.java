package cn.com.chengziapp.framework.redis;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtil {
	
	@Autowired
	LoadRedis loadRedis;	
    
    public JedisPool getPool(){
    	return loadRedis.initRedisPool();
    }
    
    public void closePool(){
    	getPool().close();
    }
    
    public Jedis getJedis(){    	
    	return getPool().getResource();
    }
    
    public void returnJedis(Jedis jedis){
    	if(jedis != null){
    		jedis.close();
    	}
    }    
    
    public void setValue(String key, String data,int seconds){
    	Jedis jedis = getJedis();
    	try{
    		jedis.set(key, data);
    		if(seconds > 0) jedis.expire(key, seconds);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally{
    		returnJedis(jedis);
    	}
    }
    
    public String getValue(String key){
    	Jedis jedis = getJedis();
    	try{
    		return jedis.get(key);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally{
    		returnJedis(jedis);
    	}
    	
    	return null;
    }
    
    public Long delValue(String key){
    	Jedis jedis = getJedis();
    	try{
    		return jedis.del(key);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally{
    		returnJedis(jedis);
    	}
    	
    	return null;
    }
    
    public List<String> getLrange(String key,long start, long end){
    	Jedis jedis = getJedis();
    	try{
    		return jedis.lrange(key, start , end);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally{
    		returnJedis(jedis);
    	}
    	
    	return null;
    }
    
    public Long setRpush(String key, String data){
    	Jedis jedis = getJedis();
    	try{
    		return jedis.rpush(key, data);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally{
    		returnJedis(jedis);
    	}
    	
    	return null;
    }
    
    public Set<String> getkeys(String key){
    	Jedis jedis = getJedis();
    	try{
    		return jedis.keys(key);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally{
    		returnJedis(jedis);
    	}
    	
    	return null;
    }
}
