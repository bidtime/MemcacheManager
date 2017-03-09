package org.bidtime.memcache;

/**
 * 
 * jss
 */
public class CacheManageExt extends CacheManage {

	protected int defaultTm = 2 * 60 * 60;		// default: 2h = 7200s;
	
	public String getKeyId(String key) {
		return key;
	}

	public String getKeyId(String key, String ext) {
		return key + ext;
	}

	// set
	
	@Override
	public void set(String key, int seconds, Object value) {
		super.set(getKeyId(key), seconds, value);
	}

	public void set(String key, String ext, int seconds, Object value) {
		super.set(getKeyId(key, ext), seconds, value);
	}

	public void set(String key, Object value) {
		this.set(key, defaultTm, value);
	}
	
	// replace
	
//	public void replace(String key, String ext, int seconds, Object value) {
//		this.replace(getKeyId(key, ext), seconds, value);
//	}

	@Override
	public void replace(String key, int seconds, Object value) {
		super.replace(getKeyId(key), seconds, value);
	}

	public void replace(String key, Object value) {
		this.replace(key, defaultTm, value);
	}
	
	// get
	
	@Override
	public Object get(String key, int seconds, boolean delete) {
		return super.get(getKeyId(key), seconds, delete);
	}
	
	@Override
	public Object get(String key, int seconds) {
		return super.get(getKeyId(key), seconds);
	}

	public Object get(String key, boolean delete) {
		return this.get(key, this.defaultTm, delete);
	}

	public Object get(String key) {
		return this.get(key, this.defaultTm);
	}
	
	// get string
	
	@Override
	public String getString(String key, int seconds) {
		return super.getString(getKeyId(key), seconds);
	}
	
	public String getString(String key) {
		return this.getString(key, this.defaultTm);
	}
	
	// delete
	
	@Override
	public void delete(String key, int seconds) {
		super.delete(getKeyId(key), seconds);
	}
	
	public void delete(String key) {
		this.delete(key, this.defaultTm);
	}
	
	// equals
	
    public boolean equals(String key, String value, boolean allowEmpty) {
    	if (allowEmpty) {
    		return equals(key, value, this.defaultTm);
    	} else {
    		return equalsWithoutEmpty(key, value, this.defaultTm);    		
    	}
    }
    
    public boolean equalsIgnoreCase(String key, String value, boolean allowEmpty) {
    	if (allowEmpty) {
    		return equalsIgnoreCase(key, value, this.defaultTm);
    	} else {
    		return equalsIgnoreCaseWithoutEmpty(key, value, this.defaultTm);    		
    	}
    }
	
	// defaultTm

	public int getDefaultTm() {
		return defaultTm;
	}

	public void setDefaultTm(int defaultTm) {
		this.defaultTm = defaultTm;
	}

}
