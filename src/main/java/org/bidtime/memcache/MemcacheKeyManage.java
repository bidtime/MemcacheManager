package org.bidtime.memcache;

import org.apache.commons.lang.StringUtils;

/**
 * 不做登录验证枚举
 * 
 * @author jss
 * 
 */
public class MemcacheKeyManage extends AbstractMemcacheKeyManage {
	
	public MemcacheKeyManage(String userFlag) {
		super(userFlag);
	}

	@Override
	public String getKeyId(String key) {
		if (StringUtils.isNotEmpty(userFlag)) {
			StringBuilder sb = new StringBuilder();
			try {
				sb.append(key);
				sb.append(userFlag);
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
		if (StringUtils.isNotEmpty(userFlag)) {
			StringBuilder sb = new StringBuilder();
			try {
				sb.append(key);
				sb.append(userFlag);
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
