package org.bidtime.memcache;

import net.spy.memcached.MemcachedClient;

/**
 * 
 * jss
 */
public class CacheManage {
	
	private MemcachedClient memcacheClient;
	
	protected int defaultTm = 2 * 60 * 60;		// default: 2h = 7200s;
	
	public CacheManage() {
		
	}

	public String getKeyId(String key, String ext) {
		return key + ext;
	}
	
	// set
	
	public void set(String key, int seconds, Object value) {
		MemcacheUtils.set(memcacheClient, key, seconds, value);
	}

	public void set(String key, Object value) {
		this.set(key, defaultTm, value);
	}

	public void set(String key, String ext, int seconds, Object value) {
		this.set(getKeyId(key, ext), seconds, value);
	}

	public void set(String key, String ext, Object value) {
		this.set(key, ext, defaultTm, value);
	}
	
	// replace

	public void replace(String key, int seconds, Object value) {
		MemcacheUtils.replace(memcacheClient, key, seconds, value);
	}

	public void replace(String key, Object value) {
		this.replace(key, defaultTm, value);
	}
	
	public void replace(String key, String ext, int seconds, Object value) {
		this.replace(getKeyId(key, ext), seconds, value);
	}
	
	public void replace(String key, String ext, Object value) {
		this.replace(key, ext, defaultTm, value);
	}
	
	// get

	public Object get(String key, boolean delete) {
		return MemcacheUtils.get(memcacheClient, key, delete);
	}

	public Object get(String key) {
		return this.get(key, false);
	}
	
	//      get ext

	public Object get(String key, String ext, boolean delete) {
		return this.get(getKeyId(key, ext), delete);
	}

	public Object get(String key, String ext) {
		return this.get(key, ext, false);
	}
	
	//     get string
	
	public String getString(String key) {
		return getString(key, false);
	}
	
	public String getString(String key, boolean delete) {
		Object obj = get(key, delete);
		if (obj != null) {
			return String.valueOf(obj);
		} else {
			return null;
		}
	}
	
	// delete
	
	public void delete(String key) {
		MemcacheUtils.delete(memcacheClient, key);
	}
	
	public void delete(String key, String ext) {
		delete(getKeyId(key, ext));
	}
	
	// equals
	
    public boolean equals(String key, String value) {
		return MemcacheUtils.eq(getString(key), value);
    }
    
    public boolean notEquals(String key, String value) {
		return !MemcacheUtils.eq(getString(key), value);
    }
    
    public boolean equals(String key, String newValue, boolean allowEmpty) {
    	if (allowEmpty) {
    		return equals(key, newValue);
    	} else {
    		return MemcacheUtils.equalsWithoutEmpty(getString(key), newValue);
    	}
    }
    
    public boolean notEquals(String key, String newValue, boolean allowEmpty) {
    	if (allowEmpty) {
    		return notEquals(key, newValue);
    	} else {
    		return MemcacheUtils.notEquals(getString(key), newValue);
    	}
    }
    
    public boolean equalsIgnoreCase(String key, String value) {
    	return MemcacheUtils.eqIgnoreCase(getString(key), value);
    }
    
    public boolean equalsIgnoreCase(String key, String newValue, boolean allowEmpty) {
    	if (allowEmpty) {
    		return equalsIgnoreCase(key, newValue);
    	} else {
    		return MemcacheUtils.equalsIgnoreCaseWithoutEmpty(getString(key), newValue);
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
