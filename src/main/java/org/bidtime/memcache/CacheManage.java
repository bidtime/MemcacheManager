package org.bidtime.memcache;

import net.spy.memcached.MemcachedClient;

/**
 * 
 * jss
 */
public class CacheManage {
	
	private MemcachedClient memcacheClient;
	
	protected int defaultTm = 2 * 60 * 60;		// default: 2h = 7200s;
	
	public String getKeyId(String key) {
		return key;
	}

	public String getKeyId(String key, String ext) {
		return key + ext;
	}

	// set
	
	public void set(String key, int seconds, Object value) {
		MemcacheUtils.set(memcacheClient, getKeyId(key), seconds, value);
	}

	public void set(String key, Object value) {
		MemcacheUtils.set(memcacheClient, getKeyId(key), defaultTm, value);
	}

	public void set(String key, String ext, int seconds, Object value) {
		MemcacheUtils.set(memcacheClient, getKeyId(key, ext), seconds, value);
	}

	public void set(String key, String ext, Object value) {
		MemcacheUtils.set(memcacheClient, getKeyId(key, ext), defaultTm, value);
	}
	
	// replace

	public void replace(String key, int seconds, Object value) {
		MemcacheUtils.replace(memcacheClient, getKeyId(key), seconds, value);
	}

	public void replace(String key, Object value) {
		MemcacheUtils.replace(memcacheClient, getKeyId(key), defaultTm, value);
	}
	
	public void replace(String key, String ext, int seconds, Object value) {
		MemcacheUtils.replace(memcacheClient, getKeyId(key, ext), seconds, value);
	}
	
	public void replace(String key, String ext, Object value) {
		MemcacheUtils.replace(memcacheClient, getKeyId(key, ext), defaultTm, value);
	}
	
	// get

	public Object get(String key, boolean delete) {
		return MemcacheUtils.get(memcacheClient, getKeyId(key), delete);
	}

	public Object get(String key) {
		return MemcacheUtils.get(memcacheClient, getKeyId(key));
	}
	
	//      get ext

	public Object get(String key, String ext, boolean delete) {
		return MemcacheUtils.get(memcacheClient, getKeyId(key, ext), delete);
	}

	public Object get(String key, String ext) {
		return MemcacheUtils.get(memcacheClient, getKeyId(key, ext), false);
	}
	
	// get string
	
	public String getString(String key) {
		return MemcacheUtils.getString(memcacheClient, getKeyId(key));
	}
	
	// delete
	
	public void delete(String key) {
		MemcacheUtils.delete(memcacheClient, getKeyId(key));
	}
	
	public void delete(String key, String ext) {
		MemcacheUtils.delete(memcacheClient, getKeyId(key, ext));
	}
	
	// equals
	
    public boolean equals(String key, String value) {
    	return MemcacheUtils.equals(memcacheClient, getKeyId(key), value);
    }
    
    public boolean equals(String key, String value, boolean allowEmpty) {
    	if (allowEmpty) {
    		return MemcacheUtils.equals(memcacheClient, getKeyId(key), value);
    	} else {
    		return MemcacheUtils.equalsWithoutEmpty(memcacheClient, getKeyId(key), value);    		
    	}
    }
    
    public boolean equalsIgnoreCase(String key, String value) {
    	return MemcacheUtils.equalsIgnoreCase(memcacheClient, getKeyId(key), value);
    }
    
    public boolean equalsIgnoreCase(String key, String value, boolean allowEmpty) {
    	if (allowEmpty) {
    		return MemcacheUtils.equalsIgnoreCase(memcacheClient, getKeyId(key), value);
    	} else {
    		return MemcacheUtils.equalsIgnoreCaseWithoutEmpty(memcacheClient, getKeyId(key), value);    		
    	}
    }
	
	// defaultTm

	public int getDefaultTm() {
		return defaultTm;
	}

	public void setDefaultTm(int defaultTm) {
		this.defaultTm = defaultTm;
	}

	public MemcachedClient getMemcacheClient() {
		return memcacheClient;
	}

	public void setMemcacheClient(MemcachedClient memcacheClient) {
		this.memcacheClient = memcacheClient;
	}

}
