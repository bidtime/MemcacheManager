package org.bidtime.memcache;

/**
 * 
 * jss
 */
public class MemcacheManage extends CacheManageExt {

	// set
	
	public void set(String key, String ext, Object value) {
		super.set(key + ext, value);
	}
	
	public void set(String key, String ext, int seconds, Object value) {
		super.set(key + ext, seconds, value);
	}

	//     set object
	
	public void set(Object id, Object value) {
		super.set(String.valueOf(id), value);
	}
	
	public void set(Object id, int seconds, Object value) {
		super.set(String.valueOf(id), seconds, value);
	}
	
	public void set(Object key, String ext, int seconds, Object value) {
		super.set(String.valueOf(key) + ext, seconds, value);
	}
	
	// replace
	
	public void replace(String key, String ext, Object value) {
		super.replace(key + ext, value);
	}

	public void replace(String key, String ext, int seconds, Object value) {
		super.replace(key + ext, seconds, value);
	}

	//     replace object
	
	public void replace(Object id, Object value) {
		super.replace(String.valueOf(id), value);
	}
	
	public void replace(Object id, int seconds, Object value) {
		super.replace(String.valueOf(id), seconds, value);
	}
	
	public void replace(Object key, String ext, int seconds, Object value) {
		super.replace(String.valueOf(key) + ext, seconds, value);
	}
	
	// get
	
	public Object get(String key, String ext) {
		return super.get(key + ext);
	}
		
	public Object get(String key, String ext, boolean delete) {
		return super.get(key + ext, delete);
	}
	
	//     get object
	public Object get(Object key) {
		return super.get(String.valueOf(key));
	}

	public Object get(Object key, String ext) {
		return super.get(String.valueOf(key) + ext);
	}
	
	public Object get(Object key, String ext, boolean delete) {
		return super.get(String.valueOf(key) + ext, delete);
	}
	
	// delete
	
	public void delete(String key, String ext) {
		delete(key + ext);
	}

	//     delete object
	
	public void delete(Object key) {
		this.delete(String.valueOf(key));
	}
	
	public void delete(Object key, String ext) {
		this.delete(String.valueOf(key) + ext);
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
		this.inc(String.valueOf(key) + ext, var);
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

}
