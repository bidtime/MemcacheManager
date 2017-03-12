package org.bidtime.memcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.spy.memcached.MemcachedClient;

/**
 * 
 * jss
 */
public class MemcacheUtils {

	private static final Logger logger = LoggerFactory.getLogger(MemcacheUtils.class);

	// set
	
	public static void set(MemcachedClient cacheClient, String key, int seconds, Object value) {
		try {
			cacheClient.set(key, seconds, value);
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
			logger.error("set: key{}, {}", key, e);
		}
	}

//	public void set(String id, Object value) {
//		this.set(id, defaultTm, value);
//	}
	
	// replace
	
	public static void replace(MemcachedClient cacheClient, String key, int seconds, Object value) {
		try {
			cacheClient.replace(key, seconds, value);
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
			logger.error("replace: key{}, {}", key, e);
		}
	}

//	public void replace(String key, Object value) {
//		this.replace(key, defaultTm, value);
//	}
	
	// get

	protected static Object get(MemcachedClient cacheClient, String key) {
		return get(cacheClient, key);
	}
	
	protected static Object get(MemcachedClient cacheClient, String key, boolean delete) {
		Object value = null;
		try {
			value = cacheClient.get(key);
			if (logger.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder();
				try {
					sb.append("get ");
					sb.append(key);
					sb.append(":");
					sb.append(value);
					logger.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			logger.error("get: key{}, {}", key, e);
		} finally {
			if (delete) {
				delete(cacheClient, key);
			}
		}
		return value;
	}
	
	//     get string
	
	protected static String getString(MemcachedClient cacheClient, String key) {
		return getString(cacheClient, key, false);
	}
	
	protected static String getString(MemcachedClient cacheClient, String key, boolean delete) {
		Object obj = get(cacheClient, key, delete);
		if (obj != null) {
			return String.valueOf(obj);
		} else {
			return null;
		}
	}
	
	// delete
	
	protected static void delete(MemcachedClient cacheClient, String key) {
		try {
			cacheClient.delete(key);
			if (logger.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder();
				try {
					sb.append("delete ");
					sb.append(key);
					logger.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			logger.error("delete: key{}, {}", key, e);
		}
	}
	
	// equals
	
    protected static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
	
    protected static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
	
    protected static boolean equalsWithoutEmpty(MemcachedClient cacheClient, String key, String newValue) {
		String oldVal = getString(cacheClient, key);
		if ( isNotEmpty(oldVal) ) {
			return oldVal.equals(newValue);
		} else {
			return false;
		}
	}

    protected static boolean equalsIgnoreCaseWithoutEmpty(MemcachedClient cacheClient, String key, String newValue) {
		String oldVal = getString(cacheClient, key);
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

    protected static boolean equals(MemcachedClient cacheClient, String key, String value) {
		return eq(getString(cacheClient, key), value);
	}

    protected static boolean equalsIgnoreCase(MemcachedClient cacheClient, String key, String value) {
		return eqIgnoreCase(getString(cacheClient, key), value);
	}

    public static boolean equals(MemcachedClient cacheClient, String key, String value, boolean allowEmpty) {
    	if (allowEmpty) {
    		return equals(cacheClient, key, value);
    	} else {
    		return equalsWithoutEmpty(cacheClient, key, value);    		
    	}
    }
    
    public static boolean equalsIgnoreCase(MemcachedClient cacheClient, String key, String value, boolean allowEmpty) {
    	if (allowEmpty) {
    		return equalsIgnoreCase(cacheClient, key, value);
    	} else {
    		return equalsIgnoreCaseWithoutEmpty(cacheClient, key, value);    		
    	}
    }

}
