package org.bidtime.memcache;

/**
 * 不做登录验证枚举
 * 
 * @author jss
 * 
 */
public abstract class AbstractMemcacheKeyManage extends MemcacheManage {

	protected String userKeyFlag;

	public String getUserKeyFlag() {
		return userKeyFlag;
	}

	public void setUserKeyFlag(String userKeyFlag) {
		this.userKeyFlag = userKeyFlag;
	}

	public abstract String getKeyId(String key);

	public abstract String getKeyId(String key, String ext);

	@Override
	public void set(String key, int seconds, Object value) {
		super.set(getKeyId(key), seconds, value);
	}

	public void set(String key, String ext, int seconds, Object value) {
		super.set(getKeyId(key, ext), seconds, value);
	}

	public void set(String key, String ext, Object value) {
		super.set(getKeyId(key, ext), defaultTm, value);
	}

	@Override
	public void replace(String key, int seconds, Object value) {
		super.replace(getKeyId(key), seconds, value);
	}

	public void replace(String key, String ext, int seconds, Object value) {
		super.replace(getKeyId(key, ext), seconds, value);
	}

	public void replace(String key, String ext, Object value) {
		super.replace(getKeyId(key + ext), defaultTm, value);
	}

	@Override
	public Object get(String key) {
		return super.get(getKeyId(key));
	}

	@Override
	public Object get(String key, boolean delete) {
		return super.get(getKeyId(key), delete);
	}

	@Override
	public Object get(String key, String ext) {
		return super.get(getKeyId(key, ext));
	}

	public Object get(String key, String ext, boolean delete) {
		return super.get(getKeyId(key, ext), delete);
	}

	@Override
	public void delete(String key) {
		super.delete(getKeyId(key));
	}

	@Override
	public void delete(String key, String ext) {
		super.delete(getKeyId(key, ext));
	}

}
