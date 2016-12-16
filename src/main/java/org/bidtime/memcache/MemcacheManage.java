package org.bidtime.memcache;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.spy.memcached.MemcachedClient;

/**
 * 
 * jss
 */
public class MemcacheManage {

	private static final Logger logger = LoggerFactory.getLogger(MemcacheManage.class);

	protected MemcachedClient memcacheClient;

	protected int defaultTm = 2 * 60 * 60;// second;

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

	public void set(String id, Object value) {
		this.set(id, defaultTm, value);
	}

	public void set(Long id, Object value) {
		this.set(String.valueOf(id), value);
	}
	
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

	public void replace(String key, Object value) {
		this.set(key, defaultTm, value);
	}

	public void replace(String key, String ext, int seconds, Object value) {
		replace(key + ext, seconds, value);
	}
	
	public void replace(String key, String ext, Object value) {
		replace(key + ext, value);
	}
	
	// get

	public Object get(String key, boolean delete) {
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
					sb.append(this.defaultTm);
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
				delete(key);
			}
		}
		return value;
	}

	public Object get(String key) {
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
					sb.append(this.defaultTm);
					sb.append("s).");
					logger.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			logger.error("get:" + key, e);
		}
		return value;
	}
	
	public Object get(String key, String ext) {
		return get(key + ext);
	}
	
	public Object get(String key, String ext, boolean delete) {
		return get(key + ext, delete);
	}

	public Object get(Long id) {
		return this.get(String.valueOf(id));
	}
	
	// delete
	
	public void delete(String key) {
		try {
			memcacheClient.delete(key);
			if (logger.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder();
				try {
					sb.append("get ");
					sb.append(key);
					sb.append(" tm(");
					sb.append(this.defaultTm);
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
	
	public void delete(String key, String ext) {
		delete(key + ext);
	}
	
	public void dec(String id) {
		inc(id, -1);
	}

	public void delete(Long id) {
		this.delete(String.valueOf(id));
	}

	// inc
	
	public void inc(String id) {
		inc(id, 1);
	}

	public void inc(String id, int var) {
		Object value = get(id);
		if (value == null) {
			set(id, var);
		} else {
			if (value instanceof Integer) {
				set(id, (Integer) value + var);
			} else if (value instanceof Long) {
				set(id, (Long) value + var);
			} else if (value instanceof Short) {
				set(id, (Short) value + var);
			} else if (value instanceof Double) {
				set(id, (Double) value + var);
			} else if (value instanceof Float) {
				set(id, (Float) value + var);
			} else {
				int n = Integer.parseInt(String.valueOf(value));
				set(id, n + var);
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

	// equals
	
	public String getValue(String key) {
		Object obj = get(key);
		if (obj != null) {
			return String.valueOf(obj);
		} else {
			return null;
		}
	}

	public boolean equals(String key, Integer value) {
		Integer oldVal = getInteger(key);
		if (oldVal != null) {
			if (value != null) {
				return oldVal.equals(value);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean equals(String key, int value) {
		Integer oldVal = getInteger(key);
		if (oldVal != null) {
			return oldVal.equals(value);
		} else {
			return false;
		}
	}

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

	public boolean equals(String key, String value) {
		String oldVal = getValue(key);
		if (oldVal != null) {
			if (StringUtils.isNotEmpty(oldVal)) {
				return StringUtils.equals(oldVal, value);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean equalsIgnoreCase(String key, String value) {
		String oldVal = getValue(key);
		if (oldVal != null) {
			if (StringUtils.isNotEmpty(oldVal)) {
				return StringUtils.equalsIgnoreCase(oldVal, value);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean equalsWithEmpty(String key, String value) {
		return StringUtils.equals(getValue(key), value);
	}

	public boolean equalsIgnoreCaseWithEmpty(String key, String value) {
		return StringUtils.equalsIgnoreCase(getValue(key), value);
	}

}
