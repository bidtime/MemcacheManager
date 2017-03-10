package org.bidtime.memcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.spy.memcached.MemcachedClient;

/**
 * 
 * jss
 */
public class CacheBase {

	private static final Logger logger = LoggerFactory.getLogger(CacheBase.class);

	private MemcachedClient memcacheClient;

	// set
		
	public void set(String key, int seconds, Object value) {
		try {
			memcacheClient.set(key, seconds, value);
			if (logger.isDebugEnabled()) {
				StringBuffer sb = new StringBuffer();
				try {
					sb.append("set ");
					sb.append(key);
					sb.append(":");
					sb.append(value);
					sb.append(" tm(");
					sb.append(seconds);
					sb.append("s).");
					logger.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			logger.error("set:" + key, e);
		}
	}

//	public void set(String id, Object value) {
//		this.set(id, defaultTm, value);
//	}
	
	// replace
	
	public void replace(String key, int seconds, Object value) {
		try {
			memcacheClient.replace(key, seconds, value);
			if (logger.isDebugEnabled()) {
				StringBuffer sb = new StringBuffer();
				try {
					sb.append("replace ");
					sb.append(key);
					sb.append(":");
					sb.append(value);
					sb.append(" tm(");
					sb.append(seconds);
					sb.append("s).");
					logger.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			logger.error("replace:" + key, e);
		}
	}

//	public void replace(String key, Object value) {
//		this.replace(key, defaultTm, value);
//	}
	
	// get

	protected Object get(String key, int seconds, boolean delete) {
		Object value = null;
		try {
			value = memcacheClient.get(key);
			if (logger.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder();
				try {
					sb.append("get ");
					sb.append(key);
					sb.append(":");
					sb.append(value);
					sb.append(" tm(");
					sb.append(seconds);
					sb.append("s).");
					logger.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			logger.error("get:" + key, e);
		} finally {
			if (delete) {
				delete(key, seconds);
			}
		}
		return value;
	}

//	protected Object get(String key, int seconds) {
//		return this.get(key, seconds, false);
//	}
	
	//     get string
	
	protected String getString(String key, int seconds) {
		Object obj = get(key, seconds, false);
		if (obj != null) {
			return String.valueOf(obj);
		} else {
			return null;
		}
	}
	
	// delete
	
	protected void delete(String key, int seconds) {
		try {
			memcacheClient.delete(key);
			if (logger.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder();
				try {
					sb.append("get ");
					sb.append(key);
					sb.append(" tm(");
					sb.append(seconds);
					sb.append("s).");
					logger.debug(sb.toString());
					logger.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			logger.error("delete:" + key, e);
		}
	}
	
	// equals
	
    protected static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
	
    protected static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
	
    protected boolean equalsWithoutEmpty(String key, String newValue, int seconds) {
		String oldVal = getString(key, seconds);
		if ( isNotEmpty(oldVal) ) {
			return oldVal.equals(newValue);
		} else {
			return false;
		}
	}

    protected boolean equalsIgnoreCaseWithoutEmpty(String key, String newValue, int seconds) {
		String oldVal = getString(key, seconds);
		if ( isNotEmpty(oldVal) ) {
			return oldVal.equalsIgnoreCase(newValue);
		} else {
			return false;
		}
	}
	
    protected static boolean eq(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
    
    protected static boolean eqIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    protected boolean equals(String key, String value, int seconds) {
		return eq(getString(key, seconds), value);
	}

    protected boolean equalsIgnoreCase(String key, String value, int seconds) {
		return eqIgnoreCase(getString(key, seconds), value);
	}

    public boolean equals(String key, String value, boolean allowEmpty, int seconds) {
    	if (allowEmpty) {
    		return equals(key, value, seconds);
    	} else {
    		return equalsWithoutEmpty(key, value, seconds);    		
    	}
    }
    
    public boolean equalsIgnoreCase(String key, String value, boolean allowEmpty, int seconds) {
    	if (allowEmpty) {
    		return equalsIgnoreCase(key, value, seconds);
    	} else {
    		return equalsIgnoreCaseWithoutEmpty(key, value, seconds);    		
    	}
    }
	
	//
//
//	public int getDefaultTm() {
//		return defaultTm;
//	}
//
//	public void setDefaultTm(int defaultTm) {
//		this.defaultTm = defaultTm;
//	}

	public MemcachedClient getMemcacheClient() {
		return memcacheClient;
	}

	public void setMemcacheClient(MemcachedClient memcacheClient) {
		this.memcacheClient = memcacheClient;
	}

}
