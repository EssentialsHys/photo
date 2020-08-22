package org.ynu.util.jedis;

import java.util.List;

/**
 * @Description: JedisClient接口
 * @author: hys 
 * @date: 2020年4月18日
 */
public interface JedisClient {
	
	public String set(String key, String value);
	public String get(String key);
	public Boolean exists(String key);
	public Long expire(String key, int seconds);
	public Long ttl(String key);
	public Long incr(String key);
	public Long hset(String key, String field, String value);
	public String hget(String key, String field);
	public Long hdel(String key, String... field);
	public Long del(String key);
	public Long rpush(String key, String... strings);
	public List<String> lrange(String key, long start, long end);

}
