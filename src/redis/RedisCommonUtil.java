package redis;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 通用缓存
 * @author xxp
 * @since 2016年10月17日 下午1:34:05
 * @version 1
 */
public class RedisCommonUtil extends RedisUtil {
	/**
	 * 构造方法
	 * @param rp
	 */
	public RedisCommonUtil(JedisPool rp) {
		super(rp);

	}
	/**
	 * 默认数据库
	 */
	@Autowired
	private Integer defaultDB;
	/**
	 * @return the defaultDB
	 */
	public Integer getDefaultDB() {
		return defaultDB;
	}
	/**
	 * @param defaultDB the defaultDB to set
	 */
	public void setDefaultDB(Integer defaultDB) {
		this.defaultDB = defaultDB;
	}
	/**
	 * 存入数据
	 */
	public void putObject(Object key, Object value) {
		super.putObject(key, value,this.defaultDB);
	}
	
	public void putObject(Object key, Object value,int dbIndex) {
		super.putObject(key, value,dbIndex);
	}
	
	/**
	 * 存入数据
	 */
	public void putObjectWithLife(Object key, Object value, int seconds) {
		super.putObjectWithLife(key, value,this.defaultDB,seconds);
	}
	
	/**
	 * 存入数据
	 */
	public void putObjectWithLife(Object key, Object value,int dbIndex, int seconds) {
		super.putObjectWithLife(key, value,dbIndex, seconds);
	}
	/**
	 * 获取数据
	 */
	public Object getObject(Object key) {
		return super.getObject(key,this.defaultDB);
	}
	/**
	 * 获取数据
	 */
	public Object getObject(Object key,int dbIndex) {
		return super.getObject(key,dbIndex);
	}
	/**
	 * 删除数据
	 */
	public Object removeObject(Object key) {
		return super.removeObject(key,this.defaultDB);
	}
	/**
	 * 删除数据
	 */
	public Object removeObject(Object key,int dbIndex) {
		return super.removeObject(key,dbIndex);
	}
	
	/**
	 * 清空数据
	 */
	public void clear(int dbIndex) {
		super.clear(dbIndex);
	}
	
	/**
	 * 清空数据
	 */
	public void clear() {
		super.clear(this.defaultDB);
	}
	/**
	 * 全部清空
	 */
	public void clearAll()
	{
		super.clearAll();
	}

	/**
	 * 设置生命周期
	 */
	public long setKeyExpireTime(Object key, int dbIndex, int seconds)
	{
		return super.setKeyExpireTime(key, dbIndex, seconds);
	}
	/**
	 * 设置生命周期
	 */
	public long setKeyExpireTime(Object key, int seconds)
	{
		return super.setKeyExpireTime(key,  seconds);
	}
}
