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
	
//	public String getKeyId(Object key) {
//		return String.valueOf(key);
//	}
//
//	public String getKeyId(Object key, String ext) {
//		return String.valueOf(key) + ext;
//	}

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
	
	// dec
	
	public void dec(String key) {
		inc(key, -1);
	}
	
	public void dec(String key, String ext) {
		inc(key, ext, -1);
	}
	
	public void dec(String key, String ext, int var) {
		inc(key, ext, -var);
	}

	// inc
	
	public void inc(String key) {
		inc(key, 1);
	}
	
	public void inc(String key, String ext) {
		inc(key, ext, 1);
	}

	public void inc(String key, String ext, int var) {
		this.inc(key + ext, var);
	}
	
	//     inc raw
	
	public void inc(String key, int var) {
		Object value = get(key);
		if (value == null) {
			set(key, var);
		} else {
			if (value instanceof Integer) {
				set(key, (Integer) value + var);
			} else if (value instanceof Long) {
				set(key, (Long) value + var);
			} else if (value instanceof Short) {
				set(key, (Short) value + var);
			} else if (value instanceof Double) {
				set(key, (Double) value + var);
			} else if (value instanceof Float) {
				set(key, (Float) value + var);
			} else {
				int n = Integer.parseInt(String.valueOf(value));
				set(key, n + var);
			}
		}
	}

	public Integer getInteger(String key) {
		Object value = get(key);
		if (value != null) {
			if (value instanceof Number) {
				return ((Number) value).intValue();
			} else {
				return Integer.parseInt(String.valueOf(value));
			}
		} else {
			return null;
		}
	}

	// compareTo

	public int compareTo(String key, Integer value) {
		Integer oldVal = getInteger(key);
		if (oldVal != null) {
			return oldVal.compareTo(value);
		} else {
			return -1;
		}
	}

	public int compareTo(String key, int value) {
		Integer oldVal = getInteger(key);
		if (oldVal != null) {
			return oldVal.compareTo(value);
		} else {
			return -1;
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
