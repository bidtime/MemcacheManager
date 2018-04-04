package org.bidtime.cache;

import org.bidtime.cache.AbstractCache;

import net.spy.memcached.MemcachedClient;

/**
 * 
 * jss
 */
public class Memcache extends AbstractCache {
	
	private MemcachedClient memcacheClient;
	
	public void set(String key, int seconds, Object o) throws Exception {
		memcacheClient.set(key, seconds, o);
	}
	
	public void delete(String key) throws Exception {
		memcacheClient.delete(key);
	}
	
	public void replace(String key, int seconds, Object o) throws Exception {
		memcacheClient.replace(key, seconds, o);
	}
	
	public Object get(String key) throws Exception {
		return memcacheClient.get(key);
	}

}
