package org.bidtime.memcache;

/**
 * 不做登录验证枚举
 * 
 * @author jss
 * 
 */
public abstract class AbstractMemcacheKeyManage extends CacheManageExt {
	
	public AbstractMemcacheKeyManage(String userFlag) {
		this.userFlag = userFlag;
	}

	protected String userFlag;

	public abstract String getKeyId(String key);

	public abstract String getKeyId(String key, String ext);

	public String getKeyId(Object key) {
		return this.getKeyId(String.valueOf(key));
	}
	
	public String getKeyId(Object key, String ext) {
		return this.getKeyId(String.valueOf(key), ext);
	}

	// set
	
	@Override
	public void set(String key, Object value) {
		super.set(getKeyId(key), value);
	}
	
	@Override
	public void set(String key, int seconds, Object value) {
		super.set(getKeyId(key), seconds, value);
	}

	public void set(String key, String ext, Object value) {
		super.set(getKeyId(key, ext), value);
	}

	public void set(String key, String ext, int seconds, Object value) {
		super.set(getKeyId(key, ext), seconds, value);
	}
	
	//     set object
	
	public void set(Object key, Object value) {
		super.set(getKeyId(key), value);
	}
	
	public void set(Object key, int seconds, Object value) {
		super.set(getKeyId(key), seconds, value);
	}

	public void set(Object key, String ext, Object value) {
		super.set(getKeyId(key, ext), value);
	}
	
	public void set(Object key, String ext, int seconds, Object value) {
		super.set(getKeyId(key, ext), seconds, value);
	}
	
	// replace

	@Override
	public void replace(String key, Object value) {
		super.replace(getKeyId(key), value);
	}

	@Override
	public void replace(String key, int seconds, Object value) {
		super.replace(getKeyId(key), seconds, value);
	}

	public void replace(String key, String ext, Object value) {
		super.replace(getKeyId(key + ext), value);
	}

	public void replace(String key, String ext, int seconds, Object value) {
		super.replace(getKeyId(key, ext), seconds, value);
	}
	
	//     replace object

	public void replace(Object key, Object value) {
		super.replace(getKeyId(key), value);
	}
	
	public void replace(Object key, int seconds, Object value) {
		super.replace(getKeyId(key), seconds, value);
	}

	public void replace(Object key, String ext, Object value) {
		super.replace(getKeyId(key + ext), value);
	}

	public void replace(Object key, String ext, int seconds, Object value) {
		super.replace(getKeyId(key, ext), seconds, value);
	}

	// get
	
	@Override
	public Object get(String key) {
		return super.get(getKeyId(key));
	}

	@Override
	public Object get(String key, boolean delete) {
		return super.get(getKeyId(key), delete);
	}

	public Object get(String key, String ext) {
		return super.get(getKeyId(key, ext));
	}

	public Object get(String key, String ext, boolean delete) {
		return super.get(getKeyId(key, ext), delete);
	}
	
	//     get object

	public Object get(Object key) {
		return super.get(getKeyId(key));
	}

	public Object get(Object key, boolean delete) {
		return super.get(getKeyId(key), delete);
	}

	public Object get(Object key, String ext) {
		return super.get(getKeyId(key, ext));
	}

	public Object get(Object key, String ext, boolean delete) {
		return super.get(getKeyId(key, ext), delete);
	}


	// delete
	
	@Override
	public void delete(String key) {
		super.delete(getKeyId(key));
	}

	public void delete(String key, String ext) {
		super.delete(getKeyId(key, ext));
	}
	
	//     delete object
	public void delete(Object key) {
		super.delete(getKeyId(key));
	}

	public void delete(Object key, String ext) {
		super.delete(getKeyId(key, ext));
	}
	
	// equals

	@Override
	public boolean equals(String key, String newValue, boolean allowEmpty) {
		return equals(getKeyId(key), newValue, allowEmpty);
	}
	
	@Override
	public boolean equalsIgnoreCase(String key, String newValue, boolean allowEmpty) {
		return equals(getKeyId(key), newValue, allowEmpty);
	}

}
