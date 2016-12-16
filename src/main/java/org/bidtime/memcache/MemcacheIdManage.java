package org.bidtime.memcache;

import org.apache.commons.lang.StringUtils;

/**
 * 不做登录验证枚举
 * 
 * @author jss
 * 
 */
public class MemcacheIdManage extends AbstractMemcacheKeyManage {

	@Override
	public String getKeyId(String key) {
		if (StringUtils.isNotEmpty(userKeyFlag)) {
			StringBuilder sb = new StringBuilder();
			try {
				sb.append(key);
				sb.append(userKeyFlag);
				return sb.toString();
			} finally {
				sb.setLength(0);
				sb = null;
			}
		} else {
			return key;
		}
	}

	@Override
	public String getKeyId(String key, String ext) {
		if (StringUtils.isNotEmpty(userKeyFlag)) {
			StringBuilder sb = new StringBuilder();
			try {
				sb.append(key);
				sb.append(userKeyFlag);
				sb.append(ext);
				return sb.toString();
			} finally {
				sb.setLength(0);
				sb = null;
			}
		} else {
			return key;
		}
	}
	
}
