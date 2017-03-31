package org.bidtime.memcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.spy.memcached.MemcachedClient;

/**
 * 
 * jss
 */
public class MemcacheUtils {

	private static final Logger log = LoggerFactory.getLogger(MemcacheUtils.class);

	// set
	
	public static void set(MemcachedClient cacheClient, String key, int seconds, Object value) {
		try {
			cacheClient.set(key, seconds, value);
			if (log.isDebugEnabled()) {
				StringBuffer sb = new StringBuffer();
				try {
					sb.append("set ");
					sb.append(key);
					sb.append(":");
					sb.append(value);
					sb.append(" tm(");
					sb.append(seconds);
					sb.append("s).");
					log.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			log.error("set: key{}, {}", key, e);
		}
	}

//	public void set(String id, Object value) {
//		this.set(id, defaultTm, value);
//	}
	
	// replace
	
	public static void replace(MemcachedClient cacheClient, String key, int seconds, Object value) {
		try {
			cacheClient.replace(key, seconds, value);
			if (log.isDebugEnabled()) {
				StringBuffer sb = new StringBuffer();
				try {
					sb.append("replace ");
					sb.append(key);
					sb.append(":");
					sb.append(value);
					sb.append(" tm(");
					sb.append(seconds);
					sb.append("s).");
					log.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			log.error("replace: key{}, {}", key, e);
		}
	}

//	public void replace(String key, Object value) {
//		this.replace(key, defaultTm, value);
//	}
	
	// get

//	protected static Object get(MemcachedClient cacheClient, String key) {
//		return get(cacheClient, key, false);
//	}
	
	protected static Object get(MemcachedClient cacheClient, String key, boolean delete) {
		Object value = null;
		try {
			value = cacheClient.get(key);
			if (log.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder();
				try {
					sb.append("get ");
					sb.append(key);
					sb.append(":");
					sb.append(value);
					log.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			log.error("get: key{}, {}", key, e);
		} finally {
			if (delete) {
				delete(cacheClient, key);
			}
		}
		return value;
	}
	
	//     get string
	
//	protected static String getString(MemcachedClient cacheClient, String key) {
//		return getString(cacheClient, key, false);
//	}
//	
//	protected static String getString(MemcachedClient cacheClient, String key, boolean delete) {
//		Object obj = get(cacheClient, key, delete);
//		if (obj != null) {
//			return String.valueOf(obj);
//		} else {
//			return null;
//		}
//	}
	
	// delete
	
	protected static void delete(MemcachedClient cacheClient, String key) {
		try {
			cacheClient.delete(key);
			if (log.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder();
				try {
					sb.append("delete ");
					sb.append(key);
					log.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			log.error("delete: key{}, {}", key, e);
		}
	}
	
	// equals
	
    protected static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
	
    protected static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
	
    protected static boolean equalsWithoutEmpty(String oldValue, String newValue) {
		if ( isNotEmpty(oldValue) ) {
			return oldValue.equals(newValue);
		} else {
			return false;
		}
	}
    
	public static boolean notEquals(String oldValue, String newValue) {
		if ( isNotEmpty(oldValue) ) {
			return !oldValue.equals(newValue);
		} else {
			return false;
		}
	}

    protected static boolean equalsIgnoreCaseWithoutEmpty(String oldValue, String newValue) {
		if ( isNotEmpty(oldValue) ) {
			return oldValue.equalsIgnoreCase(newValue);
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
	
//	// dec
//	
//	public void dec(MemcachedClient cacheClient, String key, int seconds) {
//		inc(cacheClient, key, seconds, -1);
//	}
//	
//	public void dec(MemcachedClient cacheClient, String key, String ext, int seconds) {
//		inc(cacheClient, key, ext, seconds, -1);
//	}
//	
//	public void dec(MemcachedClient cacheClient, String key, String ext, int seconds, int var) {
//		inc(cacheClient, key, ext, seconds, -var);
//	}
//
//	// inc
//	
//	public void inc(MemcachedClient cacheClient, String key, int seconds) {
//		inc(cacheClient, key, seconds, 1);
//	}
//	
//	public void inc(MemcachedClient cacheClient, String key, String ext, int seconds) {
//		inc(cacheClient, key, ext, seconds, 1);
//	}
//
//	public void inc(MemcachedClient cacheClient, String key, String ext, int seconds, int var) {
//		this.inc(cacheClient, key + ext, seconds, var);
//	}
//	
//	//     inc raw
//	
//	public void inc(MemcachedClient cacheClient, String key, int seconds, int var) {
//		Object value = get(cacheClient, key);
//		if (value == null) {
//			set(cacheClient, key, seconds, var);
//		} else {
//			if (value instanceof Integer) {
//				set(cacheClient, key, seconds, (Integer) value + var);
//			} else if (value instanceof Long) {
//				set(cacheClient, key, seconds, (Long) value + var);
//			} else if (value instanceof Short) {
//				set(cacheClient, key, seconds, (Short) value + var);
//			} else if (value instanceof Double) {
//				set(cacheClient, key, seconds, (Double) value + var);
//			} else if (value instanceof Float) {
//				set(cacheClient, key, seconds, (Float) value + var);
//			} else {
//				int n = Integer.parseInt(String.valueOf(value));
//				set(cacheClient, key, seconds, n + var);
//			}
//		}
//	}
//
//	public Integer getInteger(MemcachedClient cacheClient, String key) {
//		Object value = get(cacheClient, key);
//		if (value != null) {
//			if (value instanceof Number) {
//				return ((Number) value).intValue();
//			} else {
//				return Integer.parseInt(String.valueOf(value));
//			}
//		} else {
//			return null;
//		}
//	}
//
//	// compareTo
//
//	public int compareTo(MemcachedClient cacheClient, String key, Integer value) {
//		Integer oldVal = getInteger(cacheClient, key);
//		if (oldVal != null) {
//			return oldVal.compareTo(value);
//		} else {
//			return -1;
//		}
//	}
//
//	public int compareTo(MemcachedClient cacheClient, String key, int value) {
//		Integer oldVal = getInteger(cacheClient, key);
//		if (oldVal != null) {
//			return oldVal.compareTo(value);
//		} else {
//			return -1;
//		}
//	}

}
