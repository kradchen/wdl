package redis;
import utility.SerializableUtility;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * redis操作基类
 * @author xxp
 * @since 2016年10月25日 上午10:58:34
 * @version
 */
public class RedisUtil {	
	
	private JedisPool RedisPool;
	
	
	
	public RedisUtil(JedisPool rp)
	{
		this.RedisPool= rp;
	}
	
	private Jedis GetClientInstance()
	{
		if(RedisPool!=null)
		{
			return RedisPool.getResource();
		}
		return null;
	}
	/**
	 * @return the jedisPool
	 */
	public JedisPool getRedisPool() {
		return RedisPool;
	}
	/**
	 * @param jedisPool the jedisPool to set
	 */
	public void setRedisPool(JedisPool jedisPool) {
		this.RedisPool = jedisPool;
	}
	
	
	public int getSize() {
		return Integer.valueOf(GetClientInstance().dbSize().toString());
	}

	protected void putObject(Object key, Object value) {
		Jedis jedis=GetClientInstance();
		try{	
			jedis.set(SerializableUtility.ByteSerialize(key.toString()),
					SerializableUtility.ByteSerialize(value));
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}		
	}
	
	protected void putObject(Object key, Object value,int dbIndex) {
		Jedis jedis=GetClientInstance();
		try{	
			jedis.select(dbIndex);
			jedis.set(SerializableUtility.ByteSerialize(key.toString()),
					SerializableUtility.ByteSerialize(value));
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}			
	}

	protected void putObjectWithLife(Object key, Object value, int seconds) {
		Jedis jedis=GetClientInstance();
		try{	
			jedis.set(SerializableUtility.ByteSerialize(key.toString()),
					SerializableUtility.ByteSerialize(value));
			jedis.expire(SerializableUtility.ByteSerialize(key.toString()), seconds);
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}		
	}
	
	protected void putObjectWithLife(Object key, Object value,int dbIndex, int seconds) {
		Jedis jedis=GetClientInstance();
		try{	
			jedis.select(dbIndex);
			jedis.set(SerializableUtility.ByteSerialize(key.toString()),
					SerializableUtility.ByteSerialize(value));
			jedis.expire(SerializableUtility.ByteSerialize(key.toString()), seconds);
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}		
	}
	
	protected long setKeyExpireTime(Object key, int dbIndex, int seconds)
	{
		Jedis jedis=GetClientInstance();
		try{	
			jedis.select(dbIndex);
			return jedis.expire(SerializableUtility.ByteSerialize(key.toString()), seconds);
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}		
	}
	protected long setKeyExpireTime(Object key, int seconds)
	{
		Jedis jedis=GetClientInstance();
		try{	
			return jedis.expire(SerializableUtility.ByteSerialize(key.toString()), seconds);
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}		
	}
	protected Object getObject(Object key) {
		
		Jedis jedis=GetClientInstance();
		try{	
			Object value = SerializableUtility.ByteDeserialize(jedis
					.get(SerializableUtility.ByteSerialize(key.toString())));
			return value;
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	
	}
	
	protected Object getObject(Object key,int dbIndex) {
		Jedis jedis=GetClientInstance();
		try{	
			jedis.select(dbIndex);
			Object value = SerializableUtility.ByteDeserialize(
					jedis.get(SerializableUtility.ByteSerialize(key.toString())));
			return value;
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}		
	}

	protected Object removeObject(Object key) {
		Jedis jedis=GetClientInstance();
		try{	
			return jedis.del(SerializableUtility.ByteSerialize(key.toString()));
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	protected Object removeObject(Object key,int dbIndex) {
		Jedis jedis=GetClientInstance();
		try
		{
			jedis.select(dbIndex);
			return jedis.del(SerializableUtility.ByteSerialize(key.toString()));
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	protected void clearAll() {
		Jedis jedis=GetClientInstance();
		try{	
			jedis.flushAll();
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	protected void clear(int dbIndex) {
		
		Jedis jedis=GetClientInstance();
		try{	
			jedis.select(dbIndex);
			jedis.flushDB();
		}finally{
			try
			{
				jedis.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	

}


