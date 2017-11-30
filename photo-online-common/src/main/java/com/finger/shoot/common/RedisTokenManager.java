package com.finger.shoot.common;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisTokenManager extends TokenManager{
	
	/**
	 * 是否需要扩展token过期时间
	 */
	private Set<String> tokenSet = new CopyOnWriteArraySet<String>();
	
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public void verifyExpired() {
		tokenSet.clear();
	}

	@Override
	public void addToken(String token, LoginUser loginUser) {
		redisUtils.setEx(token, loginUser, tokenTimeout * 1000L);
		
	}

	@Override
	public LoginUser validate(String token) {
		LoginUser loginUser = (LoginUser)redisUtils.get(token);
		if (loginUser != null && !tokenSet.contains(token)) {
			tokenSet.add(token);
			addToken(token, loginUser);
		}
		return loginUser;
		
	}

	@Override
	public void remove(String token) {
		redisUtils.del(token);
		
	}

}
