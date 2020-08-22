package org.ynu.util.jedis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description: JedisClient接口实现类
 * @author: hys 
 * @date: 2020年4月22日
 */
public class JedisPoolClient implements JedisClient{
	
	private JedisPool jedisPool;
	
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Boolean exists(String key) {
		Jedis jedis = jedisPool.getResource();
		Boolean result = jedis.exists(key);
		jedis.close();
		return result;
	}

	// 在指定键上设置超时。
	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key,seconds);
		jedis.close();
		return result;
	}

	// TTL命令返回已设置EXPIRE的键的剩余生存时间（以秒为单位）
	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	// 将键处存储的数字加1。
	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	// 将指定的哈希字段设置为指定的值。
	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key,field,value);
		jedis.close();
		return result;
	}

	// 如果key包含哈希，则检索与指定字段关联的值。
	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.hget(key,field);
		jedis.close();
		return result;
	}

	// 从存储在key处的哈希中删除指定的字段。
	@Override
	public Long hdel(String key, String... field) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(key,field);
		jedis.close();
		return result;
	}
	
	
	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public Long rpush(String key, String... strings) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.rpush(key, strings);
		return result;
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		Jedis jedis = jedisPool.getResource();
		return jedis.lrange(key, start, end);
	}
}
